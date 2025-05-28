/*
 * Esercizio:
 * Esporre un api rest in spring boot che dato un codice fiscale restituisca data di nascita ed età. Il tutto 
 * interrogabile da swagger.
 * 
 * come ottengo una data di nascita dal codice fiscale:
 * Per ricavare la data di nascita dal codice fiscale, è necessario esaminare i primi cinque caratteri (dal 7° al 11° 
 * carattere). In particolare:
   Anno di nascita: le ultime due cifre dell'anno di nascita sono indicate dai caratteri 7° e 8°. 
   Mese di nascita: il carattere 9° indica il mese di nascita tramite una lettera, che corrisponde ad una lettera di una 
   tabella. 
   Giorno di nascita: i caratteri 10° e 11° indicano il giorno di nascita. 
   Sesso: in caso di donna, al giorno di nascita va sommato il numero 40. 

   Tabella di corrispondenza tra lettera e mese di nascita:
   A = Gennaio, B = Febbraio, C = Marzo, D = Aprile, E = Maggio, H = Giugno, L = Luglio, M = Agosto, P = Settembre,
   R = Ottobre, S = Novembre, T = Dicembre.

   Esempio:
   Se il codice fiscale ha i caratteri 7° e 8° pari a 75, il 9° carattere è D e il 10° e 11° sono 01, la data di nascita è
   01/04/1975. 
 */


package com.medialogic.codfiscaleapi.service;

import com.medialogic.codfiscaleapi.eccezioni.CodiceFiscaleNonValido;
import com.medialogic.codfiscaleapi.model.DatiAnagrafici;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@Service
public class CodiceFiscaleService {

	private static final Map<Character, Month> MESE_MAP = new HashMap<>();

	static {
		MESE_MAP.put('A', Month.JANUARY);
		MESE_MAP.put('B', Month.FEBRUARY);
		MESE_MAP.put('C', Month.MARCH);
		MESE_MAP.put('D', Month.APRIL);
		MESE_MAP.put('E', Month.MAY);
		MESE_MAP.put('H', Month.JUNE);
		MESE_MAP.put('L', Month.JULY);
		MESE_MAP.put('M', Month.AUGUST);
		MESE_MAP.put('P', Month.SEPTEMBER);
		MESE_MAP.put('R', Month.OCTOBER);
		MESE_MAP.put('S', Month.NOVEMBER);
		MESE_MAP.put('T', Month.DECEMBER);
	}

    public DatiAnagrafici estraiDatiAnagrafici(String cf) {
    	if (cf == null || cf.length() != 16) {
            throw new CodiceFiscaleNonValido("Il codice fiscale deve essere lungo 16 caratteri");
        }
    	LocalDate data = estraiDataDiNascita(cf.toUpperCase());
        int eta = Period.between(data, LocalDate.now()).getYears();
        return new DatiAnagrafici(data, eta);
    }

    public LocalDate estraiDataDiNascita(String cf) {
        char meseDiNascita = cf.charAt(8);
        Month mese = MESE_MAP.get(meseDiNascita);
        if (mese == null) {
            throw new IllegalArgumentException("Carattere mese non valido: " + meseDiNascita);
        }

        int anno = Integer.parseInt(cf.substring(6, 8));
        int annoCompleto = anno >= LocalDate.now().getYear() % 100 ? 1900 + anno : 2000 + anno;
        int giorno = Integer.parseInt(cf.substring(9, 11));
        if (giorno > 40) giorno -= 40;

        return LocalDate.of(annoCompleto, mese, giorno);
    }
}


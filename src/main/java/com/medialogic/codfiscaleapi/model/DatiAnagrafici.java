package com.medialogic.codfiscaleapi.model;

import java.time.LocalDate;

public class DatiAnagrafici {
	 private LocalDate dataNascita;
	    private int eta;

	    public DatiAnagrafici(LocalDate dataNascita, int eta) {
	        this.dataNascita = dataNascita;
	        this.eta = eta;
	    }

	    public LocalDate getDataNascita() {
	        return dataNascita;
	    }

	    public int getEta() {
	        return eta;
	    }
}

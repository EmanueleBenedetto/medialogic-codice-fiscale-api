package com.medialogic.codfiscaleapi.eccezioni;

public class CodiceFiscaleNonValido  extends RuntimeException {
    public CodiceFiscaleNonValido(String message) {
        super(message);
    }
}

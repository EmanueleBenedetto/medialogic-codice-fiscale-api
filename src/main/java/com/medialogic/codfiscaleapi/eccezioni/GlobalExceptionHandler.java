package com.medialogic.codfiscaleapi.eccezioni;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Errore: " + ex.getMessage());
    }
    
    @ExceptionHandler(CodiceFiscaleNonValido.class)
    public ResponseEntity<String> handleInvalidFiscalCodeException(CodiceFiscaleNonValido ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Errore codice fiscale: " + ex.getMessage());
    }

   
}

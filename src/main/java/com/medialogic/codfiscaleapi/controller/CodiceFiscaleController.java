package com.medialogic.codfiscaleapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medialogic.codfiscaleapi.model.DatiAnagrafici;
import com.medialogic.codfiscaleapi.service.CodiceFiscaleService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/codfiscaleapi/codicefiscale")
public class CodiceFiscaleController {
	
	
	 @Autowired
	    private CodiceFiscaleService codiceFiscaleService;

	    @GetMapping("/{cf}")
	    @Operation(summary = "Calcolo la data di nascita ed età partendo da un codice fiscale")
	    public DatiAnagrafici getDati(@PathVariable String cf) {
	        return codiceFiscaleService.estraiDatiAnagrafici(cf);
	    }



}

package com.metallica.referencedata.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReferenceDataController {

	
	
	@RequestMapping(method=RequestMethod.GET, value="/countryparty")
	public String getCountryParties(){
		return "Welcome User";
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/locations")
	public String getLocations(){
		return "Welcome Admin";
	}
	
}

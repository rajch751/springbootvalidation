package com.example.validationdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.validationdemo.serviceerror.CodeTypeDTO;
import com.example.validationdemo.serviceerror.ServiceError;
import com.example.validationdemo.serviceerror.ValidationComponent;

@RestController
public class CodeTypeController {
	
	
	@Value("${test.resources.static}")
	String test;
	
	@Autowired
	ValidationComponent vc;
	
	
	@PostMapping("/validateExample")
	public CodeTypeDTO getCodeTypeValue(@RequestBody CodeTypeDTO codeTypeDTO,Errors e) {
		
		
		ServiceError error=vc.validateCodeType(codeTypeDTO, e, true);
		
		System.out.println(error.getErrors());
		return codeTypeDTO;
	}
	
	@GetMapping("/pcfenv")
	public String getCodeTypeValue2() {
		
		
		
		return test;
	}
	

}

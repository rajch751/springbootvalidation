package com.example.validationdemo.serviceerror;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ValidationComponent {
	
	@Autowired
	GenericValidator validator;
	
	public ServiceError validateCodeType(CodeTypeDTO codetypeDto,Errors errors,boolean save) {
		ServiceError error=new ServiceError();
		
		/*List<Object> t=Arrays.asList("status");
		
		List<String> strings = t.stream()
				   .map(object -> Objects.toString(object, null))
				   .collect(Collectors.toList());*/
		
		
		List<String> ls=new ArrayList<>();
		ls.add("status");
		
		validator.isEmptyField(codetypeDto, "status", error, "error.field.empty", ls);
		
		//List<Object> t1=Arrays.asList("code");
		ls.clear();
		ls.add("code");
		ls.add("15");
		//spring framework inbuilt errors
		
		
		
		validator.isEmptyField(codetypeDto, "code", error, "error.field.empty.test", ls);
		
		if(errors != null) {
			errors.getAllErrors().stream().map(emsg->error.getErrors().add(emsg.getDefaultMessage()));
			
		}
		
		return error;
	}

}

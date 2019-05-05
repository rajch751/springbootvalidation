package com.example.validationdemo.serviceerror;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ServiceError {
	
	private List<String> errors=new ArrayList<>();

	public List<String> getErrors() {
		return errors;
	}

	public void addError(String error) {
		if(!StringUtils.isEmpty(error)) {
			this.errors.add(error);
		}
	}
	

}

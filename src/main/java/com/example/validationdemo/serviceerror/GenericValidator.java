package com.example.validationdemo.serviceerror;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.example.validationdemo.serviceerror.*;

@Component
@PropertySource("classpath:config/validation-message.properties")
public class GenericValidator {

	@Autowired
	private Environment env;

	public void isEmptyField(Object obj, String field, ServiceError se, String errorMesg, List<String> fieldNames) {

		BeanWrapper beanWrapper=new BeanWrapperImpl(obj);
		Object value=beanWrapper.getPropertyValue(field);
		if(value instanceof String && StringUtils.isEmpty(value)) {
			se.addError(getErrorMessage(errorMesg,fieldNames));
		}else if (value==null) {
			se.addError(getErrorMessage(errorMesg,fieldNames));
		}
	}

	private String getErrorMessage(String errorMesg, List<String> fieldNames) {
		// TODO Auto-generated method stub
		
		String property=env.getProperty(errorMesg);
		
		if(property != null && property.indexOf("{")>=0 && !CollectionUtils.isEmpty(fieldNames)) {
			
			Pattern p=Pattern.compile("(\\{[^}]+\\})");
			Matcher m=p.matcher(property);
			int count =0;
			
			StringBuffer sb=new StringBuffer();
			
			while(m.find()) {
				
				if(fieldNames.size()>=count && fieldNames.get(count)!=null) {
					m.appendReplacement(sb, fieldNames.get(count));
				}
				count++;
				
			}
			m.appendTail(sb);
			property=sb.toString();
		}
		return property;
	}



}

package com.sdo.configuration.others;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;

public class MessageConverter {

	
	@Bean(name="messageConverter")
	public StringHttpMessageConverter messageConverter(){
		StringHttpMessageConverter converter=new StringHttpMessageConverter(StandardCharsets.UTF_8);
		return converter;
	}
}

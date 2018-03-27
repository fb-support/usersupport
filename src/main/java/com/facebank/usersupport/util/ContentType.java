package com.facebank.usersupport.util;

/**
 * 内容类型
 * @author fulin
 *
 */

public enum ContentType {

	DEFAULT("application/json; charset=UTF-8"),
	JSON("application/json"),
	PLAINTEXT("text/plain"),
    XMLAPPLICATION("application/xml");
	
	private String value;
	
	ContentType(String value){
		this.value = value;
	}
	
	
	public String getValue(){
		return value;
	}
}

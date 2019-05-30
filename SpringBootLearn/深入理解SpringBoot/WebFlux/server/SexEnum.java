package com.springboot.webflux.enumeration;

public class SexEnum{
	MALE(1, "ÄÐ"),
	FEMALE(2, "Å®");
	
	private int code;
	private String name;
	
	private SexEnum(int code, String name){
		this.code = code;
		this.name = name;
	}
	
	public static SexEnum getSexEnum(int code){
		SexEnmu[] enmus = SexEnum.values();
		
		for(SexEnum enum: enums){
			if(enum.getCode() == code){
				return enum;
			}
		}
		return null;
	}
	
	/***setter and getter***/
}
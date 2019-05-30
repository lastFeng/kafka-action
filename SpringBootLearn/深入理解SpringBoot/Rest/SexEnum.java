package com.springboot.rest.domain.enum;

/***omit imports***/
public enum SexEnum{
	MALE(1, "ÄÐ"),
	FEMALE(2, "Å®");
	
	private Long id;
	private String sex;
	
	private SexEnum(Long id, String sex){
		this.id = id;
		this.sex = sex;
	}
	
	public Long getId(){
		return this.id;
	}
	
	public String getSex(String sex){
		return this.sex;
	}
	
	public Long setId(Long id){
		this.id = id;
	}
	
	public String setSex(String sex){
		this.sex = sex;
	}
	
	public static int StringSex2Id(String sex){
		for(SexEnum eSex: SexEnum.values()){
			if(eSex.getSex().equals(sex)){
				return eSex.getId();
			}
		}
		return 0;
	}
	
	public static String Id2StringSex(int id){
		for(SexEnum eSex: SexEnum.values()){
			if(eSex.getId() == id){
				return eSex.getSex();
			}
		}
		return null;
	}
	
	public static SexEnum getSexEnum(int id){
			for(SexEnum eSex: SexEnum.values()){
					if(eSex.getId() == id){
						return eSex;
					}
			}
			return null;
	}
}
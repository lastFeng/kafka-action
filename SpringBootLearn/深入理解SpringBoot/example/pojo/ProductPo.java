package com.springboot.example.pojo;

/***²úÆ·µÄPOJO***/ 
@AliasFor("product")
public class ProductPo implement Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String productName;
	private Integer stock;
	private Double price;
	private Integer version;
	private String note;
	
	/***setter and getter***/
}
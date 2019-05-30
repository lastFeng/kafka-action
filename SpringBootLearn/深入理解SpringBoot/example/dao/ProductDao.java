package com.springboot.example.dao;

/***MapperÓ³Éä½Ó¿Ú***/
@Mapper
public interface ProductDao{
	public ProductPo getProduct(Long id);
	public int decreaseProduct(Long id, Integer quantity);
}
package com.springboot.o2o.dao;

import com.springboot.o2o.domain.ProductImg;

import java.util.List;

public interface ProductImgDao {

	List<ProductImg> queryProductImgList(long productId);

	int batchInsertProductImg(List<ProductImg> productImgList);

	int deleteProductImgByProductId(long productId);
}

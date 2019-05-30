package com.springboot.example.service.impl;

/***购买业务实现***/
@Service
public class PurchaseServiceImpl implements PurchaseService{
	@Autowired
	private PurchaseRecodeDao purchaseDao;
	@Autowired
	private ProductDao productDao;
	
	@Override
	@Transactional
	public boolean purchase(Long userId, Long productId, Integer quantity){
		// 获取产品
		ProductPo product = productDao.getProduct(productId);
		// 查看库存是否足够
		if(product.getStock() < quantity){
			return false;
		}
		
		// 扣减库存
		productDao.decreaseProduct(productId, quantity);
		
		// 插入购买记录
		PurchaseRecodePo prp = this.initPurchaseRecodePo(userId, product, quantity);
		purchaseDao.insertPurchaseRecode(prp);
		
		// 返回
		return true;
	}
	
	private PurchaseRecodePo initPurchaseRecodePo(Long userId, ProductPo product, Integer quantity){
		PurchaseRecodePo result = new PurchaseRecodePo();
		result.setUserId(userId);
		result.setProductId(product.getId());
		result.setQuantity(quantity);
		result.setPrice(product.getPrice());
		result.setSum(product.getPrice() * quantity);
		result.setNote("购买日志，时间：" + System.currentTimeMillis());
		return result;
	}
}
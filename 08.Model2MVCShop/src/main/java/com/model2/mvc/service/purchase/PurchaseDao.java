package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {
	
	public Purchase getPurchase(int tranNo);
	
	public Purchase getPurchaseByProdNo(int prodNo);
	
	public int addPurchase(Purchase purchase);
	
	public int updatePurchase(Purchase purchase);
	
	public List getPurchaseList(Map map);
	
	public int updateTranCode(Purchase purchase);
	
	
	public int getTotalCount(Map map);
}

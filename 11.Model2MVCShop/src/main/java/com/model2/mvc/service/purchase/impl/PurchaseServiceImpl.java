package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	@Qualifier("purchaseDaoImpl")
	PurchaseDao purchaseDao;
	
	
	@Override
	public Purchase getPurchase(int tranNo) {
		
		Purchase purchase=purchaseDao.getPurchase(tranNo);
		purchase.setPaymentOption(purchase.getPaymentOption().trim());
		return purchase;
	}
	
	@Override
	public Purchase getPurchaseByProdNo(int prodNo) {
		
		Purchase purchase=purchaseDao.getPurchaseByProdNo(prodNo);
		purchase.setPaymentOption(purchase.getPaymentOption().trim());
		return purchase;
	}
	
	@Override
	public int addPurchase(Purchase purchase) {
		
		return purchaseDao.addPurchase(purchase);
	}
	
	@Override
	public int updatePurchase(Purchase purchase) {
		
		return purchaseDao.updatePurchase(purchase);
	}
	
	@Override
	public int updateTranCode(Purchase purchase) {
		return purchaseDao.updateTranCode(purchase);
	}
	
	
	@Override
	public Map getPurchaseList(Search search,String userId) {
		Map paramMap=new HashMap();
		paramMap.put("search", search);
		paramMap.put("userId", userId);
		
		List <Purchase>list=purchaseDao.getPurchaseList(paramMap);
		
		for(Purchase p : list) {
			p.setTranCode(p.getTranCode().trim());	
		}
		
		
		Map<String,Object> map= new HashMap<String,Object>();
		
		int totalCount = purchaseDao.getTotalCount(paramMap);
		
		map.put("list", list);
		map.put("totalCount", totalCount);
		return map;
	}

	

}

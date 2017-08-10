package com.model2.mvc.web.purchase;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productService;

	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;
	
	@Value("#{commonProperties['pageUnit'] ?: 5}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize'] ?: 3}")
	int pageSize;
	
	@RequestMapping(value="json/getPurchase/{tranNo}" , method=RequestMethod.GET)
	public Purchase getPurchase(@PathVariable int tranNo) throws Exception{
		
		System.out.println(tranNo);
		Purchase purchase=purchaseService.getPurchase(tranNo);
		return purchase;
	}
	
	@RequestMapping(value="json/listPurchase/{userId}" , method=RequestMethod.POST)
	public Map getPurchaseList(@RequestBody Search search, 
								@PathVariable String userId) throws Exception{
		//System.out.println(prodNo);
		//Product returnProduct=productService.getProduct(prodNo);
		
		Map map=purchaseService.getPurchaseList(search, userId);
		
		Page resultPage=new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		Map returnMap = new HashMap();
		returnMap.put("list",map.get("list"));
		returnMap.put("resultPage", resultPage);
		
		return returnMap;
	}
	
	@RequestMapping(value="json/addPurchase", method=RequestMethod.POST)
	public void addPurchase(@RequestBody Purchase purchase ) throws Exception{
		
		System.out.println(purchase);
		//////////////
		
		
	}
	
	@RequestMapping(value="json/updatePurchase",method=RequestMethod.POST)
	public void updatePurchase(@RequestBody Purchase purchase ) throws Exception{
		
		System.out.println(purchase);
		//
		//purchaseService.updatePurchase(purchase);
	}
	@RequestMapping(value="json/deletePurchase/{tranNo}", method=RequestMethod.GET)
	public void deltePurchase(@PathVariable int tranNo) throws Exception{
		
		
		System.out.println(tranNo);
	}
	
	
}

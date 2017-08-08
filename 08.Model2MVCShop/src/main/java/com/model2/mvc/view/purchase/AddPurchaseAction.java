package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


public class AddPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[AddPurchaseAction.execute() start ....]");
		
		Purchase purchase=new Purchase();
		
		String buyer=request.getParameter("buyerId");
		
		///////////////////////////////////////////////////
		PurchaseService purchaseService=(PurchaseService)ContextManager.getContext().getBean("purchaseServiceImpl");
		UserService userService=(UserService)ContextManager.getContext().getBean("userServiceImpl");
		ProductService productService=(ProductService)ContextManager.getContext().getBean("productServiceImpl");
		/////////////////////////////////////////////////
		
		purchase.setBuyer(userService.getUser(buyer));
		
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		
	
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		purchase.setPurchaseProd(productService.getProduct(prodNo));
		
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		
		purchase.setTranCode("1");
		
		System.out.println(purchase);
		
		purchaseService.addPurchase(purchase);
		
		System.out.println("[AddPurchaseAction.execute() end ....]");
		return "forward:/getPurchase.do?tranNo="+purchase.getTranNo();
	}

}

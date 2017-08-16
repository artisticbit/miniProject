package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;


public class GetPuchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[GetPuchaseAction.execute() start...]");
		
		int tranNo=Integer.parseInt(request.getParameter("tranNo"));
		
		/////////////////////////////////
		PurchaseService purchaseService=(PurchaseService)ContextManager.getContext().getBean("purchaseServiceImpl");
		
		//////////////////////////
		
		Purchase purchase=purchaseService.getPurchase(tranNo);
		
		request.setAttribute("purchase", purchase);
		System.out.println(purchase);
		System.out.println("[GetPuchaseAction.execute() end...]");
		return "forward:/purchase/getPurchase.jsp";
		
	}

}

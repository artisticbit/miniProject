package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;

public class UpdateTranCodeByProdAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[UpdateTranCodeByProdAction.execute() start.......]");
		
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		String tranCode=request.getParameter("tranCode");
		
		//////////////////////////////////////////////
		PurchaseService purchaseService=(PurchaseService)ContextManager.getContext().getBean("purchaseServiceImpl");
		/////////////////////////////////////////////
		
		Purchase purchase=purchaseService.getPurchaseByProdNo(prodNo);
		purchase.setTranCode(tranCode);
		
		System.out.println("updateTranCode start");
		purchaseService.updateTranCode(purchase);
		
		System.out.println("[UpdateTranCodeByProdAction.execute() end.......]");
		return "forward:/listProduct.do?menu=manage";
	}

}

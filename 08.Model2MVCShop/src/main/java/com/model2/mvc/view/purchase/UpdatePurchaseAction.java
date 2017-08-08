package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;

public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[UpdatePurchaseAction.excute() start....]");
		
		Purchase purchase=new Purchase();
		
		purchase.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("divyDate"));
		/////////////////////
		PurchaseService purchaseService=(PurchaseService)ContextManager.getContext().getBean("purchaseServiceImpl");
		/////////////////////////////
		purchaseService.updatePurchase(purchase);
		
		
		System.out.println("[UpdatePurchaseAction.excute() end....]");
		int tranNo=purchase.getTranNo();
		return "forward:/getPurchase.do?tranNo="+tranNo;
	}

}

package com.model2.mvc.view.purchase;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;


public class ListPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[ListPurchaseAction.execute() start....]");
		
		Search search=new Search();
		
		int currentPage=1;
		if(request.getParameter("currentPage") != null)
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		
		search.setPageSize(pageSize);
		
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		if(user==null)
			System.out.println("!!!!!!!!!!!!!!!!!!!user is null!!!!!!!!!!!!!!!");
		
		//////////////////////////////////////
		PurchaseService purchaseService=(PurchaseService)ContextManager.getContext().getBean("purchaseServiceImpl");
		search.setStringParam(user.getUserId());
		///////////////////////////////////

		Map<String,Object> map=purchaseService.getPurchaseList(search,user.getUserId());
	
		
		Page resultPage=new Page(currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
	
		request.setAttribute("list", map.get("list"));
		request.setAttribute("search", search);
		request.setAttribute("resultPage", resultPage);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}

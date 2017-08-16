package com.model2.mvc.view.purchase;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;



public class ListSaleAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Search search=new Search();
		
		int currentPage=1;
		if(request.getParameter("currentPage") != null)
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		System.out.println(search.getSearchCondition()+"::"+search.getSearchKeyword()+"::");
		
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		
		search.setPageSize(pageSize);
		
		/////////////////////
		PurchaseService purchaseService=(PurchaseService)ContextManager.getContext().getBean("purchaseServiceImpl");
		/////////////////////////////
		
		
		Map<String, Object> map=null;
		map=purchaseService.getPurchaseList(search, null);
		
		int totalCount=((Integer)map.get("totalCount")).intValue();
		Page resultPage=new Page(currentPage,totalCount , pageUnit, pageSize);

		
		List list=(List)map.get("list");
		
		
		request.setAttribute("list", list);
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		
		return "forward:/purchase/listSale.jsp";
	}

}

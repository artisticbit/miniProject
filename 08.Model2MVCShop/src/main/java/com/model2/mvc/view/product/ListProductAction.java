package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;




public class ListProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListProductAction.execute() start...");
		
		Search search=new Search();
		
		String menu=request.getParameter("menu");
		System.out.println("Menu : "+menu);

		int currentPage=1;
		if(request.getParameter("currentPage") != null) {
			System.out.println("currentPage :"+ request.getParameter("currentPage"));
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		search.setSearchKeyword2(request.getParameter("searchKeyword2"));
		
		
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		////////////////////////
		ProductService productService=(ProductService)ContextManager.getContext().getBean("productServiceImpl");
		
		////////////////////////
		Map<String,Object> map=productService.getProductList(search);
		
		Page resultPage=new Page(currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("search", search);
		request.setAttribute("resultPage", resultPage);
		
		System.out.println("ListProductAction.execute() end...");
	
		return "forward:/product/listProduct.jsp";
		
		
	
	}

}

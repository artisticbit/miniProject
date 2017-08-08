package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[GetProductAction.execute() start...]");
	
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("prodNO : "+prodNo);
		
		String menu=request.getParameter("menu");
		
		/////////////////////////////////////////////////////
		ProductService productService=(ProductService)ContextManager.getContext().getBean("productServiceImpl");
		
		
		////////////////////////////////////////////////////////
		Product product = productService.getProduct(prodNo);
		request.setAttribute("product", product);
			
		
		System.out.println("Menu : " +menu);
	
		if(menu!=null){
			if(menu.equals("search")){
				System.out.println("cookie search start...");
				Cookie[] cookies = request.getCookies();
				Cookie historyCookie=null;
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("history")){
						historyCookie=cookie;
					}
				}
				
				if(historyCookie==null){
					historyCookie = new Cookie("history", prodNo+"");
					System.out.println("!!Create Cookie!!");
				}
				else{
					historyCookie.setValue(historyCookie.getValue()+","+prodNo);
					System.out.println("!!Append Cookie!!");
				}
					historyCookie.setMaxAge(60*60);
					response.addCookie(historyCookie);
				
				return "forward:/product/getProduct.jsp";
			}
			
			
			else if(menu.equals("manage")){
				return "forward:/updateProductView.do?prodNo="+prodNo+"&menu="+menu;
			}
		}
		return "forward:/product/getProduct.jsp";
		
	}

}

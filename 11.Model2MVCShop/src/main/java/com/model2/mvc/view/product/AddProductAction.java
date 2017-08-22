package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("[addProductAction start...]");
		
		Product product=new Product();	
		
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		
		String manuDate=request.getParameter("manuDate");
		manuDate=manuDate.replace("-","");
		product.setManuDate(manuDate);
		
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		
		System.out.println(product);
		
		//////////////////////////////////////////////
		ProductService productService=(ProductService)ContextManager.getContext().getBean("productServiceImpl");
		productService.addProduct(product);
		///////////////////////////////////////////
	
		
		request.setAttribute("product", product);
		System.out.println("[addProductAction end...]");
		
		return "forward:/product/addProduct.jsp";
	}
 
}

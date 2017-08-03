package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


@Controller
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	
	@Value("#{commonProperties['pageUnit'] ?: 5}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize'] ?: 3}")
	int pageSize;
	
	public ProductController() {
		System.out.println(getClass());
	}
	
	@RequestMapping("/addProduct.do")
	public ModelAndView addProduct(@ModelAttribute("product")Product product) throws Exception{
		
		product.setManuDate(product.getManuDate().replace("-", ""));
		productService.addProduct(product);
		
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/product/addProduct.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/getProduct.do")
	public ModelAndView getProduct(@RequestParam("prodNo")int prodNo,
									@RequestParam(value="menu",defaultValue="search")String menu,
									HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Product product=productService.getProduct(prodNo);
		
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.addObject("product", product);
		
		
		
		if(menu.equals("search")) {
			modelAndView.setViewName("forward:/product/getProduct.jsp");			
		}
		else if(menu.equals("manage")){
			modelAndView.setViewName("/updateProductView.do");
		}
		
		//////ÄíÅ°Ãß°¡////
		if(menu.equals("search")) {
		
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
			
		}
		
		
		return modelAndView;
	}
	
	@RequestMapping("/updateProductView.do")
	public ModelAndView updateProductView() {
		
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("/product/updateProductView.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/updateProduct.do")
	public ModelAndView updateProduct(@ModelAttribute("product")Product product) throws Exception{
		
		product.setManuDate(product.getManuDate().replace("-", ""));
		productService.updateProduct(product);
		
		ModelAndView modelAndView =new ModelAndView();

		modelAndView.setViewName("forward:/getProduct.do?+prodNo="+product.getProdNo()+"&menu=search");
		return modelAndView;
	}
	
	@RequestMapping("/listProduct.do")
	public ModelAndView listProduct(@ModelAttribute("search")Search search) throws Exception{
		
		if(search.getCurrentPage()==0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String,Object> map=productService.getProductList(search);
		
		Page resultPage=new Page(search.getCurrentPage(),((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize); 
		
	
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.addObject("list",map.get("list"));
		modelAndView.addObject("search",search);
		modelAndView.addObject("resultPage",resultPage);
		
		modelAndView.setViewName("forward:/product/listProduct.jsp");
		return modelAndView;
	}
	
}

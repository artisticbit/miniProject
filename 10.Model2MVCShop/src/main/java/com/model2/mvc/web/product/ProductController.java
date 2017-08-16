package com.model2.mvc.web.product;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


@Controller
@RequestMapping("/product/*")
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
/*	
	@RequestMapping(value="/addProduct", method=RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute("product")Product product) throws Exception{
		
		product.setManuDate(product.getManuDate().replace("-", ""));
		productService.addProduct(product);
		
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/product/addProduct.jsp");
		
		return modelAndView;
	}
	*/
/////////////////////////////////////////////////////////////////////////////////////////////	
	/*
	@RequestMapping(value="/addProduct", method=RequestMethod.POST)
	public ModelAndView addProduct(HttpServletRequest request) throws Exception{
		
		Enumeration ee=request.getParameterNames();
		while(ee.hasMoreElements()) {
			System.out.print(ee.nextElement()+"::");
		}
		
		
		Product product=new Product();
		
		if(FileUpload.isMultipartContent(request)){
			String temDir="D:\\Projects\\Git\\bitcamp\\miniProject\\07.Model2MVCShop\\WebContent\\images\\uploadFiles";
			
			DiskFileUpload fileUpload=new DiskFileUpload();
			fileUpload.setRepositoryPath(temDir);
			fileUpload.setSizeMax(1024*1024*10);
			fileUpload.setSizeThreshold(1024*100);
			
			
			if(request.getContentLength()<fileUpload.getSizeMax()) {
			
				
				StringTokenizer token=null;
				List fileItemList=fileUpload.parseRequest(request);
				int size=fileItemList.size();
				
				for(int i=0; i<size;i++) {
					FileItem fileItem=(FileItem)fileItemList.get(i);
					if(fileItem.isFormField()) {
						
						System.out.println(fileItem.getFieldName()+"::"+fileItem.getString("euc_kr"));
						
						if(fileItem.getFieldName().equals("prodName")) {
							product.setProdName(fileItem.getString("euc_kr"));
						}
						else if(fileItem.getFieldName().equals("prodDetail")) {
							product.setProdDetail(fileItem.getString("euc_kr"));
						}
						else if(fileItem.getFieldName().equals("manuDate")) {
							String manuDate=fileItem.getString("euc_kr");
							manuDate=manuDate.replace("-", "");
							product.setManuDate(manuDate);
						}
						else if(fileItem.getFieldName().equals("price")) {
							product.setPrice(Integer.parseInt((fileItem.getString("euc_kr"))));
						}
						
					}
					else {
						if(fileItem.getSize()>0) {
							System.out.println(fileItem.getName());
						
							int idx=fileItem.getName().lastIndexOf("\\");
							
							if(idx==-1) {
								idx=fileItem.getName().lastIndexOf("/");
							}
							String fileName=fileItem.getName().substring(idx+1);
							product.setFileName(fileName);
							
							try {
								File uploadFile=new File(temDir,fileName);
								fileItem.write(uploadFile);
								System.out.println(uploadFile.getPath());
							}catch(IOException e) {
								e.printStackTrace();
							}
							
						}
						else {
							product.setFileName("../../images/empty.GIF");
						}		
					}
				}
			}
			else {
				int overSize = request.getContentLength()/1000000;
				System.out.println("<script>alert(파일의 크기는 1MB까지 입니다. 올리신 파일 용량은 "+
									overSize+"MB 입니다);+"
											+ "history.back();<script/>");
			}
		}
		else {
			System.out.println("인코딩 타입이 multipart/form-data가 아닙니다..");
		}
		
		
		productService.addProduct(product);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("product",product);
		modelAndView.setViewName("/product/addProduct.jsp");
		
		return modelAndView;
	}
	*/
	@RequestMapping(value="/addProduct", method=RequestMethod.POST)
	public ModelAndView addProduct(@RequestParam("file")MultipartFile file,@ModelAttribute("product")Product product
									,HttpSession session) throws Exception{
		if(!file.isEmpty()) {
			String str=session.getServletContext().getRealPath("/images/uploadFiles");
			String temDir="D:\\Projects\\Git\\bitcamp\\miniProject\\07.Model2MVCShop\\WebContent\\images\\uploadFiles";	
			
			String url=str+"\\"+file.getOriginalFilename();
			File toFile=new File(url);
			file.transferTo(toFile);
		//	System.out.println(url);
			
			product.setFileName(file.getOriginalFilename());
		}
		productService.addProduct(product);
		
		
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("product",product);
		modelAndView.setViewName("/product/addProduct.jsp");
		
		return modelAndView;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////	
	@RequestMapping(value="/getProduct", method=RequestMethod.GET)
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
			modelAndView.setViewName("/product/updateProduct");
		}
		
		//////쿠키추가////
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
		//		historyCookie.setPath("/");
				System.out.println("!!Create Cookie!!");
			}
			else{
				historyCookie.setValue(historyCookie.getValue()+","+prodNo);
				
			//	historyCookie.setPath("/");
				System.out.println("!!Append Cookie!!\n");
	
			}
				
				historyCookie.setMaxAge(60*60);
				response.addCookie(historyCookie);
			
		}
		
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/updateProduct", method=RequestMethod.GET)
	public ModelAndView updateProductView() {
		
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("/product/updateProductView.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/updateProduct", method=RequestMethod.POST)
	public ModelAndView updateProduct(@RequestParam("file")MultipartFile file,@ModelAttribute("product")Product product
									,HttpSession session) throws Exception{
		
		if(!file.isEmpty()) {
			String str=session.getServletContext().getRealPath("/images/uploadFiles");
			String temDir="D:\\Projects\\Git\\bitcamp\\miniProject\\07.Model2MVCShop\\WebContent\\images\\uploadFiles";	
			
			String url=str+"\\"+file.getOriginalFilename();
			File toFile=new File(url);
			file.transferTo(toFile);
		//	System.out.println(url);
			
			product.setFileName(file.getOriginalFilename());
		}
		productService.addProduct(product);
		
		
		ModelAndView modelAndView=new ModelAndView();
		//modelAndView.addObject("product",product);
		modelAndView.setViewName("redirect:/product/getProduct?prodNo="+product.getProdNo()+"&menu=search");
		
		return modelAndView;
	}
	
/*	
 * @RequestMapping(value="/updateProduct", method=RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("product")Product product) throws Exception{
		
		product.setManuDate(product.getManuDate().replace("-", ""));
		productService.updateProduct(product);
		
		ModelAndView modelAndView =new ModelAndView();

		modelAndView.setViewName("redirect:/product/getProduct?prodNo="+product.getProdNo()+"&menu=search");
		System.out.println("updatea Complete!!!!!!!!!!!\n"+modelAndView.getViewName());
		return modelAndView;
	}
	*/
	
	@RequestMapping(value="/listProduct")
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

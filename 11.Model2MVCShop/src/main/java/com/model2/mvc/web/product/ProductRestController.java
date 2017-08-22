package com.model2.mvc.web.product;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/product/*")
public class ProductRestController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Value("#{commonProperties['pageUnit'] ?: 5}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize'] ?: 3}")
	int pageSize;
	
	
	@RequestMapping(value="json/addProduct", method=RequestMethod.POST)
	public int addProduct(@RequestBody Product product) {
	
		
		//데이터베이스 추가
		System.out.println(product);
		return 0;
		
	}
////////////////////////////	
	@RequestMapping(value="json/addProduct2", method=RequestMethod.POST)
	//public int addProduct2(@RequestParam("file")MultipartFile file) {
	public int addProduct2(@RequestParam("json")String json,MultipartHttpServletRequest requset
							,@RequestParam("file2")MultipartFile file) throws Exception{
		//System.out.println(file.getOriginalFilename());
	
		//MultipartFile file2=requset.getFile("file2");
		//System.out.println(file2.getName()+"::"+file2.getOriginalFilename());
	//	Map map=requset.getFileMap();
	//	System.out.println(map.size());
		File uploadFile=new File("D:\\test\\upload\\"+file.getOriginalFilename());
		file.transferTo(uploadFile);
		
		ObjectMapper objectMapper=new ObjectMapper();
		Product product=objectMapper.readValue(json, Product.class);
		

		System.out.println(product);
		//데이터베이스 추가
		//System.out.println(product);
		return 0;
		
	}
////////////////////////////////	
	@RequestMapping(value="json/getProduct/{prodNo}" , method=RequestMethod.GET)
	public Product getProduct(@PathVariable int prodNo) throws Exception{
		System.out.println(prodNo);
		
		Product returnProduct=productService.getProduct(prodNo);
		return returnProduct;
	}
	
	@RequestMapping(value="json/listProduct" , method=RequestMethod.POST)
	public Map getProductList(@RequestBody Search search ) throws Exception{
		//System.out.println(prodNo);
		//Product returnProduct=productService.getProduct(prodNo);
		System.out.println(search);
		
		Map map=productService.getProductList(search);
		
		Page resultPage=new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		Map returnMap = new HashMap();
		returnMap.put("list",map.get("list"));
		returnMap.put("resultPage", resultPage);
		
		return returnMap;
	}
	
	
	@RequestMapping(value="json/updateProduct", method=RequestMethod.POST)
	public int updateProduct(@RequestBody Product product) throws Exception{
		System.out.println(product);
		//productService.updateProduct(product);
		return 0;
	}
	
	@RequestMapping(value="json/deleteProduct/{prodNo}" , method=RequestMethod.GET)
	public int deleteProduct(@PathVariable int prodNo) throws Exception{
		System.out.println(prodNo);
		
		
		return 0;
	}
	
}

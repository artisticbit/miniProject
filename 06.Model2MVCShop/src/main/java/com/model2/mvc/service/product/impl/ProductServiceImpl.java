package com.model2.mvc.service.product.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	
	public ProductServiceImpl() {
		System.out.println(getClass());
	}
	
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}


	@Override
	public int addProduct(Product product) throws Exception {
			//int test=productDao.addProduct(product);
			//throw new SQLException();
		return productDao.addProduct(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		
		return productDao.getProduct(prodNo);
	}

	@Override
	public int updateProduct(Product product) throws Exception {
		
		return productDao.updateProduct(product);
	}

	@Override
	public int removeProduct(int prodNo) throws Exception {
		
		return productDao.removeProduct(prodNo);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		
		Map<String,Object> map=new HashMap<String, Object>();
		int totalCount= productDao.getProductTotalCount(search);	
		List<Product> list =productDao.getProductList(search);
		///////tranCode trim///////
		for(Product p : list) {
			if(p.getProTranCode()!=null)
			p.setProTranCode(p.getProTranCode().trim());
		}
		///////////////////////
		
		map.put("list", list);
		map.put("totalCount", totalCount);
		return map;
	}


	@Override
	public int getTotalCount(Search search) throws Exception {
		
		return productDao.getProductTotalCount(search);
	}
	
	

}

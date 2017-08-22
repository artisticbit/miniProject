package product;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

public class test{

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		ProductService productService=(ProductService)ContextManager.getContext().getBean("productServiceImpl");
		
		Search search=new Search();
		search.setPageSize(5);
		search.setCurrentPage(1);
		
		Map<String,Object> map=productService.getProductList(search);
		List<Product> list=(List<Product>)map.get("list");
		for(Product p : list) {
			System.out.println(p);
		}
	}

}

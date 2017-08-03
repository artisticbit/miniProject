package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@Controller
public class PurchaseController {

		@Autowired
		@Qualifier("purchaseServiceImpl")
		PurchaseService purchaseService;
		
		@Autowired
		@Qualifier("productServiceImpl")
		ProductService productService;
	
		@Autowired
		@Qualifier("userServiceImpl")
		UserService userService;
		
		@Value("#{commonProperties['pageUnit'] ?: 5}")
		int pageUnit;
		
		@Value("#{commonProperties['pageSize'] ?: 3}")
		int pageSize;
		
		public PurchaseController() {
			System.out.println(getClass());
		}
		
		@RequestMapping("/addPurchase.do")
		public String addPurchase(@ModelAttribute("purchase")Purchase purchase
//									,@RequestParam("prodNo")int prodNo,
//									@RequestParam("buyerId")String buyerId
									) throws Exception{
			//test
			//purchase.setPurchaseProd(productService.getProduct(prodNo));
			//purchase.setBuyer(userService.getUser(buyerId));
			purchase.setTranCode("1");
			
			purchaseService.addPurchase(purchase);
			
			
			return "/getPurchase.do?tranNo="+purchase.getTranNo();
		}
		
		@RequestMapping("/addPurchaseView.do")
		public String addPurchaseView(@RequestParam("prod_no")int prodNo,
										Model model) throws Exception{
			
			Product product=productService.getProduct(prodNo);
			
			model.addAttribute("product",product);
			
			return "forward:/purchase/addPurchaseView.jsp";
		}
		
		@RequestMapping("/getPurchase.do")
		public String getPurchase(@RequestParam(value="tranNo")int tranNo,Model model) throws Exception{
			
			Purchase purchase=purchaseService.getPurchase(tranNo);
			
			model.addAttribute("purchase",purchase);
			
			return "forward:/purchase/getPurchase.jsp";
		}
		
		@RequestMapping("/updatePurchaseView.do")
		public String updatePurchaseView(@RequestParam(value="tranNo")int tranNo,
											Model model) throws Exception{
			
			Purchase purchase=purchaseService.getPurchase(tranNo);
			//쿼리에서 수정
			purchase.setDivyDate(purchase.getDivyDate().split(" ")[0]);
			//
			model.addAttribute("purchase",purchase);
			
			return "forward:/purchase/updatePurchaseView.jsp";
		}
		
		@RequestMapping("/updatePurchase.do")
		public String updatePurchase(@ModelAttribute("purchase")Purchase purchase) throws Exception{
			
			purchaseService.updatePurchase(purchase);
			
			
			//return "forward:/getPurchase.do?tranNo="+purchase.getTranNo();
			return "forward:/getPurchase.do";
		}
		
		@RequestMapping("/updateTranCode.do")
		public String updateTranCode(@RequestParam(value="tranNo")int tranNo,
									@RequestParam(value="tranCode")String tranCode) throws Exception{
			
			Purchase purchase=new Purchase();
			purchase.setTranNo(tranNo);
			purchase.setTranCode(tranCode);
			
			purchaseService.updateTranCode(purchase);
			
			return "forward:/listPurchase.do";
		}
		
		@RequestMapping("/updateTranCodeByProd.do")
		public String updateTranCodeByProd(@RequestParam(value="prodNo")int prodNo,
									 @RequestParam(value="tranCode")String tranCode) throws Exception{
			
			Purchase purchase=purchaseService.getPurchaseByProdNo(prodNo);
			
			purchase.setTranCode(tranCode);
			
			purchaseService.updateTranCode(purchase);
			
			return "forward:/listSale.do";
		}
		
		@RequestMapping("/listPurchase.do")
		public String listPurchase(@ModelAttribute("search")Search search,
								HttpSession session,Model model) throws Exception{
			String userId=((User)session.getAttribute("user")).getUserId();
		
			if(search.getCurrentPage()==0) {
				search.setCurrentPage(1);
			}
			search.setPageSize(pageSize);
			
			Map<String,Object> map=purchaseService.getPurchaseList(search,userId);
			
			Page resultPage=new Page(search.getCurrentPage(),((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize); 
			
		
			ModelAndView modelAndView =new ModelAndView();
			model.addAttribute("list",map.get("list"));
			model.addAttribute("search",search);
			model.addAttribute("resultPage",resultPage);
			
			
			return "forward:/purchase/listPurchase.jsp";
		}
		
		@RequestMapping("/listSale.do")
		public String listSale(@ModelAttribute("search")Search search,
								Model model) throws Exception{
			
			if(search.getCurrentPage()==0) {
				search.setCurrentPage(1);
			}
			search.setPageSize(pageSize);
			
			Map<String,Object> map=purchaseService.getPurchaseList(search,null);
			
			Page resultPage=new Page(search.getCurrentPage(),((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize); 
			
		
			ModelAndView modelAndView =new ModelAndView();
			model.addAttribute("list",map.get("list"));
			model.addAttribute("search",search);
			model.addAttribute("resultPage",resultPage);
			
			
			return "forward:/purchase/listSale.jsp";
		}
		
}

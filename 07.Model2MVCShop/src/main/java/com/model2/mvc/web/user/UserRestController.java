package com.model2.mvc.web.user;

import org.osgi.framework.SynchronousBundleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.user.UserService;

@RestController
@RequestMapping("/user/*")
public class UserRestController {

	@Autowired
	@Qualifier("userServiceImpl")
	UserService UserService;
	
	public UserRestController() {
		System.out.println(getClass());
	}
	
	@RequestMapping(value="json/checkDuplication/{value}") 
	public boolean checkDuplication(@PathVariable String value) throws Exception {
	 // public boolean checkDuplication() throws Exception {
		System.out.println("jsonTest!!!!!!!!!!!!!!!!!!!");
		System.out.println(value);
		boolean isDup;
		isDup=UserService.checkDuplication(value);
		
		return isDup;
	}
	
	
}

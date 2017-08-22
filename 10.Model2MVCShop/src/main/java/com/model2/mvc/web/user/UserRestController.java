package com.model2.mvc.web.user;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.framework.SynchronousBundleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

@RestController
@RequestMapping("/user/*")
public class UserRestController {

	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;
	
	public UserRestController() {
		System.out.println(getClass());
	}
	
	@RequestMapping(value="json/checkDuplication/{value}") 
	public boolean checkDuplication(@PathVariable String value) throws Exception {
	 // public boolean checkDuplication() throws Exception {
		System.out.println("jsonTest!!!!!!!!!!!!!!!!!!!");
		System.out.println("pathvariable : "+value);
		String test=new String(value.getBytes("8859_1"),"EUC-KR");
		String test2=URLDecoder.decode((URLDecoder.decode(value, "8859_1")), "EUC-KR");
		String test3=new String(value.getBytes("8859_1"), "UTF-8");
		String test4=new String(value.getBytes("8859_1"));
		System.out.println("double decode : "+test2);
		System.out.println("double decode 2 : "+test3);
		System.out.println("test  :" +test);
		System.out.println("test 4 :" +test4);
		System.out.println("8859_1 :"+test);
		System.out.println("urledcoder : "+URLDecoder.decode(value));
		boolean isDup;
		isDup=userService.checkDuplication(value);
		
		return isDup;
	}
	
	@RequestMapping(value="json/addUser")
	public void addUser(@RequestBody User user) throws Exception{
		System.out.println(user);
		
		userService.addUser(user);
		
		//return 999;
	}
	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		return userService.getUser(userId);
	}
	
	@RequestMapping( value="json/getUserList", method=RequestMethod.POST)
	public void getUser(@RequestBody Search search ) throws Exception{
		System.out.println(search);
		
		Map map=userService.getUserList(search);
		List<User> list=(List<User>)map.get("list");
		for(User u: list) {
			System.out.println(u);
		}
		
		
		//Business Logic
		//return userService.getUser(userId);
	}

	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user,
									HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		if(dbUser!=null) {
			if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
			}
		}
		return dbUser;
	}
	///////유저 아이디 목록 
	@RequestMapping(value="json/getUserIds/{userId}")
	public List getUserIds(@PathVariable String userId) throws Exception {
		
		return	userService.getUserIds(userId);
	}
}

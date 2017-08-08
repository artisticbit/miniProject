package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class LoginAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		
		User user=new User();
		user.setUserId(request.getParameter("userId"));
		user.setPassword(request.getParameter("password"));
		
		
		
		////////////////
		UserService userService=(UserService)ContextManager.getContext().getBean("userServiceImpl");
		User dbVO=userService.getUser(request.getParameter("userId"));
		////////////////////////
		System.out.println("dbVO :"+dbVO);
		
		HttpSession session=request.getSession();
		session.setAttribute("user", dbVO);
		
		return "redirect:/index.jsp";
	}
}
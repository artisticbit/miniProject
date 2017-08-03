package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.ContextManager;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;



public class UpdateUserViewAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String userId=request.getParameter("userId");
		/////////////////////////
		UserService userService=(UserService)ContextManager.getContext().getBean("userServiceImpl");
		///////////////////////
		User userVO=userService.getUser(userId);
		
		request.setAttribute("user", userVO);
		
		return "forward:/user/updateUser.jsp";
	}
}

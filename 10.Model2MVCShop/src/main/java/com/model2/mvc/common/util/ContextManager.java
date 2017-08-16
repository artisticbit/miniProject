package com.model2.mvc.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextManager {

	private static ApplicationContext context;
	
	private ContextManager() {
	}
	
	public static ApplicationContext getContext(){
		if(context==null){
			//context=new ClassPathXmlApplicationContext("/config/commonservice.xml");
			context=new ClassPathXmlApplicationContext(new String[]{"/config/context-common.xml",
																	"/config/context-aspect.xml",
																	"/config/context-mybatis.xml",
																	"/config/context-transaction.xml" });
		}
		return context;
	}
	
}

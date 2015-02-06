package com.codingyun.core.action.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/system/article")
public class SysArticleController {
	
	private static Logger logger = Logger.getLogger(SysArticleController.class);
	
	/**
	 * 系统管理员  管理文章 
	 * @user jack
	 * 2014年8月7日
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getArticleList(HttpServletRequest request) throws Exception{
		logger.info("系统管理员管理文章");
		return "system/blogArticleList";
	}

}

package hu;

import java.io.*;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	//SpringMVC will auto inject a webprocessservice
	@Autowired
	hu.service.WebProcessSerivce webs;
	//Target URL
	//String url="https://www.google.com/search?q=123&ie=utf-8&oe=utf-8&hl=zh-cn";
	String url="https://www.baidu.com/s?wd=1&ie=utf-8";
	                                                   
	@RequestMapping(value="/baidu/{para}")
	public void getURLContent(@PathVariable String para,HttpServletRequest request,HttpServletResponse response) throws  IOException {
		//防止乱码问题
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		
		if(para!=null) {
			url="https://www.baidu.com/s?wd=1&ie=utf-8";
		}
		PrintWriter writer=response.getWriter();
		String html=webs.getResultForBaidu(url);
		writer.write(html);
		writer.close();
	}
	
	/*
	 * Hello World for test
	 */
	@RequestMapping("/hello")
	public String sayHello(HttpServletRequest request) {
		return "helloworld";
	}
}

package hu;

import java.io.*;
import java.net.*;

import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	String url="https://www.google.com/search?q=123&ie=utf-8&oe=utf-8&hl=zh-cn";
	@RequestMapping("baidu1")
	public ModelAndView test(HttpServletRequest request) throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/baidu");
		mav.addObject("html", "html");
		return mav;
	}
	                                                   
	@RequestMapping("/baidu")
	public void getURLContent(HttpServletRequest request,HttpServletResponse response) throws  IOException {
		//防止乱码问题
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter writer=response.getWriter();
		String html=getHtmlByURL(url);
		writer.write(html);
		writer.close();
	}
	@RequestMapping("/hello")
	public String sayHello(HttpServletRequest request) {
		return "helloworld";
	}
	@RequestMapping("/login")
	public String toLogin(HttpServletRequest request){
		return "login";
	}
}

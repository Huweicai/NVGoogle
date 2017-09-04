package hu;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	StringBuilder url=new StringBuilder("https://www.baidu.com/baidu?wd=123&oe=utf-8");
	                                                   
	@RequestMapping(value="/baidu")
	public void getURLContent(HttpServletRequest request,HttpServletResponse response) throws  IOException {
		//put Parameters into URL 
		Map<String, String[]> requestParam=request.getParameterMap();
		if(requestParam.get("wd")!=null) {
			url=new StringBuilder("https://www.baidu.com/baidu?oe=utf-8");
		}
		for(Entry<String, String[]>  entry: requestParam.entrySet()) {
			//though this type is String[],but apearently it is just only a String
			String[] value=entry.getValue();
			url.append(entry.getKey()+"="+value[0]+"&");
		}
		System.out.println("why it doesn't show anything ??");
		//防止乱码问题
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer=response.getWriter();
		String html=webs.getResultForBaidu(request,url.toString());
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

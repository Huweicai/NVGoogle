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
	StringBuilder urlgg=new StringBuilder("https://www.google.com/search?q=123&ie=utf-8&oe=utf-8&lr=lang_zh-CN");//google url
	                                                   
	@RequestMapping(value="/baidu")
	public void getURLContent(HttpServletRequest request,HttpServletResponse response) throws  IOException {
		//put Parameters into URL 
		Map<String, String[]> requestParam=request.getParameterMap();
		if(requestParam.get("wd")!=null) {
			url=new StringBuilder("https://www.baidu.com/baidu?wd="+requestParam.get("wd")[0]+"&oe=utf-8");
			if(requestParam.get("pn")!=null) {
				url.append("&pn="+requestParam.get("pn")[0]);
			}
		}
//		for(Entry<String, String[]>  entry: requestParam.entrySet()) {
//			//though this type is String[],but apearently it is just only a String
//			String[] value=entry.getValue();
//			url.append(entry.getKey()+"="+value[0]+"&");
//		}
		
		//encoding 
		String finalUrl=url.toString();
		finalUrl = new String(finalUrl.getBytes("ISO-8859-1"), "UTF-8");
		//防止乱码问题
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer=response.getWriter();
		String html=webs.getResultForBaidu(finalUrl);
		writer.write(html);
		writer.close();
	}
	@RequestMapping(value="/google")
	public void getURLContentgg(HttpServletRequest request,HttpServletResponse response) throws  IOException {
		//put Parameters into URL wd
		Map<String, String[]> requestParam=request.getParameterMap();
		if(requestParam.get("q")!=null) {
			urlgg=new StringBuilder("https://www.google.com/search?q="+requestParam.get("q")[0]+"&ie=utf-8&oe=utf-8&lr=lang_zh-CN");
			if(requestParam.get("start")!=null) {
				urlgg.append("&start="+requestParam.get("start")[0]);
			}
		}
//		for(Entry<String, String[]>  entry: requestParam.entrySet()) {
//			//though this type is String[],but apearently it is just only a String
//			String[] value=entry.getValue();
//			url.append(entry.getKey()+"="+value[0]+"&");
//		}
		
		//encoding 
		String finalUrl=urlgg.toString();
//		finalUrl = new String(finalUrl.getBytes("ISO-8859-1"), "UTF-8");
		//防止乱码问题
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer=response.getWriter();
		String html=webs.getResultForGoogle(finalUrl);
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

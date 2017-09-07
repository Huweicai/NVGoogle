package hu.service;

import java.io.BufferedReader;
import java.util.regex.*;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Service;

@Service
public class WebProcess implements WebProcessSerivce{

	/*
	 * this function can get html string corresponding to the url
	 */
	@Override
	public String getHtmlByUrl(String url) {
		//log : output the url
		System.out.println("URL : "+url);
		url.replace(' ','+');
		HttpURLConnection con;
		//StringBuiller is more suit for dynamic string
		StringBuilder result=new StringBuilder();
		try {
			//open link
			//the location in 302 is also useless
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:53.0) Gecko/20100101 Firefox/53.0");
			con.setRequestProperty("refer", "https://www.baidu.com/s");
			con.setConnectTimeout(60000);
			con.setReadTimeout(60000);
			//获取数据流
			BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
			String temp=null;
			//保存数据流
			while((temp=in.readLine())!=null) {
				result.append(temp);
			}
			in.close();
			con.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	/*
	 * search in the string "origin" and replace all target with replacemet
	 */
	@Override
	public String replaceString(String origin, String target, String replacement) {
		Pattern pt=Pattern.compile(target);
		Matcher mt=pt.matcher(origin);
		//it needs to get 
		origin=mt.replaceAll(replacement);
		return origin;
	}


	
	@Override
	public String getResultForBaidu(String url) {
		String html=getHtmlByUrl(url);
		//replace URL to my local URL
//		html=replaceString(html, "href=\"\\/s\\?", "href=\"/NVGoogle/baidu?");
		html=replaceString(html, "\"/s\\?", "\"/NVGoogle/baidu?");
		//search box
		html=replaceString(html, "action=\"\\/s", "action=\"/NVGoogle/baidu");
		//logo
		html=replaceString(html, "//www.baidu.com/img/baidu_jgylogo3.gif", "/NVGoogle/Resources/google.png");
		//wd
		html=replaceString(html, "百度", "GOOGLE");
		
		return html;
	}
	@Override
	public String getResultForGoogle(String url) {
		String html=getHtmlByUrl(url);
		//replace URL to my local URL
		//replace link
		html=replaceString(html, "\"/search\\?", "\"/NVGoogle/google?");
		//search box
		html=replaceString(html, "action=\"\\/search", "action=\"/NVGoogle/google");
		//logo
		html=replaceString(html,"/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png","/NVGoogle/Resources/google.png");
		return html;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		String url="https://www.baidu.com/s?wd=1&ie=utf-8";
		WebProcess webs=new WebProcess();
//		String html=webs.getHtmlByUrl(url);
		String html="src=\"/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png\"";
		html=webs.replaceStringTest(html, "/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png", "哈哈哈");
		System.out.println("?");
	}
	
	public String replaceStringTest(String origin, String target, String replacement) {
		Pattern pt=Pattern.compile(target);
		Matcher mt=pt.matcher(origin);
		System.out.println(mt.find()?"Find":"NoFind");
		origin=mt.replaceAll(replacement);
		System.out.println(origin);
		return null;
	}

}

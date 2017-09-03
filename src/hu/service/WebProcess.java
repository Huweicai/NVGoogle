package hu.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		URLConnection con;
		String result="";
		try {
			//打开连接
			con = new URL(url).openConnection();
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:53.0) Gecko/20100101 Firefox/53.0");
			con.setConnectTimeout(60000);
			con.setReadTimeout(60000);
			//获取数据流
			BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
			String temp=null;
			//保存数据流
			while((temp=in.readLine())!=null) {
				result+=temp;
			}
			//转码
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * this function can change 
	 */
	@Override
	public String changeLinkToLocal(String html) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * this function can replace All 
	 */
	@Override
	public String replaceString(String origin, String result) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

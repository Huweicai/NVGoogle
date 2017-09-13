package hu.service;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;


import hu.sql.Logs;

public class WebLog implements WebLogService,Runnable{
	HttpServletRequest request;
	String wd;
	hu.sql.LogsOPface logop=new hu.sql.LogsOP();//operation on database
	
	public WebLog(HttpServletRequest request, String wd) {
		this.request=request;
		this.wd=wd;
	}
	
	@Override
	public String getTime() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date currentTime = new java.util.Date();
		String date1 = formatter.format(currentTime); 
		String date2 = currentTime.toString();
		return date1+date2;
	}

	@Override
	public String getIP() {
		return request.getRemoteAddr();
	}

	@Override
	public String getLocationByIP(String ip) {
		URL url;
		byte[] b=null;
		try {
			url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=&ip="+ip+"&encoding=UTF-8");
			URLConnection con=url.openConnection();
			InputStream in=con.getInputStream();
			b=new byte[100];
			in.read(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result1 = new String(b);
		char[] s=result1.toCharArray();
		StringBuilder result=new StringBuilder("");
		for(int i=7;i<s.length;i++){
			if(s[i]!='\0'&& !Character.isSpace(s[i]))
				result.append(s[i]);
		} 
		return result.toString();
	}

	@Override
	public Logs makeLogs() {
		String time=getTime();
		String ip=getIP();
		String location=getLocationByIP(ip);
		String ua=getUa();
		return new Logs(time,ip,location,wd,ua);
	}

	@Override
	public String getUa() {
		return request.getParameter("User-Agent");
	}

	@Override
	public void run() {
		logop.init();
		logop.addLogs(makeLogs());
		logop.destroy();
	}

}

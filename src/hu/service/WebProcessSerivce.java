package hu.service;

import javax.servlet.http.HttpServletRequest;

public interface WebProcessSerivce {
	public String getHtmlByUrl(String url);
	public String replaceString(String origin,String target,String replacement);
	public String getResultForGoogle(String url);
	public String getResultForBaidu(String url);
}

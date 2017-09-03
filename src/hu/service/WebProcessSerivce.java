package hu.service;

public interface WebProcessSerivce {
	public String getHtmlByUrl(String url);
	public String changeLinkToLocal(String html);
	public String replaceString(String origin,String target,String 8);
}

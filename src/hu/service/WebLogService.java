package hu.service;

import javax.servlet.http.HttpServletRequest;

import hu.sql.Logs;

public interface WebLogService {
	public Logs makeLogs();
	public String getTime();
	public String getIP();
	public String getLocationByIP(String ip);
	public String getUa();
}

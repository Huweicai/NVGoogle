package hu.sql;

public class Logs {
	private int id;
	private String time;
	private String ip;
	private String location;
	private String wd;//key word
	private String ua;//user agent
	public Logs() {
		
	}
	public Logs(String time,String ip,String location,String wd,String ua) {
		this.time=time;
		this.ip=ip;
		this.location=location;
		this.wd=wd;
		this.ua=ua;
	}
	public int getId() {
		return id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWd() {
		return wd;
	}
	public void setWd(String wd) {
		this.wd=wd;
	}
	public String getUa() {
		return ua;
	}
	public void setUa() {
		this.ua=ua;
	}
	@Override
	public String toString() {
		return(id+" "+time+" "+ip+" "+location);
	}
}

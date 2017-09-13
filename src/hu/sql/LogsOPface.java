package hu.sql;

import org.springframework.stereotype.Repository;;


public interface LogsOPface {
	public void init();
	public void addLogs(Logs l);
	public void show();
	public void destroy();
}

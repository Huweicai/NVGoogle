package hu.sql;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.springframework.stereotype.Repository;

import java.io.*;

@Repository
public class LogsOP  implements LogsOPface{
	private SqlSessionFactory fac;
	private SqlSession session;
	public void init(){
		String resource = "mybatis-conf.xml";
		InputStream in; 
		try{
			in=Resources.getResourceAsStream(resource);
			fac =new SqlSessionFactoryBuilder().build(in);
			session=fac.openSession();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public void addLogs(Logs l) {
		session.insert("addLogs", l);
	}
	
	public String show() {
		java.util.List<Logs> l=session.selectList("show");
		StringBuilder sb=new StringBuilder("");
		for(Logs s:l) {
			sb.append(s.toString()+"/n");
		}
		return sb.toString();
	}
	
	public void destroy() {
		session.commit();
		session.close();
	}
	public static void main(String[] args) {
		LogsOP op=new LogsOP();
		op.init();
		op.show();
		op.destroy();
	}
}

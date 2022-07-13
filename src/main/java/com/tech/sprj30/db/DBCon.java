package com.tech.sprj30.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
	public static Connection getConnection() throws Exception {
		
		Connection con=null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
	      //접속
	      String url="jdbc:oracle:thin:@localhost:1521:xe";
	      String user="hr";
	      String pw="123456";
	      con=DriverManager.getConnection(url, user, pw);
		
		return con;
	}
}

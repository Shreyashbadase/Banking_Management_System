package com.JdbcTemp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsertDemo {

	public static void main(String[] args) throws Exception {
		
		String name= "Shubham";
		int rollNo=106;
		int mark=90;
//		rollNo=106;
		
		//oracal-> oracal.jdbc.Driver
		// mySql-> com.mysql.cj.jdbc.Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//git Connection
		//protocol always be jdbc,  protocol:subprotocol:database specific information
		//jdbc:oracal:thin: @ip:1521:TNS
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/shreu2", "root" ,"Pass@123");
		
		//Insert
//		PreparedStatement ps =con.prepareStatement("insert into student values('"+ rollNo+"', '"+name+"', '"+mark+"')");
		
//		update (sql injection) concept
	
//		sql Compiler, sql interface, data
		
//		PreparedStatement ps =con.prepareStatement("update student set Roll_no=? where Name=?");
//		ps.setInt(1, rollNo);
//		ps.setString(2, name);
		
//		int i=ps.executeUpdate();

//		if(i>0) {
//			System.out.println("success");
//		}
//		else {
//			System.out.println("Fail");
//		}
		
		//Print Data we can print data by using statement and PreparedStatement
		
//		PreparedStatement ps =con.prepareStatement("select * from student");
//		ResultSet rs= ps.executeQuery();
		
		String query="select * from student";
		Statement stmt= con.createStatement();
		ResultSet rs= stmt.executeQuery(query);
		
		while(rs.next()) {
//			System.out.println(rs.getInt("Roll_no")+" "+rs.getString("Name")+" "+rs.getInt("marks"));
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
		}
		
		con.close();
	}

}

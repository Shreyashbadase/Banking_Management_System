package com.JdbcTransection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTransection {

	public static void main(String[] args) throws Exception{
		String withdrawQuery="Update Accounts set balance=balance-? where AccNo=?";
		String depositeQuery="Update Accounts set balance=balance+? where AccNo=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Successfully!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/shreu2", "root" ,"Pass@123");
			System.out.println("Connection loaded Successfully");
			con.setAutoCommit(false);
			
			try {
				PreparedStatement withdrawStatement = con.prepareStatement(withdrawQuery);
				PreparedStatement depositeStatement = con.prepareStatement(depositeQuery);
				withdrawStatement.setDouble(1, 500.00);
				withdrawStatement.setString(2, "account456");
				depositeStatement.setDouble(1, 500.00);
				depositeStatement.setString(2, "account123");
				int rowAffectedWithdraw= withdrawStatement.executeUpdate();
				int rowAffectedDeposite= depositeStatement.executeUpdate();
				
				if(rowAffectedWithdraw>0 && rowAffectedDeposite >0) {
					con.commit();
					System.out.println("Transection Successful");
				}
				else {
					con.rollback();
					System.out.println("Transection Failed");
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}

		
		
		
			
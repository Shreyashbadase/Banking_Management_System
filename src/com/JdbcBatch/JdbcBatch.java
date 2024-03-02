package com.JdbcBatch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcBatch {

	public static void main(String[] args) throws Exception{
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
			
			String query="Insert into employees (name, jobTitle, salary) values (?,?,?)";
			
			con.setAutoCommit(false);
			PreparedStatement preparedstatement = con.prepareStatement(query);
			Scanner sc= new Scanner(System.in);
			
			while(true) {
				System.out.print("Name: ");
				String name= sc.nextLine();
				System.out.print("Job Title: ");
				String jobTitle= sc.nextLine();
				System.out.print("Salary: ");
				double salary= sc.nextDouble();
				
				sc.nextLine();
				
				preparedstatement.setString(1, name);
				preparedstatement.setString(2, jobTitle);
				preparedstatement.setDouble(3, salary);
				preparedstatement.addBatch();
				
				
				System.out.println("Add more values Y/N : ");
				
				String decision= sc.nextLine().trim(); 
				
				if(decision.toUpperCase().equals("N")) {
					break;
				}
			}
			int [] batchResult =preparedstatement.executeBatch();
			con.commit();
			System.out.println("Batch Execute Sucessfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

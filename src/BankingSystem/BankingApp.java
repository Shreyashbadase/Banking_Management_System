package BankingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {

	public static void main(String[] args) throws Exception{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/BankingSystem", "root" ,"Pass@123");
			Scanner scanner= new Scanner(System.in);
			User user=new User(connection, scanner);
			Accounts accounts= new Accounts(connection, scanner);
			AccountManeger accountManeger=new AccountManeger(connection, scanner);
			
			String email;
			long account_number;
			
			while(true){
                System.out.println("--- WELCOME TO BANKING SYSTEM ---");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("Enter your choice: ");
                int choice1 = scanner.nextInt();
                switch (choice1){
                    case 1:
                        user.register();
                        break;
                        
                    case 2:
                        email = user.login();
                        if(email!=null){
                            System.out.println();
                            System.out.println("User Logged In!");
                            if(!accounts.account_exist(email)){
                                System.out.println();
                                System.out.println("1. Open a new Bank Account");
                                System.out.println("2. Exit");
                                if(scanner.nextInt() == 1) {
                                    account_number = accounts.open_account(email);
                                    System.out.println("Account Created Successfully");
                                    System.out.println("Your Account Number is: " + account_number);
                                }else{
                                    break;
                                }

                            }
                            account_number = accounts.getAccount_number(email);
                            int choice2 = 0;
                            while (choice2 != 5) {
                                System.out.println();
                                System.out.println("1. Debit Money");
                                System.out.println("2. Credit Money");
                                System.out.println("3. Transfer Money");
                                System.out.println("4. Check Balance");
                                System.out.println("5. Log Out");
                                System.out.println("Enter your choice: ");
                                choice2 = scanner.nextInt();
                                switch (choice2) {
                                    case 1:
                                        accountManeger.debit_money(account_number);
                                        break;
                                    case 2:
                                        accountManeger.credit_money(account_number);
                                        break;
                                    case 3:
                                        accountManeger.transfer_money(account_number);
                                        break;
                                    case 4:
                                        accountManeger.getBalance(account_number);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("Enter Valid Choice!");
                                        break;
                                }
                            }

                        }
                        else{
                            System.out.println("Incorrect Email or Password!");
                        }
                    case 3:
                        System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
                        System.out.println("Exiting System!");
                        return;
                    default:
                        System.out.println("Enter Valid Choice");
                        break;
                }
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}

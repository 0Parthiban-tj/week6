package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.nexmo.client.NexmoClientException;

import DAO.AgentDAO;
import DAO.UserDAO;
import businesslogic.ApplyLoan;
import businesslogic.Validation;

public class Main {
	public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException, NexmoClientException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int choice = 0;
		String yes;
		Validation validation=new Validation();
		UserDAO userc=new UserDAO();
		AgentDAO agentc=new AgentDAO();
		ApplyLoan aloan=new ApplyLoan();
		do {
			System.out.println("-------------------Insurance System------------------");
			System.out.println("Enter your choice");
			System.out.println("1)User Login");
			System.out.println("2)Agent Login");
			System.out.println("3)Apply for Loan");
			choice =Integer.parseInt(br.readLine());
			
			switch(choice) {
			case 1:
				int userid=0;
				String pass;
				System.out.println("-------------------User Login-------------------");
				System.out.println("Enter the UserID:");
				userid=Integer.parseInt(br.readLine());
				System.out.println("Enter the password:");
				pass=br.readLine();
				boolean a=validation.ValidateUser(userid,pass);
				if(a==true) {
					userc.getUserDetails(userid);
				}
				else
					System.out.println("Login username or password incorrect");
				break;
				
			
			case 2:
				int agentid=0;
				String password;
				System.out.println("-----------------Agent Login-------------------");
				System.out.println("Enter the AgentID");
				agentid=Integer.parseInt(br.readLine());
				System.out.println("Enter the password");
				password=br.readLine();
				a=validation.ValidateAgent(agentid,password); 
				if(a==true) {	
				agentc.AgentProcess(agentid);
				}
				else
					System.out.println("Login username or password incorrect");
				
				break;
				
			case 3:
				System.out.println("-----------------Apply for loan----------------");
				System.out.println("Please enter your name:");
				String name=br.readLine();
				System.out.println("Please enter your mobile number:");
				String mnum=br.readLine();
				System.out.println("Please enter the purpose of taking loan(life/vehicle/home)");
				String purpose=br.readLine();
				aloan.getNewLoan(name, mnum, purpose);
				//aloan.SendEmail();
			
				
				
			}
			System.out.println("Do you want to continue (y/n)");
			yes=br.readLine();
		}while(yes.equals("y"));
	}

}

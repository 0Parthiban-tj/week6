package DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utility.ConnectionManager;

public class AgentDAO {
	User user=new User();
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	String sql;
	String yes;
	public void AgentProcess(int agentid) throws NumberFormatException, IOException, ClassNotFoundException, SQLException {
		int choice=0;
		do {
		System.out.println("------------------------------------------------");
		System.out.println("1)Create User");
		System.out.println("2)Insert User details");
		System.out.println("3)Insert Home Loan Details");
		System.out.println("4)Insert Vehicle Loan Details");
		System.out.println("5)Insert Life Loan Details");
		System.out.println("Enter you choice:");
		choice=Integer.parseInt(br.readLine());
		
			
		
		switch(choice) {
		case 1:
			createUser();
			break;
		case 2:
			insertUserDetails();
			break;
		case 3:
			insertHomeLoan(agentid);
			break;
		case 4:
			insertAutoLoan(agentid);
			break;
		case 5:
			insertLifeLoan(agentid);
			break;
		}
		System.out.println("Do you want to continue(y/n)");
		yes=br.readLine();
		}while(yes.equals("y"));
	}
	public void createUser() throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		System.out.println("---------------------Create User----------------");
		System.out.println("Enter the user_id:");
		int id=Integer.parseInt(br.readLine());
		System.out.println("Enter the password:");
		String pass=br.readLine();
		Connection con=ConnectionManager.getConnection();
		sql="INSERT INTO USER_LOGIN(USER_ID,PASSWORD) VALUES (?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,id);
		ps.setString(2, pass);
		ResultSet rs=ps.executeQuery();
		rs.next();
		System.out.println("User created Sucessfully");
	}
	public void insertUserDetails() throws SQLException, ClassNotFoundException, IOException {
		Connection con=ConnectionManager.getConnection();
		System.out.println("------------------Insert user details--------------");
		System.out.println("Enter the userid:");
		user.setId(Integer.parseInt(br.readLine()));
		System.out.println("Enter mobile number");
		user.setMobile(br.readLine());
		System.out.println("Enter the email:");
		user.setEmail(br.readLine());
		System.out.println("Enter the address");
		user.setAddress(br.readLine());
		sql="INSERT INTO USERR (USER_ID,USER_NAME,USER_MOBILE,USER_EMAIL,USER_ADDRESS) VALUES (?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getMobile());
		ps.setString(4, user.getEmail());
		ps.setString(5, user.getAddress());
		ResultSet rs=ps.executeQuery();
		rs.next();
		
		System.out.println("User Inserted Sucessfully");
	}
	
	public void insertHomeLoan(int agentid) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		Connection con=ConnectionManager.getConnection();
		System.out.println("-----------------Insert Home Loan-------------------");
		System.out.println("Enter the user_id:");
		int id=Integer.parseInt(br.readLine());
		System.out.println("Enter Home_Plan_ID:");
		int hid=Integer.parseInt(br.readLine());
		System.out.println("Enter the document id:");
		String did=br.readLine();
		System.out.println("Enter the home Address:");
		String address=br.readLine();
		System.out.println("Enter the Plan_cost:");
		int pcost=Integer.parseInt(br.readLine());
		System.out.println("Enter the Plan start date");
		String sdate=br.readLine();
		System.out.println("Enter the plan end date:");
		String edate=br.readLine();
		System.out.println("Enter the emi amount");
		int emi=Integer.parseInt(br.readLine());
		sql="INSERT INTO HOUSEDETAILS (USER_ID,AGENT_ID,HOME_PLAN_ID,DOCUMENT_ID,HOUSE_ADDRESS,PLAN_COST,PLAN_START_DATE,PLAN_END_DATE,EMI_AMOUNT) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,id);
		ps.setInt(2, agentid);
		ps.setInt(3,hid);
		ps.setString(4, did);
		ps.setString(5,address);
		ps.setInt(6, pcost);
		ps.setString(7,sdate);
		ps.setString(8,edate);
		ps.setInt(9,emi);
		ResultSet rs=ps.executeQuery();
		rs.next();
		System.out.println("Details Inserted Sucessfully");
		
	}
	
	public void insertAutoLoan(int agentid) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		Connection con=ConnectionManager.getConnection();
		System.out.println("-----------------Insert Auto Loan-------------------");
		System.out.println("Enter the user_id:");
		int id=Integer.parseInt(br.readLine());
		System.out.println("Enter Auto_Plan_ID:");
		int aid=Integer.parseInt(br.readLine());
		System.out.println("Enter the Vehicle id:");
		String vid=br.readLine();
		System.out.println("Enter the Plan_cost:");
		int pcost=Integer.parseInt(br.readLine());
		System.out.println("Enter the Plan start date");
		String sdate=br.readLine();
		System.out.println("Enter the plan end date:");
		String edate=br.readLine();
		System.out.println("Enter the emi amount");
		int emi=Integer.parseInt(br.readLine());
		sql="INSERT INTO AUTODETAILS(USER_ID,AGENT_ID,AUTO_PLAN_ID,VEHICLE_NO,PLAN_COST,PLAN_START_DATE,PLAN_END_DATE,EMI_AMOUNT) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,id);
		ps.setInt(2, agentid);
		ps.setInt(3,aid);
		ps.setString(4, vid);
		ps.setInt(5, pcost);
		ps.setString(6,sdate);
		ps.setString(7,edate);
		ps.setInt(8,emi);
		ResultSet rs=ps.executeQuery();
		rs.next();
		System.out.println("Details Inserted Sucessfully");
		
	}
	
	public void insertLifeLoan(int agentid) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		Connection con=ConnectionManager.getConnection();
		System.out.println("-----------------Insert Life Loan-------------------");
		System.out.println("Enter the user_id:");
		int id=Integer.parseInt(br.readLine());
		System.out.println("Enter Life_Plan_ID:");
		int lid=Integer.parseInt(br.readLine());
		System.out.println("Enter the Plan_cost:");
		int pcost=Integer.parseInt(br.readLine());
		System.out.println("Enter the Plan start date");
		String sdate=br.readLine();
		System.out.println("Enter the plan end date:");
		String edate=br.readLine();
		System.out.println("Enter the emi amount");
		int emi=Integer.parseInt(br.readLine());
		sql="INSERT INTO LIFEDETAILS(USER_ID,AGENT_ID,LIFE_PLAN_ID,PLAN_COST,PLAN_START_DATE,PLAN_END_DATE,EMI_AMOUNT) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,id);
		ps.setInt(2, agentid);
		ps.setInt(3,lid);
		ps.setInt(4, pcost);
		ps.setString(5,sdate);
		ps.setString(6,edate);
		ps.setInt(7,emi);
		ResultSet rs=ps.executeQuery();
		rs.next();
		System.out.println("Details Inserted Sucessfully");
		
	}
	
	public void notpaidEmi(int agentid) throws SQLException, ClassNotFoundException {
		Connection con=ConnectionManager.getConnection();
		System.out.println("-------Customers Not Paid Emi--------------");
		sql="SELECT USER_ID FROM HOUSEDETAILS WHERE AGENT_ID=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1,agentid);
		ResultSet rs=ps.executeQuery();
		rs.next();
		 String home=rs.getString("HOME_DUE");
		 String auto=rs.getString("AUTO_DUE");
		 String life=rs.getString("LIFE_DUE");
	}

}

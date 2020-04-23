package DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import utility.ConnectionManager;

public class UserDAO {
	int choice=0;
	String yes;
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		public void getUserDetails(int id) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
			Connection con=ConnectionManager.getConnection();
			 Date date = new Date();
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(date);
			 int day = cal.get(Calendar.DAY_OF_MONTH);
			 int homeemi=0,autoemi=0,lifeemi=0,press=0;
			 int choice1;
			 String home,life,auto;
			do {
			System.out.println("-------------------------------------------------");	
			System.out.println("1)List your Loans");
			System.out.println("2)List your dues");
			System.out.println("3)Pay your dues");
			choice=Integer.parseInt(br.readLine());
			switch(choice) {
			case 1:
			System.out.println("-------------------User Details------------------");
			String sql="SELECT * FROM USERR WHERE USER_ID=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
			for(int i=1;i<=5;i++) {
				System.out.println(rsmd.getColumnName(i)+" : "+rs.getString(i));
			}
			}
			System.out.println("--------------Your current Loans-----------------");
			sql="SELECT * FROM HOUSEDETAILS WHERE USER_ID =?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,id);
			rs=ps.executeQuery();
			rsmd=rs.getMetaData();
			if(rs!=null) {
			System.out.println("------------------Home Loan---------------------");
				while(rs.next()) {
					for(int i=1;i<=9;i++) {
						System.out.println(rsmd.getColumnName(i)+" : "+rs.getString(i));
					}	
					}
			}
			
			sql="SELECT * FROM AUTODETAILS WHERE USER_ID =?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,id);
			rs=ps.executeQuery();
			rsmd=rs.getMetaData();
			if(rs!=null) {
			System.out.println("------------------Vehicle Loan------------------");
				while(rs.next()) {
					for(int i=1;i<=8;i++) {
						System.out.println(rsmd.getColumnName(i)+" : "+rs.getString(i));
					}	
					}
			}
			
			sql="SELECT * FROM LIFEDETAILS WHERE USER_ID =?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,id);
			rs=ps.executeQuery();
			rsmd=rs.getMetaData();
			if(rs!=null) {
			System.out.println("--------------------Life Loan-------------------");
				while(rs.next()) {
					for(int i=1;i<=7;i++) {
						System.out.println(rsmd.getColumnName(i)+" : "+rs.getString(i));
					}	
					}
			}
			
			break;
			
			case 2:
				String sql1="SELECT HOME_DUE,AUTO_DUE,LIFE_DUE FROM DUE WHERE USER_ID=?";
				ps=con.prepareStatement(sql1);
				ps.setInt(1,id);
				rs=ps.executeQuery();
				rsmd = rs.getMetaData();
				rs.next();
				 home=rs.getString("HOME_DUE");
				 auto=rs.getString("AUTO_DUE");
				 life=rs.getString("LIFE_DUE");
			System.out.println("-------------------Due Loan----------------------");
			if(home.equals("N") || auto.equals("N") || life.equals("N")) {
				sql="SELECT EXTRACT(DAY FROM PLAN_END_DATE) FROM HOUSEDETAILS WHERE USER_ID=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1,id);
				rs=ps.executeQuery();
				rsmd = rs.getMetaData();
				rs.next();
				int dueday=rs.getInt(1);
				if(dueday<day && home.equals("N")) {
					System.out.println("Your House loan is on due.. Please pay immediatly");
				}
				sql="SELECT EXTRACT(DAY FROM PLAN_END_DATE) FROM AUTODETAILS WHERE USER_ID=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1,id);
				rs=ps.executeQuery();
				rsmd = rs.getMetaData();
				rs.next();
				 dueday=rs.getInt(1);
				 if(dueday<day && auto.equals("N")) {
						System.out.println("Your Vehicle loan is on due.. Please pay immediatly");
					}
				 sql="SELECT EXTRACT(DAY FROM PLAN_END_DATE) FROM LIFEDETAILS WHERE USER_ID=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					rs=ps.executeQuery();
					rsmd = rs.getMetaData();
					rs.next();
					 dueday=rs.getInt(1);
					 if(dueday<day && auto.equals("N")) {
							System.out.println("Your Life loan is on due.. Please pay immediatly");
						}
			}
			else
				System.out.println("No dues Available");
				
				break;
				
			case 3:
			System.out.println("--------------------Pay your dues------------------");
				sql="SELECT HOME_DUE,AUTO_DUE,LIFE_DUE FROM DUE WHERE USER_ID=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1,id);
				rs=ps.executeQuery();
				rsmd = rs.getMetaData();
				rs.next();
				 home=rs.getString("HOME_DUE");
				 auto=rs.getString("AUTO_DUE");
				 life=rs.getString("LIFE_DUE");
			
				if(home.equals("N")) {
					sql="SELECT EMI_AMOUNT FROM HOUSEDETAILS WHERE USER_ID=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					rs=ps.executeQuery();
					rs.next();
					homeemi=rs.getInt("EMI_AMOUNT");
					System.out.println("Your Emi Amount for Home LOAN:Rs."+homeemi);
					System.out.println("1)Pay your home loan");
				}
			
				if(auto.equals("N")) {
					sql="SELECT EMI_AMOUNT FROM AUTODETAILS WHERE USER_ID=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1,id);
					rs=ps.executeQuery();
					rs.next();
					autoemi=rs.getInt("EMI_AMOUNT");
					System.out.println("Your Emi amount For Vehicle Loan:Rs."+autoemi);
					System.out.println("2)Pay your Vehicle loan");
				}
				
					if(life.equals("N")) {
						sql="SELECT EMI_AMOUNT FROM LIFEDETAILS WHERE USER_ID=?";
						ps=con.prepareStatement(sql);
						ps.setInt(1,id);
						rs=ps.executeQuery();
						rs.next();
						lifeemi=rs.getInt("EMI_AMOUNT");
						System.out.println("Your Emi amount For Life Loan:Rs."+lifeemi);
						System.out.println("3)Pay your Life loan");
					}
					if(homeemi!=0 || autoemi!=0 || lifeemi!=0) {
					System.out.println("4)Pay all your loans:");
					System.out.println("Enter your choice:");
					System.out.println("----------------------------------------------------");
					choice1=Integer.parseInt(br.readLine());
					switch(choice1) {
					case 1:
						System.out.println("Your Home Loan :"+homeemi+" Press '1' to pay...!");
						press=Integer.parseInt(br.readLine());
						if(press==1) {
							System.out.println("Redirecting to payment....Please wait");
							sql="UPDATE DUE SET HOME_DUE='Y' WHERE USER_ID=?";
							ps=con.prepareStatement(sql);
							ps.setInt(1,id);
							rs=ps.executeQuery();
							rs.next();
							System.out.println("Emi paid sucessfully");
							homeemi=0;
							break;
						}
					case 2:
						System.out.println("Your Auto Loan :"+autoemi+" Press '1' to pay...!");
						press=Integer.parseInt(br.readLine());
						if(press==1) {
							System.out.println("Redirecting to payment....Please wait");
							sql="UPDATE DUE SET AUTO_DUE='Y' WHERE USER_ID=?";
							ps=con.prepareStatement(sql);
							ps.setInt(1,id);
							rs=ps.executeQuery();
							rs.next();
							System.out.println("Emi paid sucessfully");
							autoemi=0;
							break;
					}
					case 3:
						System.out.println("Your Life Loan :"+lifeemi+" Press '1' to pay...!");
						press=Integer.parseInt(br.readLine());
						if(press==1) {
							System.out.println("Redirecting to payment....Please wait");
							sql="UPDATE DUE SET LIFE_DUE='Y' WHERE USER_ID=?";
							ps=con.prepareStatement(sql);
							ps.setInt(1,id);
							rs=ps.executeQuery();
							rs.next();
							System.out.println("Emi paid sucessfully");
							lifeemi=0;
							break;
						}
						
					case 4:
						System.out.println("Your Loan is about :"+(homeemi+autoemi+lifeemi)+" Press '1' to pay...!");
						press=Integer.parseInt(br.readLine());
						if(press==1) {
							System.out.println("Redirecting to payment....Please wait");
							sql="UPDATE DUE SET HOME_DUE='Y',AUTO_DUE='Y',LIFE_DUE='Y' WHERE USER_ID=?";
							ps=con.prepareStatement(sql);
							ps.setInt(1,id);
							rs=ps.executeQuery();
							rs.next();
							System.out.println("Emi paid sucessfully");
							homeemi=0;
							autoemi=0;
							lifeemi=0;
							break;
						}
					}
					}
					else
					System.out.println("No dues Available");
				break;
				
		}
			
			System.out.println("Do you want to continue (y/n)");
			yes=br.readLine();
			}while(yes.equals("y"));
			
			
			
		}
		}



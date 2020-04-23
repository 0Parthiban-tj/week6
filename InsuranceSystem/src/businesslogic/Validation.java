package businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.ConnectionManager;

public class Validation {
	
	
	public boolean ValidateUser(int userid,String pass) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionManager.getConnection();
		final String sql="SELECT USER_ID,PASSWORD FROM USER_LOGIN WHERE USER_ID=? AND PASSWORD=?";
		final PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, userid);
		ps.setString(2, pass);
		final ResultSet rs=ps.executeQuery();	
		
		return rs.next();
	}
	public boolean ValidateAgent(int agentid,String password) throws ClassNotFoundException, SQLException {
		
		Connection con = ConnectionManager.getConnection();
		final String sql="SELECT * FROM AGENT_LOGIN WHERE AGENT_ID='"+agentid+"' AND PASSWORD='"+password+"'";
		final PreparedStatement ps=con.prepareStatement(sql);
		final ResultSet rs=ps.executeQuery();
		return rs.next();
	}

}

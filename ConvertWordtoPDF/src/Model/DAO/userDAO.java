package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.Bean.user;

public class userDAO {

	public boolean isValidUser(String username, String password) {
		Connection conn = null;
		String username1="root";
		String password1="";
		String url ="jdbc:mysql://localhost:3306/DULIEU2";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username1, password1);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user Where username='"+username+"' and password='"+password+"'");
			if(rs.next()){
				return true;
			}else {
				return false;
			}
		}catch(Exception e){
			System.out.println("Loi CheckLogin UserDAO:"+e.getMessage());
			return false;
		}
	}

	public void InsertUser(user user) {
		Connection conn = null;
		String username1="root";
		String password1="";
		String url ="jdbc:mysql://localhost:3306/DULIEU2";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username1, password1);
			String sql ="insert into user(username,password) values(?,?)";
			PreparedStatement ppst= conn.prepareStatement(sql);
			ppst.setString(1, user.Getusername());
			ppst.setString(2, user.Getpassword());
			ppst.executeUpdate();
		}catch(Exception e){
			System.out.println("Loi InsertUserDAO:"+e.getMessage());
		}
	}

	public user FindUser(String username, String password) {
		Connection conn = null;
		String username1="root";
		String password1="";
		String url ="jdbc:mysql://localhost:3306/DULIEU2";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username1, password1);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user Where username='"+username+"' and password='"+password+"'");
			if (rs.next()) {
				user user = new user();
				user.Setusername(rs.getString("username"));
				user.Setpassword(rs.getString("password"));
				user.Setid(rs.getInt("id"));
				return user;
			}
		}catch(Exception e){
			System.out.println("Loi CheckLogin UserDAO:"+e.getMessage());
		}
		return null;
	}



}

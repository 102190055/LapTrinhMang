package Model.DAO;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.http.Part;

import Model.Bean.File;
import Model.Bean.user;

public class FileDAO {

	public File UploadFile(File file) {
		Connection conn = null;
		String username1="root";
		String password1="";
		String url ="jdbc:mysql://localhost:3306/DULIEU2";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username1, password1);
			String query = "INSERT INTO FILE(Userid, FileName, Status, Date, Data) values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, file.GetUserID());
			ps.setString(2, file.GetFileName());
			ps.setBoolean(3, file.GetStatus());
			ps.setDate(4, file.GetDateTime());
			ps.setBlob(5, file.GetData());
			if (ps.executeUpdate() != 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while(rs.next()) {
					file.Setid(rs.getInt(1));
				}
				rs.close();
			}
			conn.close();
			return file;
		} catch (Exception e) {
			System.out.println("Loi DAO UpFile: " + e.toString());
			return null;
		}
	}

	public ArrayList<File> GetFiles(user user) {
		Connection conn = null;
		String username1="root";
		String password1="";
		String url ="jdbc:mysql://localhost:3306/DULIEU2";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username1, password1);
			ArrayList<File> listFiles = new ArrayList<File>();
			String query = "SELECT * from file where Userid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, user.Getid());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				File f = new File();
				f.Setid(rs.getInt("ID"));
				f.SetFileName(rs.getString("FileName"));
				f.SetStatus(rs.getBoolean("Status"));
				f.SetDate(rs.getDate("Date"));
				listFiles.add(f);
			}
			return listFiles;
		} catch (Exception e) {
			System.out.println("Loi DAO GetFile: " + e.toString());
			return null;
		}
	}

	public File GetFileFromDB(int id) {
		Connection conn = null;
		String username1="root";
		String password1="";
		String url ="jdbc:mysql://localhost:3306/DULIEU2";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username1, password1);
			String query = "SELECT * FROM FILE WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				String FileName  = rs.getString("FileName");
				int userId = rs.getInt("UserID");
				Date date = rs.getDate("Date");
				Blob data = rs.getBlob("Data");
				boolean status = rs.getBoolean("Status");
				return new File(id,userId,FileName,status,date,data);
			}
			else return null; // not exist in database
		} catch (Exception e) {
			System.out.println("Loi DAO UpFile: " + e.toString());
			return null;
		}
	}

}

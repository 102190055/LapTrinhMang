package Model.BO;

import Model.Bean.user;
import Model.DAO.userDAO;

public class userBO {
	userDAO CheckLoginDAO = new userDAO(); 
	public boolean isValidUser(String username, String password) {
		return CheckLoginDAO.isValidUser(username,password);
	}
	public void InsertUser(user user) {
		CheckLoginDAO.InsertUser(user);
	}
	public user FindUser(String username, String password) {
		
		return CheckLoginDAO.FindUser( username,password);
	}
}

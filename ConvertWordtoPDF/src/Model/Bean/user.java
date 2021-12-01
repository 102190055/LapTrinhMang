package Model.Bean;

public class user {
	private int ID;
	private String username;
	private String password;
	public user()
	{
		
	}
	public user(String user,String pass) {
		this.username=user;
		this.password=pass;
	}
	public void Setid(int id) {
		this.ID = id;
	}
	public int Getid() {
		return ID;
	}
	public void Setusername(String user) {
		this.username=user;
	}
	public String Getusername() {
		return username;
	}
	public void Setpassword(String pass) {
		this.password=pass;
	}
	public String Getpassword() {
		return password;
	}
}

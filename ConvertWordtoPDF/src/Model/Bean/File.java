package Model.Bean;

import java.sql.Blob;
import java.sql.Date;


public class File {
	private int ID;
	private int userID;
	private String FileName;
	private boolean Status;
	private Date DateTime;
	private Blob Data;
	public File()
	{
		
	}
	public File(int id,int userID,String FileName,boolean Status,Date DateTime,Blob Data) {
		this.ID = id;
		this.userID=userID;
		this.FileName=FileName;
		this.Status=Status;
		this.DateTime= DateTime;
		this.Data = Data;
	}
	public void Setid(int id) {
		this.ID = id;
	}
	public int Getid() {
		return ID;
	}
	public void SetUserID(int id) {
		this.userID = id;
	}
	public int GetUserID() {
		return userID;
	}
	public void SetFileName(String FileName) {
		this.FileName = FileName;
	}
	public String GetFileName() {
		return FileName;
	}
	public void SetStatus(boolean stt) {
		this.Status = stt;
	}
	public boolean GetStatus() {
		return Status;
	}
	public void SetDate(Date DateTime) {
		this.DateTime = DateTime;
	}
	public Date GetDateTime() {
		return DateTime;
	}
	public void SetData(Blob Data) {
		this.Data = Data;
	}
	public Blob GetData() {
		return Data;
	}
	
}

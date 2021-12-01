package Model.BO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Model.Bean.File;
import Model.Bean.user;
import Model.DAO.FileDAO;
import fr.opensagres.poi.xwpf.converter.core.XWPFConverterException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class FileBO {
	FileDAO fileDAO = new FileDAO();
	
	public boolean UpFile(byte[] data, File file) {
		if (file != null) {
			new XuLy(data, file).start();
			return true;
		}
		return false;
	}
	public boolean CheckStatus(File file) {
		return file.GetStatus();
	}
	public byte[] ConvertToFilePDF(byte[] docx) throws XWPFConverterException, IOException {
	    InputStream inputdata = new ByteArrayInputStream(docx);
	    XWPFDocument doc = new XWPFDocument(inputdata);
	    PdfOptions options = PdfOptions.create();
	    ByteArrayOutputStream pdffile = new ByteArrayOutputStream();
	    PdfConverter.getInstance().convert(doc, pdffile, options);
	    doc.write(pdffile);
	    doc.close();
	    return pdffile.toByteArray();
	}
	public ArrayList<File> GetFiles(user user) {
		if (user != null)
			return new FileDAO().GetFiles(user);
		return null;
	}
	public File GetFileFromDB(int id) {
		return fileDAO.GetFileFromDB(id);
	}
}
class XuLy extends Thread {
	byte[] data;
	File file;
	FileDAO fileDAO = new FileDAO();
	FileBO fileBO = new FileBO();
	String end =".pdf";
	public XuLy(byte[] data, File file) {
		this.data = data;
		this.file = file;
	}
	public void run() {
		if (this.file != null) {
			try {	
				System.out.println("Dang xu li file: " + this.file.GetFileName());
			
				this.data = fileBO.ConvertToFilePDF(this.data);
				this.file.SetData(new javax.sql.rowset.serial.SerialBlob(this.data));	
				this.file.SetFileName(this.file.GetFileName()+ end);
				this.file.SetStatus(true);
				fileDAO.UploadFile(file);
			} catch (Exception e) {
				System.out.println("Xu li file loi: " + e.toString());
			}
			System.out.println(this.file.GetFileName() + " xu li xong");	
		}
	}
}
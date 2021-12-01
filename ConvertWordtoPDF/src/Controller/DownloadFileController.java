package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import Model.BO.FileBO;
import Model.Bean.File;
import Model.Bean.user;

@WebServlet("/DownloadFileController")
public class DownloadFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DownloadFileController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {  
			FileBO fileBO = new FileBO();
	           int id = 0;
	           user Session = (user) request.getSession().getAttribute("User");
               id = Integer.parseInt(request.getParameter("id"));
	           File file = fileBO.GetFileFromDB(id);
	           
	           String file_name = file.GetFileName();
	           String content_type = this.getServletContext().getMimeType(file_name);
	           response.setHeader("Content-Type", content_type);
	           response.setHeader("Content-Length", String.valueOf(file.GetData().length()));
	           response.setHeader("Content-Disposition", "inline; filename=\"" + file.GetFileName() + "\"");
	           Blob data = file.GetData();
	           InputStream inputstream = data.getBinaryStream();
	           byte[] bytes = new byte[1024];
	           int readbyte;
	           while ((readbyte = inputstream.read(bytes)) != -1) {
	               response.getOutputStream().write(bytes, 0, readbyte);
	           }
	           inputstream.close();
	       } catch (Exception e) {
	           throw new ServletException(e);
	       } 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

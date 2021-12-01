package Controller;


import java.io.IOException;
import java.nio.file.Paths;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import Model.BO.FileBO;
import Model.Bean.File;
import Model.Bean.user;

@WebServlet("/UpFileController")
@MultipartConfig(maxFileSize = 1024 * 1024 * 50) 
public class UpFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpFileController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			Part filePart = request.getPart("file");
			user user = (user)request.getSession().getAttribute("User");
			FileBO fileBO = new FileBO();
			String message = "";
			if (filePart != null) {
				byte[] data = filePart.getInputStream().readAllBytes();
				String GUIDwithext = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				String FileName = GUIDwithext.substring(0,GUIDwithext.lastIndexOf('.'));
				String extension = GUIDwithext.substring(GUIDwithext.lastIndexOf('.'));
				if (".doc".equals(extension) || ".docx".equals(extension)) {
					File file = new File();
					file.SetUserID(user.Getid());
					file.SetFileName(FileName);
					file.SetStatus(false);
					file.SetDate(new java.sql.Date(new java.util.Date().getTime()));
					if (fileBO.UpFile(data, file))
					{
						message = "Upload File Success";
					}
					else
						message = "Upload File Error";
				} else {	
					message = "Extension Error";
				}
				request.setAttribute("message", message);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoadFileController");
				rd.forward(request, response);
			}
		} catch (IllegalStateException e) {
			System.out.println("Loi UpFile: " + e.toString());
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

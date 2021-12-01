package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import Model.BO.FileBO;
import Model.Bean.user;

@WebServlet("/DownFileController")
public class DownFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DownFileController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileBO fileBO = new FileBO();
		user user = (user)request.getSession().getAttribute("User");
		request.setAttribute("UserFiles", fileBO.GetFiles(user));
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/DownloadFile.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

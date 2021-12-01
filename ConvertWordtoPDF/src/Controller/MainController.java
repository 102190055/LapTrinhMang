package Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.text.Document;

import Model.BO.FileBO;
import Model.BO.userBO;
import Model.Bean.user;


@WebServlet("/")
@MultipartConfig(maxFileSize = 16177215)  
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action) {
		case"/login":{
			String destinate = "/Index.jsp";
			RequestDispatcher rs = request.getRequestDispatcher(destinate);
			rs.forward(request, response);
			break;
		}
		case "/register": {
			String destinate="/Register.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(destinate);
			rd.forward(request, response);
			break;
		}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action) {
		case"/login":{
			String username = request.getParameter("userName");
			String password = request.getParameter("password");
			userBO checkLoginBO = new userBO();
			user user = new userBO().FindUser(username,password);
			if(checkLoginBO.isValidUser(username, password))
			{
				request.getSession().setAttribute("User", user);
				RequestDispatcher rs = request.getRequestDispatcher("HomeView.jsp");
				rs.forward(request, response);
			}
			else {
				RequestDispatcher rs = request.getRequestDispatcher("Index.jsp");
				rs.forward(request, response);
			}
			break;
		}
		case "/register":{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			user user = new user(username,password);
			userBO checkLoginBO = new userBO();
			checkLoginBO.InsertUser(user);
			String destinate="/Index.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(destinate);
			rd.forward(request, response);
			break;
		}
	}
	}

}

package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/addUser")
public class AddUserServlet extends HttpServlet {
	private UserService service = UserServiceImpl.getInstance();

	private boolean isInvalid;

	public AddUserServlet() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/addUser.jsp");
		if (isInvalid) {
			request.setAttribute("isNotValid", true);
			isInvalid = false;
		}
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		if (name.isEmpty() || password.isEmpty()) {
			isInvalid = true;
			response.sendRedirect("/admin/addUser");
			return;
		}

		if (role.isEmpty()) {
			role = "user";
		}

		service.addUser(new User(name, password, role));
		response.sendRedirect("/admin");
	}
}

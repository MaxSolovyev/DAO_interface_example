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

@WebServlet("/admin/edit")
public class EditUserServlet extends HttpServlet {
	private UserService service = UserServiceImpl.getInstance();

	private boolean isInvalid;

	public EditUserServlet() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		User user = service.getUserById(id);
		if (user == null) {
			response.sendRedirect("users");
			return;
		}
		if (isInvalid) {
			request.setAttribute("isNotValid", true);
			isInvalid = false;
		}
		request.setAttribute("user", user);

		response.setContentType("text/html");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		if (name.isEmpty() || password.isEmpty()) {
			isInvalid = true;
			response.sendRedirect("edit?id=" + id);
			return;
		}

		if (role.isEmpty()) {
			role = "user";
		}

		service.editUser(new User(id, name, password, role));
		response.sendRedirect("/admin");
	}
}

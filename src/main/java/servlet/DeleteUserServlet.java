package servlet;

import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/delete")
public class DeleteUserServlet extends HttpServlet {
	private UserService service = UserServiceImpl.getInstance();

	public DeleteUserServlet() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		service.remove(id);
		response.sendRedirect("/admin");
	}
}

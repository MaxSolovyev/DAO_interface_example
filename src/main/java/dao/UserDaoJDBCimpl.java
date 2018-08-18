package dao;


import dao.executor.Executor;
import model.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCimpl implements UserDao {
	private final Executor executor;

	public UserDaoJDBCimpl(Connection connection) {
		this.executor = new Executor(connection);
	}

	@Override
	public User getUserById(long id) {
		System.out.println("get user by ID ...");
		return executor.execQuery("SELECT * FROM users WHERE id='" + id + "'", result -> {
			result.next();
			return  new User(
					result.getLong(1),
					result.getString(2),
					result.getString(3),
					result.getString(4)
			);
		});
	}

	@Override
	public User getUserByName(String name) {
		return executor.execQuery("SELECT * FROM users WHERE name='" + name + "'", result -> {
			result.next();
			return  new User(
					result.getLong(1),
					result.getString(2),
					result.getString(3),
					result.getString(4)
			);
		});
	}

	@Override
	public void addUser(User user) {
		executor.execUpdate("INSERT INTO users (name, password, role) VALUES ('" + user.getName() + "', '"
				+ user.getPassword() + "', '" + user.getRole() + "')");
	}

	@Override
	public void editUser(User user) {
		executor.execUpdate("UPDATE users SET name='" + user.getName() + "', password='" + user.getPassword()
				+ "', role='" + user.getRole() + "' WHERE id='" + user.getId() + "'");
	}

	@Override
	public void remove(long id) {
		executor.execUpdate("DELETE FROM users WHERE id='" + id + "'");
	}

	@Override
	public List<User> getAllUsers() {
		return executor.execQuery("SELECT * FROM users;",
				result -> {
					List<User> usList = new ArrayList<>();
					while (result.next()) {
						usList.add(
								new User(
										result.getLong(1),
										result.getString(2),
										result.getString(3),
										result.getString(4)
								));
					}
					return usList;
				});
	}
}

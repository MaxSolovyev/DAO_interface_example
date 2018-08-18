package dao.executor;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
	private final Connection connection;

	public Executor(Connection connection) {
		this.connection = connection;
	}

	public void execUpdate(String update) {
		try {
			connection.setAutoCommit(false);

			Statement stmt = connection.createStatement();
			stmt.execute(update);
			stmt.close();

			connection.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException ignore) {
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException ignore) {
			}
		}
	}

	public <T> T execQuery(String query, ExecutorHelper<T> helper) {
		T user = null;
		try {
			connection.setAutoCommit(false);

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			user = helper.help(resultSet);
			resultSet.close();
			statement.close();

			connection.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException ignore) {
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException ignore) {
			}
		}

		return user;
	}
}

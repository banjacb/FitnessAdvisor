package net.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.etf.dto.Advisor;

public class AdvisorDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username=? AND password=?";
	private static final String SQL_GET_USER_ROLE = "SELECT role FROM user WHERE username = ?";
	private static final String SQL_GET_USER_ID = "SELECT id FROM user WHERE username = ?";

	public AdvisorDAO() {
		// TODO Auto-generated constructor stub
	}

	public static Advisor selectByUsernameAndPassword(String username, String password) {
		Advisor user = null;
		Connection connection = null;
		ResultSet resultSet = null;
		Object values[] = { username, password };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_USERNAME_AND_PASSWORD, false,
					values);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				user = new Advisor(resultSet.getInt("id"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("city"), resultSet.getString("email"),
						resultSet.getInt("role"), resultSet.getBoolean("status"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}

	public static int getUserRole(String username) {
		int role = -1;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_USER_ROLE, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				role = rs.getInt("role");
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return role;
	}

	public static int getUserIdByUsername(String username) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int userId = -1;
		try {
			connection = connectionPool.checkOut();
			statement = connection.prepareStatement(SQL_GET_USER_ID);
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				userId = resultSet.getInt("id");
			}
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return userId;
	}

}

package net.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.etf.dto.AdvisorMessage;

public class AdvisorMessageDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String  SQL_SELECT_ALL_MESSAGES = "SELECT am.id AS messageId,\r\n"
			+ "       am.date AS createdAt,\r\n"
			+ "       am.text AS messageText,\r\n"
			+ "       am.seen AS messageSeen,\r\n"
			+ "       am.category_id AS categoryId,\r\n"
			+ "       c.name AS categoryName,\r\n"
			+ "       am.advisor_id AS advisorId,\r\n"
			+ "       uad.first_name AS advisorFirstName,\r\n"
			+ "       uad.last_name AS advisorLastName,\r\n"
			+ "       uad.email AS advisorEmail,\r\n"
			+ "       am.user_id AS userId,\r\n"
			+ "       uu.first_name AS userFirstName,\r\n"
			+ "       uu.last_name AS userLastName,\r\n"
			+ "       uu.email AS userEmail\r\n"
			+ "FROM advisor_message AS am\r\n"
			+ "JOIN category AS c ON am.category_id = c.id\r\n"
			+ "JOIN user AS uad ON am.advisor_id = uad.id\r\n"
			+ "JOIN user AS uu ON am.user_id = uu.id;\r\n"
			+ "";

	
	private static final String SEARCH_MESSAGE = "SELECT am.id AS messageId,\r\n"
	        + "    am.date AS createdAt,\r\n"
	        + "    am.text AS messageText,\r\n"
	        + "    am.seen AS messageSeen,\r\n"
	        + "    am.category_id AS categoryId,\r\n"
	        + "    c.name AS categoryName,\r\n"
	        + "    am.advisor_id AS advisorId,\r\n"
	        + "    uad.first_name AS advisorFirstName,\r\n"
	        + "    uad.last_name AS advisorLastName,\r\n"
	        + "    uad.email AS advisorEmail,\r\n"
	        + "    am.user_id AS userId,\r\n"
	        + "    uu.first_name AS userFirstName,\r\n"
	        + "    uu.last_name AS userLastName,\r\n"
	        + "    uu.email AS userEmail\r\n"
	        + "FROM advisor_message AS am\r\n"
	        + "JOIN category AS c ON am.category_id = c.id\r\n"
	        + "JOIN user AS uad ON am.advisor_id = uad.id\r\n"
	        + "JOIN user AS uu ON am.user_id = uu.id\r\n"
	        + "WHERE am.text LIKE ?;";


	private static final String UPDATE_SEEN="update advisor_message set seen=1 where id=?";
	
	
	public AdvisorMessageDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<AdvisorMessage> findAll()throws SQLException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<AdvisorMessage> result = new ArrayList<>();
		try {
			connection=connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_MESSAGES, false, new Object[0]);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				
			
				result.add(new AdvisorMessage(resultSet.getInt("messageId"), resultSet.getDate("createdAt"), resultSet.getString("messageText"),resultSet.getBoolean("messageSeen"),resultSet.getInt("categoryId"),resultSet.getString("categoryName"),resultSet.getInt("advisorId"),resultSet.getInt("userId"),resultSet.getString("userFirstName"),resultSet.getString("userLastName"),resultSet.getString("userEmail")));
			   
			}
			
		} finally {
			connectionPool.checkIn(connection);
			
		}
		return result;
	}
	
	public boolean update(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		Object[]values= {id};
		boolean result=false;
		try {
			connection=connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, UPDATE_SEEN, false,values);
			result=statement.executeUpdate()==1;
		}
		finally {
			connectionPool.checkIn(connection);

		}
		return result;
	}
	
	public List<AdvisorMessage> search(String str) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<AdvisorMessage> result = new ArrayList<>();
		Object[]values= {"%"+str+"%"};
		try {
			connection=connectionPool.checkOut();
			statement = DAOUtil.prepareStatement(connection, SEARCH_MESSAGE, false,values);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				result.add(new AdvisorMessage(resultSet.getInt("messageId"), resultSet.getDate("createdAt"), resultSet.getString("messageText"),resultSet.getBoolean("messageSeen"),resultSet.getInt("categoryId"),resultSet.getString("categoryName"),resultSet.getInt("advisorId"),resultSet.getInt("userId"),resultSet.getString("userFirstName"),resultSet.getString("userLastName"),resultSet.getString("userEmail")));
				   }
			
		} finally {
			connectionPool.checkIn(connection);
		
		}
		return result;
	}
}

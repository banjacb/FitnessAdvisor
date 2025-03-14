package net.etf.dto;

import java.io.Serializable;
import java.util.Date;

public class AdvisorMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date date;
	private String text;
	private boolean seen;
	private int categoryId;
	private String categoryName;
	private int advisorId;
	private String userName;
	private String userLastName;
	private String advisorFirstName;
	private String advisorLastName;
	private String userEmail;
	private int userId;
	
	


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAdvisorFirstName() {
		return advisorFirstName;
	}

	public void setAdvisorFirstName(String advisorFirstName) {
		this.advisorFirstName = advisorFirstName;
	}

	public String getAdvisorLastName() {
		return advisorLastName;
	}

	public void setAdvisorLastName(String advisorLastName) {
		this.advisorLastName = advisorLastName;
	}

	public AdvisorMessage(int id,Date date, String text, boolean seen, int categoryId, String categoryName, int advisorId, int userId,
			String userName, String userLastName, String userEmail) {
		super();
		this.id=id;
		this.date = date;
		this.text = text;
		this.seen = seen;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.advisorId = advisorId;
		this.userId=userId;
		this.userName = userName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
	}
	
	

	public AdvisorMessage( Date createdAt, String text, boolean seen, int categoryId, String categoryName,
			int advisorId, int userId, String userName, String userLastName, String userEmail) {

		this.date = createdAt;
		this.text = text;
		this.seen = seen;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.advisorId = advisorId;
		this.userName = userName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return date;
	}

	public void setCreatedAt(Date createdAt) {
		this.date = createdAt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getAdvisorId() {
		return advisorId;
	}

	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AdvisorMessage() {
		// TODO Auto-generated constructor stub
	}


	
	

}

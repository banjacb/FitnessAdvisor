package net.etf.beans;

import java.io.Serializable;

import net.etf.dao.AdvisorDAO;
import net.etf.dto.Advisor;

public class AdvisorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Advisor advisor= new Advisor();
	private boolean isLoggedIn = false;

	public boolean login(String username, String password) {
		if ((advisor = AdvisorDAO.selectByUsernameAndPassword(username, password)) != null) {
			isLoggedIn = true;
			return true;
		}
		return false;
	}
	
	public  boolean isAdvisor(String username) {
	    int role = AdvisorDAO.getUserRole(username);
	    return role==1;
	}
	
	public  int getUserIdByUsername(String username) {
	    
		int id = AdvisorDAO.getUserIdByUsername(username);
	    return id;
	    
	}
	
	
	public Advisor getAdvisor() {
		return advisor;
	}


	public void setAdvisor(Advisor advisor) {
		this.advisor = advisor;
	}


	public boolean isLoggedIn() {
		return isLoggedIn;
	}


	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}


	public AdvisorBean() {
		// TODO Auto-generated constructor stub
	}
}

package net.etf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.etf.dao.AdvisorMessageDAO;
import net.etf.dto.AdvisorMessage;

public class AdvisorMessageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private AdvisorMessageDAO advisorMessageDAO;
	private List<AdvisorMessage> advisorMessages = new ArrayList<>();
	private AdvisorMessage selected = null;

	public AdvisorMessageDAO getAdvisorMessageDAO() {
		return advisorMessageDAO;
	}

	public void setAdvisorMessageDAO(AdvisorMessageDAO advisorMessageDAO) {
		this.advisorMessageDAO = advisorMessageDAO;
	}

	public List<AdvisorMessage> getAdvisorMessages() {
		return advisorMessages;
	}

	public void setAdvisorMessages(List<AdvisorMessage> advisorMessages) {
		this.advisorMessages = advisorMessages;
	}

	public AdvisorMessage getSelected() {
		return selected;
	}

	public void setSelected(AdvisorMessage selected) {
		this.selected = selected;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AdvisorMessageBean() {

		this.advisorMessageDAO = new AdvisorMessageDAO();
		
	
	}

	public void findAll() {
		try {

			advisorMessages = advisorMessageDAO.findAll();
		} catch (Exception e) {

		}
	}

	public void readMessage(int id) {
		try {
			advisorMessageDAO.update(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void search(String str) {
		try {
			advisorMessages = this.advisorMessageDAO.search(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

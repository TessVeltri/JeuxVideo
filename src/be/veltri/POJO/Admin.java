package be.veltri.POJO;

import java.io.Serializable;
import java.util.ArrayList;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Admin extends User implements Serializable{
	private static final long serialVersionUID = 2916863941316481954L;
	private ArrayList<Message> lstMessage;
	
	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Admin> adminDAO = dao.getAdminDAO();

	// Constructeur par défaut
	public Admin() {}
	
	// Constructeur avec arguments
	public Admin(String username, String password) {
		super(username, password);
		this.setLstMessage(new ArrayList<>());
	}
	public Admin(String username, String password, ArrayList<Message> lstMessage) {
		super(username, password);
		this.setLstMessage(lstMessage);
	}
	
	// Getters et Setters 
	public ArrayList<Message> getLstMessage() {
		return lstMessage;
	}

	public void setLstMessage(ArrayList<Message> lstMessage) {
		this.lstMessage = lstMessage;
	}
	
	// Méthodes
	public Admin findById(int i) {
		return adminDAO.findById(i);	
	}
	
	public Admin find () {
		return adminDAO.find(this);
	}
	
}

package be.veltri.POJO;

import java.io.Serializable;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Admin extends User implements Serializable{
	private static final long serialVersionUID = 2916863941316481954L;
	
	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Admin> adminDAO = dao.getAdminDAO();

	// Constructeur par défaut
	public Admin() {}
	
	// Constructeur avec arguments
	public Admin(String username, String password) {
		super(username, password);
	}
	
	// Méthodes
	
}

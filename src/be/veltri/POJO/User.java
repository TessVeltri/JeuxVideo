package be.veltri.POJO;

import java.io.Serializable;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public abstract class User implements Serializable{
	private static final long serialVersionUID = 4378927386956734076L;
	private String username;
	private String password;
	
	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<User> userDAO = dao.getUserDAO();

	// Constructeur par défaut
	public User() {}

	// Constructeur avec arguments
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// Getters et Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Méthodes	
	// str1 = discriminator, str2 = username
	public int findIdByName(String str1, String str2) {
		return userDAO.findIdByName(str1, str2, "", "");
	}
	
	public User login() {
		return userDAO.find(this);
	}

}

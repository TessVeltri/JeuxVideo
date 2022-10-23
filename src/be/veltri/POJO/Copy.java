package be.veltri.POJO;

import java.io.Serializable;
import java.util.ArrayList;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Copy implements Serializable{
	private static final long serialVersionUID = -5295891430835243265L;
	private Player owner;
	private Game game;
	
	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Copy> copyDAO = dao.getCopyDAO();
	
	// Constructeur par défaut
	public Copy () {}
	
	// Constructeur avec arguments
	public Copy(Player owner, Game game) {
		this.owner = owner;
		this.game = game;
	}
	
	// Getters et Setters
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	// Méthodes
	public boolean create () {
		return copyDAO.create(this);
	}
	
	public boolean update () {
		return copyDAO.update(this);
	}
	
	public int findIdByName(String username, String gameName, String versionName, String method) {
		return copyDAO.findIdByName(username, gameName, versionName, method);
	}
	
	public Copy find () {
		return copyDAO.find(this);
	}
	
	public static ArrayList<Copy> getAll (String str){
		return copyDAO.getAll(str, "");
	}
	
	public void ReleaseCopy() {
        // TODO implement here
    }

	public void Borrow() {
        // TODO implement here
    }
    public boolean IsAvailable() {
        Copy copy = copyDAO.find(this);
        if(copy != null) 
        	return true;
        else
        	return false;
    }   
    public Copy findById (int i) {
    	return copyDAO.findById(i);
    }
    
}

package be.veltri.POJO;

import java.io.Serializable;

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
	public void ReleaseCopy() {
        // TODO implement here
    }

	public void Borrow() {
        // TODO implement here
    }
    public boolean IsAvailable() {
        // TODO implement here
        return false;
    }
}

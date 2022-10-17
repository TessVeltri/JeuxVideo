package be.veltri.POJO;

import java.io.Serializable;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Message implements Serializable{
	private static final long serialVersionUID = -3612908683506989354L;
	private String textMessage;
    private Player player;
    
    private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Message> messageDAO = dao.getMessageDAO();
    
    // Constructeur par défaut
	public Message() {}

	// Constructeur avec arguments
	public Message(String textMessage, Player player) {
		this.textMessage = textMessage;
		this.player = player;
	}

	// Getters et Setters
	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	// Méthodes
	
}

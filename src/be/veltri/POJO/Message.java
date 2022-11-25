package be.veltri.POJO;

import java.io.Serializable;
import java.util.ArrayList;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Message implements Serializable {
	private static final long serialVersionUID = -3612908683506989354L;
	private String textMessage;
	private boolean read;
	private User sender;
	private User receiver;

	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Message> messageDAO = dao.getMessageDAO();

	// Constructeur par défaut
	public Message() {}

	// Constructeur avec arguments
	public Message(String textMessage, boolean read, User sender, User receiver) {
		this.textMessage = textMessage;
		this.read = read;
		this.sender = sender;
		this.receiver = receiver;
	}

	// Getters et Setters
	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	// Méthodes
	public boolean create () {
		return messageDAO.create(this);
	}
	
	public boolean update() {
		return messageDAO.update(this);
	}
	
	public static ArrayList<Message> getAll (String str){
		return messageDAO.getAll(str, "", "", "");
	}
	
}

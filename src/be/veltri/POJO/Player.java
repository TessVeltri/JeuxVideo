package be.veltri.POJO;

import java.io.Serializable;
import java.time.LocalDate;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Player extends User implements Serializable{
	private static final long serialVersionUID = -8309693069723806111L;
	private String pseudo;
	private LocalDate dateOfBirth;
	private LocalDate dateInscription;
	private int balance;
	
	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Player> playerDAO = dao.getPlayerDAO();

	// Constructeur par défaut
	public Player() {
	}

	// Constructeur avec arguments
	public Player(String username, String password, String pseudo, LocalDate dateOfBirth, LocalDate dateInscription,
			int balance) {
		super(username, password);
		this.pseudo = pseudo;
		this.dateOfBirth = dateOfBirth;
		this.dateInscription = dateInscription;
		this.balance = balance;
	}

	// Getters et Setters
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// Méthodes
	
	public boolean create () {
		return playerDAO.create(this);
	}
	public Player find () {
		Player player = playerDAO.find(this);
		return player;
	}

	public boolean LocationAllowed() {
		// TODO implement here
		return false;
	}

	@Override
	public String toString() {
		return "Player [username= " + super.getUsername() + ", password=" + super.getPassword() + ", pseudo=" + pseudo
				+ ", dateOfBirth=" + dateOfBirth + ", dateInscription=" + dateInscription + ", balance=" + balance
				+ "]";
	}

	public void AddBirthdayBonus() {
		// TODO implement here

	}

}

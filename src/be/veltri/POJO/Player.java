package be.veltri.POJO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Player extends User implements Serializable {
	private static final long serialVersionUID = -8309693069723806111L;
	private String pseudo;
	private LocalDate dateOfBirth;
	private LocalDate dateInscription;
	private int balance;
	private boolean checkBirthDay;
	private ArrayList<Copy> lstCopy;
	private ArrayList<Location> lstLocationOwner;
	private ArrayList<Location> lstLocationBorrower;
	private ArrayList<Reservation> lstReservation;
	private ArrayList<Rating> lstRating;
	private ArrayList<Message> lstMessageSender;
	private ArrayList<Message> lstMessageReceiver;

	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Player> playerDAO = dao.getPlayerDAO();

	// Constructeur par défaut
	public Player() {
	}

	// Constructeur avec arguments

	public Player(String username, String password, String pseudo, LocalDate dateOfBirth, LocalDate dateInscription,
			int balance, boolean checkBirthDay, ArrayList<Copy> lstCopy, ArrayList<Location> lstLocationOwner,
			ArrayList<Location> lstLocationBorrower, ArrayList<Reservation> lstReservation, ArrayList<Rating> lstRating,
			ArrayList<Message> lstMessageSender, ArrayList<Message> lstMessageReceiver) {
		super(username, password);
		this.pseudo = pseudo;
		this.dateOfBirth = dateOfBirth;
		this.dateInscription = dateInscription;
		this.balance = balance;
		this.checkBirthDay = checkBirthDay;
		this.lstCopy = lstCopy;
		this.lstLocationBorrower = lstLocationBorrower;
		this.lstLocationOwner = lstLocationOwner;
		this.lstMessageReceiver = lstMessageReceiver;
		this.lstMessageSender = lstMessageSender;
		this.lstRating = lstRating;
		this.lstReservation = lstReservation;
	}

	public Player(String username, String password, String pseudo, LocalDate dateOfBirth, LocalDate dateInscription,
			int balance, boolean checkBirthDay) {
		super(username, password);
		this.pseudo = pseudo;
		this.dateOfBirth = dateOfBirth;
		this.dateInscription = dateInscription;
		this.balance = balance;
		this.checkBirthDay = checkBirthDay;
		this.lstCopy = new ArrayList<>();
		this.lstLocationBorrower = new ArrayList<>();
		this.lstLocationOwner = new ArrayList<>();
		this.lstMessageReceiver = new ArrayList<>();
		this.lstMessageSender = new ArrayList<>();
		this.lstRating = new ArrayList<>();
		this.lstReservation = new ArrayList<>();
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

	public boolean isCheckBirthDay() {
		return checkBirthDay;
	}

	public void setCheckBirthDay(boolean checkBirthDay) {
		this.checkBirthDay = checkBirthDay;
	}

	public ArrayList<Copy> getLstCopy() {
		return lstCopy;
	}

	public void setLstCopy(ArrayList<Copy> lstCopy) {
		this.lstCopy = lstCopy;
	}

	public ArrayList<Location> getLstLocationOwner() {
		return lstLocationOwner;
	}

	public void setLstLocationOwner(ArrayList<Location> lstLocationOwner) {
		this.lstLocationOwner = lstLocationOwner;
	}

	public ArrayList<Location> getLstLocationBorrower() {
		return lstLocationBorrower;
	}

	public void setLstLocationBorrower(ArrayList<Location> lstLocationBorrower) {
		this.lstLocationBorrower = lstLocationBorrower;
	}

	public ArrayList<Reservation> getLstReservation() {
		return lstReservation;
	}

	public void setLstReservation(ArrayList<Reservation> lstReservation) {
		this.lstReservation = lstReservation;
	}

	public ArrayList<Rating> getLstRating() {
		return lstRating;
	}

	public void setLstRating(ArrayList<Rating> lstRating) {
		this.lstRating = lstRating;
	}

	public ArrayList<Message> getLstMessageSender() {
		return lstMessageSender;
	}

	public void setLstMessageSender(ArrayList<Message> lstMessageSender) {
		this.lstMessageSender = lstMessageSender;
	}

	public ArrayList<Message> getLstMessageReceiver() {
		return lstMessageReceiver;
	}

	public void setLstMessageReceiver(ArrayList<Message> lstMessageReceiver) {
		this.lstMessageReceiver = lstMessageReceiver;
	}

	// Méthodes
	public boolean create() {
		return playerDAO.create(this);
	}

	public boolean update() {
		return playerDAO.update(this);
	}

	public Player find() {
		return playerDAO.find(this);
	}

	public int findIdByName() {
		return playerDAO.findIdByName(this, null, "");
	}

	public Player findById(int i) {
		return playerDAO.findById(i);
	}

	public boolean locationAllowed() {
		if (this.getBalance() <= 0)
			return false;
		else
			return true;
	}

	public void addBirthdayBonus() {
		if (LocalDate.now().getDayOfMonth() == (this.getDateOfBirth().getDayOfMonth())
				&& LocalDate.now().getMonth() == (this.getDateOfBirth().getMonth())) {
			if (this.isCheckBirthDay() == false) {
				this.setBalance(this.getBalance() + 2);
				this.setCheckBirthDay(true);
				JOptionPane.showMessageDialog(null, "Happy birthday !!!");
			}
		} else {
			this.setCheckBirthDay(false);
		}
	}

	public int getAge() {
		return Period.between(this.getDateOfBirth(), LocalDate.now()).getYears();
	}

}

package be.veltri.POJO;

import java.io.Serializable;
import java.time.LocalDate;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 5780975148202725087L;
	private LocalDate dateReservation;
    private String statusReservation;
    private Player borrower;
    private Game game;
    
    private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Reservation> reservationDAO = dao.getReservationDAO();
    
    // Constructeur par défaut 
    public Reservation () {}
	
    // Constructeur avec arguments
	public Reservation(LocalDate dateReservation, String statusReservation, Player borrower, Game game) {
		this.dateReservation = dateReservation;
		this.statusReservation = statusReservation;
		this.borrower = borrower;
		this.game = game;
	}

	// Getters et Setters
	public LocalDate getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(LocalDate dateReservation) {
		this.dateReservation = dateReservation;
	}

	public String getStatusReservation() {
		return statusReservation;
	}

	public void setStatusReservation(String statusReservation) {
		this.statusReservation = statusReservation;
	}

	public Player getBorrower() {
		return borrower;
	}

	public void setBorrower(Player borrower) {
		this.borrower = borrower;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
    
	// Méthodes
	
    
    
}

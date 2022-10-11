package be.veltri.POJO;

import java.time.LocalDate;

public class Reservation {
	private LocalDate dateReservation;
    private String statusReservation;
    private Player borrower;
    private Games game;
    
    // Constructeur par défaut 
    public Reservation () {}
	
    // Constructeur avec arguments
	public Reservation(LocalDate dateReservation, String statusReservation, Player borrower, Games game) {
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

	public Games getGame() {
		return game;
	}

	public void setGame(Games game) {
		this.game = game;
	}
    
	// Méthodes
	
    
    
}

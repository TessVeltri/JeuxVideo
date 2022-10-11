package be.veltri.POJO;

import java.time.LocalDate;

public class Location {
	private LocalDate dateBeginLocation;
    private LocalDate dateEndLocation;
    private int totalUnits;
    private boolean onGoing;
    private Player owner; 
    private Player borrower;
    private Copy copy;
    
    // Constructeur par défaut
    public Location() {}

    // Constructeur avec arguments
	public Location(LocalDate dateBeginLocation, LocalDate dateEndLocation, int totalUnits, boolean onGoing,
			Player owner, Player borrower, Copy copy) {
		this.dateBeginLocation = dateBeginLocation;
		this.dateEndLocation = dateEndLocation;
		this.totalUnits = totalUnits;
		this.onGoing = onGoing;
		this.owner = owner;
		this.borrower = borrower;
		this.copy = copy;
	}
    
    // Getters et Setters
    public LocalDate getDateBeginLocation() {
		return dateBeginLocation;
	}

	public void setDateBeginLocation(LocalDate dateBeginLocation) {
		this.dateBeginLocation = dateBeginLocation;
	}

	public LocalDate getDateEndLocation() {
		return dateEndLocation;
	}

	public void setDateEndLocation(LocalDate dateEndLocation) {
		this.dateEndLocation = dateEndLocation;
	}

	public int getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(int totalUnits) {
		this.totalUnits = totalUnits;
	}

	public boolean isOnGoing() {
		return onGoing;
	}

	public void setOnGoing(boolean onGoing) {
		this.onGoing = onGoing;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Player getBorrower() {
		return borrower;
	}

	public void setBorrower(Player borrower) {
		this.borrower = borrower;
	}

	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}

	// Méthodes
	public void CalculateBalance() {
        // TODO implement here
    }
	
	public void EndLocation() {
        // TODO implement here
    }
}

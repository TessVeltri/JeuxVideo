package be.veltri.POJO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Location implements Serializable {
	private static final long serialVersionUID = -2602965353131941195L;
	private LocalDate dateBeginLocation;
	private LocalDate dateEndLocation;
	private int totalUnits;
	private boolean onGoing;
	private Player owner;
	private Player borrower;
	private Copy copy;

	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Location> locationDAO = dao.getLocationDAO();

	// Constructeur par défaut
	public Location() {
	}

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

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	// Méthodes
	public boolean create () {
		return locationDAO.create(this);
	}
	
	public int calculateBalance() {
		int total = 0;
		int daysLoc = (int) this.getDateBeginLocation().until(this.getDateEndLocation(), ChronoUnit.DAYS);
		int weekLoc = daysLoc/7;
		int units = this.getCopy().getGame().getUnits();
		int days = (int) this.getDateEndLocation().until(LocalDate.now(), ChronoUnit.DAYS);
		if (days>0) {
			int week = days/7;
			int rest = days%7;
			if (rest==0)
				total = (units*week) + (days*5) + (units*weekLoc);
			else 
				total = units + (units*week) + (days*5) + (units*weekLoc);
		} else
			total = units*weekLoc;
		return total;
	}
	
	public Location find () {
		return locationDAO.find(this);
	}

	public boolean endLocation() {
		return locationDAO.update(this);
	}
	
	public static ArrayList<Location> getAll (String str){
		return locationDAO.getAll(str, "");
	}
}

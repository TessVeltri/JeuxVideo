package be.veltri.POJO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class UnitsHistory implements Serializable{
	private static final long serialVersionUID = 9090057972360040406L;
	private LocalDate dateChange;
	private int units;
	private Game game;
	
	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<UnitsHistory> unitsHistoryDAO = dao.getUnitsHistoryDAO();
	
	// Constructeur par défaut
	public UnitsHistory() {}
	
	// Constructeur avec arguments
	public UnitsHistory(LocalDate dateChange, int units, Game game) {
		super();
		this.dateChange = dateChange;
		this.units = units;
		this.game = game;
	}
	// Getters et Setters
	public LocalDate getDateChange() {
		return dateChange;
	}
	public void setDateChange(LocalDate dateChange) {
		this.dateChange = dateChange;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	// Méthodes
	public boolean create() {
		return unitsHistoryDAO.create(this);
	}
	
	public static ArrayList<UnitsHistory> getAll(Game game) {
		return unitsHistoryDAO.getAll(game, null);
	}

}

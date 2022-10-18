package be.veltri.POJO;

import java.io.Serializable;
import java.util.ArrayList;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Game implements Serializable{
	private static final long serialVersionUID = -1601507844842915600L;
	private String nameGame;
	private int units;
	private String nameConsole;
	private String nameVersion;
	
	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Game> gameDAO = dao.getGameDAO();

	// Constructeur par défaut
	public Game() {}

	// Constructeur avec arguments
	public Game(String nameGame, int units, String nameConsole, String nameVersion) {
		this.nameGame = nameGame;
		this.units = units;
		this.nameConsole = nameConsole;
		this.nameVersion = nameVersion;
	}

	// Getters et Setters
	public String getNameGame() {
		return nameGame;
	}

	public void setNameGame(String nameGame) {
		this.nameGame = nameGame;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public String getNameConsole() {
		return nameConsole;
	}

	public void setNameConsole(String nameConsole) {
		this.nameConsole = nameConsole;
	}

	public String getNameVersion() {
		return nameVersion;
	}

	public void setNameVersion(String nameVersion) {
		this.nameVersion = nameVersion;
	}

	// Méthodes
	public boolean create() {
		return gameDAO.create(this);
	}
	public Game find () {
		return gameDAO.find(this);
	}
	public int findIdByName () {
		return gameDAO.findIdByName(getNameGame(), getNameVersion());
	}
	
	public static ArrayList<String> getAll (String str1, String str2){
		return gameDAO.getAll(str1, str2);
	}
	
	public Copy CopyAvailable() {
		return null;
	}

	public void SelectBooking() {
		// TODO implement here
	}
}

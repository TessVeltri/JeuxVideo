package be.veltri.POJO;

public class Games {
	private String nameGame;
	private int units;
	private String nameConsole;
	private String nameVersion;

	// Constructeur par défaut
	public Games() {}

	// Constructeur avec arguments
	public Games(String nameGame, int units, String nameConsole, String nameVersion) {
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
	public Copy CopyAvailable() {
		return null;
	}

	public void SelectBooking() {
		// TODO implement here
	}
}

package be.veltri.POJO;

import java.io.Serializable;
import java.time.LocalDate;
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
	
	public boolean update () {
		return gameDAO.update(this);
	}
	
	public Game find () {
		return gameDAO.find(this);
	}
	
	public int findIdByName () {
		return gameDAO.findIdByName(getNameGame(), getNameVersion(), "", "");
	}
	
	public static ArrayList<String> getAllName (String str1, String str2){
		return gameDAO.getAllName(str1, str2);
	}
	
	public static ArrayList<Game> getAll (Game game){
		return gameDAO.getAll(game, null);
	}
	
	public Game findById(int i) {
		return gameDAO.findById(i);
	}
	
	// TODO changer quand liste créée
	public Copy copyAvailable(Copy copy) {
		return copy.find();
	}

	public Reservation selectBooking() {
		ArrayList<Reservation> allRes = Reservation.getAll(null, this);
		
		ArrayList<Reservation> lstTmpUnits = new ArrayList<Reservation>();
		ArrayList<Reservation> lstTmpDate = new ArrayList<Reservation>();
		ArrayList<Reservation> lstTmpInscription = new ArrayList<Reservation>();
		ArrayList<Reservation> lstTmpAge = new ArrayList<Reservation>();
		
		int unitsTmp = 0;
		// borrower with the most units
		for (Reservation r : allRes) {
			if (r.getBorrower().getBalance() > unitsTmp) {
				unitsTmp = r.getBorrower().getBalance();
			}
		}
		for (Reservation r : allRes) {
			if (r.getBorrower().getBalance() == unitsTmp) {
				lstTmpUnits.add(r);
			}
		}
		if (lstTmpUnits.size() == 1) {
			return lstTmpUnits.get(0);
		} else {
			// oldest reservation 
			LocalDate dateTmp = LocalDate.now();
			for (Reservation r : allRes) {
				if (r.getDateReservation().isBefore(dateTmp)) {
					dateTmp = r.getDateReservation();
				}
			}
			for (Reservation r : allRes) {
				if (r.getDateReservation().isEqual(dateTmp)) {
					lstTmpDate.add(r);
				}
			}
			if (lstTmpDate.size() == 1) {
				return lstTmpDate.get(0);
			} else {
				// oldest borrower's inscription 
				LocalDate dateInsc = LocalDate.now();
				for (Reservation r : allRes) {
					if (r.getBorrower().getDateInscription().isBefore(dateInsc)) {
						dateTmp = r.getBorrower().getDateInscription();
					}
				}
				for (Reservation r : allRes) {
					if (r.getBorrower().getDateInscription().isEqual(dateInsc)) {
						lstTmpInscription.add(r);
					}
				}
				if (lstTmpInscription.size() == 1) {
					return lstTmpInscription.get(0);
				} else {
					// oldest borrower
					int ageTmp = 0;
					for (Reservation r : allRes) {
						if (r.getBorrower().getAge()>ageTmp) {
							ageTmp = r.getBorrower().getAge();
						}
					}
					for (Reservation r : allRes) {
						if (r.getBorrower().getAge() == ageTmp) {
							lstTmpAge.add(r);
						}
					}
					if (lstTmpAge.size() == 1) {
						return lstTmpAge.get(0);
					} else {
						return allRes.get(0);
					}
				}
			}
		}
	}
}

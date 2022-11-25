package be.veltri.POJO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Copy implements Serializable {
	private static final long serialVersionUID = -5295891430835243265L;
	private Player owner;
	private Game game;

	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Copy> copyDAO = dao.getCopyDAO();

	// Constructeur par défaut
	public Copy() {
	}

	// Constructeur avec arguments
	public Copy(Player owner, Game game) {
		this.owner = owner;
		this.game = game;
	}

	// Getters et Setters
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	// Méthodes
	public boolean create() {
		return copyDAO.create(this);
	}

	public boolean update() {
		return copyDAO.update(this);
	}

	// str1 = username, str2 = gameName, str3 = versionName, str4 = method
	public int findIdByName(String str1, String str2, String str3, String str4) {
		return copyDAO.findIdByName(str1, str2, str3, str4);
	}

	public Copy find() {
		return copyDAO.find(this);
	}

	public static ArrayList<Copy> getAll(String str) {
		return copyDAO.getAll(str, "", "", "");
	}

	public boolean releaseCopy() {
		return this.update();
	}

	public boolean borrow() {
		Reservation res = this.getGame().selectBooking();
		if (res != null) {
			res.setStatusReservation(ReservationStatus.Validated.toString());
			boolean updateRes = res.update();
			Copy copy = this.find();
			Location loc = new Location(LocalDate.now(), LocalDate.now().plusWeeks(1), copy.getGame().getUnits(), true,
					copy.getOwner(), res.getBorrower(), copy);
			boolean createLoc = loc.create();
			boolean updateCopy = copy.releaseCopy();
			if (updateRes && createLoc && updateCopy) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean isAvailable() {
		Copy copy = copyDAO.find(this);
		if (copy != null)
			return true;
		else
			return false;
	}

	public Copy findById(int i) {
		return copyDAO.findById(i);
	}

}

package be.veltri.DAO;

import be.veltri.POJO.*;

public abstract class AbstractDAOFactory {
	public static final int DAO_FACTORY = 0;
	public static final int XML_DAO_FACTORY = 1;

	public abstract DAO<Admin> getAdminDAO();

	public abstract DAO<Copy> getCopyDAO();

	public abstract DAO<Game> getGameDAO();

	public abstract DAO<Location> getLocationDAO();

	public abstract DAO<Message> getMessageDAO();

	public abstract DAO<Player> getPlayerDAO();

	public abstract DAO<Rating> getRatingDAO();

	public abstract DAO<Reservation> getReservationDAO();

	public abstract DAO<User> getUserDAO();
	
	public abstract DAO<UnitsHistory> getUnitsHistoryDAO();

	public static AbstractDAOFactory getFactory(int type) {
		switch (type) {
		case DAO_FACTORY:
			return new DAOFactory();
		default:
			return null;
		}
	}

}

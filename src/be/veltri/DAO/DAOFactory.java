package be.veltri.DAO;

import java.sql.Connection;

import be.veltri.DB.JeuxVideoConnection;
import be.veltri.POJO.*;

public class DAOFactory extends AbstractDAOFactory {
	protected static final Connection conn = JeuxVideoConnection.getInstance();

	@Override
	public DAO<Admin> getAdminDAO() {
		return new AdminDAO(conn);
	}

	@Override
	public DAO<Copy> getCopyDAO() {
		return new CopyDAO(conn);
	}

	@Override
	public DAO<Game> getGameDAO() {
		return new GameDAO(conn);
	}

	@Override
	public DAO<Location> getLocationDAO() {
		return new LocationDAO(conn);
	}

	@Override
	public DAO<Message> getMessageDAO() {
		return new MessageDAO(conn);
	}

	@Override
	public DAO<Player> getPlayerDAO() {
		return new PlayerDAO(conn);
	}

	@Override
	public DAO<Rating> getRatingDAO() {
		return new RatingDAO(conn);
	}

	@Override
	public DAO<Reservation> getReservationDAO() {
		return new ReservationDAO(conn);
	}

	@Override
	public DAO<User> getUserDAO() {
		return new UserDAO(conn);
	}
}

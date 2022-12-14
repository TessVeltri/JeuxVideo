package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Admin;
import be.veltri.POJO.Copy;
import be.veltri.POJO.Location;
import be.veltri.POJO.Message;
import be.veltri.POJO.Player;
import be.veltri.POJO.Rating;
import be.veltri.POJO.Reservation;
import be.veltri.POJO.User;

public class UserDAO extends DAO<User> {

	public UserDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(User obj) {
		return false;
	}

	@Override
	public boolean delete(User obj) {
		return false;
	}

	@Override
	public boolean update(User obj) {
		return false;
	}

	@Override
	public User find(User obj) {
		User user = null;
		ArrayList<Copy> lstCopy = new ArrayList<>();
		ArrayList<Location> lstLocBorrower = new ArrayList<>();
		ArrayList<Reservation> lstReservation = new ArrayList<>();
		ArrayList<Rating> lstRating = new ArrayList<>();
		ArrayList<Message> lstMessageReceiver = new ArrayList<>();
		if (obj.getPassword()!= null) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT userName, pseudo, password, dateBirth, dateInscription, "
								+ "balance, discriminator, checkBirthDay FROM User WHERE userName = '" + obj.getUsername() + "' "
								+ "AND password = '" + obj.getPassword() + "'");

				if (result.first())
					if (result.getString("discriminator").equals("Player")) {
						user = new Player(result.getString("userName"), result.getString("password"),
								result.getString("pseudo"), result.getDate("dateBirth").toLocalDate(),
								result.getDate("dateInscription").toLocalDate(), result.getInt("balance"),
								result.getBoolean("checkBirthDay"));
						lstCopy = Copy.getAll((Player) user, null);
						lstLocBorrower = Location.getAll((Player)user, null);
						lstReservation = Reservation.getAll((Player)user, null);
						lstRating = Rating.getAll((Player)user);
						lstMessageReceiver = Message.getAll(user);
						((Player)user).setLstCopy(lstCopy);
						((Player)user).setLstLocationBorrower(lstLocBorrower);
						((Player)user).setLstReservation(lstReservation);
						((Player)user).setLstRating(lstRating);
						((Player)user).setLstMessageReceiver(lstMessageReceiver);
						
					} else if (result.getString("discriminator").equals("Admin")) {
						user = new Admin(result.getString("userName"), result.getString("password"));
						lstMessageReceiver = Message.getAll(user);
						((Admin)user).setLstMessage(lstMessageReceiver);
					}
				return user;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT userName, pseudo, password, dateBirth, dateInscription, "
								+ "balance, discriminator, checkBirthDay FROM User WHERE userName = '" + obj.getUsername() + "'");

				if (result.first())
					if (result.getString("discriminator").equals("Player")) {
						user = new Player(result.getString("userName"), result.getString("password"),
								result.getString("pseudo"), result.getDate("dateBirth").toLocalDate(),
								result.getDate("dateInscription").toLocalDate(), result.getInt("balance"),
								result.getBoolean("checkBirthDay"));
						lstCopy = Copy.getAll((Player) user, null);
						lstLocBorrower = Location.getAll((Player)user, null);
						lstReservation = Reservation.getAll((Player)user, null);
						lstRating = Rating.getAll((Player)user);
						lstMessageReceiver = Message.getAll(user);
						((Player)user).setLstCopy(lstCopy);
						((Player)user).setLstLocationBorrower(lstLocBorrower);
						((Player)user).setLstReservation(lstReservation);
						((Player)user).setLstRating(lstRating);
						((Player)user).setLstMessageReceiver(lstMessageReceiver);
					} else if (result.getString("discriminator").equals("Admin")) {
						user = new Admin(result.getString("userName"), result.getString("password"));
						lstMessageReceiver = Message.getAll(user);
						((Admin)user).setLstMessage(lstMessageReceiver);
					}
				return user;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}

	@Override
	public ArrayList<String> getAllName(String str1, String str2) {
		return null;
	}
	
	// o1 = player, str = discriminator
	@Override
	public int findIdByName(Object o1, Object o2, String str) {
		int id = 0;
		if (str.equals("Admin")) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idUser FROM User WHERE discriminator = 'Admin'");
				if (result.first()) {
					id = result.getInt(1);
				}
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		} else {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idUser FROM User WHERE userName = '" + ((Player)o1).getUsername() + "'");
				if (result.first()) {
					id = result.getInt(1);
				}
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}
	}

	@Override
	public int returnUnits(String name) {
		return 0;
	}

	@Override
	public String find(int i, String str) {
		return null;
	}

	@Override
	public ArrayList<User> getAll(Object o1, Object o2) {
		return null;
	}

	@Override
	public User findById(int i) {
		return null;
	}

}

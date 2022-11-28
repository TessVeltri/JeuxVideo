package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Copy;
import be.veltri.POJO.Player;

public class PlayerDAO extends DAO<Player> {

	public PlayerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Player obj) {
		try {
			this.connect.createStatement()
					.executeUpdate("INSERT INTO User(userName, pseudo, password, dateBirth, dateInscription, "
							+ "balance, discriminator, checkBirthDay) Values('" + obj.getUsername() + "', '"
							+ obj.getPseudo() + "', '" + obj.getPassword() + "', '" + obj.getDateOfBirth() + "', '"
							+ obj.getDateInscription() + "', '" + obj.getBalance() + "', 'Player', '"
							+ obj.isCheckBirthDay() + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Player obj) {
		return false;
	}

	@Override
	public boolean update(Player obj) {
		try {
			int result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeUpdate("UPDATE User SET balance = " + obj.getBalance() + ", checkBirthDay = '"
							+ obj.isCheckBirthDay() + "' WHERE userName = '" + obj.getUsername() + "'");
			if (result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Player find(Player obj) {
		Player player = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT userName, pseudo, password, dateBirth, dateInscription, "
							+ "balance, discriminator, checkBirthDay FROM User WHERE userName = '" + obj.getUsername()
							+ "'");
			if (result.first())
				player = new Player(result.getString("userName"), result.getString("password"),
						result.getString("pseudo"), result.getDate("dateBirth").toLocalDate(),
						result.getDate("dateInscription").toLocalDate(), result.getInt("balance"),
						result.getBoolean("checkBirthDay"));
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<String> getAllName(String str1, String str2) {
		return null;
	}

	// o1 = player
	@Override
	public int findIdByName(Object o1, Object o2, String str) {
		int id = 0;
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

	@Override
	public int returnUnits(String name) {
		return 0;
	}

	@Override
	public String find(int i, String str) {
		return null;
	}

	@Override
	public ArrayList<Player> getAll(Object o1, Object o2) {
		return null;
	}

	@Override
	public Player findById(int i) {
		Player player = new Player();
		ArrayList<Copy> lstCopy = new ArrayList<>();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT username, password, pseudo, dateBirth, dateInscription, balance, checkBirthDay "
									+ "FROM User WHERE idUser = '" + i + "'");
			if (result.first()) {
				player = new Player(result.getString("username"), result.getString("password"),
						result.getString("pseudo"), result.getDate("dateBirth").toLocalDate(),
						result.getDate("dateInscription").toLocalDate(), result.getInt("balance"),
						result.getBoolean("checkBirthDay"));
				lstCopy = Copy.getAll(player);
				player.setLstCopy(lstCopy);
			}
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}

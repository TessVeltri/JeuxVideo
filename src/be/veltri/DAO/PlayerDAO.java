package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;

import be.veltri.POJO.Player;
import be.veltri.POJO.User;

public class PlayerDAO extends DAO<Player> {

	public PlayerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Player obj) {
		try {
			this.connect.createStatement()
					.executeUpdate("INSERT INTO User(userName, pseudo, password, dateBirth, dateInscription, "
							+ "balance, discriminator) Values('" + obj.getUsername() + "', '" + obj.getPseudo() + "', '"
							+ obj.getPassword() + "', '" + obj.getDateOfBirth() + "', '" + obj.getDateInscription()
							+ "', '10', 'Player')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Player obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Player obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player find(Player obj) {
		Player player = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT userName, pseudo, password, dateBirth, dateInscription, "
							+ "balance, discriminator FROM User WHERE userName = '" + obj.getUsername() + "'");
			if (result.first())
				player = new Player(result.getString("userName"), result.getString("password"),
						result.getString("pseudo"), result.getDate("dateBirth").toLocalDate(),
						result.getDate("dateInscription").toLocalDate(), result.getInt("balance"));
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<String> getAllName(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findIdByName(String str1, String str2) {
		int id = 0;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT idUser FROM User WHERE userName = '" + str1 + "'");
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String find(int i, String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Player> getAll(String str1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player findById(int i) {
		Player player = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT username, password, pseudo, dateBirth, dateInscription, balance "
							+ "FROM User WHERE idUser = '" + i + "'");
			if (result.first()) {
				player = new Player(result.getString("username"), result.getString("password"),
						result.getString("pseudo"), result.getDate("dateBirth").toLocalDate(),
						result.getDate("dateInscription").toLocalDate(), result.getInt("balance"));
			}
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}

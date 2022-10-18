package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Admin;
import be.veltri.POJO.Player;
import be.veltri.POJO.User;

public class UserDAO extends DAO<User> {

	public UserDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User find(User obj) {
		User user = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT userName, pseudo, password, dateBirth, dateInscription, "
							+ "balance, discriminator FROM User WHERE userName = '" + obj.getUsername() + "' "
							+ "AND password = '" + obj.getPassword() + "'");

			if (result.first())
				if (result.getString("discriminator").equals("Player")) {
					user = new Player(result.getString("userName"), result.getString("password"),
							result.getString("pseudo"), result.getDate("dateBirth").toLocalDate(),
							result.getDate("dateInscription").toLocalDate(), result.getInt("balance"));
				} else if (result.getString("discriminator").equals("Admin")) {
					user = new Admin(result.getString("userName"), result.getString("password"));
				}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<String> getAll(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findIdByName(String str1, String str2) {
		int id = 0;
		if (str1.equals("Admin")) {
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
		} else if (str1.equals("Player")) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idUser FROM User WHERE userName = '" + str2 + "'");
				if (result.first()) {
					id = result.getInt(1);
				}
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		} else {
			return -1;
		}
	}

	@Override
	public int returnUnits(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

}

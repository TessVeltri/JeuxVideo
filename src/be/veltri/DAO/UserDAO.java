package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}

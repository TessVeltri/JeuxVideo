package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

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

}

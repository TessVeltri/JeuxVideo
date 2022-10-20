package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Copy;
import be.veltri.POJO.Game;
import be.veltri.POJO.Player;

public class CopyDAO extends DAO<Copy> {

	public CopyDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Copy obj) {
		try {
			this.connect.createStatement()
					.executeUpdate("INSERT INTO Copy(available, idOwner, idGame) " + "Values('true', '"
							+ obj.getOwner().findIdByName() + "', '" + obj.getGame().findIdByName() + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Copy obj) {
		try {
			int result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeUpdate("UPDATE Copy SET available = CASE available "
							+ "WHEN 'true' THEN 'false' "
							+ "WHEN 'false' THEN 'true' "
							+ "END WHERE idCopy = '"
							+ findIdByName(obj.getOwner().getUsername(), obj.getGame().getNameGame(),
									obj.getGame().getNameVersion())+ "'");
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
	public Copy find(Copy obj) {
		Copy copy = null;
		Player player = new Player();
		Game game = new Game();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT available, idOwner, idGame FROM Copy WHERE idGame = '"
							+ obj.getGame().findIdByName() + "' AND available = 'true'");
			if (result.first()) {
				copy = new Copy(player.findById(result.getInt("idOwner")), game.findById(result.getInt("idGame")));
			}
			return copy;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// str1 = ownerName, str2 = gameName, str3 = gameVersion
	@Override
	public int findIdByName(String str1, String str2, String str3) {
		int id = 0;
		Player owner = new Player();
		owner.setUsername(str1);
		Game game = new Game();
		game.setNameGame(str2);
		game.setNameVersion(str3);
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT idCopy FROM Copy WHERE idOwner = '" + owner.findIdByName()
							+ "' AND idGame = '" + game.findIdByName() + "' AND available = 'true'");
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
	public ArrayList<Copy> getAll(String str1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllName(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Copy findById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}

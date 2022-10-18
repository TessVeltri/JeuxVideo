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
		// TODO Auto-generated method stub
		return false;
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
				copy = new Copy(player.findById(result.getInt("idOwner")),game.findById(result.getInt("idGame")));
			}
			return copy;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int findIdByName(String str1, String str2) {
		// TODO Auto-generated method stub
		return 0;
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

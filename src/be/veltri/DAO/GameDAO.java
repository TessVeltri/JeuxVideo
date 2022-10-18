package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Game;

public class GameDAO extends DAO<Game> {

	public GameDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Game obj) {
		try {
			this.connect.createStatement().executeUpdate(
					"INSERT INTO Game(gameName, units, idVersion) "
							+ "Values('" + obj.getNameGame() + "', '" + this.returnUnits(obj.getNameGame()) + "', '"
							+ this.findIdByName("Version", obj.getNameVersion()) + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Game obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Game obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Game find(Game obj) {
		Game game = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT gameName, units FROM Game WHERE gameName = '" + obj.getNameGame() + "' "
									+ "AND idVersion = '" + this.findIdByName("Version", obj.getNameVersion()) + "'");
			if (result.first()) {
				game = new Game(result.getString("gameName"), result.getInt("units"), obj.getNameConsole(), obj.getNameVersion());
			}
			return game;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<String> getAll(String str1, String str2) {
		ArrayList<String> all = new ArrayList<>();
		if (str1.equals("Game") && str2.equals("")) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT DISTINCT gameName FROM Game");
				while (result.next()) {
					all.add(result.getString("gameName"));
				}
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else if (str1.equals("Console") && str2.equals("")) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT DISTINCT consoleName FROM Console");
				while (result.next()) {
					all.add(result.getString("consoleName"));
				}
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else if (str1.equals("Version") && !str2.equals("")) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT DISTINCT versionName FROM Version WHERE idConsole = '"
								+ this.findIdByName("Console", str2) + "'");
				while (result.next()) {
					all.add(result.getString("versionName"));
				}
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return all;
	}

	@Override
	public int findIdByName(String str1, String str2) {
		int id = 0;
		if (str1.equals("Console")) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idConsole FROM Console WHERE consoleName = '" + str2 + "'");
				if (result.first()) {
					id = result.getInt(1);
				}
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		} else if (str1.equals("Version")){
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idVersion FROM Version WHERE versionName = '" + str2 + "'");
				if (result.first()) {
					id = result.getInt(1);
				}
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		} else if (!str1.equals("") && !str2.equals("")) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idGame FROM Game WHERE gameName = '" + str1 + "' AND idVersion = '"
								+ findIdByName("Version", str2) + "'");
				if (result.first()) {
					id = result.getInt(1);
				}
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		} else {
			return 0;
		}

	}

	@Override
	public int returnUnits(String name) {
		int units = 0;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT units FROM Game WHERE gameName = '" + name + "'");
			if (result.first()) {
				units = result.getInt(1);
			}
			return units;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}

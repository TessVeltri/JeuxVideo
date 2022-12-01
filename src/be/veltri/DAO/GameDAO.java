package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.veltri.POJO.Copy;
import be.veltri.POJO.Game;
import be.veltri.POJO.UnitsHistory;

public class GameDAO extends DAO<Game> {

	public GameDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Game obj) {
		if (obj.getNameVersion().equals("")) {
			obj.setNameVersion("XBOX 360");
		}
		if (obj.getNameGame().equals("")) {
			int idConsole = this.findIdByName(new Game(null, 0, obj.getNameConsole(), null), null, "");
			// Creation de version si console existante
			if (idConsole > 0) {
				try {
					this.connect.createStatement().executeUpdate("INSERT INTO Version(versionName, idConsole) "
							+ "Values('" + obj.getNameVersion() + "', '" + idConsole + "')");
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			// Creation de version et de console
			} else {
				try {
					Statement stmt = this.connect.createStatement();
					stmt.executeUpdate("INSERT INTO Console(consoleName) "
							+ "Values('" + obj.getNameConsole() + "')");
					ResultSet res = stmt.getGeneratedKeys();
					if(res.next())
					{
						int idC = res.getInt(1);
						try {
							this.connect.createStatement().executeUpdate("INSERT INTO Version(versionName, idConsole) "
									+ "Values('" + obj.getNameVersion() + "', '" + idC + "')");
							return true;
						} catch (SQLException e) {
							e.printStackTrace();
							return false;
						}
					}
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		// Creation de jeux
		} else {
			try {
				this.connect.createStatement()
						.executeUpdate("INSERT INTO Game(gameName, units, idVersion) " + "Values('" + obj.getNameGame()
								+ "', '" + this.returnUnits(obj.getNameGame()) + "', '"
								+ this.findIdByName(new Game(null, 0, null, obj.getNameVersion()),null , "") + "')");
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	@Override
	public boolean delete(Game obj) {
		return false;
	}

	@Override
	public boolean update(Game obj) {
		try {
			int result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeUpdate("UPDATE Game SET units = '" + obj.getUnits() + "' WHERE gameName = '" + obj.getNameGame()
							+ "' AND idVersion = '" + this.findIdByName(new Game(null, 0, null, obj.getNameVersion()),null , "") + "'");
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
	public Game find(Game obj) {
		Game game = null;
		ArrayList<Copy> lstCopy = new ArrayList<>();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT gameName, units FROM Game WHERE gameName = '" + obj.getNameGame() + "' "
							+ "AND idVersion = '" + this.findIdByName(new Game(null, 0, null, obj.getNameVersion()),null , "") + "'");
			if (result.first()) {
				game = new Game(result.getString("gameName"), result.getInt("units"), obj.getNameConsole(),
						obj.getNameVersion());
				lstCopy = Copy.getAll(null, game);
				game.setLstCopy(lstCopy);
			}
			return game;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// str1 = "Game/Console/Version", str2 = consoleName
	@Override
	public ArrayList<String> getAllName(String str1, String str2) {
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
								+ this.findIdByName(new Game (null, 0, str2, null),null, "") + "'");
				while (result.next()) {
					all.add(result.getString("versionName"));
				}
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else if (str1.equals("Version") && str2.equals("")) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT DISTINCT versionName FROM Version");
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

	// o1 = game
	@Override
	public int findIdByName(Object o1, Object o2, String str) {
		Game game = (Game) o1;
		int id = 0;
		if (game.getNameConsole()!= null && game.getNameGame() == null) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idConsole FROM Console WHERE consoleName = '" + game.getNameConsole() + "'");
				if (result.first()) {
					id = result.getInt(1);
				}
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		} else if (game.getNameVersion() != null && game.getNameGame() == null) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idVersion FROM Version WHERE versionName = '" + game.getNameVersion() + "'");
				if (result.first()) {
					id = result.getInt(1);
				}
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		} else if (game.getNameGame() != null) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idGame FROM Game WHERE gameName = '" + game.getNameGame() + "' AND idVersion = '"
								+ findIdByName(new Game(null, 0, null, game.getNameVersion()), null, "") + "'");
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
		int units = 1;
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
			return 1;
		}
	}

	// o1 = game
	@Override
	public ArrayList<Game> getAll(Object o1, Object o2) {
		ArrayList<Game> all = new ArrayList<>();
		if (o1 == null) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT gameName, units, idVersion FROM Game");
				while (result.next()) {
					all.add(new Game(result.getString("gameName"), result.getInt("units"),
							this.find(result.getInt("idVersion"), "Console"),
							this.find(result.getInt("idVersion"), "")));
				}
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT gameName, units, idVersion FROM Game " + "WHERE idVersion = '"
								+ this.findIdByName(new Game (null, 0, null, ((Game) o1).getNameVersion()),null, "") + "'");
				while (result.next()) {
					all.add(new Game(result.getString("gameName"), result.getInt("units"),
							this.find(result.getInt("idVersion"), "Console"),
							this.find(result.getInt("idVersion"), "")));
				}
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	// i = idVersion, str = "Console/''"
	@Override
	public String find(int i, String str) {
		String find = "";
		if (str.equals("")) {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT versionName FROM Version WHERE idVersion = '" + i + "'");
				if (result.first()) {
					find = result.getString(1);
				}
				return find;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT consoleName FROM Console "
								+ "INNER JOIN Version ON Version.idConsole = Console.idConsole " + "WHERE idVersion = '"
								+ i + "'");
				if (result.first()) {
					find = result.getString(1);
				}
				return find;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

	}

	@Override
	public Game findById(int i) {
		Game game = null;
		ArrayList<Copy> lstCopy = new ArrayList<>();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT gameName, units, idVersion FROM Game WHERE idGame = '" + i + "'");
			if (result.first()) {
				game = new Game(result.getString("gameName"), result.getInt("units"),
						this.find(result.getInt("idVersion"), "Console"), this.find(result.getInt("idVersion"), ""));
			}
			lstCopy = Copy.getAll(null, game);
			game.setLstCopy(lstCopy);
			return game;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}

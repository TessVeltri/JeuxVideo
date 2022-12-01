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
		return false;
	}

	@Override
	public boolean update(Copy obj) {
		try {
			int result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeUpdate("UPDATE Copy SET available = CASE available " + "WHEN 'true' THEN 'false' "
							+ "WHEN 'false' THEN 'true' " + "END WHERE idCopy = '"
							+ this.findIdByName(obj.getOwner(), obj.getGame(), "") + "'");
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

	// o1 = player, o2 = game
	@Override
	public int findIdByName(Object o1, Object o2, String str) {
		int id = 0;
		Player owner = (Player) o1;
		Game game = (Game) o2;
		if (str.equals("CREATE") || str.equals("UPDATE")) { // available = true
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idCopy FROM Copy WHERE idOwner = '" + owner.findIdByName()
								+ "' AND idGame = '" + game.findIdByName() + "' AND available = 'true'");
				if (result.first()) {
					id = result.getInt("idCopy");
				}
				return id;
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		} else if (str.equals("FIND")) { // available = false
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idCopy FROM Copy WHERE idOwner = '" + owner.findIdByName()
								+ "' AND idGame = '" + game.findIdByName() + "' AND available = 'false'");
				if (result.first()) {
					id = result.getInt("idCopy");
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
						.executeQuery("SELECT idCopy FROM Copy WHERE idOwner = '" + owner.findIdByName()
								+ "' AND idGame = '" + game.findIdByName() + "'");
				if (result.first()) {
					id = result.getInt("idCopy");
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

	// o1 = player, o2 = game
	@Override
	public ArrayList<Copy> getAll(Object o1, Object o2) {
		ArrayList<Copy> all = new ArrayList<>();
		Player owner = new Player();
		Game game = new Game();
		if (o2 == null) {
			owner = (Player) o1;
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
								"SELECT idGame FROM Copy INNER JOIN User ON Copy.idOwner = User.idUser WHERE userName = '"
										+ owner.getUsername() + "'");
				while (result.next()) {
					all.add(new Copy(owner, game.findById(result.getInt("idGame"))));
				}
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			game = (Game) o2;
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT idOwner, idGame, userName FROM Copy "
								+ "INNER JOIN Game ON Copy.idGame = Game.idGame "
								+ "INNER JOIN Version ON Game.idVersion = Version.idVersion "
								+ "INNER JOIN User ON Copy.idOwner = User.idUser WHERE gameName = '"
								+ game.getNameGame() + "' AND versionName = '" + game.getNameVersion() + "'");
				while (result.next()) {
					owner.setUsername(result.getString("userName"));
					all.add(new Copy(owner.find(), game));
				}
				return all;
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

	@Override
	public Copy findById(int i) {
		Copy copy = new Copy();
		Player owner = new Player();
		Game game = new Game();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT idOwner, idGame FROM Copy WHERE idCopy = '" + i + "'");
			if (result.first()) {
				copy = new Copy(owner.findById(result.getInt("idOwner")), game.findById(result.getInt("idGame")));
			}
			return copy;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}

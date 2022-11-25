package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Copy;
import be.veltri.POJO.Game;
import be.veltri.POJO.Location;
import be.veltri.POJO.Player;

public class LocationDAO extends DAO<Location> {

	public LocationDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Location obj) {
		try {

			this.connect.createStatement()
					.executeUpdate("INSERT INTO Location(dateBeginLoc, dateEndLoc, onGoing, totalUnits, idBorrower, "
							+ "idCopy) Values('" + obj.getDateBeginLocation() + "', '" + obj.getDateEndLocation()
							+ "', '" + obj.isOnGoing() + "', '" + obj.getTotalUnits() + "', '"
							+ obj.getBorrower().findIdByName() + "', '"
							+ obj.getCopy().findIdByName(obj.getOwner().getUsername(),
									obj.getCopy().getGame().getNameGame(), obj.getCopy().getGame().getNameVersion(),
									"CREATE")
							+ "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Location obj) {
		return false;
	}

	@Override
	public boolean update(Location obj) {
		try {
			int result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeUpdate("UPDATE Location SET onGoing = 'false', totalUnits = '" + obj.getTotalUnits()
							+ "' WHERE idCopy = '"
							+ obj.getCopy().findIdByName(obj.getOwner().getUsername(),
									obj.getCopy().getGame().getNameGame(), obj.getCopy().getGame().getNameVersion(),
									"UPDATE")
							+ "' AND onGoing = 'true'");
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
	public Location find(Location obj) {
		Location loc = new Location();
		Player borrower = new Player();
		Player owner = new Player();
		Copy copy = new Copy();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT dateBeginLoc, dateEndLoc, onGoing, totalUnits, idBorrower, idCopy, idOwner "
							+ "FROM Location INNER JOIN Copy ON Location.idCopy = Copy.idCopy " + "WHERE onGoing = '"
							+ obj.isOnGoing() + "' AND idCopy = '"
							+ obj.getCopy().findIdByName(obj.getOwner().getUsername(),
									obj.getCopy().getGame().getNameGame(), obj.getCopy().getGame().getNameVersion(),
									"FIND")
							+ "'");

			if (result.first()) {
				loc = new Location(result.getDate("dateBeginLoc").toLocalDate(),
						result.getDate("dateEndLoc").toLocalDate(), result.getInt("totalUnits"),
						result.getBoolean("onGoing"), owner.findById(result.getInt("idOwner")),
						borrower.findById(result.getInt("idBorrower")), copy.findById(result.getInt("idCopy")));
			}
			return loc;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<String> getAllName(String str1, String str2) {
		return null;
	}

	@Override
	public int findIdByName(String str1, String str2, String str3, String str4) {
		return 0;
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
	public ArrayList<Location> getAll(Object o1, Object o2) {
		ArrayList<Location> all = new ArrayList<>();
		if (o2 == null) {
			Player player = (Player) o1;
			int idBorrower = player.findIdByName();
			Player borrower = new Player();
			Copy copy = new Copy();
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT dateBeginLoc, dateEndLoc, totalUnits, idCopy FROM Location "
								+ "WHERE idBorrower = '" + idBorrower + "' AND onGoing = 'true'");
				while (result.next()) {
					copy = copy.findById(result.getInt("idCopy"));
					all.add(new Location(result.getDate("dateBeginLoc").toLocalDate(),
							result.getDate("dateEndLoc").toLocalDate(), result.getInt("totalUnits"), true,
							copy.getOwner(), borrower.findById(idBorrower), copy));
				}
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			Player player = (Player) o1;
			int idBorrower = player.findIdByName();
			Player borrower = new Player();
			Copy copy = new Copy();
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT dateBeginLoc, dateEndLoc, totalUnits, idCopy " + "FROM Location "
								+ "INNER JOIN Copy ON Copy.idCopy = Location.idCopy "
								+ "INNER JOIN Game ON Game.idGame = Copy.idGame "
								+ "INNER JOIN Version ON Version.idVersion = Game.idVersion " + "WHERE idBorrower = '"
								+ idBorrower + "' AND onGoing = 'true' " + "AND Game.gameName = '" + ((Game)o2).getNameGame()
								+ "' AND Version.versionName = '" + ((Game)o2).getNameVersion() + "'");
				while (result.next()) {
					copy = copy.findById(result.getInt("idCopy"));
					all.add(new Location(result.getDate("dateBeginLoc").toLocalDate(),
							result.getDate("dateEndLoc").toLocalDate(), result.getInt("totalUnits"), true,
							copy.getOwner(), borrower.findById(idBorrower), copy));
				}
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

	}

	@Override
	public Location findById(int i) {
		return null;
	}

}

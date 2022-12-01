package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Game;
import be.veltri.POJO.UnitsHistory;

public class UnitsHistoryDAO extends DAO<UnitsHistory> {

	public UnitsHistoryDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(UnitsHistory obj) {
		try {
			this.connect.createStatement().executeUpdate(
					"INSERT INTO UnitsHistory(dateHistory, units, idGame) " + "Values('" + obj.getDateChange() + "', '"
							+ obj.getUnits() + "', '" + obj.getGame().findIdByName() + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(UnitsHistory obj) {
		return false;
	}

	@Override
	public boolean update(UnitsHistory obj) {
		return false;
	}

	@Override
	public UnitsHistory find(UnitsHistory obj) {
		return null;
	}

	@Override
	public UnitsHistory findById(int i) {
		return null;
	}

	@Override
	public String find(int i, String str) {
		return null;
	}

	// o1 = game
	@Override
	public ArrayList<UnitsHistory> getAll(Object o1, Object o2) {
		ArrayList<UnitsHistory> all = new ArrayList<>();
		Game game = (Game) o1;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT dateHistory, units FROM UnitsHistory WHERE idGame = '" + game.findIdByName()
							+ "'");
			while (result.next()) {
				all.add(new UnitsHistory(result.getDate("dateHistory").toLocalDate(), result.getInt("units"),
						game.find()));
			}
			return all;
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
	public int findIdByName(Object o1, Object o2, String str) {
		return 0;
	}

	@Override
	public int returnUnits(String name) {
		return 0;
	}

}

package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import be.veltri.POJO.Copy;
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

	// str1 = beginDate, str2 = endDate, str3 = gameName, str4 = versionName
	@Override
	public ArrayList<UnitsHistory> getAll(String str1, String str2, String str3, String str4) {
		ArrayList<UnitsHistory> all = new ArrayList<>();
		Game game = new Game(str3, 0, "", str4);
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT dateHistory, units FROM UnitsHistory WHERE idGame = '" + game.findIdByName() + "' AND dateHistory BETWEEN '" + LocalDate.parse(str1) + "'"
							+ " AND '" + LocalDate.parse(str2) + "'");
			while (result.next()) {
				all.add(new UnitsHistory(result.getDate("dateHistory").toLocalDate(), result.getInt("units"), game.find()));
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
	public int findIdByName(String str1, String str2, String str3, String str4) {
		return 0;
	}

	@Override
	public int returnUnits(String name) {
		return 0;
	}

}

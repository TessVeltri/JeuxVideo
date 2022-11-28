package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Player;
import be.veltri.POJO.Rating;

public class RatingDAO extends DAO<Rating>{

	public RatingDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Rating obj) {
		try {
			this.connect.createStatement()
					.executeUpdate("INSERT INTO Rating(rate, idUser) " + "Values('"+ obj.getRating() 
					+ "', '" + obj.getPlayer().findIdByName() + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Rating obj) {
		return false;
	}

	@Override
	public boolean update(Rating obj) {
		return false;
	}
	
	@Override
	public Rating find(Rating obj) {
		return null;
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

	@Override
	public String find(int i, String str) {
		return null;
	}

	// o1 = player
	@Override
	public ArrayList<Rating> getAll(Object o1, Object o2) {
		ArrayList<Rating> all = new ArrayList<>();
		Player player = (Player)o1;
		int idUser = player.findIdByName();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT rate FROM Rating WHERE idUser = '" + idUser + "'");
			while (result.next()) {
				all.add(new Rating(result.getInt("rate"),player.findById(idUser)));
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Rating findById(int i) {
		return null;
	}

}

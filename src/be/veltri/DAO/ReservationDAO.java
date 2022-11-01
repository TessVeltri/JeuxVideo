package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Game;
import be.veltri.POJO.Player;
import be.veltri.POJO.Reservation;
import be.veltri.POJO.ReservationStatus;

public class ReservationDAO extends DAO<Reservation> {

	public ReservationDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Reservation obj) {
		try {
			this.connect.createStatement().executeUpdate(
					"INSERT INTO Reservation(dateReservation, statusReservation, idBorrower, idGame) Values('"
							+ obj.getDateReservation() + "', '" + obj.getStatusReservation() + "', '"
							+ obj.getBorrower().findIdByName() + "', '" + obj.getGame().findIdByName() + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Reservation obj) {
		return false;
	}

	@Override
	public boolean update(Reservation obj) {
		int idBorrower = obj.getBorrower().findIdByName();
		int idGame = obj.getGame().findIdByName();
		try {
			int result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeUpdate(
							"UPDATE Reservation SET statusReservation = '" + ReservationStatus.Cancelled.toString()
									+ "' WHERE dateReservation = '" + obj.getDateReservation() + "' AND idBorrower = '"
									+ idBorrower + "' AND idGame = '" + idGame + "'");
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
	public Reservation find(Reservation obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllName(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findIdByName(String str1, String str2, String str3, String str4) {
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
	public ArrayList<Reservation> getAll(String str1, String str2) {
		ArrayList<Reservation> all = new ArrayList<>();
		Player player = new Player();
		player.setUsername(str1);
		int idBorrower = player.findIdByName();
		Player borrower = new Player();
		Game game = new Game();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT dateReservation, statusReservation, idGame FROM Reservation "
							+ "WHERE idBorrower = '" + idBorrower + "' AND statusReservation <> '"
							+ ReservationStatus.Cancelled + "'");
			while (result.next()) {
				all.add(new Reservation(result.getDate("dateReservation").toLocalDate(),
						result.getString("statusReservation"), borrower.findById(idBorrower),
						game.findById(result.getInt("idGame"))));
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Reservation findById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}

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
					"INSERT INTO Reservation(dateReservation, statusReservation, nbrWeeks, idBorrower, idGame) Values('"
							+ obj.getDateReservation() + "', '" + obj.getStatusReservation() + "', '"
							+ obj.getNbrWeeks() + "' ,'" + obj.getBorrower().findIdByName() + "', '"
							+ obj.getGame().findIdByName() + "')");
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
					.executeUpdate("UPDATE Reservation SET statusReservation = '" + obj.getStatusReservation()
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
		Reservation res = null;
		int idBorrower = obj.getBorrower().findIdByName();
		int idGame = obj.getGame().findIdByName();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT dateReservation, statusReservation, nbrWeeks, idBorrower, idGame FROM Reservation "
									+ "WHERE idBorrower = '" + idBorrower + "' " + "AND idGame = '" + idGame + "' "
									+ "AND statusReservation = '" + ReservationStatus.InProgress.toString() + "'");
			if (result.first()) {
				res = new Reservation(result.getDate("dateReservation").toLocalDate(),
						result.getString("statusReservation"), result.getInt("nbrWeeks"), obj.getBorrower(),
						obj.getGame());
			}
			return res;
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

	@Override
	public String find(int i, String str) {
		return null;
	}

	// o1 = player, o2 = game
	@Override
	public ArrayList<Reservation> getAll(Object o1, Object o2) {
		ArrayList<Reservation> all = new ArrayList<>();
		if (o1!=null && o2==null) {
			Player player = (Player) o1;
			int idBorrower = player.findIdByName();
			Player borrower = new Player();
			Game game = new Game();
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT dateReservation, statusReservation, nbrWeeks, idGame FROM Reservation "
								+ "WHERE idBorrower = '" + idBorrower + "' AND statusReservation <> '"
								+ ReservationStatus.Cancelled + "'");
				while (result.next()) {
					all.add(new Reservation(result.getDate("dateReservation").toLocalDate(),
							result.getString("statusReservation"), result.getInt("nbrWeeks"),
							borrower.findById(idBorrower), game.findById(result.getInt("idGame"))));
				}
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			Player borrower = new Player();
			Game game = (Game) o2;
			int idGame = game.findIdByName();
			try {
				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
								"SELECT dateReservation, statusReservation, nbrWeeks, idBorrower FROM Reservation "
										+ "WHERE idGame = '" + idGame + "' AND statusReservation = '"
										+ ReservationStatus.InProgress + "'");
				while (result.next()) {
					all.add(new Reservation(result.getDate("dateReservation").toLocalDate(),
							result.getString("statusReservation"), result.getInt("nbrWeeks"),
							borrower.findById(result.getInt("idBorrower")), game.findById(idGame)));
				}
				return all;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	@Override
	public Reservation findById(int i) {
		return null;
	}

}

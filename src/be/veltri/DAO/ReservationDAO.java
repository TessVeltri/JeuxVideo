package be.veltri.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Reservation;

public class ReservationDAO extends DAO<Reservation> {

	public ReservationDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Reservation obj) {
		try {
			this.connect.createStatement().executeUpdate(
					"INSERT INTO Reservation(dateReservation, statusReservation, idBorrower, idGame) Values('"
							+ obj.getDateReservation() + "', '" + obj.getStatusReservation() + "', '" + obj.getBorrower().findIdByName()
							+ "', '" + obj.getGame().findIdByName() + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
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
	public int findIdByName(String str1, String str2, String str3) {
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
	public ArrayList<Reservation> getAll(String str1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation findById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}

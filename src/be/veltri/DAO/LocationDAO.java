package be.veltri.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Location;

public class LocationDAO extends DAO<Location>{

	public LocationDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Location obj) {
		try {
			this.connect.createStatement()
					.executeUpdate("INSERT INTO Location(dateBeginLoc, dateEndLoc, onGoing, totalUnits, idBorrower, "
							+ "idCopy) Values('" + obj.getDateBeginLocation() + "', '" + obj.getDateEndLocation() + "', '"
							+ obj.isOnGoing() + "', '" + obj.getTotalUnits() + "', '" + obj.getBorrower().findIdByName()
							+ "', '"+ obj.getCopy().findIdByName() +"')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Location obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Location obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Location find(Location obj) {
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
	public ArrayList<Location> getAll(String str1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location findById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}

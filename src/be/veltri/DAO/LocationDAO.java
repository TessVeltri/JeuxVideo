package be.veltri.DAO;

import java.sql.Connection;

import be.veltri.POJO.Location;

public class LocationDAO extends DAO<Location>{

	public LocationDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Location obj) {
		// TODO Auto-generated method stub
		return false;
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

}

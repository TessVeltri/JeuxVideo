package be.veltri.DAO;

import java.sql.Connection;

import be.veltri.POJO.Rating;

public class RatingDAO extends DAO<Rating>{

	public RatingDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Rating obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Rating obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Rating obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Rating find(Rating obj) {
		// TODO Auto-generated method stub
		return null;
	}

}

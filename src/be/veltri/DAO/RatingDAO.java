package be.veltri.DAO;

import java.sql.Connection;
import java.util.ArrayList;

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

	@Override
	public ArrayList<String> getAllName(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findIdByName(String str1, String str2) {
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
	public ArrayList<Rating> getAll(String str1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rating findById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}

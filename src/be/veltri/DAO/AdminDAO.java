package be.veltri.DAO;

import java.sql.Connection;
import java.util.ArrayList;

import be.veltri.POJO.Admin;

public class AdminDAO extends DAO<Admin>{

	public AdminDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Admin obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Admin obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Admin obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Admin find(Admin obj) {
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
	public ArrayList<Admin> getAll(String str1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin findById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}

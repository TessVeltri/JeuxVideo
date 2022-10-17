package be.veltri.DAO;

import java.sql.Connection;

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

}

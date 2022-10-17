package be.veltri.DAO;

import java.sql.Connection;

import be.veltri.POJO.Copy;

public class CopyDAO extends DAO<Copy>{

	public CopyDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Copy find(Copy obj) {
		// TODO Auto-generated method stub
		return null;
	}

}

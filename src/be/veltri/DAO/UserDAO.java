package be.veltri.DAO;

import java.sql.Connection;

import be.veltri.POJO.User;

public class UserDAO extends DAO<User>{

	public UserDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User find(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

}

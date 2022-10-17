package be.veltri.DAO;

import java.sql.Connection;

import be.veltri.POJO.Game;

public class GameDAO extends DAO<Game>{

	public GameDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Game obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Game obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Game obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Game find(Game obj) {
		// TODO Auto-generated method stub
		return null;
	}

}

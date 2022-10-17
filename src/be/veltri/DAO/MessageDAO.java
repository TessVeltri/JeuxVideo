package be.veltri.DAO;

import java.sql.Connection;

import be.veltri.POJO.Message;

public class MessageDAO extends DAO<Message>{

	public MessageDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Message obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Message obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Message obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Message find(Message obj) {
		// TODO Auto-generated method stub
		return null;
	}

}

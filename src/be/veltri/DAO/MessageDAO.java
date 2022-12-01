package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Admin;
import be.veltri.POJO.Message;
import be.veltri.POJO.Player;
import be.veltri.POJO.User;

public class MessageDAO extends DAO<Message> {

	public MessageDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Message obj) {
		if (obj.getReceiver() == null) {
			Admin admin = new Admin();
			try {
				this.connect.createStatement()
						.executeUpdate("INSERT INTO Message(txtMessage, read, idSender, idReceiver) " + "Values('"
								+ obj.getTextMessage() + "', 'false', '"
								+ obj.getSender().findIdByName(obj.getSender(), "") + "', '"
								+ admin.findIdByName(null, "Admin") + "')");
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			try {
				this.connect.createStatement()
						.executeUpdate("INSERT INTO Message(txtMessage, read, idSender, idReceiver) " + "Values('"
								+ obj.getTextMessage() + "', 'false', '" + obj.getSender().findIdByName(null, "Admin")
								+ "', '" + obj.getReceiver().findIdByName(obj.getSender(), "")
								+ "')");
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

	}

	@Override
	public boolean delete(Message obj) {
		return false;
	}

	@Override
	public boolean update(Message obj) {
		int idSender = obj.getSender().findIdByName(obj.getSender(), "");
		int idReceiver = obj.getReceiver().findIdByName(obj.getReceiver(), "");
		try {
			int result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeUpdate("UPDATE Message SET read = CASE read " + "WHEN 'true' THEN 'false' "
							+ "WHEN 'false' THEN 'true' " + "END WHERE txtMessage = '" + obj.getTextMessage()
							+ "' AND idSender = '" + idSender + "' AND idReceiver = '" + idReceiver + "'");
			if (result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Message find(Message obj) {
		return null;
	}

	@Override
	public ArrayList<String> getAllName(String str1, String str2) {
		return null;
	}

	@Override
	public int findIdByName(Object o1, Object o2, String str) {
		return 0;
	}

	@Override
	public int returnUnits(String name) {
		return 0;
	}

	@Override
	public String find(int i, String str) {
		return null;
	}

	// o1 = user
	@Override
	public ArrayList<Message> getAll(Object o1, Object o2) {
		ArrayList<Message> all = new ArrayList<>();
		User receiver;
		int idReceiver = 0;
		if (o1 instanceof Player) {
			receiver = (Player) o1;
			idReceiver = receiver.findIdByName(receiver, "");
		} else {
			receiver = (Admin) o1;
			idReceiver = receiver.findIdByName(null, "Admin");
		}
		User sender;
		
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT txtMessage, read, idSender, discriminator "
							+ "FROM Message "
							+ "INNER JOIN User ON User.idUser = Message.idSender "
							+ "WHERE idReceiver = '" + idReceiver + "'");
			while (result.next()) {
				if (result.getString("discriminator").equals("Admin")){
					sender = new Admin();
					all.add(new Message(result.getString("txtMessage"), result.getBoolean("read"),
							((Admin) sender).findById(result.getInt("idSender")), (Player) o1));
				} else {
					sender = new Player();
					if (o1 instanceof Player) {
						all.add(new Message(result.getString("txtMessage"), result.getBoolean("read"),
							((Player) sender).findById(result.getInt("idSender")), (Player) o1));
					} else {
						all.add(new Message(result.getString("txtMessage"), result.getBoolean("read"),
								((Player) sender).findById(result.getInt("idSender")), ((Admin) o1)));
					}
				}	
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Message findById(int i) {
		return null;
	}

}

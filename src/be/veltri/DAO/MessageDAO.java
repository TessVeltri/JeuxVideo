package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			User user = new User();
			try {
				this.connect.createStatement()
						.executeUpdate("INSERT INTO Message(txtMessage, read, idSender, idReceiver) " + "Values('"
								+ obj.getTextMessage() + "', 'false', '"
								+ obj.getSender().findIdByName("Player", obj.getSender().getUsername()) + "', '"
								+ user.findIdByName("Admin", "") + "')");
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			try {
				this.connect.createStatement()
						.executeUpdate("INSERT INTO Message(txtMessage, read, idSender, idReceiver) " + "Values('"
								+ obj.getTextMessage() + "', 'false', '" + obj.getSender().findIdByName("Admin", "")
								+ "', '" + obj.getReceiver().findIdByName("Player", obj.getSender().getUsername())
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Message obj) {
		int idSender = obj.getSender().findIdByName("", obj.getSender().getUsername());
		int idReceiver = obj.getReceiver().findIdByName("", obj.getReceiver().getUsername());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllName(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findIdByName(String str1, String str2, String str3, String str4) {
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

	// str1 = username, srt2 = ""
	@Override
	public ArrayList<Message> getAll(String str1, String str2) {
		ArrayList<Message> all = new ArrayList<>();
		Player receiver = new Player();
		Player sender = new Player();
		receiver.setUsername(str1);
		int idReceiver = receiver.findIdByName();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT txtMessage, read, idSender FROM Message WHERE idReceiver = '" + idReceiver + "'");
			while (result.next()) {
				all.add(new Message(result.getString("txtMessage"), result.getBoolean("read"),
						sender.findById(result.getInt("idSender")), receiver.findById(idReceiver)));
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Message findById(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}

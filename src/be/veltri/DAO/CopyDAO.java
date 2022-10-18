package be.veltri.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Copy;

public class CopyDAO extends DAO<Copy> {

	public CopyDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Copy obj) {
		try {
			this.connect.createStatement()
					.executeUpdate("INSERT INTO Copy(available, idOwner, idGame) " + "Values('true', '"
							+ obj.getOwner().findIdByName() + "', '" + obj.getGame().findIdByName() + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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

	@Override
	public ArrayList<String> getAll(String str1, String str2) {
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

}

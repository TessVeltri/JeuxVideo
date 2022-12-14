package be.veltri.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.veltri.POJO.Admin;

public class AdminDAO extends DAO<Admin>{

	public AdminDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Admin obj) {
		return false;
	}

	@Override
	public boolean delete(Admin obj) {
		return false;
	}

	@Override
	public boolean update(Admin obj) {
		return false;
	}

	@Override
	public Admin find(Admin obj) {
		Admin admin = new Admin();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT userName, password FROM User WHERE discriminator = 'Admin'");
			if (result.first())
				admin = new Admin(result.getString("userName"), result.getString("password"));
			return admin;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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

	@Override
	public ArrayList<Admin> getAll(Object o1, Object o2) {
		return null;
	}

	@Override
	public Admin findById(int i) {
		Admin admin = new Admin();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT username, password, pseudo, dateBirth, dateInscription, balance, checkBirthDay "
									+ "FROM User WHERE idUser = '" + i + "'");
			if (result.first()) {
				admin = new Admin(result.getString("username"), result.getString("password"));
			}
			return admin;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}

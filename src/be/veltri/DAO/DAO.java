package be.veltri.DAO;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
	protected Connection connect = null;

	public DAO(Connection conn) {
		this.connect = conn;
	}
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(T obj);
	
	public abstract ArrayList<String> getAll (String str1, String str2);
	
	public abstract int findIdByName(String str1, String str2);
	
	public abstract int returnUnits (String name);
	

}

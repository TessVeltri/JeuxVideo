package be.veltri.POJO;

import java.io.Serializable;
import java.util.ArrayList;

import be.veltri.DAO.AbstractDAOFactory;
import be.veltri.DAO.DAO;

public class Rating implements Serializable{
	private static final long serialVersionUID = 4090766884818572279L;
	private int rating; 
	private Player player;
	
	private static AbstractDAOFactory dao = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	private static DAO<Rating> ratingDAO = dao.getRatingDAO();
	
	// Constructeur par défaut
	public Rating() {}
	
	// Constructeur avec arguments
	public Rating(int rating, Player player) {
		this.rating = rating;
		this.player = player;
	}
	
	// Getters et Setters
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	// Méthodes
	public boolean create () {
		return ratingDAO.create(this);
	}
	
	public static int getAverage(String str1) {
		ArrayList<Rating> all = Rating.getAll(str1);
		int total = 0;
		for (Rating r : all)
			total += r.getRating();		
		if (all.size()!=0) {
			return total/all.size();
		} else {
			return 0;
		}
	}
	
	public static ArrayList<Rating> getAll (String str1){
		return ratingDAO.getAll(str1, "");
	}
}

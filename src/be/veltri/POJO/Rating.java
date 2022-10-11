package be.veltri.POJO;

public class Rating {
	private int rating; 
	private Player player;
	
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
}

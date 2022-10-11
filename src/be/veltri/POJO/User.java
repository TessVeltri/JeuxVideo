package be.veltri.POJO;

public abstract class User {

	private String username;
	private String password;

	// Constructeur par défaut
	public User() {}

	// Constructeur avec arguments
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// Getters et Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Méthodes
	public void Login() {
		// TODO implement here
	}

}

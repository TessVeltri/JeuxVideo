package be.veltri.POJO;

import java.time.LocalDate;

public class Player extends User {
	private String pseudo;
    private LocalDate dateOfBirth;
    private LocalDate dateInscription;
    private int balance;

    // Constructeur par défaut
    public Player() {}

    // Constructeur avec arguments
	public Player(String username, String password, String pseudo, LocalDate dateOfBirth, LocalDate dateInscription,
			int balance) {
		super(username, password);
		this.pseudo = pseudo;
		this.dateOfBirth = dateOfBirth;
		this.dateInscription = dateInscription;
		this.balance = balance;
	}

    // Getters et Setters
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// Méthodes
	public boolean LocationAllowed() {
        // TODO implement here
        return false;
    }

    public void AddBirthdayBonus() {
        // TODO implement here
        
    }

}

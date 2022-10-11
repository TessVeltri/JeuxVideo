package be.veltri.POJO;

public class Messages {
	
    private String textMessage;
    private Player player;
    
    // Constructeur par défaut
	public Messages() {}

	// Constructeur avec arguments
	public Messages(String textMessage, Player player) {
		this.textMessage = textMessage;
		this.player = player;
	}

	// Getters et Setters
	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	// Méthodes
	
}

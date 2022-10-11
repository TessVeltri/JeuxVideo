package be.veltri.POJO;

public class Copy {
	private Player owner;
	private Games game;
	
	// Constructeur par défaut
	public Copy () {}
	
	// Constructeur avec arguments
	public Copy(Player owner, Games game) {
		this.owner = owner;
		this.game = game;
	}
	
	// Getters et Setters
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Games getGame() {
		return game;
	}

	public void setGame(Games game) {
		this.game = game;
	}

	// Méthodes
	public void ReleaseCopy() {
        // TODO implement here
    }

	public void Borrow() {
        // TODO implement here
    }
    public boolean IsAvailable() {
        // TODO implement here
        return false;
    }
}

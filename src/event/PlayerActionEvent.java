package roguelike.event;

import roguelike.Player;

public abstract class PlayerActionEvent {
	protected Player player;
	protected String description;
	
	public PlayerActionEvent() {
		
	}

	public Player getPlayer() {
		return player;
	}
	
	public String getMessage() {
		return description;
	}
}

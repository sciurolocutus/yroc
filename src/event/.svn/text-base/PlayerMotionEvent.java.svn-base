package roguelike.event;

import roguelike.Cell;
import roguelike.Player;

public class PlayerMotionEvent extends PlayerActionEvent {
	private Cell cellTo;
	
	public PlayerMotionEvent(Player p, Cell c) {
		this.player = p;
		this.cellTo = c;
	}
	
	public Cell getCellTo() {
		return cellTo;
	}
}
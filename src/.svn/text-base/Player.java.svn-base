package roguelike;

import java.awt.Color;
import java.io.PrintStream;

import org.apache.log4j.Logger;

// Referenced classes of package roguelike:
//            Entity, PlayerStats, Cell

public class Player extends Entity {
	private String name;
	private PlayerStats stats;
	private Color color;
	private Cell cell;
	
	static Logger logger = Logger.getLogger(Player.class);

	public Player() {
		this("Enk", Color.red, 10, 10, 12, 12);
	}

	public Player(String s, PlayerStats playerstats, Color color1) {
		name = s;
		stats = playerstats;
		color = color1;
	}

	public Player(String s, Color color1, int i, int j, int k, int l) {
		name = s;
		color = color1;
		stats = new PlayerStats(i, j, k, l);
	}

	public Player(String s) {
		this(s, Color.red, 10, 10, 12, 12);
	}

	public boolean addToCell(Cell cell1) {
		if(cell1 == null) {
			logger.debug("Cannot add a Player to a null Cell.");
			return false;
		}
		if(cell == null) {
			if(cell1.addEntity(this)) {
				cell = cell1;
				return true;
			}
		} else
		if(Cell.moveEntity(cell, cell1)) {
			cell = cell1;
			return true;
		}
		return false;
	}

	public Cell getCell() {
		return cell;
	}

	public PlayerStats getStats() {
		return stats;
	}

	public Color getColor() {
		return color;
	}

	public String getChar() {
		return "@";
	}

	public boolean isAlive() {
		return true;
	}
	
	public String toString() {
		return name;
	}
}

package roguelike;

import java.awt.Color;
import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import roguelike.event.CombatEvent;

import org.apache.log4j.Logger;

import static org.apache.logging.log4j.LogManager.*;

// Referenced classes of package roguelike:
//            Entity, PlayerStats, Cell

public class Player extends Entity implements CanDefend, CanAttack {
	private PlayerStats stats;
	private Color color;
	private Cell cell;
	
	static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Player.class);

	public Player() {
		this("Enk", Color.red, 10, 10, 12, 12);
	}

	public Player(String s, PlayerStats playerstats, Color color1) {
		this.name = s;
		this.stats = playerstats;
		this.color = color1;
	}

	public Player(String s, Color color1, int i, int j, int k, int l) {
		this.name = s;
		this.color = color1;
		this.stats = new PlayerStats(i, j, k, l);
	}

	public Player(String s) {
		this(s, Color.red, 10, 10, 12, 12);
	}

	public boolean addToCell(Cell dest) throws CombatEvent{
		if(dest == null) {
			logger.debug("Cannot add a Player to a null Cell.");
			return false;
		}
		//If the Player is nowhere, just add him
		if(this.cell == null) {
			if(dest.addEntity(this)) {
				this.cell = dest;
				return true;
			}
		} else {
			//else ask the Cell if it's okay. (may throw CombatEvent)
			if(Cell.moveEntity(this.cell, dest)) {
				this.cell = dest;
				return true;
			}
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
	
	//attacking and defending
	
	//TODO: finish these
	//roll a die and add it to dex
	public int provideAccuracy() {
		return stats.getDex();
	}
	
	//roll a die and add it to str
	// (may in the future take into acct weapons)
	public int provideDamage() {
		return stats.getStr();
	}
	
	//roll a die and add it to dex/eva?
	public int provideEvasion() {
		return stats.getDex();
	}
	
	//total armor, any resistances, and [hardiness]?
	//perhaps include life, though keep track separately
	public int provideProtection() {
		return 1;
	}
	
	public String toString() {
		return this.name;
	}
}

package roguelike;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.apache.log4j.Logger;
import roguelike.event.CombatEvent;

public class Monster extends Entity {
	private MonsterStats stats;
	private Color color;
	private Cell cell;
	
	static Logger logger = Logger.getLogger(Monster.class);
	
	public Monster() {
		this("Goblin", Color.blue, 10, 12, 10, 10);
	}
	
	public Monster(String s, MonsterStats ms, Color color1) {
		this.name = s;
		this.stats = ms;
		this.color = color1;
	}
	
	public Monster(String s, Color color1, int i, int j, int k, int l) {
		this.name =s ;
		this.color = color1;
		this.stats = new MonsterStats(i, j, k, l);
	}
	
	//TODO: implement
	public Monster(Random r) {
		//choose a random monster from the DB
	}
	
	public boolean addToCell(Cell cell1) throws CombatEvent {
		if(cell1 == null) {
			logger.debug("Cannot add a Monster to a null Cell.");
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
	
	public Cell nextMove() {
		//for the default Monster, choose a random direction.
		//Perhaps later this will be overriden as public Action nextAction
		ArrayList<Cell> neigh = this.cell.getNeighbors();
		Collections.shuffle(neigh);
		for(Cell c : neigh) {
			if(c.isEmpty())
				return c;
		}
		
		//none are empty, "move" to the same cell
		return this.cell;
	}

	public Cell getCell() {
		return cell;
	}

	public MonsterStats getStats() {
		return stats;
	}

	public Color getColor() {
		return color;
	}

	public String getChar() {
		return "g";
	}

	public boolean isAlive() {
		return true;
	}
	
	public String toString() {
		return this.name;
	}
}

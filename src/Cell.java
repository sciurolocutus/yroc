package roguelike;

import java.awt.Color;
import java.util.ArrayList;

// Referenced classes of package roguelike:
//            Item, Displayable, Entity

public class Cell implements Displayable {
	
	private ArrayList<Item> contents;
	private Entity ent;
	
	private ArrayList<Cell> neighbors;
	
	private int type;
	private int x;
	private int y;
	private boolean changed;
	public static final int TYPE_STONEFLOOR = 1;
	public static final int TYPE_STONEWALL = 2;
	private static final Cell absolute_wall = new Cell(2, -1, -1);

	public Cell(int type, int j, int k) {
		this.type = type;
		x = j;
		y = k;
		changed = true;
		contents = new ArrayList<Item>();
		neighbors = new ArrayList<Cell>();
	}

	public Cell(int i, int j) {
		this(1, i, j);
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public boolean addNeighbor(Cell neigh) {
		if(neigh == null) {
			neighbors.add(absolute_wall);
		} else {
			neighbors.add(neigh);
		}
		return true;
	}

	public boolean addEntity(Entity entity) {
		if(type == 1 && ent == null) {
			ent = entity;
			changed = true;
			return true;
		} else {
			return false;
		}
	}

	public static boolean moveEntity(Cell cell, Cell cell1) {
		if(cell.ent != null) {
			if(cell == cell1)
				return true;
			if(cell1.addEntity(cell.ent)) {
				cell.ent = null;
				cell.changed = cell1.changed = true;
				return true;
			}
		}
		return false;
	}

	public void setChanged(boolean flag) {
		changed = flag;
	}

	public boolean getChanged() {
		return changed;
	}

	public String getChar() {
		if(ent != null)
			return ent.getChar();
		if(type == 2)
			return "#";
		if(contents.size() > 0)
			return ((Item)contents.get(contents.size() - 1)).getChar();
		else
			return ".";
	}

	public Color getColor() {
		if(ent != null)
			return ent.getColor();
		if(contents.size() > 0)
			return ((Item)contents.get(contents.size() - 1)).getColor();
		else
			return Color.gray;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb .append("(");
		sb.append(x);
		sb.append(", ");
		sb.append(y);
		sb.append(")\nType: ");
		sb.append(type);
		sb.append("\nEntity: ");
		sb.append(ent);
		sb.append("\ncontents: ");
		sb.append(contents.toString());
		return sb.toString();
	}
	
	public ArrayList<Cell> getNeighbors() {
		return new ArrayList<Cell>(this.neighbors);
	}
	
	/**
	* @return true iff this cell is not a wall and is not currently occupied.
	*/
	public boolean isEmpty() {
		return this.ent == null && this.type == TYPE_STONEWALL;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

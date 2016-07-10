package roguelike;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import roguelike.event.CombatEvent;

// Referenced classes of package roguelike:
//            Item, Displayable, Entity

public class Cell implements Displayable {
	
	private ArrayList<Item> contents;
	private Entity ent;
	
	private ArrayList<Cell> neighbors;
	
	private Celltype type;
	private int x;
	private int y;
	private boolean changed;
	//public static final int TYPE_STONEFLOOR = 1;
	//public static final int TYPE_STONEWALL = 2;
	private static final Cell absolute_wall = new Cell(Celltype.STONEWALL, -1, -1);
	
	//TODO: replace all 'int celltype' with 'Celltype type'
	protected enum Celltype {
		STONEFLOOR, STONEWALL
	}

	public Cell(Celltype type, int j, int k) {
		this.type = type;
		this.x = j;
		this.y = k;
		changed = true;
		contents = new ArrayList<Item>();
		neighbors = new ArrayList<Cell>();
	}

	public Cell(int type, int j, int k) {
		this(getType(type), j, k);
	}

	public Cell(Random r, int j, int k) {
		this(Celltype.values()[r.nextInt(Celltype.values().length)], j, k);
	}

	public Cell(int i, int j) {
		this(Celltype.STONEFLOOR, i, j);
	}

	public void setType(Celltype type) {
		this.type = type;
	}

	public static Celltype getType(int i) {
		if(i % 2 == 0) {
			return Celltype.STONEFLOOR;
		} else {
			return Celltype.STONEWALL;
		}
	}
	
	public boolean addNeighbor(Cell neigh) {
		if(neigh == null) {
			neighbors.add(absolute_wall);
		} else {
			neighbors.add(neigh);
		}
		return true;
	}

	public boolean addEntity(Entity entity) throws CombatEvent {
		if(this.type == Celltype.STONEFLOOR && this.ent == null) {
			ent = entity;
			changed = true;
			return true;
		} else if(this.type == Celltype.STONEFLOOR && this.ent != null) {
			System.err.println("Combat! " + entity.toString() + " " + this.ent.toString());
			throw new CombatEvent(entity, this.ent);
		} else {	//type!=1 - wall or something
			return false;
		}
	}

	public static boolean moveEntity(Cell src, Cell dest) throws CombatEvent {
		if(src.ent != null) {
			//If the destination = src, we're done.
			if(src == dest)
				return true;
			//If the destination Cell lets us in, update the 2 cells.
			if(dest.addEntity(src.ent)) {
				src.ent = null;
				src.changed = dest.changed = true;
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
		//if there's an entity there, display it rather than anything else
		if(ent != null)
			return ent.getChar();
		//if there are items but no entity, show the toppest one
		if(contents.size() > 0)
			return ((Item)contents.get(contents.size() - 1)).getChar();
		//otherwise give a char based on the cell type
		if(type == Celltype.STONEWALL)
			return "#";
		if(type == Celltype.STONEFLOOR)
			return ".";
		return ".";	//if all fails, show .
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
		sb.append(this.x);
		sb.append(", ");
		sb.append(this.y);
		sb.append(")\nType: ");
		sb.append(this.type);
		sb.append("\nEntity: ");
		sb.append(this.ent);
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
		return this.ent == null && this.type == Celltype.STONEWALL;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

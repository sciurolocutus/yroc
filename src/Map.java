package roguelike;

import java.awt.Color;
import java.awt.Graphics;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

// Referenced classes of package roguelike:
//            Cell, RectArea, Area

public class Map {
	private ArrayList<Area> areas;
	private ArrayList<ArrayList<Cell>> cols;
	private int width;
	private int height;
	
	public Map(int i, int j) {
		width = i;
		height = j;
		cols = new ArrayList<ArrayList<Cell>>(i);
		for(int k = 0; k < i; k++) {
			cols.add(new ArrayList<Cell>(j));
			
		}

		areas = new ArrayList<Area>();
	}

	public Cell get(int i, int j) {
		if(i > -1 && j > -1 && i < width && j < height) {
			return (cols.get(i)).get(j);
		} else {
			//System.err.println((new StringBuilder()).append("(").append(i).append(", ").append(j).append(") out of bounds.").toString());
			return null;
		}
	}

	/*
	* Generate a dungeon 
	*/
	public void generateRandumbDungeon(long i) {
		Random random = new Random(i);
		for(int j = 0; j < width; j++) {
			(cols.get(j)).clear();
			Cell c;
			for(int k = 0; k < height; k++) {
				c = new Cell(random.nextInt(2) + 1, j, k);
				(cols.get(j)).add(c);
			}
		}
		
		addNeighbors();

	}
	
	public void addNeighbors() {
		//add neighbors to each cell starting with North,
		// then going clockwise (N,NE,E,SE,S,SW,W,NW)
		for(int j=0; j<cols.size(); j++) {
			ArrayList<Cell> row = cols.get(j);
			for(int k=0; k<row.size(); k++) {
				Cell c = row.get(k);
				c.addNeighbor(this.get(j, k-1)); //N
				c.addNeighbor(this.get(j+1, k-1)); //NE
				c.addNeighbor(this.get(j+1, k)); //E
				c.addNeighbor(this.get(j+1, k+1)); //SE
				c.addNeighbor(this.get(j, k+1)); //S
				c.addNeighbor(this.get(j-1, k+1)); //SW
				c.addNeighbor(this.get(j-1, k)); //W
				c.addNeighbor(this.get(j-1, k-1)); //NW
			}
		}
	}

	public void generateDungeonRespectingAreas(long i) {
		Random random = new Random(i);
		for(int j = 0; j < width; j++) {
			((ArrayList)cols.get(j)).clear();
			for(int k = 0; k < height; k++) {
				Area area = isInArea(j, k);
				Cell cell;
				if(area != null)
					cell = new Cell(area.getType(), j, k);
				else
					cell = new Cell(random.nextInt(2) + 1, j, k);
				(cols.get(j)).add(cell);
			}
		}
		
		addNeighbors();
	}

	public void generateDungeonRespectingAreas() {
		generateDungeonRespectingAreas((int)System.currentTimeMillis());
	}

	public void generateDungeonWithAreas(long i) {
		Random random = new Random(i);
		generateAreas(i);
		for(int j = 0; j < width; j++) {
			((ArrayList)cols.get(j)).clear();
			for(int k = 0; k < height; k++) {
				Area area = isInArea(j, k);
				Cell cell;
				if(area != null)
					cell = new Cell(area.getType(), j, k);
				else
					cell = new Cell(random.nextInt(2) + 1, j, k);
				(cols.get(j)).add(cell);
			}

		}

	}

	public void generateDungeonWithAreas() {
		generateDungeonWithAreas((int)System.currentTimeMillis());
	}

	public void generateDungeonUsingOnlyAreas(int i) {
		for(int j = 0; j < width; j++) {
			((ArrayList)cols.get(j)).clear();
			for(int k = 0; k < height; k++) {
				Area area = isInArea(j, k);
				Cell cell;
				if(area != null)
					cell = new Cell(area.getType(), j, k);
				else
					cell = new Cell(i, j, k);
				(cols.get(j)).add(cell);
			}
		}
		addNeighbors();
	}

	public void generateAreas(long i) {
		Random random = new Random(i);
		int j = random.nextInt(6) + 3;
		areas.clear();
		for(int k = 0; k < j; k++)
			areas.add(new RectArea(random.nextInt(width), random.nextInt(height), random.nextInt(7) + 3, random.nextInt(7) + 3, random.nextInt(1) + 1));

	}

	public void generateAreas() {
		generateAreas((int)System.currentTimeMillis());
	}

	public void addArea(Area area) {
		areas.add(area);
	}

	private Area isInArea(int i, int j) {
		for(int k = 0; k < areas.size(); k++)
			if(((Area)areas.get(k)).contains(i, j))
				return (Area)areas.get(k);

		return null;
	}

	private ArrayList<Area> getAreas(int i, int j) {
		ArrayList<Area> arraylist = new ArrayList<Area>();
		for(int k = 0; k < areas.size(); k++)
			if((areas.get(k)).contains(i, j))
				arraylist.add(areas.get(k));

		return arraylist;
	}

	public void display(Graphics g) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				g.setColor(get(i, j).getColor());
				g.drawString(get(i, j).getChar(), i * 15 + 5, j * 15 + 15);
			}

		}

	}

	public void update(Graphics g) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				Cell cell = get(i, j);
				if(cell.getChanged()) {
					g.setColor(Color.black);
					g.fillRect(i * 15 + 5, j * 15 + 5, 15, 15);
					g.setColor(cell.getColor());
					g.drawString(cell.getChar(), i * 15 + 5, j * 15 + 15);
					cell.setChanged(false);
				}
			}

		}

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}

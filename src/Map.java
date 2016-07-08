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
	
	public Map(int w, int h) {
		width = w;
		height = h;
		cols = new ArrayList<ArrayList<Cell>>(w);
		for(int i = 0; i < w; i++) {
			cols.add(new ArrayList<Cell>(h));
			
		}

		areas = new ArrayList<Area>();
	}

	public Cell get(int x, int y) {
		if(x > -1 && y > -1 && x < width && y < height) {
			return (cols.get(x)).get(y);
		} else {
			//System.err.println((new StringBuilder()).append("(").append(i).append(", ").append(j).append(") out of bounds.").toString());
			return null;
		}
	}

	/*
	* Generate a dungeon in the most arbitrary, random way possible.
	*/
	public void generateRandumbDungeon(long seed) {
		Random random = new Random(seed);
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

	public void generateDungeonRespectingAreas(long seed) {
		Random random = new Random(seed);
		for(int j = 0; j < width; j++) {
			((ArrayList)cols.get(j)).clear();
			for(int k = 0; k < height; k++) {
				Area area = isInArea(j, k);
				Cell cell;
				if(area != null) {
					cell = new Cell(Cell.Celltype.STONEFLOOR, j, k);
				} else {
					cell = new Cell(random.nextInt(2) + 1, j, k);
				}
				(cols.get(j)).add(cell);
			}
		}
		
		addNeighbors();
	}

	public void generateDungeonRespectingAreas() {
		generateDungeonRespectingAreas((int)System.currentTimeMillis());
	}

	public void generateDungeonWithAreas(long seed) {
		Random random = new Random(seed);
		generateAreas(seed);
		for(int j = 0; j < width; j++) {
			((ArrayList)cols.get(j)).clear();
			for(int k = 0; k < height; k++) {
				Area area = isInArea(j, k);
				Cell cell;
				if(area != null)
					cell = new Cell(Cell.Celltype.STONEFLOOR, j, k);
				else
					cell = new Cell(random.nextInt(2) + 1, j, k);
				(cols.get(j)).add(cell);
			}

		}

	}

	public void generateDungeonWithAreas() {
		generateDungeonWithAreas((int)System.currentTimeMillis());
	}

	public void generateDungeonUsingOnlyAreas(int seed) {
		Random random = new Random(seed);
		for(int j = 0; j < width; j++) {
			((ArrayList)cols.get(j)).clear();
			for(int k = 0; k < height; k++) {
				Area area = isInArea(j, k);
				Cell cell;
				if(area != null) {
					cell = new Cell(Cell.Celltype.STONEFLOOR, j, k);
				} else {
					cell = new Cell(Cell.getType(random.nextInt(2)), j, k);
				}
				(cols.get(j)).add(cell);
			}
		}
		addNeighbors();
	}

	public void generateAreas(long seed) {
		Random random = new Random(seed);
		int numAreas = random.nextInt(6) + 3;
		areas.clear();
		for(int i = 0; i < numAreas; i++)
			areas.add(new RectArea(random.nextInt(width), random.nextInt(height), random.nextInt(7) + 3, random.nextInt(7) + 3, random.nextInt(1) + 1));

	}

	public void generateAreas() {
		generateAreas((int)System.currentTimeMillis());
	}

	public void addArea(Area area) {
		areas.add(area);
	}

	//Returns the first Area containing the Cell (x,y), or null if none was found.
	private Area isInArea(int x, int y) {
		for(int k = 0; k < areas.size(); k++) {
			if(((Area)areas.get(k)).contains(x, y)) {
				return (Area)areas.get(k);
			}
		}

		return null;
	}

	//Return a list of areas that a given Cell(x,y) belongs to
	private ArrayList<Area> getAreas(int x, int y) {
		ArrayList<Area> arraylist = new ArrayList<Area>();
		for(int k = 0; k < areas.size(); k++)
			if((areas.get(k)).contains(x, y))
				arraylist.add(areas.get(k));

		return arraylist;
	}

	//Draw each cell to the screen; each cell has a color and a char (String) to represent it.
	//The color and string will be based on its topmost contents.
	public void display(Graphics g) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				g.setColor(get(i, j).getColor());
				g.drawString(get(i, j).getChar(), i * 15 + 5, j * 15 + 15);
			}

		}
	}

	//Draw each cell which has changed since the last draw.
	//This allows for more efficient (and less flashy) drawing to screens when
	//only a small portion of the Cells actually changed (due to
	// player or monster movement, or other game events).
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

	//The width of the current map
	public int getWidth() {
		return width;
	}
	//The height of the current map
	public int getHeight() {
		return height;
	}
}

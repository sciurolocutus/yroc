package roguelike;

import java.util.ArrayList;

//A simple Area composition class - doesn't do anything fancy
// like meld the areas together or precompute a more efficient
// containment algorithm - though it could in the future
public class CompositeArea extends Area {
	private ArrayList<Area> components;
	private AreaType type;
	private int x;	//upper-left X coord

	public CompositeArea(int xpos, int ypos, AreaType t) {
		this.type = t;
		this.x = xpos;
		int y = ypos;
		this.components = new ArrayList<Area>();
	}

	public CompositeArea(int xpos, int ypos, Area a) {
		this(xpos, ypos, a.getType());
	}

	public AreaType getType() { return this.type; }

	//iterate through each of the Areas and see if that area contains
	// the given point.
	public boolean contains(int xpos, int ypos) {
		for(int i=0; i<components.size(); i++) {
			if(components.get(i).contains(xpos, ypos)) {
				return true;
			}
		}
		return false;
	}
}

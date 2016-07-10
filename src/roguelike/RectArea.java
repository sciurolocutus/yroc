package roguelike;

// Referenced classes of package roguelike:
//            Area

public class RectArea extends Area
{

	public RectArea(int xpos, int ypos, int wd, int ht, AreaType t) {
		type = t;
		x = xpos;
		y = ypos;
		height = ht;
		width = wd;
	}

	public RectArea(int xpos, int ypos, int wd, int ht, int type) {
		this(xpos, ypos, wd, ht, Area.getType(type));
	}

	public boolean contains(int xpos, int ypos) {
		return xpos > x && ypos > y && xpos <= x + height && ypos <= y + width;
	}

	public AreaType getType() {
		return type;
	}

	private AreaType type;
	private int x;
	private int y;
	private int height;
	private int width;
}

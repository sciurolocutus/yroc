package roguelike;

// Referenced classes of package roguelike:
//            Area

public class RectArea extends Area
{

	public RectArea(int i, int j, int k, int l, int i1) {
		type = i1;
		x = i;
		y = j;
		height = k;
		width = l;
	}

	public boolean contains(int i, int j) {
		return i > x && j > y && i <= x + height && j <= y + width;
	}

	public int getType() {
		return type;
	}

	private int type;
	private int x;
	private int y;
	private int height;
	private int width;
}

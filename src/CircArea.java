package roguelike;

// Referenced classes of package roguelike:
//            Area

public class CircArea extends Area {

	public CircArea(int i, int j, int k, AreaType t) {
		type = t;
		x = i;
		y = j;
		rad = k;
	}

	public boolean contains(int i, int j) {
		return (i - x) * (i - x) + (j - y) * (j - y) < rad * rad;
	}

	public AreaType getType() {
		return type;
	}

	private AreaType type;
	private int x;
	private int y;
	private int rad;
}

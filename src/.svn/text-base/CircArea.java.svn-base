package roguelike;

// Referenced classes of package roguelike:
//            Area

public class CircArea extends Area {

	public CircArea(int i, int j, int k, int l) {
		type = l;
		x = i;
		y = j;
		rad = k;
	}

	public boolean contains(int i, int j) {
		return (i - x) * (i - x) + (j - y) * (j - y) < rad * rad;
	}

	public int getType() {
		return type;
	}

	private int type;
	private int x;
	private int y;
	private int rad;
}

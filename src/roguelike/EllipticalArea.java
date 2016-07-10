package roguelike;

// Referenced classes of package roguelike:
//            Area

public class EllipticalArea extends Area {

	public EllipticalArea(int i, int j, int k, int l, AreaType t) {
		type = t;
		a = k;
		b = l;
		x = i;
		y = j;
	}

	public boolean contains(int i, int j) {
		return (i - x) * (i - x) * b * b + (j - y) * (j - y) * a * a <= a * a * b * b;
	}

	public AreaType getType() {
		return type;
	}

	private AreaType type;
	private int x;
	private int y;
	private int a;
	private int b;
}

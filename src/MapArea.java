package roguelike;

// Referenced classes of package roguelike:
//            Area

public class MapArea extends Area
{

	public MapArea(AreaType t) {
		type = t;
	}

	public boolean contains(int i, int j) {
		return true;
	}

	public AreaType getType() {
		return type;
	}

	private AreaType type;
}

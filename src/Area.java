package roguelike;

public abstract class Area {

	public Area() { }

	public abstract boolean contains(int i, int j);

	public abstract AreaType getType();

	public static AreaType getType(int t) {
		if(t % 2 == 0) {
			return AreaType.ROOM;
		} else {
			return AreaType.EFFECT_AREA;
		}
	}
	
	protected enum AreaType {
		ROOM, EFFECT_AREA
	}
}

package roguelike;

import java.awt.Color;

// Referenced classes of package roguelike:
//            Displayable

public abstract class Entity implements Displayable {

	public Entity() {
	}

	public void setName(String s) {
		this.name = s;
	}

	public abstract boolean isAlive();

	public abstract String getChar();

	public abstract Color getColor();
	
	public String getName() {
		return this.name;
	}

	protected String name;
}

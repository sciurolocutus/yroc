package roguelike.graphics;

import java.awt.*;
import roguelike.Game;
import roguelike.Map;

public class MapCanvas extends Canvas {

	public MapCanvas(Game game) {
		map = game.currentMap();
		addKeyListener(game.getArbitrator());
		setBackground(Color.black);
		setSize(400, 500);
	}

	public void setMap(Map map1) {
		map = map1;
	}

	public void repaint(Graphics g) {
		super.repaint();
		map.display(g);
	}

	public void paint(Graphics g) {
		map.display(g);
	}

	public void update() {
		map.update(getGraphics());
	}

	private Map map;
}

package roguelike.graphics;

import java.awt.*;
import roguelike.PlayerStats;

public class StatsCanvas extends Canvas {

	public StatsCanvas(PlayerStats playerstats) {
		stats = playerstats;
		setSize(500, 100);
	}

	public void paint(Graphics g) {
		setBackground(Color.white);
		g.setColor(Color.black);
		stats.display(g);
	}

	private PlayerStats stats;
}

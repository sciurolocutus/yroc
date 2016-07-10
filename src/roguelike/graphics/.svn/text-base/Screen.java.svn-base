package roguelike.graphics;

import java.awt.*;
import javax.swing.*;
import roguelike.Game;
import roguelike.Player;
import roguelike.TextAreaAppender;
import roguelike.event.MapHandler;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.varia.DenyAllFilter;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.LevelMatchFilter;



// Referenced classes of package roguelike.graphics:
//            StatsCanvas, MapCanvas

public class Screen {
	
	private JFrame theFrame;
	private JPanel upper;
	private JPanel lower;
	private StatsCanvas stats;
	private MapCanvas map;
	private JTextArea messages;
	
	static Logger logger = Logger.getLogger(MapHandler.class);
	
	public Screen(Game game) {
		stats = new StatsCanvas(game.getPlayer().getStats());
		map = new MapCanvas(game);
		messages = new JTextArea(25, 30);
		messages.setEditable(false);
		theFrame = new JFrame();
		upper = new JPanel();
		lower = new JPanel();
		theFrame.setTitle("Youthful Ranges of Certainty 0.0.1");
		Container container = theFrame.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(upper, "North");
		upper.setLayout(new FlowLayout());
		upper.add(stats);
		container.add(lower, "Center");
		lower.setLayout(new FlowLayout());
		lower.add(map);
		lower.add(messages);
		theFrame.pack();
		map.requestFocus();
		theFrame.setVisible(true);
		theFrame.setDefaultCloseOperation(3);
		
		//have the logger append only to the text area
		logger.removeAllAppenders();
		TextAreaAppender tap = new TextAreaAppender(messages);
		ConsoleAppender cap = new ConsoleAppender();
		LevelMatchFilter lmf = new LevelMatchFilter();
		LevelMatchFilter lmf2 = new LevelMatchFilter();
		DenyAllFilter daf = new DenyAllFilter();
		lmf.setLevelToMatch("INFO");
		lmf2.setLevelToMatch("DEBUG");
		tap.addFilter(lmf);
		tap.addFilter(daf);
		cap.addFilter(lmf2);
		cap.addFilter(daf);
		logger.addAppender(tap);
		logger.addAppender(cap);
	}

	public void update() {
		map.update();
	}
}

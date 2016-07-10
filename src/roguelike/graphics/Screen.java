package roguelike.graphics;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import org.apache.logging.log4j.LogManager;
import roguelike.Game;
import roguelike.event.MapHandler;



// Referenced classes of package roguelike.graphics:
//            StatsCanvas, MapCanvas

public class Screen {

	private MapCanvas map;

	static org.apache.logging.log4j.Logger logger = LogManager.getLogger(MapHandler.class);
	
	public Screen(Game game) {
		StatsCanvas stats = new StatsCanvas(game.getPlayer().getStats());
		map = new MapCanvas(game);
		JTextArea messages = new JTextArea(25, 30);
		messages.setEditable(false);
		JFrame theFrame = new JFrame();
		JPanel upper = new JPanel();
		JPanel lower = new JPanel();
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
		
		/* 
		 * This is giving me way too much trouble. It seems like log4j has changed its API, or at least locations of its classes that I use here.
		 * Though I've spent a lot of time getting log4j instrumented throughout this project, it may be best to just rethink how logging should be done.
		 * Certainly, I'd like to avoid explicit instantiation of appenders and filters in a class, and instead move that to config.
		 */
		
		/*
		//have the logger append only to the text area
		logger.removeAllAppenders();
		TextAreaAppender tap = new TextAreaAppender(messages);
		ConsoleAppender cap = ConsoleAppender.createAppender(null, null, null, "ConsoleAppender", null, null);
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
		*/
	}

	public void update() {
		map.update();
	}
}

package roguelike.event;

import java.awt.event.KeyEvent;
import java.io.PrintStream;
import roguelike.*;
import roguelike.graphics.Screen;

//logging
import org.apache.log4j.Logger;

// Referenced classes of package roguelike.event:
//            ActionHandler

public class MapHandler extends ActionHandler {

	private Game game;
	private Map map;
	static Logger logger = Logger.getLogger(MapHandler.class);
	
	public MapHandler(Game game1) {
		game = game1;
		map = game1.currentMap();
	}

	public void handleEvent(KeyEvent keyevent) {
		Player player = game.getPlayer();
		Cell cell = player.getCell();
		byte horiz = 0;
		byte vert = 0;
		switch(keyevent.getKeyCode()) {
			case 97: // 'a'
				horiz = vert = -1;
			break;

			case 40: // '('
			case 98: // 'b'
				vert = -1;
			break;

			case 99: // 'c'
				horiz = 1;
				vert = -1;
			break;

			case 37: // '%'
			case 100: // 'd'
				horiz = -1;
			break;

			case 39: // '\''
			case 102: // 'f'
				horiz = 1;
			break;

			case 103: // 'g'
				horiz = -1;
				vert = 1;
			break;

			case 38: // '&'
			case 104: // 'h'
				vert = 1;
			break;

			case 105: // 'i'
				horiz = vert = 1;
			break;
		}
		
		if(horiz != 0 || vert != 0) {
			boolean flag = player.addToCell(map.get(cell.getX() + horiz, cell.getY() - vert));
			if(flag) {
				logger.debug((new StringBuilder()).append("Player moved to ").append(player.getCell()).toString());
			} else {
				logger.debug((new StringBuilder()).append("Player did not move to ").append(map.get(cell.getX() + horiz, cell.getY() - vert)).toString());
				logger.info("Ouch.  You bump your head.");
			}
			game.getScreen().update();
		}
	}
}

package roguelike.event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import roguelike.Game;

// Referenced classes of package roguelike.event:
//            MapHandler, ActionHandler

public class InputArbitrator implements KeyListener {

	public InputArbitrator(Game game1) {
		this.handler = new MapHandler(game1);
	}

	protected void setHandler(ActionHandler actionhandler) {
		handler = actionhandler;
	}

	public void keyReleased(KeyEvent keyevent) {
	}

	public void keyPressed(KeyEvent keyevent) {
		handler.handleEvent(keyevent);
	}

	public void keyTyped(KeyEvent keyevent) {
	}

	private ActionHandler handler;
	private static InputArbitrator theInstance;
}

package roguelike;

// Referenced classes of package roguelike:
//            Game

import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;

public class Test {
	
	public Test() { }
	
	public static void main(String args[]) {
		BasicConfigurator.configure();
		Logger logger = Logger.getLogger(Test.class);
		logger.info("Entering YROC");
		Game game = new Game();
	}
}

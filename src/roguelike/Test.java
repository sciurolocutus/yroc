package roguelike;

// Referenced classes of package roguelike:
//            Game

import org.apache.log4j.BasicConfigurator;
import org.apache.logging.log4j.LogManager;

public class Test {
	
	public Test() { }
	
	public static void main(String args[]) {
		BasicConfigurator.configure();
		org.apache.logging.log4j.Logger logger = LogManager.getLogger(Test.class);
		logger.info("Entering YROC");
		Game game = new Game();
	}
}
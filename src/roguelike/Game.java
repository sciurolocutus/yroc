package roguelike;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;
import roguelike.event.CombatEvent;
import roguelike.event.InputArbitrator;
import roguelike.graphics.Screen;

// Referenced classes of package roguelike:
//            Player, PlayerStats, Map

//TODO: re-architect this to use an event structure, where Game arbitrates all
// 

public class Game
{

	private Map m;
	private Player p;
	private Screen sc;
	private InputArbitrator arb;
	
	private int turn;	//not used yet, but keep track of the number of moves

	public Game() {
		this(System.currentTimeMillis());
	}

	public Game(long i) {
		Random random = new Random(i);
		p = new Player("Enk", new PlayerStats(10, 10, 12, 12), Color.red);
		Monster mon = new Monster();
		m = new Map(26, 33);
		m.generateAreas(i);
		m.generateDungeonRespectingAreas(i);
		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", m);
		
		//need this try-catch clause, since addToCell could throw a CombatEvent
		//but we don't want to handle it in the default way, e.g. monster hits player
		//or vice-versa
		try {
			//add the player (and then the Monster) to some random unoccupied cell.
			while(!p.addToCell(m.get(random.nextInt(26), random.nextInt(33)))) ;
			while(!mon.addToCell(m.get(random.nextInt(26), random.nextInt(33)))) ;
		} catch(CombatEvent ce) {
			//if we had logging here, it might be good.  Else, we can have an empty handler,
			//since we don't actually want to do any combat.
		}
			
		arb = new InputArbitrator(this);
		sc = new Screen(this);
	}

	//warning: does not check nullity of map or player
	public Game(Map map, Player player) {
		m = map;
		p = player;
	}

	public Map currentMap() {
		return m;
	}

	public InputArbitrator getArbitrator() {
		return arb;
	}

	public Player getPlayer() {
		return p;
	}

	public Screen getScreen() {
		return sc;
	}

}

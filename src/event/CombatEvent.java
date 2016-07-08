package roguelike.event;

import roguelike.Entity;

public class CombatEvent extends Throwable {
	protected Entity attacker, defender;
	protected String description;
	
	public CombatEvent(Entity atk, Entity def) {
		this.attacker = atk;
		this.defender = def;
	}

	public Entity getAttacker() {
		return attacker;
	}
	
	public Entity getDefender() {
		return defender;
	}
	
	public String getMessage() {
		return attacker.getName() + " is attacking " + defender.getName();
		//return description;
	}
}

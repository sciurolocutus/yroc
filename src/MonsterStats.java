package roguelike;

import java.awt.Graphics;

public class MonsterStats {
	private int hp;
	private int maxhp;
	private int mp;
	private int maxmp;
	private int str;
	private int dex;
	
	public MonsterStats(int i, int j, int k, int l) {
		hp = maxhp = i;
		mp = maxmp = j;
		str = k;
		dex = l;
	}

	public void modHP(int i) {
		hp += i;
		if(hp > maxhp)
			hp = maxhp;
	}

	public String getString() {
		return (new StringBuilder()).append("HP: ").append(hp).append("/").append(maxhp).append("    MP: ").append(mp).append("/").append(maxmp).append("    STR: ").append(str).append("    DEX: ").append(dex).toString();
	}

	public void display(Graphics g) {
		g.drawString(getString(), 0, 30);
	}
}

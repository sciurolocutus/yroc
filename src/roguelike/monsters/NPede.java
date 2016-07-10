package roguelike.monsters;

import roguelike.*;
import java.awt.Color;
import java.util.HashMap;
import org.apache.log4j.Logger;

public class NPede extends Monster {
	private int length;
	private boolean lengthUpdated = false;
	private MonopodHead head;

	private static HashMap<Integer, String> names = new HashMap<Integer, String>();
	static {
		names.put(1, "Monopod");
		names.put(2, "Bipod");
		names.put(3, "Tripod");
		names.put(4, "Tetrapod");
		names.put(5, "Pentapede");
		names.put(6, "Hexapede");
		names.put(7, "Septapede");
		names.put(8, "Octopede");
		names.put(9, "Enneapede");
		names.put(10, "Decapod");
		names.put(11, "Hendecapod");
		names.put(12, "Dodecapod");
		names.put(13, "Triskaidekapod");
		names.put(20, "Icosapede");
		names.put(21, "Icosakaihenapede");
		names.put(30, "Triacontapede");
		names.put(40, "Tetracontapede");
		names.put(50, "Pentacontapede");
		names.put(60, "Hexacontapede");
		names.put(70, "Heptacontapede");
		names.put(80, "Octacontapede");
		names.put(90, "Enneacontapede");
		names.put(100, "Centipede");
	}

	public NPede() {
		super("N-Pede", Color.red, 10, 12, 10, 10);
	}

	public int calculateLength() {
		int len = 0;
		if(head != null) {
			len = 1;
			MonopodSegment current = head;
			while(current != null) {
				current = current.getNext();
				len++;
			}

		}
		this.length = len;
		this.lengthUpdated = true;
		return len;
	}

	public int getLength() {
		if(lengthUpdated) {
			return this.length;
		} else {
			return calculateLength();
		}
	}

	public String getChar() {
		return "C";
	}

	public String getName() {
		String nom = names.get(this.getLength());
		if(nom != null) {
			return nom;
		} else {
			return String.valueOf(this.length) + "-Pede";
		}
	}

	public String toString() {
		return this.name;
	}

	public boolean isAlive() {
		if(head != null) {
			return head.isAlive();
		} else {
			return false;
		}
	}

	private class MonopodSegment extends Monster {
		protected NPede body;
		protected MonopodSegment next;	//tail-ward segment
		protected MonopodSegment getNext() { return this.next; }

		protected MonopodSegment(NPede body) {
			super("MonopodSegment", Color.red, 10, 12, 10, 10);
			this.body = body;
		}
	}

	private class MonopodHead extends MonopodSegment {
		public String getChar() { return "C"; }
		public MonopodHead (NPede body) {
			super(body);
			this.name = "MonopodHead";
		}

		public String getName() {
			return this.body.getName();
		}
	}

	private class MonopodTail extends MonopodSegment {
		private NPede body;
		private MonopodSegment prev;	//head-ward
		
		public MonopodTail(NPede body) {
			super(body);
			this.name = "MonopodTail";
		}

		public String getName() {
			return this.body.getName();
		}
		public String getChar() { return "c"; }
	}

}

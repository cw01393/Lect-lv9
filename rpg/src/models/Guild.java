package models;

import java.util.ArrayList;

public class Guild {
	
	public static final int guildSize = 10;
	public static final int partySize = 4;
	
	private ArrayList<Unit> guild = new ArrayList<Unit>();
	
	public Unit getGuild(int index) {
		return this.guild.get(index);
	}
	
	public int getGuildSize() {
		return this.guild.size();
	}
	public void setGuile(int index, Unit unit) {
		this.guild.set(index, unit);
	}
	
	public void addGuild(Unit unit) {
		this.guild.add(unit);
	}
	
	public void removeGuild(int index) {
		this.guild.remove(index);
	}
}

package me.mizaki.boussole.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class CompassManager {

	private Map<Player, Compass> list;
	
	public CompassManager() {
		this.list = new HashMap<>();
	}
	
	public void addCompass(Compass compass) {
		this.list.put(compass.getPlayer(), compass);
	}
	
	public void removeCompass(Player player) {
		this.list.remove(player);
	}
	
	public Compass getCompass(Player player) {
		return this.list.get(player);
	}
	
	public boolean contains(Player player) {
		return getCompass(player) != null;
	}
	
	public Collection<Compass> getList() {
		return this.list.values();
	}
}

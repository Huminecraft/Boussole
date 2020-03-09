package me.mizaki.boussole.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.mizaki.boussole.main.CompassMain;

public class QuitEvent implements Listener
{

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		
		if(CompassMain.getSearchDemands().contains(player.getName()))
			CompassMain.getSearchDemands().remove(player.getName());
	}
}

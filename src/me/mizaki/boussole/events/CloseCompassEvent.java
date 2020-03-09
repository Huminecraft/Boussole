package me.mizaki.boussole.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import me.mizaki.boussole.main.CompassMain;

public class CloseCompassEvent implements Listener {

	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		if(!(event.getPlayer() instanceof Player))
			return;
		
		if(CompassMain.getCompassManager().contains((Player) event.getPlayer())) {
			CompassMain.getCompassManager().removeCompass((Player) event.getPlayer());
		}
	}
}

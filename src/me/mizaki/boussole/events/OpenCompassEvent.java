package me.mizaki.boussole.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.mizaki.boussole.main.CompassMain;
import me.mizaki.boussole.utils.Compass;

public class OpenCompassEvent implements Listener {

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (player.getInventory().getItemInMainHand() != null || player.getInventory().getItemInOffHand() != null) {
			if (player.getInventory().getItemInMainHand().getType() == Material.COMPASS || player.getInventory().getItemInOffHand().getType() == Material.COMPASS) {
				if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					Compass compass = new Compass(player);
					CompassMain.getCompassManager().addCompass(compass);
					compass.openMainMenu();
				}
			}
		}
	}

}

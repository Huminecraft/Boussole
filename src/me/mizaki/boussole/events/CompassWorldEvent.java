package me.mizaki.boussole.events;

import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class CompassWorldEvent
{

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		
		if(player.getInventory().getItemInMainHand() != null || player.getInventory().getItemInOffHand() != null)
		{
			if(player.getInventory().getItemInMainHand().getType() == Material.COMPASS || player.getInventory().getItemInOffHand().getType() == Material.COMPASS)
			{
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
				{
					if(player.getLocation().getWorld().getEnvironment() != Environment.NORMAL) {
						
					}
				}
			}
		}
	}
}

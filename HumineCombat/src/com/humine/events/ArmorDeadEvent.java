package com.humine.events;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.humine.main.BattleMain;


public class ArmorDeadEvent implements Listener {

	@EventHandler
	public void onDead(EntityDamageByEntityEvent event) {
		Entity victim = event.getEntity();
		Entity damager = event.getDamager();
		com.humine.util.ArmorStand armorStandUtil;
		ArmorStand armorStandVictim = null;
		Player killer = null;

		if (victim instanceof ArmorStand) {
			armorStandVictim = (ArmorStand) victim;
			armorStandUtil = getArmorStandUtil(armorStandVictim);

			if (damager instanceof Player) {

				// initiation des variables
				killer = (Player) damager;
				// fin

				if (armorStandUtil != null) {

					if (armorStandUtil.getArmorStand().getHealth() > 2.0){
						armorStandUtil.getArmorStand().setHealth((armorStandUtil.getArmorStand().getHealth() - 2.0));
					}
					else {
						armorStandUtil.getArmorStand().setHealth(0.0);
						
						if(armorStandUtil.hasDrop() == false) {
							dropArmor(armorStandUtil.getArmorStand(), armorStandUtil.getInventory());
							
							for (Player p : Bukkit.getOnlinePlayers())
								BattleMain.sendMessage(p, armorStandUtil.getCustomName() + " was slain by " + killer.getName() + " (Disconnected)");
							
							
							armorStandUtil.setDrop(true);
						}
					}
					
					makeBloodParticle(armorStandUtil.getArmorStand().getLocation());
				}
			} else {

				if (armorStandUtil != null) {

					if (armorStandUtil.getArmorStand().getHealth() <= 0.0) {
						armorStandUtil.getArmorStand().setVisible(false);
						dropArmor(armorStandUtil.getArmorStand(), armorStandUtil.getInventory());
						
						for (Player p : Bukkit.getOnlinePlayers())
							BattleMain.sendMessage(p, armorStandUtil.getCustomName() + " was killed (disconnected)");
					}

				}
			}
		}
	}

	private void dropArmor(ArmorStand armor, ItemStack[] inventory) {
		if (inventory != null) {
			for (int i = 0; i < inventory.length; i++) {
				if (inventory[i] != null) {
					if (inventory[i].getType() != Material.AIR) {
						armor.getLocation().getWorld().dropItem(armor.getLocation(), inventory[i]);
					}
				}
			}
		}
	}

	private com.humine.util.ArmorStand getArmorStandUtil(ArmorStand armorStand) {
		boolean find = false;
		int i = 0;
		com.humine.util.ArmorStand armor = null;

		while (i < BattleMain.getInstance().getArmors().size() && find == false) {
			if (BattleMain.getInstance().getArmors().get(i).getArmorStand().getCustomName()
					.equals(armorStand.getCustomName())) {
				find = true;
				armor = BattleMain.getInstance().getArmors().get(i);
			}
			i++;
		}

		return armor;
	}
	
	private void makeBloodParticle(Location loc) {
		DustOptions dustOptions = new DustOptions(Color.RED, (float) 10.0);
        loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 10, 0, 0.5, 0, 2, dustOptions);
	}
}

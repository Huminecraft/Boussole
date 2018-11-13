package com.humine.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.humine.main.BattleMain;

public class ArmorStand {

	private String name;
	private org.bukkit.entity.ArmorStand armorStand;
	private ItemStack[] inventory;
	private boolean Drop;
	
	public ArmorStand(Player player) {
		this.armorStand = player.getWorld().spawn(player.getLocation(), org.bukkit.entity.ArmorStand.class);
		this.name = player.getName();
		
		this.inventory = player.getInventory().getContents();
		this.Drop = false;
		
		this.armorStand.setBasePlate(false);
		this.armorStand.setSmall(false);
		this.armorStand.setArms(true);
		this.armorStand.setCollidable(true);
		this.armorStand.setCustomName(this.name);
		this.armorStand.setCustomNameVisible(true);
		this.armorStand.setVisible(true);
		this.armorStand.setGravity(true);
		this.armorStand.setInvulnerable(false);
		this.armorStand.setHealth(20.0);
		
		if(player.getInventory().getHelmet() != null)
			this.armorStand.setHelmet(player.getInventory().getHelmet());
		if(player.getInventory().getChestplate() != null)
			this.armorStand.setChestplate(player.getInventory().getChestplate());
		if(player.getInventory().getLeggings() != null)
			this.armorStand.setLeggings(player.getInventory().getLeggings());
		if(player.getInventory().getBoots() != null)
			this.armorStand.setBoots(player.getInventory().getBoots());
		
		BattleMain.getInstance().getArmors().add(this);
	}
	
	public String getCustomName() {
		return name;
	}

	public void setCustomName(String name) {
		this.name = name;
	}

	public org.bukkit.entity.ArmorStand getArmorStand() {
		return armorStand;
	}

	public ItemStack[] getInventory() {
		return inventory;
	}

	public void setInventory(ItemStack[] inventory) {
		this.inventory = inventory;
	}

	public boolean hasDrop() {
		return Drop;
	}

	public void setDrop(boolean drop) {
		Drop = drop;
	}
}

package me.mizaki.boussole.utils;

import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;

import com.aypi.utils.Button;
import com.aypi.utils.Menu;
import com.aypi.utils.inter.MenuItemListener;

public class Compass {

	private Menu mainInventory;
	private Menu poleInventory;
	private Player player;
	
	public Compass(Player player) {
		this.player = player;
		this.mainInventory = new Menu(player, "Boussole de " + player.getName(), 9, false);
		this.poleInventory = new Menu(player, "Pole", 9, false);
		if(player.getWorld().getEnvironment() == Environment.NORMAL) {
			initializeMainInventory();
			initializePoleInventory();
		}
		else {
			Button paper = CompassButton.createButton(Material.PAPER, "Indisponible dans ce monde", new MenuItemListener() {
				@Override
				public void onItemClick() {}
			});
			this.mainInventory.setButton(0, paper);
		}
	}
	
	private void initializeMainInventory() {
		Button lit = CompassButton.bedButton(this.player, this.mainInventory);
		Button savePosition = CompassButton.positionButton(this.player, this.mainInventory);
		Button originPosition = CompassButton.positionOriginButton(this.player, this.mainInventory);
		Button followPlayer = CompassButton.followPlayerButton(this.player, this.mainInventory);
		Button spawnPosition = CompassButton.positionSpawnButton(this.player, this.mainInventory);
		Button pole = CompassButton.poleButton(this.player, this.mainInventory, this.poleInventory);
		
		this.mainInventory.setButton(0, lit);
		this.mainInventory.setButton(1, savePosition);
		this.mainInventory.setButton(2, originPosition);
		this.mainInventory.setButton(3, followPlayer);
		this.mainInventory.setButton(4, spawnPosition);
		this.mainInventory.setButton(5, pole);
	}
	
	private void initializePoleInventory() {
		Button north = CompassButton.northButton(this.player, this.poleInventory);
		Button south = CompassButton.southButton(this.player, this.poleInventory);
		Button east = CompassButton.eastButton(this.player, this.poleInventory);
		Button west = CompassButton.westButton(this.player, this.poleInventory);
		
		this.poleInventory.setButton(0, north);
		this.poleInventory.setButton(1, south);
		this.poleInventory.setButton(2, east);
		this.poleInventory.setButton(3, west);
	}
	
	public void openMainMenu() {
		this.mainInventory.openMenu();
	}
	
	public void closeMainMenu() {
		this.mainInventory.closePlayerMenu();
	}
	
	public void openPoleMenu() {
		this.poleInventory.openMenu();
	}
	
	public void closePoleMenu() {
		this.poleInventory.closePlayerMenu();
	}
	
	public Player getPlayer() {
		return player;
	}
}

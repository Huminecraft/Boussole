package me.mizaki.boussole.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.aypi.utils.Button;
import com.aypi.utils.Menu;
import com.aypi.utils.inter.MenuItemListener;

import main.MainWelcome;
import me.mizaki.boussole.main.CompassMain;

public abstract class CompassButton {

	public static Button bedButton(Player player, Menu menu) {
		Button button = createButton(Material.RED_BED, "Lit", new MenuItemListener() {
			@Override
			public void onItemClick() {
				if(player.getBedSpawnLocation() != null)
				{
					CompassMain.sendMessage(player, "Direction le lit !");
					player.setCompassTarget(player.getBedSpawnLocation());
				}
				else
					CompassMain.sendMessage(player, "Vous n'avez pas de lit !");
				
				menu.closePlayerMenu();
			}
		});
		
		return button;
	}
	
	public static Button positionButton(Player player, Menu menu) {
		Button button = createButton(Material.ENDER_PEARL, "Position enregistrée", new MenuItemListener() {
			
			@Override
			public void onItemClick() {
				if(CompassMain.getInstance().getPositions().containsKey(player.getName())) {
					player.setCompassTarget(CompassMain.getInstance().getPositions().get(player.getName()));
					CompassMain.sendMessage(player, "Direction la position enregistrée !");
				}
				else
					CompassMain.sendMessage(player, "Aucune position enregistrée !");
				
				menu.closePlayerMenu();
			}
		});
		return button;
	}
	
	public static Button positionOriginButton(Player player, Menu menu) {
		Button button = createButton(Material.APPLE, "Pointez le 0 !", new MenuItemListener() {
			
			@Override
			public void onItemClick() {
				CompassMain.sendMessage(player, "Direction l'origine du monde !");
				player.setCompassTarget(new Location(player.getWorld(), 0.0, 0.0, 0.0));
				menu.closePlayerMenu();
			}
		});
		return button;
	}
	
	public static Button followPlayerButton(Player player, Menu menu) {
		Button button = createButton(Material.SKELETON_SKULL, "Suivre un joueur", new MenuItemListener() {
			
			@Override
			public void onItemClick() {
				CompassMain.sendMessage(player, "Veuillez entrer le nom du joueur ci-dessous");
				CompassMain.getSearchDemands().add(player.getName());
				menu.closePlayerMenu();
			}
		});
		return button;
	}
	
	public static Button positionSpawnButton(Player player, Menu menu) {
		Button button = createButton(Material.BLUE_BED, "Spawn par défaut", new MenuItemListener() {
			
			@Override
			public void onItemClick() {
				Location loc = MainWelcome.getInstance().loadNewbieLocation(player);
				
				if(loc == null)
					CompassMain.sendMessage(player, "Aucun spawn de départ défini");
				else {
					player.setCompassTarget(loc);
					CompassMain.sendMessage(player, "Direction le spawn de départ !");
				}
				
				menu.closePlayerMenu();
			}
		});
		return button;
	}
	
	public static Button poleButton(Player player, Menu menu, Menu poleMenu) {
		Button button = createButton(Material.COMPASS, "Pointez vers un pole", new MenuItemListener() {
			
			@Override
			public void onItemClick() {
				menu.closePlayerMenu();
				poleMenu.openMenu();
			}
		});
		return button;
	}
	
	public static Button northButton(Player player, Menu menu) {
		Button button = createButton(Material.GRASS_BLOCK, "NORD", new MenuItemListener() {
			
			@Override
			public void onItemClick() {
				double x, y, z;
				x = player.getLocation().getX();
				y = player.getLocation().getY();
				z = (player.getLocation().getZ() - 10_000.0);
				
				CompassMain.sendMessage(player, "Direction le nord !");
				player.setCompassTarget(new Location(player.getWorld(), x, y, z));
				menu.closePlayerMenu();
			}
		});
		return button;
	}
	
	public static Button southButton(Player player, Menu menu) {
		Button button = createButton(Material.GRASS_BLOCK, "SUD", new MenuItemListener() {
			
			@Override
			public void onItemClick() {
				double x, y, z;
				x = player.getLocation().getX();
				y = player.getLocation().getY();
				z = (player.getLocation().getZ() + 10_000.0);
				Location loc = new Location(player.getWorld(), x, y, z);
				
				CompassMain.sendMessage(player, "Direction le sud !");
				player.setCompassTarget(loc);
				menu.closePlayerMenu();
			}
		});
		return button;
	}
	
	public static Button eastButton(Player player, Menu menu) {
		Button button = createButton(Material.GRASS_BLOCK, "EST", new MenuItemListener() {
			
			@Override
			public void onItemClick() {
				double x, y, z;
				x = (player.getLocation().getX() + 10_000.0);
				y = player.getLocation().getY();
				z = player.getLocation().getZ();
				Location loc = new Location(player.getWorld(), x, y, z);
				
				CompassMain.sendMessage(player, "Direction l'est !");
				player.setCompassTarget(loc);
				menu.closePlayerMenu();
			}
		});
		return button;
	}
	
	public static Button westButton(Player player, Menu menu) {
		Button button = createButton(Material.GRASS_BLOCK, "OUEST", new MenuItemListener() {
			
			@Override
			public void onItemClick() {
				double x, y, z;
				x = (player.getLocation().getX() - 10_000.0);
				y = player.getLocation().getY();
				z = player.getLocation().getZ();
				Location loc = new Location(player.getWorld(), x, y, z);
				
				CompassMain.sendMessage(player, "Direction l'ouest !");
				player.setCompassTarget(loc);
				menu.closePlayerMenu();
			}
		});
		return button;
	}
	
	public static Button createButton(Material material, String name, MenuItemListener listener) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + name);
		item.setItemMeta(meta);
		return new Button(item, listener);
	}
}

package me.mizaki.boussole.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.mizaki.boussole.commands.ChangeRegisterLocationCommand;
import me.mizaki.boussole.commands.CompassFollowAcceptCommand;
import me.mizaki.boussole.commands.CompassFollowRefuseCommand;
import me.mizaki.boussole.events.CloseCompassEvent;
import me.mizaki.boussole.events.OpenCompassEvent;
import me.mizaki.boussole.events.QuitEvent;
import me.mizaki.boussole.events.ReceiptTargetNameEvent;
import me.mizaki.boussole.utils.CompassManager;
import me.mizaki.boussole.utils.PositionInvitationManager;

public class CompassMain extends JavaPlugin {

	private static CompassMain instance;
	
	private static PositionInvitationManager positionInvitationManager;
	private static CompassManager compassManager;
	
	private static List<String> searchDemands;
	private HashMap<String, Location> Positions;
	
	@Override
	public void onEnable() {
		super.onEnable();

		instance = this;
		positionInvitationManager = new PositionInvitationManager();
		compassManager = new CompassManager();
		
		Positions = new HashMap<String, Location>();

		searchDemands = new ArrayList<String>();

		this.saveDefaultConfig();
		this.getDataFolder().setWritable(true);

		if (this.getFile().exists()) {
			if (this.getConfig().contains("RegisterLocation")) {
				for (String key : this.getConfig().getConfigurationSection("RegisterLocation").getKeys(false)) {
					this.Positions.put(key, (Location) this.getConfig().get("RegisterLocation." + key));
				}
			}
		}

		commands();
		events();
	}

	private void events() {
		this.getServer().getPluginManager().registerEvents(new ReceiptTargetNameEvent(), this);
		this.getServer().getPluginManager().registerEvents(new QuitEvent(), this);
		this.getServer().getPluginManager().registerEvents(new OpenCompassEvent(), this);
		this.getServer().getPluginManager().registerEvents(new CloseCompassEvent(), this);
		this.getServer().getPluginManager().registerEvents(new QuitEvent(), this);
	}

	private void commands() {
		this.getCommand("compass").setExecutor(new ChangeRegisterLocationCommand());
		this.getCommand("compassfollowaccept").setExecutor(new CompassFollowAcceptCommand());
		this.getCommand("compassfollowrefuse").setExecutor(new CompassFollowRefuseCommand());
	}

	@Override
	public void onDisable() {
		super.onDisable();

		if (this.getDataFolder().exists()) {
			try {
				for (Map.Entry<String, Location> entry : this.Positions.entrySet()) {
					this.getConfig().set("RegisterLocation." + entry.getKey(), entry.getValue());
				}

				this.saveConfig();

			} catch (Exception e) {
				sendMessage(this.getServer().getConsoleSender(), ChatColor.RED + "ERROR enregistrement des positions");
				sendMessage(this.getServer().getConsoleSender(), ChatColor.RED + e.getMessage());
			}
		} else
			sendMessage(this.getServer().getConsoleSender(), ChatColor.RED + "Fichier config introuvable");

		searchDemands.clear();
		this.Positions.clear();
	}

	public static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "[Boussole] " + ChatColor.RESET + message);
	}

	public static CompassMain getInstance() {
		return instance;
	}

	public static PositionInvitationManager getPositionInvitationManager() {
		return positionInvitationManager;
	}
	
	public static CompassManager getCompassManager() {
		return compassManager;
	}
	
	public HashMap<String, Location> getPositions() {
		return Positions;
	}

	public void setPositions(HashMap<String, Location> positions) {
		Positions = positions;
	}

	public static List<String> getSearchDemands() {
		return searchDemands;
	}
}

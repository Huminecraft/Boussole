package me.mizaki.boussole.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.mizaki.boussole.main.CompassMain;

public class ReceiptTargetNameEvent implements Listener {

    @EventHandler
    public void onReceipt(AsyncPlayerChatEvent event) {
	final Player player = event.getPlayer();
	if ((CompassMain.getInstance().getTargetDemands().containsKey(player.getName()))) {
	    Player target = Bukkit.getPlayer(CompassMain.getInstance().getTargetDemands().get(player.getName()));
	    if ((target != null) && (!CompassMain.getInstance().isPlayerBlocked(player.getName(), target.getName()))) {
		if (event.getMessage().equalsIgnoreCase("yes") || event.getMessage().equalsIgnoreCase("oui")) {
		    CompassMain.sendMessage(player, "Vous avez accepté la demande");
		    CompassMain.sendMessage(target,
			    ChatColor.GOLD + player.getName() + ChatColor.GREEN + " a accepte(e) votre demande");
		    target.setCompassTarget(player.getLocation());
		} else if (event.getMessage().equalsIgnoreCase("no") || event.getMessage().equalsIgnoreCase("non")) {
		    CompassMain.sendMessage(player, "Vous avez refusé la demande");
		    CompassMain.sendMessage(target,
			    ChatColor.GOLD + player.getName() + ChatColor.RED + " a refuse(e) votre demande");
		} else {
		    CompassMain.sendMessage(player, "Réponse inconnue, demande déclinée par défaut.");
		    CompassMain.sendMessage(target,
			    ChatColor.GOLD + player.getName() + ChatColor.RED + " a refuse(e) votre demande");
		}
	    } else
		CompassMain.sendMessage(player, "Le joueur déconnecté ou vous a bloqué");

	    CompassMain.getInstance().getTargetDemands().remove(player.getName());
	    event.setCancelled(true);
	}

	if (CompassMain.getInstance().getSearchDemands().contains(player.getName())) {

	    Player target = Bukkit.getPlayer(event.getMessage());

	    if (target != null) {
		if (!CompassMain.getInstance().getTargetDemands().containsKey(target.getName())) {
		    CompassMain.sendMessage(player, "Demande envoyée a " + ChatColor.GREEN + target.getName());
		    CompassMain.sendMessage(target, ChatColor.GOLD + player.getName() + ChatColor.RESET
			    + " vous demande s'il peut prendre vos coordonnées");
		    CompassMain.sendMessage(target,
			    ChatColor.GREEN + "yes | oui " + ChatColor.RESET + "ou " + ChatColor.RED + "no | non");
		    CompassMain.getInstance().getTargetDemands().put(target.getName(), player.getName());
		} else
		    CompassMain.sendMessage(player, "Une demande est déjà en cours vers ce joueur");
	    } else
		CompassMain.sendMessage(player, ChatColor.RED + "Joueur introuvable !");

	    CompassMain.getInstance().getSearchDemands().remove(player.getName());
	    event.setCancelled(true);
	}

	if (CompassMain.getInstance().getBlockDemands().contains(player.getName())) {
	    String target = event.getMessage();
	    if (CompassMain.getInstance().isPlayerBlocked(player.getName(), target)) {
		CompassMain.getInstance().removeBlockedPlayerFromList(player.getName(), target);
		CompassMain.sendMessage(player, " vous avez débloqué " + ChatColor.GREEN + target);

	    } else {
		CompassMain.getInstance().addBlockedPlayerToList(player.getName(), target);
		CompassMain.sendMessage(player, " vous avez bloqué " + ChatColor.GREEN + target);
	    }

	}

    }
}

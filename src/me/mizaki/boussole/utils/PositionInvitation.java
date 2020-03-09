 package me.mizaki.boussole.utils;

import org.bukkit.entity.Player;

import me.mizaki.boussole.main.CompassMain;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class PositionInvitation {

	private Player sender;
	private Player target;
	
	public PositionInvitation(Player sender, Player target) {
		this.sender = sender;
		this.target = target;
	}
	
	public void notifyTarget() {
		TextComponent accept = new TextComponent("Accept");
		accept.setColor(ChatColor.GREEN);
		accept.setBold(true);
		accept.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/compassfollowaccept"));
		accept.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Accepter la demande").create()));
	
		TextComponent refuse = new TextComponent(" Refuse");
		refuse.setColor(ChatColor.RED);
		refuse.setBold(true);
		refuse.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/compassfollowrefuse"));
		refuse.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Refuser la demande").create()));
	
		accept.addExtra(refuse);
		
		CompassMain.sendMessage(this.target, sender.getName() + " demande pour pointer sa boussole à votre position, acceptez-vous ?");
		this.target.spigot().sendMessage(accept);
		CompassMain.sendMessage(this.target, "Le message sera automatiquement refuser au bout de 10 secondes");
	}
	
	public void notifySender(boolean invitationAccept) {
		if(invitationAccept)
			CompassMain.sendMessage(this.sender, "Le joueur " + this.target.getName() + " a accepté votre demande");
		else
			CompassMain.sendMessage(this.sender, "Le joueur " + this.target.getName() + " a refusé votre demande");
	}
	
	public void redirectCompassSender() {
		CompassMain.sendMessage(this.sender, "Boussole redirigée sur " + this.target.getName());
		this.sender.setCompassTarget(this.target.getLocation());
	}
	
	public Player getSender() {
		return sender;
	}
	
	public Player getTarget() {
		return target;
	}
}

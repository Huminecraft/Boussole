package me.mizaki.boussole.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.mizaki.boussole.main.CompassMain;
import me.mizaki.boussole.utils.PositionInvitation;

public class CompassFollowAcceptCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			CompassMain.sendMessage(sender, "Vous devez être un joueur");
			return false;
		}
		
		Player player = (Player) sender;
		PositionInvitation invitation = CompassMain.getPositionInvitationManager().getInvitationTarget(player);
		
		if(invitation == null) {
			CompassMain.sendMessage(player, "Vous n'avez aucune invitation");
			return false;
		}
		
		CompassMain.sendMessage(player, "Vous avez acceptée la demande");
		invitation.notifySender(true);
		invitation.redirectCompassSender();
		CompassMain.getPositionInvitationManager().removeInvitation(invitation);
		return true;
	}
}

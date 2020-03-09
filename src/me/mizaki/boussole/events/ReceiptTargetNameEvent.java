package me.mizaki.boussole.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.aypi.utils.Timer;
import com.aypi.utils.inter.TimerFinishListener;

import me.mizaki.boussole.main.CompassMain;
import me.mizaki.boussole.utils.PositionInvitation;

public class ReceiptTargetNameEvent implements Listener {

	@EventHandler
	public void onReceipt(AsyncPlayerChatEvent event) {
		if(CompassMain.getSearchDemands().contains(event.getPlayer().getName())) {
			Player target = CompassMain.getInstance().getServer().getPlayer(event.getMessage());
			if(target != null) {
				PositionInvitation invitation = new PositionInvitation(event.getPlayer(), target);
				CompassMain.sendMessage(event.getPlayer(), "Invitation envoyée");
				CompassMain.getPositionInvitationManager().addInvitation(invitation);
				invitation.notifyTarget();
				
				CompassMain.getInstance().getServer().getScheduler().runTask(CompassMain.getInstance(), new Runnable() {
					public void run() {
						Timer timer = new Timer(CompassMain.getInstance(), 10, new TimerFinishListener() {
							
							@Override
							public void execute() {
								if(CompassMain.getPositionInvitationManager().containsInvitation(invitation)) {
									CompassMain.getPositionInvitationManager().removeInvitation(invitation);
									invitation.notifySender(false);
									CompassMain.sendMessage(target, "Temps écoulé");
								}
							}
						});
						timer.start();
					}
				});
			}
			else
				CompassMain.sendMessage(event.getPlayer(), "Joueur introuvable");
		
			CompassMain.getSearchDemands().remove(event.getPlayer().getName());
			event.setCancelled(true);
		}
	}
}

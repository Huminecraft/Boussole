package me.mizaki.boussole.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class PositionInvitationManager {

	private List<PositionInvitation> list;
	
	public PositionInvitationManager() {
		this.list = new ArrayList<>();
	}
	
	public boolean addInvitation(PositionInvitation invitation) {
		return this.list.add(invitation);
	}
	
	public boolean removeInvitation(PositionInvitation invitation) {
		return this.list.remove(invitation);
	}
	
	public boolean containsInvitation(PositionInvitation invitation) {
		return this.list.contains(invitation);
	}
	
	public PositionInvitation getInvitationSender(Player sender) {
		for(PositionInvitation i : this.list) {
			if(i.getSender().getName().equals(sender.getName()))
				return i;
		}
		return null;
	}
	
	public PositionInvitation getInvitationTarget(Player target) {
		for(PositionInvitation i : this.list) {
			if(i.getTarget().getName().equals(target.getName()))
				return i;
		}
		return null;
	}
}

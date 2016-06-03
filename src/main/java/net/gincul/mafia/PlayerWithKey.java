package net.gincul.mafia;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PlayerWithKey extends Player {

	public PlayerWithKey(Player user) {
		super(user.playerName);
		this.playerId = user.playerId;
		this.playerKey = user.playerKey;
	}

	@JsonIgnore(false)
	@Override
	public UUID getPlayerKey() {
		return super.getPlayerKey();
	}
	
}

package net.gincul.mafia;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Player {

	public enum PlayerState {
		ALIVE, KILLED, NOT_PLAYING
	}

	private static AtomicLong lastId = new AtomicLong();
	
	protected long playerId = lastId.getAndIncrement();
	
	protected UUID playerKey = UUID.randomUUID();
	
	protected String playerName;
	
	private PlayerState playerState = PlayerState.ALIVE;
	
	private Set<Long> voters = new HashSet<Long>();
	
	public Player(String playerName) {
		this.playerName = playerName;
	}
	
	public Long getPlayerId() {
		return playerId;
	}

	@JsonIgnore
	public UUID getPlayerKey() {
		return playerKey;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public PlayerState getPlayerState() {
		return playerState;
	}
	
	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
	}
	
	public Set<Long> getVoters() {
		return voters;
	}
	
	public void vote(long votingPlayerId) {
		voters.add(votingPlayerId);
	}
	
	public void unvote(long unvotingPlayerId) {
		voters.remove(unvotingPlayerId);
	}
}

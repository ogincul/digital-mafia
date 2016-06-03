package net.gincul.mafia;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Game {

	private static AtomicLong lastId = new AtomicLong();
	
	private long gameId = lastId.getAndIncrement();
	
	private UUID gameKey = UUID.randomUUID();
	
	private Map<Long,Player> idToPlayerMap = new HashMap<Long,Player>();
	
	public long getGameId() {
		return gameId;
	}
	
	public UUID getGameKey() {
		return gameKey;
	}
	
	public void addPlayer(Player player) {
		idToPlayerMap.put(player.getPlayerId(), player);
	}
	
	public Player getPlayer(long playerId) {
		return idToPlayerMap.get(playerId);
	}
	
	@JsonIgnore
	public Collection<Player> getPlayers() {
		return idToPlayerMap.values();
	}
	
	public void votePlayer(long playerId, long votingPlayerId) {
		Player user = idToPlayerMap.get(playerId);
		if (user != null) {
			user.vote(votingPlayerId);
		}
	}
	
	public void unvotePlayer(long playerId, long unvotingPlayerId) {
		Player player = idToPlayerMap.get(playerId);
		if (player != null) {
			player.unvote(unvotingPlayerId);
		}
	}
	
}

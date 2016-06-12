package net.gincul.mafia;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.gincul.mafia.Player.PlayerState;

@RestController
public class MafiaController {	
	
	private Map<Long,Game> idToGameMap = new HashMap<Long,Game>();

    @RequestMapping("/new")
    public Game createGame() {
    	Game newGame = new Game();
    	idToGameMap.put(newGame.getGameId(), newGame);
        
    	return newGame;
    }
    
    @RequestMapping("/join")
    public ResponseEntity<PlayerWithKey> joinGame(Long gameId, String playerName) {
    	if (gameExists(gameId)) {
        	Game game = idToGameMap.get(gameId);    	
    		Player player = new Player(playerName);
            game.addPlayer(player);
            return new ResponseEntity<PlayerWithKey>(new PlayerWithKey(player), HttpStatus.OK); 
    	}
    	
    	return new ResponseEntity<PlayerWithKey>(HttpStatus.BAD_REQUEST);
    }
    
    @CrossOrigin
    @RequestMapping("/players")
    private ResponseEntity<Collection<Player>> getGamePlayers(Long gameId, String gameKey, Long playerId, String playerKey) {
    	if (isGameKeyValid(gameId, gameKey) || (gameExists(gameId) && isPlayerKeyValid(gameId, playerId, playerKey))) {
        	Game game = idToGameMap.get(gameId);
            return new ResponseEntity<Collection<Player>>(game.getPlayers(), HttpStatus.OK);
    	}
    	
    	return new ResponseEntity<Collection<Player>>(HttpStatus.BAD_REQUEST);
	}
    
    @RequestMapping("/leave")
    private ResponseEntity<Player> leaveGame(Long gameId, String gameKey, Long playerId) {
    	if (isGameKeyValid(gameId, gameKey) && playerExists(gameId, playerId)) {
        	Game game = idToGameMap.get(gameId);
    		Player player = game.getPlayer(playerId);
    		player.setPlayerState(PlayerState.NOT_PLAYING);
            return new ResponseEntity<Player>(HttpStatus.OK);
    	}
    	
    	return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
	}
    
    @RequestMapping("/kill")
    private ResponseEntity<Player> killPlayer(Long gameId, String gameKey, Long playerId) {
    	if (isGameKeyValid(gameId, gameKey) && playerExists(gameId, playerId)) {
        	Game game = idToGameMap.get(gameId);
    		Player player = game.getPlayer(playerId);
    		player.setPlayerState(PlayerState.KILLED);
            return new ResponseEntity<Player>(HttpStatus.OK);
    	}
    	
    	return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
	}
    
    @RequestMapping("/vote")
    private ResponseEntity<Player> votePlayer(Long gameId, Long playerId, Long votingPlayerId, String votingPlayerKey) {
    	if (isPlayerKeyValid(gameId, votingPlayerId, votingPlayerKey) && playerExists(gameId, playerId)) {
    		Game game = idToGameMap.get(gameId);
        	game.votePlayer(playerId, votingPlayerId);
            return new ResponseEntity<Player>(HttpStatus.OK);
    	}
    	
    	return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
	}

    @RequestMapping("/unvote")
    private ResponseEntity<Player> unvotePlayer(Long gameId, Long playerId, Long unvotingPlayerId, String unvotingPlayerKey) {
    	if (isPlayerKeyValid(gameId, unvotingPlayerId, unvotingPlayerKey) && playerExists(gameId, playerId)) {
    		Game game = idToGameMap.get(gameId);
        	game.unvotePlayer(playerId, unvotingPlayerId);
            return new ResponseEntity<Player>(HttpStatus.OK);
    	}
    	
    	return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
	}
    
    private boolean gameExists(Long gameId) {
    	if (gameId != null) {
    		Game game = idToGameMap.get(gameId);
    		if (game != null) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private boolean playerExists(Long gameId, Long playerId) {
    	if (gameExists(gameId) && playerId != null) {
    		Game game = idToGameMap.get(gameId);
    		Player player = game.getPlayer(playerId);
    		if (player != null) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private boolean isGameKeyValid(Long gameId, String gameKey) {
    	if (gameExists(gameId)) {
    		Game game = idToGameMap.get(gameId);
    		if (gameKey != null && gameKey.equals(game.getGameKey().toString())) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private boolean isPlayerKeyValid(Long gameId, Long playerId, String playerKey) {
    	if (playerExists(gameId, playerId)) {
    		Game game = idToGameMap.get(gameId);
    		Player player = game.getPlayer(playerId);
    		if (playerKey != null && playerKey.equals(player.getPlayerKey().toString())) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
}
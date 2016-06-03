## Digital Mafia Web Service

### Create game
/mafia/new  

Parameters:
> gameId  
> gameKey

Example:
```
http://localhost:8080/mafia/new
```

### Join game
/mafia/join?gameId=0&playerName="Oleg"

Parameters:
> playerId  
> playerKey

Example:
```
http://localhost:8080/mafia/join?gameId=0&playerName=Oleg
```

### Get players
/mafia/players?gameId=0&gameKey=abc

Parameters:
> gameId  
> gameKey

/mafia/players?gameId=0&playerId=0&playerKey=abc

Parameters:
> gameId  
> playerId  
> playerKey

Example:
```
http://localhost:8080/mafia/players?gameId=0&gameKey=84e8ce16-8853-431b-a756-41b29a2f86d9
http://localhost:8080/mafia/players?gameId=0&playerId=0&playerKey=e61e80a0-cefe-4b64-8f04-02990773739e
```

### Leave game
/mafia/leave?gameId=0&gameKey=abc&playerId=0

Parameters:
> gameId  
> gameKey

Example:
```
http://localhost:8080/mafia/leave?gameId=0&gameKey=84e8ce16-8853-431b-a756-41b29a2f86d9&playerId=0
```

### Kill player
/mafia/kill?gameId=0&gameKey=abc&playerId=0

Parameters:
> gameId
> gameKey
> playerId

Example:
```
http://localhost:8080/mafia/kill?gameId=0&gameKey=84e8ce16-8853-431b-a756-41b29a2f86d9&playerId=0
```

### Vote player
/mafia/kill?gameId=0&playerId=0&votingPlayerId=0&votingPlayerKey=abc

Parameters:
> gameId  
> playerId  
> votingPlayerId  
> votingPlayerKey

Example:
```
http://localhost:8080/mafia/vote?gameId=0&playerId=0&votingPlayerId=0&votingPlayerKey=e61e80a0-cefe-4b64-8f04-02990773739e
```

### Unvote player
/mafia/unvote?gameId=0&playerId=0&unvotingPlayerId=0&unvotingPlayerKey=abc

Parameters:
> gameId  
> playerId  
> unvotingPlayerId  
> unvotingPlayerKey

Example:
```
http://localhost:8080/mafia/unvote?gameId=0&playerId=0&unvotingPlayerId=0&unvotingPlayerKey=e61e80a0-cefe-4b64-8f04-02990773739e
```
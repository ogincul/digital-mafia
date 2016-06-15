var app = angular.module('myApp', []);


app.controller('ctrl', function($scope, $http) {

	$scope.players = [];
	$scope.gameId = "";
	$scope.gameKey = "";

	var playersToMap = function() {

		$scope.playersMap = [];
		var abort = false;
		for(var i=0; i<$scope.players.length; i++) {
			for(var j=0; j<$scope.players.length && !abort; j++) {
				for(var k=0; k<$scope.players[j].voters.length && !abort; k++) {
					if($scope.players[i].playerId === $scope.players[j].voters[k]) {
						$scope.playersMap.push({"player": $scope.players[i].playerName, "status": $scope.players[i].playerState, "score": $scope.players[i].voters.length, "votedFor": $scope.players[j].playerName})
						abort = true;
					}
				}
			}
			if(!abort) {
				$scope.playersMap.push({"player": $scope.players[i].playerName, "status": $scope.players[i].playerState, "score": $scope.players[i].voters.length, "votedFor": ""});	
			}
			abort=false;
		}
		$scope.playersMap.sort(comparator);
	}

    var comparator = function (x,y) {
    	return ((x.status == y.status) ? 0 : ((x.status > y.status) ? 1 : -1 )) ||
      			y.score - x.score || 
      			((x.player == y.player) ? 0 : ((x.player > y.player) ? 1 : -1 ));
    }

	var checkForUpdates = function(url, time) {

		$http.get(url).then(function(response) {
			
	      	if(response.data.length !== $scope.players.length) {
	      		$scope.playersMap = [];
				$scope.players = [];
				for(var i=0; i<response.data.length; i++) {
					$scope.players.push(response.data[i]);
				}
				playersToMap();
	      	} else {
		      	for(var i=0; i<response.data.length; i++) {
		      		if(response.data[i].playerName === $scope.players[i].playerName && response.data[i].voters.length !== $scope.players[i].voters.length) {
		      			$scope.players[i].voters.length = response.data[i].voters.length;
		      		}
		      	}
		      	playersToMap();
	      	}
	  	});
		
		setTimeout(function() { checkForUpdates(url); }, time);

	}

	$scope.getPlayers = function() {

		var url = "./players?gameId=" + $scope.gameId + "&gameKey=" + $scope.gameKey;

		$http.get(url).then(function(response) {
	      	for(var i=0; i<response.data.length; i++) {
	      		$scope.players.push(response.data[i]);
	      	}
	      	playersToMap();
	  	});
	  	checkForUpdates(url, 5000);
	}

});
function Players($scope, $http, $location) {
    $http.get('./players?gameId=' + $location.search()['gameId'] + '&gameKey=' + $location.search()['gameKey']).
        success(function(data) {
            $scope.players = data;
        });
}
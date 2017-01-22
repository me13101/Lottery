/**
 *
 */

// function newsapi() {
//     $.ajax({
//         url: "https://newsapi.org/v1/sources?source=the-next-web&sortBy=latest&apiKey=ca73649cfc624eaf975ffa109305e988",
//         context: document.getElementById("news"),
//         success: function(result){

//         }});
//         //console.log("got here");
//     }
var league = angular.module('league', []);

league.controller('leagueController', function ($scope, $http) {
    //document.cookie += "test: this is a test";
	var quickOrder = getOrderCookie();
	if (quickOrder.length > 0){
	    $scope.leagues = quickOrder;
    }

	$http.get("https://newsapi.org/v1/articles?source=nfl-news&sortBy=latest&apiKey=ca73649cfc624eaf975ffa109305e988")
	.then(function(response) {
		$scope.newsArticles = response.data.articles;
		console.log($scope.newsArticles);
	});

    function getOrderCookie() {
        //var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        if (decodedCookie) {
            var ca = decodedCookie.split(';');
            var cb = decodedCookie.split(':');
            if (cb[0] == "order") {
                var c = cb[1];
                c = cb[1].split(',');
                return c;
            }
        }
        return "";
    }
});

//var app = angular.module('myApp', []);
//app.controller('myCtrl', function($scope, $http) {
//  $http.get("https://newsapi.org/v1/sources?source=the-next-web&sortBy=latest&apiKey=ca73649cfc624eaf975ffa109305e988")
//  .then(function(response) {
//      $scope.newsArticles = response.data;
//  });
//});
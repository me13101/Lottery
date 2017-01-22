/** lottery.js
* js functions for lottery application
*
**/

var draft = angular.module('lottery', []);

draft.controller('lotteryController', function ($scope, $http) {
$scope.draftOrder;
function getList(order){
    $scope.draftOrder = order;
    document.cookie = "order:"+order;
    window.location = "leagueDashboard.html";
    console.log("got here");

}
function runLottery(range){
    return Math.floor((Math.random() * range));
}
document.getElementById("runLottery").addEventListener("click", function(){
  //runLottery();
	var numTeams = $( "#teamSelect" ).val();
    var order = [];
    for (var i = 0; i < numTeams; i++){
        var teamNames = "#teamname"+(i+1);
        var j = runLottery(numTeams);
        if (order[j] != null){
            i--;
            continue;
        }
        order[j] = $( teamNames ).val();
    }
    getList(order)
	// var xhr = new XMLHttpRequest();
	// xhr.onreadystatechange = function(data) {
	// 	if (xhr.readyState == 4 && xhr.status == 200) {
	// 		//console.log(data.target.response);
	// 		getList(data.target.response);
	// 	}
	// }
	// xhr.open('GET', 'Lottery?league_name='+leagueName+'&num_teams='+numTeams+'&commName='+commName+'&ownerList='+ownerList, true);
	// xhr.send(null);
});


});
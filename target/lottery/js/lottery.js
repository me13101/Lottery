/** lottery.js
* js functions for lottery application
*
**/

var draft = angular.module('lottery', []);

draft.controller('lotteryController', function ($scope, $http) {

var numTeams = $( "#teamSelect" ).val();
var ownerList = [];
for (var i = 0; i <= numTeams; i++){
    ownerList[i-1] = "Player "+i;
}
function getList(order){
console.log("order: "+order);
for (var i = 0; i < order.length; i++){
    console.log(order[i]);
    }
}
function runLottery(){
//this is a test
//    $.ajax({
//        type: "POST",
//        data :JSON.stringify(customer),
//        url: "api/Customer",
//        contentType: "application/json"
//    });
}
document.getElementById("runLottery").addEventListener("click", function(){
  //runLottery();
	var numTeams = $( "#teamSelect" ).val();
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(data) {
		if (xhr.readyState == 4 && xhr.status == 200) {
			//console.log(data.target.response);
			getList(data.target.response);
		}
	}
	xhr.open('GET', '/Lottery?league_name='+leagueName+'&num_teams='+numTeams+'&commName='+commName+'&ownerList='+ownerList, true);
	xhr.send(null);
});


});
var login = angular.module('login', []);
var email;
var pswd;
login.controller('loginController', function ($scope, $http) {

function login(response){
    if (response == "true"){
        document.cookie = "email="+email;
        document.cookie = "pswd="+pswd;
        window.location = "http://localhost:8080/WebContent/home.html";
    }
    else {
        alert("email/password inccorrect, please try again");
    }
}

function checkDB(email, pswd){
      var xhr = new XMLHttpRequest();
          xhr.onreadystatechange = function(data) {
              if (xhr.readyState == 4 && xhr.status == 200) {
              	login(data.target.response);
              }
          }
          xhr.open('GET', "/Authent?email="+email+"&password="+pswd, true);
          xhr.send(null);
}
document.getElementById("signIn").addEventListener("click", function(){
    email = $( "#inputEmail" ).val();
    pswd = $( "#inputPassword" ).val();
    checkDB(email, pswd);
});
//	var host = window.location.host;
//    	window.open(host+"/WebContent/playerDashboard.html");
//	});

//      var xhr = new XMLHttpRequest();
//          xhr.onreadystatechange = function() {
//              if (xhr.readyState == 4 && xhr.status == 200) {
//              	//leagueScreen();
//              }
//          }
//          xhr.open('GET', '/login', true);
//          xhr.send(null);
});
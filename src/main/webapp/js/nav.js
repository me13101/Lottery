/**
 * 
 */

//var va = document.cookie;
//if (va.includes("email=")){
//  document.getElementById("login").style.display = 'none';
//  document.getElementById("account").style.display = 'block';
//}
//else {
//  document.getElementById("login").style.display = 'block';
//  document.getElementById("account").style.display = 'none';
//}

document.getElementById("createLeague").addEventListener("click", function(){
	window.location = "commCreateLeague.html";
});

document.getElementById("joinLeague").addEventListener("click", function(){
  window.location = "playerDashboard.html";
});

//document.getElementById("login").addEventListener("click", function(){
//	window.location = "http://localhost:8080/WebContent/login.html";
//	});

document.getElementById("lottery").addEventListener("click", function(){
    window.location = "lottery.html";
});
document.getElementById("navbar01").addEventListener("click", function(){
    window.location = "home.html";
    });


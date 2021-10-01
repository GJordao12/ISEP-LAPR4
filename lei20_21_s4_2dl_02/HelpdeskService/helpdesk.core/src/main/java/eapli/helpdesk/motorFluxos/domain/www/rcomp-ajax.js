
// IMPORTANT: notice the next request is scheduled only after the
//            previous request is fully processed either successfully
//	      or not.

function refreshDate() {
	var request = new XMLHttpRequest();
        var vBoard=document.getElementById("data");
        
        request.onload = function() {
            vBoard.innerHTML = this.responseText;
            vBoard.style.color="black";
            setTimeout(refreshDate, 2000);
            };
            
        request.ontimeout = function() {
            vBoard.innerHTML = "Server timeout, still trying ...";
            vBoard.style.color="red";
            setTimeout(refreshDate, 100);
        };
        
        request.onerror = function() { 
            vBoard.innerHTML = "No server reply, still trying ...";
            vBoard.style.color="red";
            setTimeout(refreshDate, 5000);
        };
        
  	request.open("GET", "/data", true);
	request.timeout = 5000;
  	request.send();
}

function refreshTable() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("Tickets");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="white";
        setTimeout(refreshTable, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTable, 100);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTable, 5000);
    };

    request.open("GET", "/Tickets", true);
    request.timeout = 5000;
    request.send();
}

function refreshTable1() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("Percentagens");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="white";
        setTimeout(refreshTable1, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTable1, 100);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshTable1, 5000);
    };

    request.open("GET", "/Percentagens", true);
    request.timeout = 5000;
    request.send();
}



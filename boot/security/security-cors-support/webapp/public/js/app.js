var USERNAME = "user";
var PASSWORD = "password"

$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/getApps",
        headers: {
   		 "Authorization": "Basic " + btoa(USERNAME + ":" + PASSWORD)
  		}
    }).then(function(data, status, jqxhr) {
    
    	for(index in data){
    		
    		var row = "<tr><td>"+data[index].id+"</td><td>"+data[index].name+"</td><td>"+data[index].status+"</td></tr>"
    		
    		$('#appTable tbody').append(row);	
    	}
       console.log(jqxhr);
    });
});
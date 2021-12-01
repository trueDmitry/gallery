window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

var locatURL = (function () {
	var url = new URL(window.location.href); 
	
	function  setParam(name, value) {
		url.searchParams.set(name, value);
	}
	
	function navigate(){
		window.location.replace(url.toString());		
	}
	
	return {
		setParam : setParam,
		navigate : navigate 
	};
})();

function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
function eraseCookie(name) {   
    document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}


$(document).ready(function(){
	
	
  // paginator page click
  $(".dataTable-pagination-list").find("a").click(function(){
	locatURL.setParam("page", $(this).data("page"));
	locatURL.navigate();
  });

  $("#language-selector").find("a").click(function(){
	var landId = $(this).data("lang");
	console.log(landId );
	setCookie("lang", landId, 360);
	document.location.reload();
  });


});

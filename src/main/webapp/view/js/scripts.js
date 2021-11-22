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

$(document).ready(function(){
	
	
  // paginator page click
  $(".dataTable-pagination-list").find("a").unbind().click(function(event){
///	event.stopPropagation();
	locatURL.setParam("page", $(this).data("page"));
	locatURL.navigate();
  });

});

<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/view/components/init.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="/view/components/Head.jsp" %>
</head>
<body class="sb-nav-fixed">
	<%@include file="/view/components/Navbar.jsp" %>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<%@include file="/view/components/Sidebar.jsp" %>
		</div>
		<div id="layoutSidenav_content">
			<footer class="py-4 bg-light mt-auto">
				<%@include file="/view/components/Footer.jsp" %>
			</footer>
		</div>
	</div>
	<%@include file="/view/components/BasementScripts.jsp" %>
</body>
</html>

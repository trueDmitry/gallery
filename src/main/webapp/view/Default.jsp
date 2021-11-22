<%@page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="/view/components/Head.jsp" />
</head>
<body class="sb-nav-fixed">
	<jsp:include page="/view/components/Navbar.jsp" />
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<jsp:include page="/view/components/Sidebar.jsp" />
		</div>
		<div id="layoutSidenav_content">
			<footer class="py-4 bg-light mt-auto">
				<jsp:include page="/view/components/Footer.jsp" />
			</footer>
		</div>
	</div>
	<jsp:include page="/view/components/BasementScripts.jsp" />
</body>
</html>

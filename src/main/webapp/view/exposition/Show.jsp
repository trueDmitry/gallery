<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="userRoleName" value="user" />
<c:set var="expo" value="${expoView.exposition}" />

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
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">Exposition</h1>
					<ol class="breadcrumb mb-4">
						<!--					<li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
					<li class="breadcrumb-item active">Tables</li> -->
					</ol>
					<div class=tools>
						<c:if test="${not empty user && not expoView.isTicketBought}">
							<a class="btn btn-primary" href="exposition/Acquire?id=${expo.getId()}" >Purchase</a>
						</c:if>
					</div>
					<br>
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> ${expo.getTheme()}
						</div>
						<div class="card-body">
							<c:if test="${expoView.isTicketBought}">
								<div> 
								 	<span>You have a ticket</span>
								</div>
							</c:if>
							<label for="price">Price</label> 
							<input name="price" type="text" readonly="readonly" value="${expo.getPrice()}"> 
							<label for="start">Start</label> 
							<input name="start" type="text" readonly="readonly" value="${expo.getStart()}"> 
							<label for="end">End</label> 
							<input name="end" type="text" readonly="readonly" value="${expo.getEnd()}"> 
							<label for="fromTime">opens at</label> 
							<input name="open" type="text" readonly="readonly" value="${expo.getOpen()}"> 
							<label for="tillTime">closed at</label> 
							<input name="close" type="text" readonly="readonly" value="${expo.getClose()}">

							<label>Occupied rooms</label>
							<ul class="rooms">
								<c:forEach items="${expo.getRooms()}" var="room">
									<li class=room_list_element id="${room.getId()}">${room.getName()}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</main>

			<footer class="py-4 bg-light mt-auto">
				<jsp:include page="/view/components/Footer.jsp" />
			</footer>
		</div>
	</div>
	<jsp:include page="/view/components/BasementScripts.jsp" />
</body>
</html>

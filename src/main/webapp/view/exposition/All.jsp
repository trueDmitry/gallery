<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8" %>

<c:set var="adminRoleName" value="admin" />
<c:set var="userRoleName" value="user" />
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
					<h1 class="mt-4">Expositions</h1>
					<ol class="breadcrumb mb-4">
						<!--					<li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
					<li class="breadcrumb-item active">Tables</li> -->
					</ol>
					<div class=tools>
						<c:if test="${user.hasRole(adminRoleName) == true}">
							<a class="btn btn-primary" href="exposition/Edit" >Add</a>
						</c:if>
					</div>
					<br>

					<!-- div class="dataTable-dropdown">
						<label> <select class="dataTable-selector">
								<option value="5">5</option>
								<option value="10" selected="">10</option>
								<option value="15">15</option>
								<option value="20">20</option>
								<option value="25">25</option>
						</select> entries per page
						</label>
					</div -->
					<br>	
					<c:forEach items="${data.paginator.fetchData()}" var="expoView">
						<c:set var="expo" value="${expoView.exposition}" />
						<div class="card mb-4">
							<div class="card-header">
								<i class="fas fa-table me-1"></i> 
								<a href="exposition/${user.hasRole(adminRoleName) == true?"Edit" : "Show"}?id=${expo.getId()}">${expo.getTheme()} </a>
								&nbsp;
								<c:if test="${expo.getPublished() == true && user.hasRole(adminRoleName) == true }">
							 		<span>&lt${expo.getId()}&gt</span>
									<c:if test="${expo.getPublished() == true}">
								 		<span>published</span>
									</c:if>
								</c:if>
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

								<c:if test="${expo.getRooms().size() > 0}">
									<div>
										<label >Occupied rooms</label>
										<ul class="rooms" >
											<c:forEach items="${expo.getRooms()}" var="room">
												<li class=room_list_element id="${room.getId()}">${room.getName()}</li>
											</c:forEach>
										</ul>
									</div>
								</c:if>
							</div>
						</div>
					</c:forEach>
					<div class="dataTable-bottom">
						<jsp:include page="/view/components/Paging.jsp" />
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

<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/view/components/init.jsp"%>

<c:set var="adminRoleName" value="admin" />
<c:set var="userRoleName" value="user" />

<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="/view/components/Head.jsp" %>
</head>
<body class="sb-nav-fixed">
	<%@include file="/view/components/Navbar.jsp"%>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<%@include file="/view/components/Sidebar.jsp"%>
		</div>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4"><fmt:message key="content.title.expositions"/></h1>
					<ol class="breadcrumb mb-4">
						<!-- <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
					<li class="breadcrumb-item active">Tables</li> -->
					</ol>
					<div class=tools>
						<c:if test="${user.hasRole(adminRoleName) == true}">
							<a class="btn btn-primary" href="exposition/Edit" ><fmt:message key="button.add"/></a>
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
								 		<span><fmt:message key="exposition.published"/></span>
									</c:if>
								</c:if>
							</div>
							<div class="card-body">
								<c:if test="${expoView.isTicketBought}">
									<div> 
									 	<span><fmt:message key="exposition.user_has_ticket"/></span>
									</div>
								</c:if>
								<label for="price"><fmt:message key="exposition.price"/></label> 
								<input name="price" type="text" readonly="readonly" value="${expo.getPrice()}">

								<label for="start"><fmt:message key="exposition.start"/></label>
								<input name="start" type="text" readonly="readonly" value="${expo.getStart()}">

								<label for="end"><fmt:message key="exposition.end"/></label> 
								<input name="end" type="text" readonly="readonly" value="${expo.getEnd()}">
								 
								<label for="fromTime"><fmt:message key="exposition.open"/></label>
								<input name="open" type="text" readonly="readonly" value="${expo.getOpen()}">
								 
								<label for="tillTime"><fmt:message key="exposition.close"/></label>
								<input name="close" type="text" readonly="readonly" value="${expo.getClose()}">

								<c:if test="${expo.getRooms().size() > 0}">
									<div>
										<label ><fmt:message key="exposition.rooms"/></label>
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
						<%@include file="/view/components/Paging.jsp"%>
					</div>
				</div>
			</main>

			<footer class="py-4 bg-light mt-auto">
				<%@include file="/view/components/Footer.jsp"%>
			</footer>
		</div>
	</div>
	<jsp:include page="/view/components/BasementScripts.jsp" />
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/view/components/init.jsp"%>

<c:set var="userRoleName" value="user" />
<c:set var="expo" value="${expoView.exposition}" />

<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="/view/components/Head.jsp" %>
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
					<h1 class="mt-4"><fmt:message key="content.form.exposition"/></h1>
					<ol class="breadcrumb mb-4">
						<!--					<li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
					<li class="breadcrumb-item active">Tables</li> -->
					</ol>
					<div class=tools>
						<c:if test="${not empty user && not expoView.isTicketBought}">
							<a class="btn btn-primary" href="exposition/Acquire?id=${expo.getId()}" ><fmt:message key="content.form.purchase"/></a>
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

							<label><fmt:message key="exposition.rooms"/></label>
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

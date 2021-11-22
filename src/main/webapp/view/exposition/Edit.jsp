<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html; charset=UTF-8" %>

<c:set var="expo" value="${expoEditView.expo}" />
<c:set var="rooms" value="${expoEditView.rooms}" />

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
					<form method="post" action="exposition/Save">
					<div>
						<button class ="btn btn-primary" type="submit"> Save </button>
						<a class ="btn btn-primary" href ="exposition/Delete?id=${expo.id}" > Delete </a>
					</div>
					<br>
					<div class="card mb-4">
						<div class="card-header">
							<c:if test="${expo.getId() > 0 }">
								<span>${expo.getTheme()} &lt${expo.getId()}&gt</span>
							</c:if>
							<c:if test="${expo.getId() == 0 }">
								<span>New exposition</span>
							</c:if>
							  
						</div>
						<div class="card-body">
							<div class="exposition ">
								<input type="hidden" value="${expo.getId()}" name="id" >
								
								<label for="theme">Theme</label> 
								<input name="theme" type="text" value="${expo.getTheme()}"> 
								 
								<label for="price">Price</label> 
								<input name="price" type="text" value="${expo.getPrice()}"> 
									
								<label for="start">Start</label> 
								<input name="start" type="text" value="${expo.getStart()}"> 
									
								<label for="end">End</label> 
								<input name="end" type="text" value="${expo.getEnd()}"> 
									
								<label for="fromTime">opens at</label> 
								<input name="open" type="text" value="${expo.getOpen()}">
									
								<label for="tillTime">closed at</label> 
								<input name="close" type="text" value="${expo.getClose()}">

								<label>Occupied rooms</label>
								<div class="rooms">
									<c:forEach items="${rooms}" var="room">
										<label>
											<input type="checkbox" name=room value="${room.getId()}" ${ expo.getRooms().contains(room)? "checked": "" }>
											<span>${room.getName()}</span>
										</label>
									</c:forEach>
									<br>
								</div>
								<label >
									<input name="published" type="checkbox" value="true"  ${expo.getPublished() ? "checked": ""}>
									<span>publish</span>
								</label> 
								
								
							</div>
						</div>
					</div>
							</form>	
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

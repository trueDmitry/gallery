<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/view/components/init.jsp"%>

<c:set var="expo" value="${expoEditView.expo}" />
<c:set var="rooms" value="${expoEditView.rooms}" />

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
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4"><fmt:message key="content.form.exposition"/></h1>
					<ol class="breadcrumb mb-4">
						<!--					<li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
					<li class="breadcrumb-item active">Tables</li> -->
					</ol>
					<form method="post" action="exposition/Save">
					<div>
						<button class ="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
						<a class ="btn btn-primary" href ="exposition/Delete?id=${expo.id}" ><fmt:message key="button.delete"/></a>
					</div>
					<br>
					<div class="card mb-4">
						<div class="card-header">
							<c:if test="${expo.getId() > 0 }">
								<span>${expo.getTheme()} &lt${expo.getId()}&gt</span>
							</c:if>
							<c:if test="${expo.getId() == 0 }">
								<span><fmt:message key="content.form.title.new"/></span>
							</c:if>
							  
						</div>
						<div class="card-body">
							<div class="exposition ">
								<input type="hidden" value="${expo.getId()}" name="id" >
								
								<label for="theme"><fmt:message key="exposition.theme"/></label> 
								<input name="theme" type="text" value="${expo.getTheme()}"> 
								 
								<label for="price"><fmt:message key="exposition.price"/></label> 
								<input name="price" type="text" value="${expo.getPrice()}"> 
									
								<label for="start"><fmt:message key="exposition.start"/></label> 
								<input name="start" type="text" value="${expo.getStart()}"> 
									
								<label for="end"><fmt:message key="exposition.end"/></label> 
								<input name="end" type="text" value="${expo.getEnd()}"> 
									
								<label for="fromTime"><fmt:message key="exposition.open"/></label> 
								<input name="open" type="text" value="${expo.getOpen()}">
									
								<label for="tillTime"><fmt:message key="exposition.close"/></label> 
								<input name="close" type="text" value="${expo.getClose()}">

								<label><fmt:message key="exposition.rooms"/></label>
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
									<span><fmt:message key="exposition.published"/></span>
								</label> 
							</div>
						</div>
					</div>
				</form>	
				</div>
			</main>

			<footer class="py-4 bg-light mt-auto">
				<%@include file="/view/components/Footer.jsp" %>
			</footer>
		</div>
	</div>
	<jsp:include page="/view/components/BasementScripts.jsp" />
</body>
</html>

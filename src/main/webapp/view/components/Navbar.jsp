<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
	<a class="navbar-brand ps-3" href="${pageContext.request.contextPath}">Gallery</a>
	<!-- Navbar-->
	<ul
		class="navbar-nav d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
			aria-expanded="false"> <i class="fas fa-user fa-fw"></i>
		</a>

			<ul class="dropdown-menu dropdown-menu-end"
				aria-labelledby="navbarDropdown">
				<!--  <li><a class="dropdown-item" href="#!">Settings</a></li>
					<li><a class="dropdown-item" href="#!">Activity Log</a></li>
					<li><hr class="dropdown-divider" /></li>
					 -->
				<li><c:choose>
						<c:when test="${app.getUser() == null}">
							<a class="dropdown-item" id="login"
								href="Login">Login</a>
						</c:when>
						<c:otherwise>
							<a class="dropdown-item" id="login"
								href="Logout">Logout</a>
						</c:otherwise>
					</c:choose></li>
			</ul>
	</ul>
</nav>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/view/components/init.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<%@include file="/view/components/Head.jsp" %>
</head>
<body class="bg-primary">
	<div id="layoutAuthentication">
		<div id="layoutAuthentication_content">
			<main>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-5">
							<div class="card shadow-lg border-0 rounded-lg mt-5">
								<div class="card-header">
									<h3 class="text-center font-weight-light my-4"><fmt:message key="сontent.login.title"/></h3>
								</div>
								<div class="card-body">
									<form action="Authenticate" method="post">
										<div class="form-floating mb-3">
											<input name="inputEmail" class="form-control" id="inputEmail" type="email" placeholder="name@example.com" />
											<label for="inputEmail"><fmt:message key="form.login.email"/></label>
										</div>
										<div class="form-floating mb-3">
											<input name ="inputPassword" class="form-control" id="inputPassword" type="password" placeholder="Password" /> 
											<label for="inputPassword"><fmt:message key="form.login.password"/></label>
										</div>
										<!-- div class="form-check mb-3">
											<input class="form-check-input" id="inputRememberPassword" type="checkbox" value="" /> 
											<label class="form-check-label" for="inputRememberPassword"><fmt:message key="form.login.remember_pwd"/></label>
										</div -->
										<div class="d-flex align-items-center justify-content-between mt-4 mb-0">
											<span> </span> <!--  a class="small" href="password.html"><fmt:message key="form.login.forgot_pwd"/></a -->
											<button class="btn btn-primary" type="submit"><fmt:message key="button.login"/></button>
										</div>
									</form>
									
								</div>
								<div class="card-footer text-center py-3">
									<!--  div class="small">
										<a href="register.html"><fmt:message key="form.login.singup"/></a>
									</div -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
		<div id="layoutAuthentication_footer">
			<footer class="py-4 bg-light mt-auto">
				<%@include file="/view/components/Footer.jsp" %>
			</footer>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
</body>
</html>

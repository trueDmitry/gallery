<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="/view/components/init.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
		<%@include file="/view/components/Head.jsp" %>    
    </head>
    <body>
        <div id="layoutError">
            <div id="layoutError_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-6">
                                <div class="text-center mt-4">
                                    <img class="mb-4 img-error" src="assets/img/error-404-monochrome.svg" />
                                    <p class="lead"><fmt:message key="error.notfound.msg"/></p>
                                    <a href="">
                                        <i class="fas fa-arrow-left me-1"></i>
                                        <fmt:message key="button.home"/>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutError_footer">
                <footer class="py-4 bg-light mt-auto">
                   <%@include file="/view/components/Footer.jsp"%>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>

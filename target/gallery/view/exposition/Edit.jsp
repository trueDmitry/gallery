<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/view/components/init.jsp"%>

<c:set var="expo" value="${expoEditView.expo}" />
<c:set var="rooms" value="${expoEditView.rooms}" />

<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/view/components/Head.jsp"%>
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
          <h1 class="mt-4">
            <fmt:message key="content.form.exposition" />
          </h1>
          <!--ol class="breadcrumb mb-4">
            <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
					<li class="breadcrumb-item active">Tables</li>
          </ol-->
          <form method="post" action="exposition/Save">
            <div>
              <button class="btn btn-primary" type="submit">
                <fmt:message key="button.save" />
              </button>
              <a class="btn btn-primary" href="exposition/Delete?id=${expo.id}"><fmt:message key="button.delete" /></a>
            </div>
            <br>
            <div class="card mb-4">
              <div class="card-header">
                <c:if test="${expo.getId() > 0 }">
                  <span>${expo.getTheme()} &lt${expo.getId()}&gt</span>
                </c:if>
                <c:if test="${expo.getId() == 0 }">
                  <span><fmt:message key="content.form.title.new" /></span>
                </c:if>

              </div>
              <div class="card-body">
                <div class="row g-3">
                  <input type="hidden" value="${expo.getId()}" name="id">
                  <div class="col-md-6">
                    <label for="form-label"><fmt:message key="exposition.theme" /></label>
                    <input name="theme" class="form-control"  type="text" value="${expo.getTheme()}">
                  </div>

                  <div class="col-md-6">
                    <label for="form-label"><fmt:message key="exposition.price" /></label>
                    <input name="price" class="form-control" type="text" value="${expo.getPrice()}">
                  </div>
                  <div class="col-md-6">
                    <label for="form-label"><fmt:message key="exposition.start" /></label>
                    <input name="start" class="form-control" type="text" value="${expo.getStart()}">
                  </div>
                  <div class="col-md-6">
                    <label for="form-label"><fmt:message key="exposition.end" /></label>
                    <input name="end" class="form-control" type="text" value="${expo.getEnd()}">
                  </div>
                  <div class="col-md-6">
                    <label for="form-label"><fmt:message key="exposition.open" /></label>
                    <input name="open" class="form-control" type="text" value="${expo.getOpen()}">
                  </div>
                  <div class="col-md-6">
                    <label for="form-label"><fmt:message key="exposition.close" /></label>
                    <input name="close" class="form-control" type="text" value="${expo.getClose()}">
                  </div>
                  
                  <div class="col-md-12">
                    <label class="form-label" ><fmt:message key="exposition.rooms" /></label>
                    <ul class="mt-2 ps-0">
                      <c:forEach items="${rooms}" var="room">
                        <li class="d-inline me-2 p-2 bg-light border rounded-3">
                        <label>
                          <input type="checkbox" name=room value="${room.getId()}" ${ expo.getRooms().contains(room)? "checked": "" }> <span>${room.getName()}</span>
                        </label>
                        </li>
                      </c:forEach>
                    </ul>   
                  </div>
                  
                  <div class="col-md-12">
                    <label class="d-inline me-2 p-2 bg-light border rounded-3"> 
                      <input name="published" type="checkbox" value="true" ${expo.getPublished() ? "checked": ""}> 
                      <fmt:message key="exposition.published" />
                    </label>
                  </div>
                </div>
              </div>
            </div>
          </form>
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

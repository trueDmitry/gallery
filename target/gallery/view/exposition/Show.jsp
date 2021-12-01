<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/view/components/init.jsp"%>

<c:set var="userRoleName" value="user" />
<c:set var="expo" value="${expoView.exposition}" />

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
            <fmt:message key="content.exposition.show.title" />
          </h1>
          <ol class="breadcrumb mb-4">
            <!--					<li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
					<li class="breadcrumb-item active">Tables</li> -->
          </ol>
          <div class=tools>
            <c:if test="${not empty user && not expoView.isTicketBought}">
              <a class="btn btn-primary" href="exposition/Acquire?id=${expo.getId()}">
                <fmt:message key="button.purchase" />
              </a>
            </c:if>
          </div>
          <br>
          <div class="card mb-4">
            <div class="card-header">
              <i class="fas fa-table me-1"></i> ${expo.getTheme()}
            </div>
            <div class="card-body">
              <div class="row g-3">
                <c:if test="${expoView.isTicketBought}">
                  <div class="col-md-12">
                    <span><fmt:message key="exposition.user_has_ticket" /></span>
                  </div>
                </c:if>
                <div class="col-md-6">                
                  <label for="form-label"><fmt:message key="exposition.start" /></label>
                  <input name="start" class="form-control"  type="text" readonly="readonly" value="${expo.getStart()}">
                </div>
                <div class="col-md-6">
                  <label for="form-label"><fmt:message key="exposition.end" /></label> 
                  <input name="end" class="form-control"  type="text" readonly="readonly" value="${expo.getEnd()}">
                </div>
                <div class="col-md-6">
                  <label for="form-label"><fmt:message key="exposition.open" /></label>
                  <input name="open" class="form-control"  type="text" readonly="readonly" value="${expo.getOpen()}">
                </div>
                <div class="col-md-6">
                  <label for="form-label"><fmt:message key="exposition.close" /></label>
                  <input name="close" class="form-control"  type="text" readonly="readonly" value="${expo.getClose()}">
                </div>
                
                <div class="col-md-6">
                  <label for="form-label"><fmt:message key="exposition.price" /></label>
                  <input name="price" class="form-control" type="text" readonly="readonly" value="${expo.getPrice()}">
                </div>
                
                <div class="col-md-12">
                  <label class="form-label"><fmt:message key="exposition.rooms" /></label>
                  <ul class="mt-2 ps-0">
                    <c:forEach items="${expo.getRooms()}" var="room">
                      <li class="d-inline me-2 p-2 bg-light border rounded-3">${room.getName()}</li>
                    </c:forEach>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>

      <footer class="py-4 bg-light mt-auto">
        <%@include file="/view/components/Footer.jsp"%>
      </footer>
    </div>
  </div>
  <%@include file="/view/components/BasementScripts.jsp"%>
</body>
</html>

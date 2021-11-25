<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="paginator" value="${data.paginator}" />

<div class="dataTable-info">
<fmt:message key="content.paging.from"/> ${paginator.getStartNumber()}
	<c:if test="${paginator.startNumber != paginator.endNumber}">
		<fmt:message key="content.paging.to"/> ${paginator.endNumber}		
	</c:if>	 
 <fmt:message key="content.paging.total"/> ${paginator.count }</div>

<c:if test="${paginator.pageCount > 1}">
	<nav class="dataTable-pagination">
		<ul class="dataTable-pagination-list">
		
			<c:if test="${paginator.hasLeftPages()}">
				<li class="">
					<a href="javascript:void(0)" data-page="${paginator.page - 1}">
						<i class="fas fa-chevron-left"></i>
					</a>
				</li>
			</c:if>	
			
			<c:forEach var="i" begin="1" end="${paginator.pageCount}">
				
				<li class="${i == paginator.page ? "active" : "" }"><a href="javascript:void(0)" data-page="${i}">${i}</a></li>
			</c:forEach>

			<c:if test="${paginator.hasRightPages()}">
				<li class="pager">
					<a href="javascript:void(0)" data-page="${paginator.page + 1}">
						<i class="fas fa-chevron-right"></i>
					</a>
				</li>
			</c:if>	
				
		</ul>
	</nav>
</c:if>

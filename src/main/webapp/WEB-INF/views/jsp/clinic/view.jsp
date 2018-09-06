<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

	<body>
	
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
	
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<fieldset>
						<legend>Clinic Details</legend>
						<table class="table table-striped">
							<tr>
								<td>Id</td>
								<td>Name</td>
								<td>Address</td>
								<td>Contact</td>
								<td>Image</td>
							</tr>
							<tr>
								<td>${clinic.id}</td>
								<td>${clinic.name}</td>
								<td>${clinic.address}</td>
								<td>${clinic.contact}</td>
								<td><img src="http://localhost:8080/${clinic.imageURL}" height="100" width="100"/></td>
							</tr>
						</table>
					</fieldset>
				</div>
			</div>
		</div>
		<jsp:include page="../fragments/footer.jsp" />
	</body>
</html>
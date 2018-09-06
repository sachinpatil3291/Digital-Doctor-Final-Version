<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />
<head>
<style type="text/css">
#clinicName{
	word-wrap: break-word;
	min-width: 150px;
	max-width: 150px;
}

#clinicAddress{
	word-wrap: break-word;
	min-width: 150px;
	max-width: 150px;
}
</style>

</head> 
<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<table class="table table-condensed">
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Address</th>
					<th>Contact</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="clinic" items="${clinic}">
				<tr>
					<td>
						${clinic.id}
					</td>
					<td id="clinicName">${clinic.name}</td>
					<td id="clinicAddress">${clinic.address}</td>
					<td>${clinic.contact}</td>
					<td>
						<spring:url value="/clinic/${clinic.id}/view" var="clinicViewUrl" />
						<spring:url value="/clinic/${clinic.id}/delete" var="clinicDeleteUrl" /> 
						<spring:url value="/clinic/${clinic.id}/update/form" var="clinicUpdateUrl" />
						<spring:url value="/clinic/${clinic.id}/upload/form" var="clinicUploadUrl" />
												
						<button class="btn btn-info btn-sm" onclick="location.href='${clinicViewUrl}'">View</button>
						<button class="btn btn-primary btn-sm" onclick="location.href='${clinicUpdateUrl}'">Update</button>
						<button class="btn btn-danger btn-sm" onclick="this.disabled=true;post('${clinicDeleteUrl}')">Delete</button>
						<button class="btn btn-primary btn-sm" onclick="location.href='${clinicUploadUrl}'">Upload</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>
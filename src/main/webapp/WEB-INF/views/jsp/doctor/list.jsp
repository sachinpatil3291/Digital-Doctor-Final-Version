<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

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
					<th>Email</th>
					<th>Clinic</th>
					<th>Contact</th>
					<th>Degree</th>
					<th>Specialist</th>	
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="doctor" items="${doctor}">
				<tr>
					<td>
						${doctor.id}
					</td>
					<td>${doctor.name}</td>
					<td>${doctor.email}</td>
					<td>${doctor.clinicName}</td>
					<td>${doctor.contact}</td>
					<td>${doctor.degree}</td>
					<td>${doctor.specialist}</td>
					<td>
						<spring:url value="/doctor/${doctor.id}/view" var="doctorViewUrl" />
						<spring:url value="/doctor/${doctor.id}/delete" var="doctorDeleteUrl" /> 
						<spring:url value="/doctor/${doctor.id}/update/form" var="doctorUpdateUrl" />
						<spring:url value="/doctor/${doctor.id}/upload/form" var="uploadDoctorImageUrl" />

						<button class="btn btn-info btn-sm" onclick="location.href='${doctorViewUrl}'">View</button>
						<button class="btn btn-primary btn-sm" onclick="location.href='${doctorUpdateUrl}'">Update</button>
						<button class="btn btn-danger btn-sm" onclick="this.disabled=true;post('${doctorDeleteUrl}')">Delete</button>
						<button class="btn btn-primary btn-sm" onclick="location.href='${uploadDoctorImageUrl}'">Upload</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>
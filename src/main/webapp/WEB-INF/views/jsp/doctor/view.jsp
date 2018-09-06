<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />
	<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<fieldset>
						<legend>Doctor Details</legend>
						<table class="table table-striped">
							<tr>
								<td>Id</td>
								<td>Name</td>
								<td>Clinic Assigned</td>
								<td>Qualification</td>
								<td>Email</td>
								<td>Contact</td>
								<td>Specialist</td>
								<td>Awards</td>
								<td>Photo</td>
							</tr>
							<tr>
								<td>${doctor.id}</td>
								<td>${doctor.name}</td>
								<td>${doctor.clinicName}</td>
								<td>${doctor.degree}</td>
								<td>${doctor.email}</td>
								<td>${doctor.contact}</td>
								<td>${doctor.specialist}</td>
								<td>${doctor.awards}</td>
								<td><img src="<%=request.getRemoteHost()%>:8080/${doctor.photo}" height="100" width="100"/></td>
							</tr>
						</table>
					</fieldset>
				</div>
			</div>
		</div>
<jsp:include page="../fragments/footer.jsp" />
</body>
</html>
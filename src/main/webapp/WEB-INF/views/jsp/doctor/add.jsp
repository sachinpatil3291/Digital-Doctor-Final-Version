<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../fragments/header.jsp" />
<div class="container">
	<h2>Add Doctor</h2>
	<br />
	<spring:url value="/doctor/add" var="doctorActionUrl" />
	<form:form class="form-horizontal" method="post" modelAttribute="addDrForm" enctype="multipart/form-data" action="${doctorActionUrl}">
		<form:hidden path="id" />
	<%-- 	<form:hidden path="clinicId" /> --%>
		<form:hidden path="photo" />
		
		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-4">
					<form:input path="name" type="text" class="form-control " id="name" placeholder="Name" />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<spring:bind path="contact">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Contact</label>
				<div class="col-sm-4">
					<form:input path="contact" class="form-control" id="contact" placeholder="Contact" />
					<form:errors path="contact" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<spring:bind path="password">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Password</label>
				<div class="col-sm-4">
					<form:password path="password" class="form-control" id="password" placeholder="Password" />
					<form:errors path="password" class="control-label" />
				</div>
			</div>
		</spring:bind>
			<spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-4">
					<form:input path="email" class="form-control" id="email" placeholder="Email" />
					<form:errors path="email" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<%-- <spring:bind path="clinicId"> --%>
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Clinic Assigned</label>
				<div class="col-sm-4">
		 			<%-- <form:select path="clinicId">
        				<c:forEach items="${clinicList}" var="clinic">
            				<option value="${clinic.id}">${clinic.name}</option>
        				</c:forEach>
    				</form:select> --%>
    				<form:select path="clinicId" items="${clinicList}" itemValue="id" itemLabel="name" />
				</div>
			</div>
		<%-- </spring:bind> --%>
			<spring:bind path="degree">
				<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Education</label>
				<div class="col-sm-4">
					<form:input path="degree" class="form-control" id="contact" placeholder="Degree" />
					<form:errors path="degree" class="control-label" />
				</div>
				</div>
		</spring:bind>
		<spring:bind path="specialist">
				<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Specialist</label>
				<div class="col-sm-4">
					<form:input path="specialist" class="form-control" id="contact" placeholder="Specialist In" />
					<form:errors path="specialist" class="control-label" />
				</div>
				</div>
		</spring:bind>
		<spring:bind path="awards">
				<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Awards</label>
				<div class="col-sm-4">
					<form:input path="awards" class="form-control" id="contact" placeholder="Awards" />
					<form:errors path="awards" class="control-label" />
				</div>
				</div>
		</spring:bind>
		
		<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Upload your photo</label>
					
					<div class="row setup-content" id="step-1">
						
							<div class="col-sm-4">			
								<form:input type="file" path="images" id="images" />
								<div id="fileName"></div>
								<div id="fileSize"></div>
								<div id="fileType"></div>
					
								<!-- <input type="submit" value="Upload" /> -->
								<%-- <button class="btn btn-danger btn-sm" onclick="this.disabled=true;post('${clinicDeleteUrl}')">Delete</button> --%>
			
								<div id="progressNumber"></div>

							</div>
					</div>					
			</div>
		
			
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn-lg btn-primary pull-left btn-sm">Add</button>
				<button type="submit" class="btn-lg btn-primary pull-left btn-sm" id="cancel">Cancel</button>
			</div>
		</div>
	</form:form>
</div>
<jsp:include page="../fragments/footer.jsp" />
</body>
</html>
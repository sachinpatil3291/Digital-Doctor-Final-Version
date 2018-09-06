<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<h2>Add Clinic</h2>
	<br />

	<spring:url value="/clinic/add" var="clinicActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="AddClinicForm" enctype="multipart/form-data" action="${clinicActionUrl}" >

		<form:hidden path="id" />
		<form:hidden path="imageURL" />

		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-4">
					<form:input path="name" type="text" class="form-control " id="name" placeholder="Name" />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="address">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Address</label>
				<div class="col-sm-4">
					<form:textarea path="address" rows="5" class="form-control" id="address" placeholder="address" />
					<form:errors path="address" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="contact">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Contact</label>
				<div class="col-sm-4">
					<form:input path="contact" class="form-control" id="contact" placeholder="contact" />
					<form:errors path="contact" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<%-- <spring:url value="/clinic/upload/image" var="uploadActionUrl" /> --%>
		<%-- <form:hidden path="clinicId" /> --%>

			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Select a File to Upload</label>
					
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
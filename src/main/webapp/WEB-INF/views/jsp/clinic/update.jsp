<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<h2>Update Clinic</h2>
	<br />

	<spring:url value="/clinic/update" var="clinicActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="clinic" action="${clinicActionUrl}" >

		<form:hidden path="id" />

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
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn-lg btn-primary pull-left btn-sm">Update</button>
				<button type="submit" class="btn-lg btn-primary pull-left btn-sm" id="cancel">Cancel</button>
			</div>
			
		</div>
	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>
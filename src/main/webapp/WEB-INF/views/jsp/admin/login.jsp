<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Digi Doc</title>
	<spring:url value="/resources/core/css/hello.css" var="coreCss" />
	<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${coreCss}" rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-inverse ">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">Digital Doctor</a>
			</div>
		</div>
	</nav>
	<div class="container">

		<spring:url value="/authenticate/admin" var="userActionUrl" />
		<form:form class="form-horizontal" method="post"
			modelAttribute="loginForm" action="${userActionUrl}">

			<spring:bind path="userName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">User Name</label>
					<div class="col-sm-4">
						<form:input path="userName" type="text" class="form-control "
							id="userName" placeholder="User Name" />
						<form:errors path="userName" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Password</label>
					<div class="col-sm-4">
						<form:password path="password" class="form-control" id="password"
							placeholder="Password" />
						<form:errors path="password" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-sm btn-primary">Login</button>
				</div>
			</div>
		</form:form>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>
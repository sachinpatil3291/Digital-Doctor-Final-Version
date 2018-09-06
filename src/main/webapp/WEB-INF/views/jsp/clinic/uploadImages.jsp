<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">

body {
	margin-top: 100px;
}
</style>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
</head>
<jsp:include page="../fragments/header.jsp" />
<body>
	 <spring:url value="/clinic/upload/image" var="clinicActionUrl" />
	 <form:form method="post" modelAttribute="fileUpload" enctype="multipart/form-data" action="${clinicActionUrl}" >
		<div class="row setup-content" id="step-1">
			<form:hidden path="clinicId" />
			<div class="col-xs-12">
				<div class="col-sm-12 well text-center">
					<label for="fileToUpload">Select a File to Upload</label><br />
					<form:input type="file" path="images" id="images" />
				</div>
				<div id="fileName"></div>
				<div id="fileSize"></div>
				<div id="fileType"></div>
				<div class="row">
					<center><input type="submit" value="Upload" /></center>
					<%-- <button class="btn btn-danger btn-sm" onclick="this.disabled=true;post('${clinicDeleteUrl}')">Delete</button> --%>
				</div>
				<div id="progressNumber"></div>

			</div>
		</div>
	  </form:form>
</body>
</html>
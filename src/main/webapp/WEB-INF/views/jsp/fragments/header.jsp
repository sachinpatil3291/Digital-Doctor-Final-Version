<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Digi Doc</title>
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head> 

<spring:url value="/home" var="homeURL" />
<spring:url value="/clinics" var="clinicListURL" />
<spring:url value="/clinic/add/form" var="addClinicURL" />
<spring:url value="/clinic/update" var="updateClinicURL" />
<spring:url value="/clinic/delete" var="deleteClinicURL" />
<spring:url value="/doctors" var="doctorListURL" />
<spring:url value="/doctor/add/form" var="addDoctorURL" />
<spring:url value="/doctor/update" var="updateDoctorURL" />
<spring:url value="/doctor/delete" var="deleteDoctorURL" />


<nav class="navbar navbar-inverse ">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" >Digi Doc</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="link">
				<a href='${homeURL}'>Home</a>
			</li>
			<li class="dropdown">
				<a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Clinic <span
					class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href='${addClinicURL}'>Add</a></li>
					<li><a href='${clinicListURL}'>List</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Doctor <span
					class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href='${addDoctorURL}'>Add</a></li>
					<li><a href='${doctorListURL}'>List</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Prescription <span
					class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href='${addDoctorURL}'>Add</a></li>
					<li><a href='${doctorListURL}'>List</a></li>
				</ul>
			</li>
		</ul>
	</div>
</nav>
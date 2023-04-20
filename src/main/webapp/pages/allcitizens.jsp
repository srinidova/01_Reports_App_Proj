<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html> 
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Citizens</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
	<h3 class="pt-3 pb-3 text-center">All Citizens</h3>
			<div class="pt-3 pb-3 text-center">
				<a href="/addcitizen" class="btn btn-primary">Add</a>
				<a href="/" class="btn btn-primary">Search</a>
			</div>
		<form:form method="POST" action="search" modelAttribute="search">
			<table class="table table-striped table-hover">
				<thead>
					<tr class="text-nowrap">
						<th>S.No</th>
						<th>Name</th>
						<th>Gender</th>
						<th>Plan Name</th>
						<th>Plan Status</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Benefit Amt</th>
						<th>Denail Reason</th>
						<th>Termenated Date</th>
						<th>Termenated Reason</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${citizenslst}" var="citizen" varStatus="index">
					 <tr class="text-nowrap">
					 	<td>${index.count}</td>
						<td>${citizen.citizenName}</td>
						<td>${citizen.gender}</td>
						<td>${citizen.planName}</td>
						<td>${citizen.planStatus}</td>
						<td>${citizen.planStartDate}</td>
						<td>${citizen.planEndDate}</td>
						<td>${citizen.benefitAmt}</td>
						<td>${citizen.denailReason}</td>
						<td>${citizen.terminatedDate}</td>
						<td>${citizen.terminationReason}</td>
						<td>
							<a href="/get/${citizen.citizenId}"  class="btn btn-primary">Edit</a>
							<a href="/remove/${citizen.citizenId}" class="btn btn-danger">Delete</a>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<c:if test="${empty citizenslst}">
						 	<td colspan="11" style="text-align:center">No Records Found</td>
						</c:if>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>

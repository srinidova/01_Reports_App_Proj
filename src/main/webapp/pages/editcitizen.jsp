<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Citizen</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
</head>
<body>
	<div class="container w-50">
		<h3 class="pt-3 pb-3 text-center">Edit Citizen Form</h3>
		<form:form method="POST" action="upadecitizen" modelAttribute="citizen">
		
<%-- 			<div class="row">
				<div class="col">
					<label>Citizen Id</label>
					<form:input path="citizenId" placeholder="Enter Citizen Name"
						class="form-control" />
				</div>
			</div> --%>

			<div class="row">
				<div class="col">
					<form:hidden path="citizenId"/>
					<label>Citizen Name</label>
					<form:input path="citizenName" placeholder="Enter Citizen Name"
						class="form-control" />
				</div>
				<div class="col">
					<label>Gender</label>
					<form:select path="gender" class="form-control">
						<form:option value="">-Select-</form:option>
						<form:option value="Male">Male</form:option>
						<form:option value="Fe-Male">Fe-Male</form:option>
					</form:select>
				</div>
			</div>

			<div class="row pt-2 pb-2">
				<div class="col">
					<label>Plan Name</label>
					<form:select path="planName" class="form-control">
						<form:option value="">-Select-</form:option>
						<form:options items="${plannames}" />
					</form:select>
				</div>
				<div class="col">
					<label>Plan Status</label>
					<form:select path="planStatus" class="form-control"  onchange ="planStasus()">
						<form:option value="">-Select-</form:option>
						<form:options items="${statuses}" />
					</form:select>
				</div>
			</div>


			<div class="row pt-2 pb-2">
				<div class="col">
					<label>Start Date</label>
					<form:input type="date" path="planStartDate" value="${citizen.planStartDate}" class="form-control" />
				</div>
				<div class="col">
					<label>End Date</label>
					<form:input type="date" path="planEndDate" value="${citizen.planEndDate}" class="form-control" />
				</div>
			</div>
			<div class="row pt-2 pb-2">
				<div class="col">
					<label>Benefit Amount</label>
					<form:input path="benefitAmt" placeholder="Enter Benefit Amount"
						class="form-control" />
				</div>
				<div class="col" id="denailDiv" style="display:none">
					<label>Denail Reason</label>
					<form:input path="denailReason" placeholder="Enter Denail Reason"
						class="form-control" />
				</div>
			</div>


			<div class="row pt-2 pb-2" id="terminationDiv" style="display:none">
				<div class="col">
					<label>Termenated Date</label>
					<form:input type="date" path="terminatedDate" value="${citizen.terminatedDate}" class="form-control" />
				</div>
				<div class="col">
					<label>Termenated Reason</label>
					<form:input path="terminationReason"
						placeholder="Enter Termenated Reason" class="form-control" />
				</div>
			</div>
			<div class="pt-3 pb-3 text-center">
				<button type="submit" class="btn btn-primary">Submit</button>
			</div>

			
		</form:form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
			<script type="text/javascript">
	function planStasus(){
		var statusVal = document.getElementById("planStatus").value;

		if(statusVal == "Terminated"){
			document.getElementById("terminationDiv").style.display = "block";
			document.getElementById("denailDiv").style.display = "none";
		}else if(statusVal == "Denined"){
			document.getElementById("terminationDiv").style.display = "none";
			document.getElementById("denailDiv").style.display = "block";
		}else{
			document.getElementById("terminationDiv").style.display = "none";
			document.getElementById("denailDiv").style.display = "none";
		}
	}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Include the Bootstrap CSS link from test.jsp -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<style>
</style>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-header">
						<h3 class="text-center">Assignment Upload</h3>
					</div>
					<div class="card-body">
						<form method="POST" action="${contextPath}/upload-assignment"
							enctype="multipart/form-data">
							<div class="form-group">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" /> 
								<div class="form-group">
								<input type="file"
								name="assignmentFile" id="assignmentFile" /> 
								
								
								</div>
                            <div class="form-group">
								<label for="newDateColumn">Assignment Date:</label> 
								<input type="date"
								name="newDateColumn" id="assignmentDate" required /> 
								
								
								 </div>
                            <div class="text-center">
								<input
								type="submit" value="Upload Assignment" class="btn btn-primary" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>





	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

	<script>
		// Function to handle the form submission and show the success message
		$('#fileUploadForm').on('submit', function(event) {
			event.preventDefault(); // Prevent the form from submitting normally

			// Perform the form submission using Ajax
			$.ajax({
				type : 'POST',
				url : $(this).attr('action'),
				data : new FormData(this),
				contentType : false,
				cache : false,
				processData : false,
				success : function() {

					alert('File uploaded successfully!');

					// You can also perform additional actions here if needed.
				},
				error : function() {
					// Handle the error scenario if the upload fails.
					// You can show an error message here if desired.
				}
			});
		});
	</script>
</body>
</html>
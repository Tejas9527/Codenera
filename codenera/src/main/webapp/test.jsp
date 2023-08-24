<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test Upload</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-header">
                        <h3 class="text-center">Test Upload</h3>
                    </div>
                    <div class="card-body">
                        <form method="POST" action="${contextPath}/upload-test" enctype="multipart/form-data" id="fileUploadForm">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <div class="form-group">
                                <label for="testFile">Choose a file:</label>
                                <input type="file" class="form-control-file" name="testFile" id="testFile" required />
                            </div>
                            <div class="form-group">
                                <label for="testDate">Test Date:</label>
                                <input type="date" class="form-control" name="newDateColumn" id="testDate" required />
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Upload Test</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    

            
            
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

	<script>
        // Function to handle the form submission and show the success message
        $('#fileUploadForm').on('submit', function (event) {
            event.preventDefault(); // Prevent the form from submitting normally

            // Perform the form submission using Ajax
            $.ajax({
                type: 'POST',
                url: $(this).attr('action'),
                data: new FormData(this),
                contentType: false,
                cache: false,
                processData: false,
                success: function () {
                    
                    alert('File uploaded successfully!');

                    // You can also perform additional actions here if needed.
                },
                error: function () {
                    // Handle the error scenario if the upload fails.
                    // You can show an error message here if desired.
                }
            });
        });
    </script>
</body>
</html>

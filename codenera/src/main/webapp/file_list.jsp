<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Files</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Override Bootstrap color variables */
        :root {
            --primary-color: #007bff; /* Your custom primary color */
            --secondary-color: #6c757d; /* Your custom secondary color */
        }

        /* Apply custom styles */
        .view-btn, .download-btn {
            background-color: var(--primary-color);
            color: #fff;
        }

        .view-btn:hover, .download-btn:hover {
            background-color: #0056b3; /* Darker shade on hover */
        }

        .table {
            margin-top: 20px;
            border-collapse: collapse;
            width: 100%;
        }

        .table th, .table td {
            border: 2px solid #0056b3; /* Dark blue border */
            padding: 8px;
        }

        .table th {
            font-weight: bold;
            background-color: var(--primary-color);
            color: #fff;
            text-align: center;
        }

        .table tbody tr:hover {
            background-color: #f2f2f2; /* Light gray on hover */
        }

        .table td {
            vertical-align: middle;
        }

        h2 {
            margin-top: 40px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>List of Files</h2>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>File Name</th>
            <th> Date</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${fileList}" var="fileData">
            <tr>
                <td>${fileData.id}</td>
                <td>${fileData.fileName}</td>
                <td>${fileData.newDateColumn}</td>
                <td>
                    <a href="${contextPath}/fileList/${fileData.id}" class="btn btn-primary view-btn">View</a>
                    <a href="${contextPath}/download/${fileData.id}" class="btn btn-primary download-btn">Download</a>
                </td>
            </tr>
             
            
        </c:forEach>
        </tbody>
    </table>
    <a href="${contextPath}/student_dashboard.jsp" class="btn btn-primary">Go to Student Dashboard</a>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script>
    // Function to disable buttons for old files
    function disableButtonsForOldFiles() {
        const currentDate = new Date();

        // Get all elements with class "view-btn" and "download-btn"
        const viewButtons = document.getElementsByClassName("view-btn");
        const downloadButtons = document.getElementsByClassName("download-btn");

        // Loop through the buttons and disable them if the date is older than the current date
        for (let i = 0; i < viewButtons.length; i++) {
            const fileDate = new Date(viewButtons[i].closest("tr").querySelector("td:nth-child(3)").textContent.trim());

            if (fileDate > currentDate) {
                viewButtons[i].setAttribute("disabled", "disabled");
                downloadButtons[i].setAttribute("disabled", "disabled");

                // Add an event listener to handle button clicks and prevent default behavior
                viewButtons[i].addEventListener("click", function(event) {
                    event.preventDefault();
                });

                downloadButtons[i].addEventListener("click", function(event) {
                    event.preventDefault();
                });
            }
        }
    }

    // Call the function when the page is fully loaded
    document.addEventListener("DOMContentLoaded", disableButtonsForOldFiles);
</script>

</body>
</html>

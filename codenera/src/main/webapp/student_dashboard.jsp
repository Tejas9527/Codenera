<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard</title>
    <style>
        /* CSS for Student Dashboard */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        h2 {
            color: #1e4c8c;
            margin-top: 20px;
        }

        p {
            color: #666;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        textarea, input[type="text"] {
            width: 60%;
            padding: 10px;
            border: 2px solid #ccc;
            border-radius: 5px;
            resize: vertical;
        }

        input[type="submit"] {
            background-color: #1e4c8c;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #184070;
        }

        pre {
            background-color: #f7f7f7;
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
            white-space: pre-wrap;
            word-wrap: break-word;
            font-size: 14px;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            color: #1e4c8c;
            text-decoration: none;
            border-bottom: 1px solid #1e4c8c;
            transition: border-bottom-color 0.2s ease-in-out;
        }

        a:hover {
            border-bottom-color: #184070;
        }

        /* Center the content in the page */
        .container {
            max-width: 1400px;
            margin: 0 auto;
            padding: 20px;
            box-sizing: border-box;
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
    .menu-btn {
            position: relative;
            width: 50px;
            background-color: #1e4c8c;
            color: #fff;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            z-index: 999;
            transition: background-color 0.3s ease-in-out;
        }

        .menu-btn:hover {
            background-color: #184070;
        }

        .navbar {
            position: relative;
            background-color: #1e4c8c;
            color: #fff;
            padding: 10px;
            border-radius: 5px;
            display: none;
            z-index: 998;
            width: 120px;
        }

        .navbar a {
            display: block;
            color: #fff;
            text-decoration: none;
            padding: 8px 0;
            text-align: center;
            transition: background-color 0.2s ease-in-out;
        }

        .navbar a:hover {
            background-color: #184070;
        }
        

    </style>
</head>
<body>
    <div class="container">
        <!-- Menu button -->
        <div class="menu-btn" onmouseover="showNavbar()" onclick="hideNavbar()">Menu &#9776;</div>
        <!-- Vertical Navbar -->
        <div class="navbar" id="navbar">
            <a href="compilertest.jsp ">Test</a>
            <a href="fileList">Assignment</a>
            <a href="/student-logout">Logout</a>
        </div>
        <h2>Welcome ${loggedInStudent.username}!</h2>
        <p>Logged in as: ${loggedInStudent.username}</p>
        
       <script>
    document.addEventListener('click', function(event) {
        var navbar = document.getElementById('navbar');
        var menuBtn = document.querySelector('.menu-btn');

        // Check if the clicked element is inside the navbar or menu button
        if (!navbar.contains(event.target) && event.target !== menuBtn) {
            navbar.style.display = 'none';
        }
    });

    function showNavbar() {
        document.getElementById('navbar').style.display = 'block';
    }

    function hideNavbar() {
        document.getElementById('navbar').style.display = 'none';
    }
</script>

</body>
</html>
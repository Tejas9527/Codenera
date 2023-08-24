<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Log in with your account</title>
  <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
  <!-- Custom CSS -->
  <style>
    body {
      background-color: #f2f2f2;
      padding-top: 20px;
      margin-top: 150px;
    }

    .container {
      max-width: 400px;
      background-color: #fff;
      border-radius: 5px;
      padding: 20px;
      top margin: 10 auto;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .form-heading {
      text-align: center;
      margin-bottom: 30px;
    }

    .form-group {
      margin-bottom: 20px;
    }

    .form-group.has-error {
      border-color: #d9534f;
    }

    .form-group span {
      color: #d9534f;
    }

    input[type="text"],
    input[type="password"] {
      width: 100%;
      height: 40px;
      padding: 10px;
      border-radius: 5px;
      border: 1px solid #ccc;
    }

    .btn-primary {
      background-color: #007bff;
      border-color: #007bff;
    }

    .btn-primary:hover {
      background-color: #0056b3;
      border-color: #0056b3;
    }

    .text-center {
      text-align: center;
    }

    .logo-container {
      text-align: center;
      margin-bottom: 2px;
      
    }

    .logo-image {
      max-width: 350px;
    }
  </style>
</head>

<body>
  <div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="logo-container">
      <img src="/resources/img/logo.png" alt="Your Logo" class="logo-image">
    </div>
    <form action="/student-login" method="post">
      <h2 class="form-heading">Log in</h2>
      <div class="form-group ${error != null ? 'has-error' : ''}">
        <span>${message}</span>
        <input name="username" type="text" class="form-control" placeholder="Username" autofocus="true" />
        <br/>
        <input name="password" type="password" class="form-control" placeholder="Password" />
        <span>${error}</span>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <br/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
      </div>
    </form>
  </div>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
    
        

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Selection</title>
</head>
<style>
/* styles.css */

body {
  font-family: Arial, sans-serif;
  background-color: #f0f0f0;
  margin: 0;
  padding: 0;
}

h1 {
  color: #333;
  text-align: center;
  margin-top: 50px;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 30px;
}

button {
  padding: 10px 20px;
  margin: 10px;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  color: #fff;
  cursor: pointer;
}
div {
border: dark;
border-radius: 5px;
}

button[value="user"] {
  background-color: #007bff; /* Blue color */
}

button[value="student"] {
  background-color: #007bff; /* Red color */
}

button:hover {
  opacity: 0.8;
}
.logo-container {
      text-align: center;
      margin-bottom: 2px;
      margin-top: 150px;
    }

    .logo-image {
      max-width: 900px;
    }

</style>
<body>
<div class="logo-container">
      <img src="/resources/img/logo.png" alt="Your Logo" class="logo-image">
 
    <h1>Welcome to the Codenera</h1>
    <form action="select-login" method="post">
        <button type="submit" name="loginType" value="user">User Login</button>
        <button type="submit" name="loginType" value="student">Student Login</button>
    </form>
       </div>
</body>
</html>

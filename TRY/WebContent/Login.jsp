<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Style Sheet Included from Local Folder -->
<link rel="stylesheet" href="./CSS/Login_Form.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="script/customscript.js"></script>
<!-- <title>Insert title here</title> -->
</head>
<body class="bg">
	<!-- Background Image - flew from CSS(Login_form.css) -->
<div align="center">
<br>
<form action="userlogin" method="post">
<h2 style="color:yellow;">Login Form</h2>
  <div class="imgcontainer">
    <img src="Images/bpcl_logo.png" alt="Avatar" class="avatar">
  </div>

  <div class="container">
<br><h5>${message}</h5><br>
    <label for="username"><b>Username</b></label>
    <input id="LoginInput" type="text" placeholder="Enter Username" name="username" required>

    <label for="password"><b>Password</b></label>
    <input id="LoginInput" type="password" placeholder="Enter Password" name="password" required>
        
    <button type="submit">Login</button>
<!-- <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label>  -->    
  </div>

  <div class="container" style="background-color:#f1f1f1">
  <input type="reset" class="cancelbtn" value="Reset">
<!--  <button type="button" class="cancelbtn" onclick="cancel">Cancel</button>  -->    
<!--  <span class="psw">Forgot <a href="#">password?</a></span>   -->
  </div>
</form>
</div>

</body>
</html>
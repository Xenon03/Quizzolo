<%@ page session="true"%>
<html>
   <head>
      <title>Login</title>
      <link rel="stylesheet" href="styles/default.css">
   </head>
   <body>
   	  
   	  <div class="container">
		<img src="images/quizzoloLogo.png" style="width:500px;height:200px;"/>
	      <p>Effettua il login</p>
	      <div>
	      	<form action="login" method="post">
	      		<p>Username:</p>
	      		<input type="text" name="username" size="30"/><br>
	      		<p>Password:</p>
	      		<input type="password" name="pwd" size="30"/><br><br>
	      		<input type="submit" value="Log In"/>
	      	</form>
	      	
	      </div>
	      <p> Clicca <a href="register.jsp"> qui </a> per registrarti </p>
      </div>
   </body>
</html>


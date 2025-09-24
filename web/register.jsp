<%@ page session="true"%>
<html>
   <head>
      <title>Login</title>
      <link rel="stylesheet" href="styles/default.css">
      <script>
        // Funzione per validare le password
        function validatePasswords(event) {
            const password = document.getElementById("password").value;
            const ripetiPassword = document.getElementById("ripetiPassword").value;
            const errorMessage = document.getElementById("error-message");

            // Regex: almeno 8 caratteri, 1 minuscola, 1 maiuscola, 1 numero
            const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;

            // Controllo forza password
            if (!passwordRegex.test(password)) {
                event.preventDefault();
                errorMessage.textContent = "La password deve avere almeno 8 caratteri, includere una lettera maiuscola, una minuscola e un numero.";
                errorMessage.style.color = "red";
                return false;
            }
            
            // Controllo coincidenza password
            if (password !== ripetiPassword) {
                event.preventDefault(); // Blocca l'invio del form
                errorMessage.textContent = "Le password non coincidono!";
                errorMessage.style.color = "red";
                return false;
            } else {
                errorMessage.textContent = "";
                return true;
            }
        }

        // Controllo in tempo reale
        function checkPasswordMatch() {
            const password = document.getElementById("password").value;
            const ripetiPassword = document.getElementById("ripetiPassword").value;
            const errorMessage = document.getElementById("error-message");

            if (ripetiPassword.length > 0) {
                if (password !== ripetiPassword) {
                    errorMessage.textContent = "Le password non coincidono!";
                    errorMessage.style.color = "red";
                } else {
                    errorMessage.textContent = "Le password coincidono.";
                    errorMessage.style.color = "green";
                }
            } else {
                errorMessage.textContent = "";
            }
        }
    </script>
   </head>
   <body>
   	  
      <div class="container">
          <img src="images/quizzoloLogo.png" style="width:500px;height:200px;"/>
	      <p>Inserisci i tuoi dati per registrarti</p>
	      <div>
	      	<form action="register" method="post" onsubmit="return validatePasswords(event)">
	            <p>nome:</p>
	      		<input type="text" name="nome" size="30" required/><br>
	            <p>cognome:</p>
	      		<input type="text" name="cognome" size="30" required/><br>
	      		<p>Username:</p>
	      		<input type="text" name="username" size="30" required/><br>
	            <p>email:</p>
	      		<input type="email" name="email" size="30" required/><br>
	      		<p>Password:</p>
	      		<input type="password" name="password" id="password" size="30" required/><br><br>
	            <p>Ripeti password:</p>
	      		<input type="password" name="ripetiPassword" id="ripetiPassword" size="30" oninput="checkPasswordMatch()" required/><br><br>
	
	            <!-- Messaggio di errore o conferma -->
	            <p id="error-message"></p>
	
	      		<input type="submit" value="registrati"/>
	      	</form>
	      </div>
	      <p> Clicca <a href="login.jsp"> qui </a> per fare il login </p>
	  </div>
   </body>
</html>


<!-- accesso alla sessione -->
<%@page import="beans.Sfida"%>
<%@page import="beans.StatoSfida"%>
<%@ page session="true"%>

<!-- import di classi Java -->

<%@ page import="beans.Utente"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="utente" class="beans.Utente" scope="session" />


<%
if (utente.getUsername().isEmpty()) response.sendRedirect("login.jsp");
%> <!-- tentativo di accesso senza login -->



<!-- codice html restituito al client -->
<html>
	<head>
		<title>Home</title>
		<link rel="stylesheet" href="styles/home.css">
		<link rel="stylesheet" href="styles/header.css">
	</head>
	<body>
		<jsp:include page="/includes/header.jsp" />
		<div class="page-container">
		    
		    <!-- Container sinistro: form per inviare sfida -->
		    <div class="container">
		        <h1>Bentornato <jsp:getProperty name="utente" property="username"/></h1><br>
		        <h3>Invia una sfida</h3>
		        <form method="post" action="sfida.jsp">
		            <fieldset>
		                <legend>Seleziona categoria di sfida</legend>
		                <label><input type="radio" name="categoria" value="informatica" required> Informatica</label>
		                <label><input type="radio" name="categoria" value="cucina"> Cucina</label>
		                <label><input type="radio" name="categoria" value="geografia"> Geografia</label>
		                <label><input type="radio" name="categoria" value="cinema"> Cinema</label>
		            </fieldset>
		            <fieldset>
		                <legend>Seleziona l'utente che vuoi sfidare</legend>
		                <select name="utenteSfidato" required>
		                    <% 
		                        Map<String, Utente> utentiRegistrati = (Map<String, Utente>) application.getAttribute("utentiRegistrati");
		                        if (utentiRegistrati != null) {
		                            for (String username : utentiRegistrati.keySet()) {
		                                if (!username.equals(utente.getUsername())) { %>
		                                    <option value="<%= username %>"><%= username %></option>
		                                <% }
		                            }
		                        }
		                    %>
		                </select>
		            </fieldset>
		            <br>
		            <input type="submit" value="Inizia sfida">
		        </form>
		    </div>
		
		    <!-- Container destro: storico sfide -->
		    <div class="container storico">
		        <h2>Sfide ricevute</h2>
		        <div class="storico-list">
		            <% 
		                Map<String, Sfida> repoSfide = (Map<String, Sfida>) application.getAttribute("repositorySfide");
		                if (repoSfide != null && !repoSfide.isEmpty()) {
		                    boolean trovato = false;
		                    int counter = 0; // massimo 5 sfide visualizzate
		                    for (Sfida s : repoSfide.values()) {
		                        // Mostra solo le sfide ricevute dall'utente loggato 
		                        if (s.getSfidato().getUsername().equals(utente.getUsername()) && s.getStatoSfida().equals(StatoSfida.INVIATA)) {
		                            trovato = true;
		                            counter++;
		            %>	
		                            <div class="storico-item">
		                                <p><strong>Categoria:</strong> <%= s.getCategoria() %></p>
		                                <p><strong>Avversario:</strong> 
		                                    <%= s.getSfidante().getUsername().equals(utente.getUsername()) ? 
		                                        s.getSfidato().getUsername() : s.getSfidante().getUsername() %>
		                                </p>
		                                <p><strong>Stato:</strong> 
		                                    <%= s.getStatoSfida().equals(StatoSfida.INVIATA) ? "In attesa" :"" %>
		                                </p>
		                                <p><strong>Punteggio:</strong> <%= s.getPunteggioSfidante() %> - <%= s.getPunteggioSfidato() %></p>
		                                
		                                <!-- Pulsanti ACCETTA / RIFIUTA visibili solo allo sfidato -->
		                                <form action="gestioneSfida" method="post" class="sfida-actions">
		                                    <input type="hidden" name="id_sfida" value="<%= s.getId() %>">
		                                    <button type="submit" name="azione" value="accetta" class="btn-accetta">Accetta</button>
		                                    <button type="submit" name="azione" value="rifiuta" class="btn-rifiuta">Rifiuta</button>
		                                </form>
		                            <% if(counter >= 4)	//raggiunto limite visualizzazione
		                            		break; 		%>
		                            </div>
		            <% 
		                        }
		                    }
		                    if (!trovato) {
		            %>
		                        <p class="no-storico">Nessuna sfida ricevuta</p>
		            <% 
		                    }
		                } else { 
		            %>
		                <p class="no-storico">Nessuna sfida registrata</p>
		            <% } %>
		        </div>
		        <p> Visualizza tutte le sfide <a href="storico.jsp"> qui </a> </p>
		    </div>
		</div>
	</body>
</html>
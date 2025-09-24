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
		<link rel="stylesheet" href="styles/default.css">
		<link rel="stylesheet" href="styles/home.css">
		<link rel="stylesheet" href="styles/header.css">
	</head>
	<body>
	<jsp:include page="/includes/header.jsp" />
	
		    <!-- Container destro: storico sfide -->
		    <div class="container">
		        <h2>Storico delle sfide</h2>
		        <div class="storico-list">
		            <% 
		                Map<String, Sfida> repoSfide = (Map<String, Sfida>) application.getAttribute("repositorySfide");
		                if (repoSfide != null && !repoSfide.isEmpty()) {
		                    boolean trovato = false;
		                    for (Sfida s : repoSfide.values()) {
		                        // Mostra solo le sfide ricevute dall'utente loggato 
		                        if (s.getSfidato().getUsername().equals(utente.getUsername()) || s.getSfidante().getUsername().equals(utente.getUsername())) {
		                            trovato = true;
		            %>	
		                            <div class="storico-item">
		                                <p><strong>Categoria:</strong> <%= s.getCategoria() %></p>
		                                <p><strong>Avversario:</strong> 
		                                    <%= s.getSfidante().getUsername().equals(utente.getUsername()) ? 
		                                        s.getSfidato().getUsername() : s.getSfidante().getUsername() %>
		                                </p>
		                                <p><strong>Stato:</strong> 
		                                    <% if (s.getStatoSfida().equals(StatoSfida.INVIATA) && s.getSfidato().getUsername().equals(utente.getUsername())) { %>
		                                    	RICEVUTA
		                                    <% } else { %>
		                         				<%= s.getStatoSfida() %>
		                                    
		                                   <%  } %>
		                                </p>
		                                <p><strong>Punteggio:</strong> <%= s.getPunteggioSfidante() %> - <%= s.getPunteggioSfidato() %></p>
		                                
		                                <!-- Pulsanti ACCETTA / RIFIUTA visibili solo allo sfidato -->
		                                <% if (s.getSfidato().getUsername().equals(utente.getUsername()) && s.getStatoSfida().equals(StatoSfida.INVIATA)) { %>
			                                <form action="gestioneSfida" method="post" class="sfida-actions">
			                                    <input type="hidden" name="id_sfida" value="<%= s.getId() %>">
			                                    <button type="submit" name="azione" value="accetta" class="btn-accetta">Accetta</button>
			                                    <button type="submit" name="azione" value="rifiuta" class="btn-rifiuta">Rifiuta</button>
			                                </form>
			                            <% } %>
		                            </div>
		            <% 
		                        }
		                    }
		                    if (!trovato) {
		            %>
		                        <p class="no-storico">Nessuna sfida trovata</p>
		            <% 
		                    }
		                } else { 
		            %>
		                <p class="no-storico">Nessuna sfida registrata</p>
		            <% } %>
		        </div>
		        <p> Torna alla home <a href="home.jsp"> qui </a> </p>
		    </div>
		
	</body>
</html>
<%@page import="beans.StatoSfida"%>
<%@page import="java.util.Arrays"%>
<%@page import="beans.RispostaMultipla"%>
<%@page import="beans.RispostaSingola"%>
<%@page import="beans.RispostaTesto"%>
<%@page import="beans.TipoRisposta"%>
<%@page import="beans.Categoria"%>
<%@page import="beans.Utente"%>
<%@page import="java.util.Map"%>
<%@page import="beans.Sfida"%>
<%@ page session="true"%>


<jsp:useBean id="utente" class="beans.Utente" scope="session" /> <!-- utente loggato -->


<% 
	if (utente == null || utente.getUsername() == null || utente.getUsername().isEmpty()) {
		response.sendRedirect("login.jsp");
		return;
	}

	// oggetti globali
	Map<String, Sfida> repoSfide =  (Map<String, Sfida>) application.getAttribute("repositorySfide");  
	Map<String, Utente> utentiRegistrati = (Map<String, Utente>) application.getAttribute("utentiRegistrati");
	
	if(repoSfide == null || utentiRegistrati == null) {		// errore: non trovati.
		response.sendRedirect("home.jsp");	
		return;
	}


	Sfida sfida;
	
	// CASI
	if (request.getParameter("id_sfida") == null) {	// CASO 1: la sfida deve essere creata in seguito a una richiesta dello sfidante da home.jsp
		// creazione sfida partendo da parametri
		String idSfida = "sfida_" + repoSfide.size();
		Utente utenteSfidato = utentiRegistrati.get(request.getParameter("utenteSfidato"));
		Categoria categoriaSfida = Categoria.valueOf(request.getParameter("categoria").toUpperCase());
		
		sfida = new Sfida(idSfida, utente, utenteSfidato, categoriaSfida);
		repoSfide.put(idSfida, sfida);
	} else  {	// CASO 2: e' stato passato un parametro id_sfida che viene utilizzato per proseguire una sfida esistente
		sfida = repoSfide.get(request.getParameter("id_sfida"));
		
	
		if(sfida == null) {	// id sfida non trovato
			response.sendRedirect("home.jsp");	//default: torno a home.jsp
			return;
		}
		
		if (sfida.getStatoSfida().equals(StatoSfida.INVIATA)) {	// l'utente sfidato ha appena accettato la sfida
			sfida.setStatoSfida(StatoSfida.ACCETTATA);
		} else {	// lo sfidante o lo sfidato rispondono a una domanda
			TipoRisposta tipoRisposta = sfida.getDomandaCorrente().getRisposta().getTipo(); 
	      	if (tipoRisposta.equals(TipoRisposta.TESTO)) {
		      	String rispostaTesto = request.getParameter("rispostaTesto");
		      	sfida.rispondiDomandaCorrente(new String[]{rispostaTesto});
	      	} else if (tipoRisposta.equals(TipoRisposta.SINGOLA)) {
	      		String rispostaSingola = request.getParameter("rispostaSingola");
		      	sfida.rispondiDomandaCorrente(new String[]{rispostaSingola});
	      	} else if (tipoRisposta.equals(TipoRisposta.MULTIPLA)) {
	      		String[] rispostaMultipla = request.getParameterValues("rispostaMultipla");
		      	sfida.rispondiDomandaCorrente(rispostaMultipla);
	      	}
		}
		
		
      	
      	if(sfida.getStatoSfida().equals(StatoSfida.INVIATA) || sfida.getStatoSfida().equals(StatoSfida.TERMINATA)) {	// va fatto per forza dopo risposta data
      		response.sendRedirect("home.jsp");	//default: torno a home.jsp
			return;
      	}
	}
    

%>

<html>
   <head>
      <link rel="stylesheet" href="styles/sfida.css">
      <link rel="stylesheet" href="styles/header.css">
      <title>Sfida</title>
   </head>
   <body>
      <jsp:include page="/includes/header.jsp" />
      
      <div class="container">
         
         <!-- Barra informazioni in alto -->
         <div class="status-bar">
            <span>Domanda <%= sfida.getIndiceDomandaCorrente() + 1 %>/5</span>
            <span>Tempo: <span id="timer">30</span>s</span>
            <span>Punteggio: <%=sfida.getPunteggioSfidante()%> : <%=sfida.getPunteggioSfidato()%></span>
         </div>

         <!-- Form della sfida -->
         <form method="post" action="sfida.jsp">
            <!-- Domanda -->
            <div class="question">
               <%= sfida.getDomandaCorrente().getQuesito() %>
            </div>

            <!-- Opzioni -->
            <div class="options">
               <% TipoRisposta tipoRisposta = sfida.getDomandaCorrente().getRisposta().getTipo(); 
               if (tipoRisposta.equals(TipoRisposta.TESTO)) { %>

                  <input type="text" name="rispostaTesto" placeholder="Inserisci la tua risposta qui">

               <% } else if (tipoRisposta.equals(TipoRisposta.SINGOLA)) { 
                     for (String opzione : ((RispostaSingola) sfida.getDomandaCorrente().getRisposta()).getOpzioni()) { %>
                     <label>
                        <input type="radio" name="rispostaSingola" value="<%= opzione %>">
                        <%= opzione %>
                     </label>
               <%   } 
               } else if (tipoRisposta.equals(TipoRisposta.MULTIPLA)) { 
                     for (String opzione : ((RispostaMultipla) sfida.getDomandaCorrente().getRisposta()).getOpzioni()) { %>
                     <label>
                        <input type="checkbox" name="rispostaMultipla" value="<%=opzione%>">
                        <%= opzione %>
                     </label>
               <%   } 
               } %>
            </div>

            <!-- Hidden per l'id sfida -->
            <input type="hidden" name="id_sfida" value="<%= sfida.getId() %>">

            <!-- Pulsante di invio -->
            <input id="nextBtn" type="submit" value="Prossima domanda">
         </form>
      </div>

      <script type="text/javascript">
         document.addEventListener("DOMContentLoaded", function() {
            const form = document.querySelector("form");
            const timerElement = document.getElementById("timer");
            const nextBtn = document.getElementById("nextBtn");
            let tempoRimanente = 30;
            timerElement.innerText = tempoRimanente;
            
            if (<%=sfida.getIndiceDomandaCorrente()%> == 4) {	// se è l'ultima domanda cambia testo del bottone submit
            	nextBtn.value = "Concludi";
            }

            const countdown = setInterval(function() {
               if (tempoRimanente > 0) {
                  tempoRimanente--;
                  timerElement.innerText = tempoRimanente;
               } else {
                  clearInterval(countdown);
               }
            }, 1000);

            setTimeout(function() {
               alert("Tempo scaduto!");
               
               const inputs = form.querySelectorAll('input[type="radio"], input[type="checkbox"], input[type="text"]');
               inputs.forEach(input => {
                  if (input.type === "radio" || input.type === "checkbox") {
                     input.checked = false;
                  } else if (input.type === "text") {
                     input.value = "";
                  }
               });
               form.submit();
            }, 30000); // 30 secondi
         });
      </script>
   </body>
</html>

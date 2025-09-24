<%-- includes/header.jsp --%>
<%-- Barra header con logo (riutilizzabile) --%>
<div id="site-header" role="banner">
  <div class="header-inner">
    <a href="home.jsp" class="logo-link" aria-label="Torna alla home">
      <img src="${pageContext.request.contextPath}/images/quizzoloLogo.png" alt="Logo Quizzolo" id="site-logo">
    </a>
    <nav class="header-nav" aria-label="Navigazione principale">
      <a href="home.jsp">Home</a>
      <a href="storico.jsp">Storico</a>
      <a href="profile.jsp">Profilo</a>
      <a href="logout" onClick="alert('Logout avvenuto con successo')">Logout</a>
    </nav>
  </div>
</div>

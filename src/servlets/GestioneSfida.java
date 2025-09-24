package servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Sfida;
import beans.StatoSfida;
import beans.Utente;

public class GestioneSfida extends HttpServlet {
	private Map<String,Utente> utentiRegistrati;
	private Map<String, Sfida> repoSfide;
	private Gson g;
	private MessageDigest md;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		
		repoSfide =  (Map<String, Sfida>) getServletContext().getAttribute("repositorySfide");
		Map<String, Utente> utentiRegistrati = (Map<String, Utente>) getServletContext().getAttribute("utentiRegistrati");
	}
	
	
	public void doPost(HttpServletRequest req,
            HttpServletResponse resp)
    throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		String azione = req.getParameter("azione");
		String idSfida = req.getParameter("id_sfida");
		Utente utente = (Utente) session.getAttribute("utente");
		Sfida sfida = repoSfide.get(idSfida);
		
		if (azione == null || sfida == null)
			resp.sendError(400);
		
		switch (azione) {
		case "accetta":
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/sfida.jsp");
			rd.forward(req, resp);
			break;
		case "rifiuta":
			repoSfide.remove(idSfida);
			resp.sendRedirect("home.jsp");
			break;
		}
		
	}
	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{}
	

}

package servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import beans.Categoria;
import beans.Sfida;
import beans.StatoSfida;
import beans.Utente;

public class Register extends HttpServlet {
	private static final long serialVersionUID = -3526526625312607382L;
	private Map<String,Utente> utentiRegistrati;
	private MessageDigest md;
	// private HashMap<String, Sfida> repositorySfide; // coppia <id_sfida, sfida> (unused)
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		utentiRegistrati = new HashMap<String, Utente>();
		
		
		try {
			md = MessageDigest.getInstance("MD5");	// MD5: non sicuro ma facile da implementare
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		String password = "password";
	    md.update(password.getBytes());
	    byte[] digest = md.digest();
		Utente u = new Utente("giovanni_vignoletti",new String(digest, StandardCharsets.UTF_8),"Giovanni","Vignoletti", "a@org.com");
		utentiRegistrati.put(u.getUsername(), u);

		password = "password";
		md.update(password.getBytes());
	    digest = md.digest();
		u = new Utente("federico_balistreri",new String(digest, StandardCharsets.UTF_8),"Federico","Balistreri", "b@org.com");
		utentiRegistrati.put(u.getUsername(), u);
		
		password = "password";
		md.update(password.getBytes());
	    digest = md.digest();
		u = new Utente("marco_patella",new String(digest, StandardCharsets.UTF_8),"Marco","Patella", "c@org.com");
		utentiRegistrati.put(u.getUsername(), u);

		//admin
		password = "password";	
		md.update(password.getBytes());
	    digest = md.digest();
		u = new Utente("admin",new String(digest, StandardCharsets.UTF_8),"nome_admin","cognome_admin", "admin@org.com");
		utentiRegistrati.put(u.getUsername(), u);
		
		// oggetti globali nell'application context
		this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);
		this.getServletContext().setAttribute("repositorySfide", new HashMap<String, Sfida>());

	}
	
	
	public void doPost(HttpServletRequest req,
            HttpServletResponse resp)
    throws ServletException, IOException
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(utentiRegistrati.get(username) != null) {
			resp.sendError(401, "username già esistente");
			return;
		}

		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String email = req.getParameter("email");
		HttpSession session = req.getSession();
		
		// crypto password 
		md.update(password.getBytes());
	    byte[] digest = md.digest();
	    String myHash = new String(digest, StandardCharsets.UTF_8);
		Utente utente = new Utente(username, myHash, nome, cognome, email);

		session.setAttribute("utente", utente);
		utentiRegistrati.put(username, utente);
		resp.sendRedirect("home.jsp");
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
//		
//		rd.forward(request, response);
		
	}
	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{}
	

}

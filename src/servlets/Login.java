package servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import beans.Utente;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 4969749523828254175L;
	private Map<String,Utente> utentiRegistrati;
	private MessageDigest md;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		
		try {
			md = MessageDigest.getInstance("MD5");	// MD5: non sicuro ma facile da implementare
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		utentiRegistrati = (Map<String,Utente>) this.getServletContext().getAttribute("utentiRegistrati");
	}
	
	
	public void doPost(HttpServletRequest req,
            HttpServletResponse resp)
    throws ServletException, IOException
	{
		String name = req.getParameter("username");
		Utente utente = utentiRegistrati.get(name);
		HttpSession session = req.getSession();
		
		// crypto password 
		String password = req.getParameter("pwd");
		if(utente == null || password == null) {
			resp.sendError(401, "login errato");
			return;
		}
		md.update(password.getBytes());
	    byte[] digest = md.digest();
	    String myHash = new String(digest, StandardCharsets.UTF_8);

		if(!utente.getHashedPwd().equals(myHash)) { // hash password non coincidente
			resp.sendError(401, "login errato");
			return;
		} // altrimenti login OK

		session.setAttribute("utente", utente);
		
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

package beans;


public class Utente {
	
	private String username, email, hashedPwd, nome, cognome;
	
	public Utente() {	// è necessario per un bean
		this.username = "";
	}	
	
    public Utente(String username, String hashedPwd, String nome, String cognome, String email) {
		this.username = username;
		this.hashedPwd = hashedPwd; 
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getHashedPwd() {
		return hashedPwd;
	}
}

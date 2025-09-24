package beans;

public class Quiz {
	private Domanda[] domande;
	private Categoria categoria;
	
	
	public Quiz(Domanda[] domande) {	// assumiamo che l'array sia di lunghezza 5 e che tutte le domande che contiene siano della stessa categoria
		this.domande = domande;
		this.categoria = this.domande[0].getCategoria();
	}
	
	public Domanda[] getDomande() {
		return this.domande;
	}
	
	public boolean rispondiADomanda(String[] risposta, int index) {	// ritorna se @r è la risposta corretta alla domanda @i
		return this.domande[index].rispondi(risposta);
	}
	
	public Categoria getCategoria() {
		return this.categoria;
	}
}

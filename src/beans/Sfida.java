package beans;

import repository.DomandeRepository;

public class Sfida {
	private String id;
	private Utente sfidante, sfidato;
	private int punteggioSfidante = 0, punteggioSfidato = 0;
	private int indiceDomandaSfidante = 0, indiceDomandaSfidato = 0;
	private Categoria categoria;
	private Quiz quiz;
	private StatoSfida statoSfida = StatoSfida.INIZIATA;
	private static final DomandeRepository DomandeRepo = new DomandeRepository();

	
	public Sfida(String id, Utente sfidante, Utente sfidato, Categoria categoria) {
		this.id = id;
		this.sfidante = sfidante;
		this.sfidato = sfidato;
		this.categoria = categoria;
		this.quiz = creaQuiz(categoria);
	}
	
	
	private Quiz creaQuiz(Categoria categoria) {	// ho omesso un pò di codice per la selezione perchè tanto sono già 5 domande per categoria
		return new Quiz(DomandeRepo.getDomandePerCategoria(categoria).toArray(Domanda[]::new)) ;
	}
	
	// ritorna l'indice della prossima domanda a cui deve rispondere lo sfidante oppure lo sfidato a seconda dello stato della sfida
	public int getIndiceDomandaCorrente() {
		return (statoSfida.equals(StatoSfida.INIZIATA)) ? indiceDomandaSfidante : indiceDomandaSfidato;
	}
	
	// ritorna la prossima domanda a cui deve rispondere lo sfidante oppure lo sfidato a seconda dello stato della sfida
	public Domanda getDomandaCorrente() {
		return this.quiz.getDomande()[getIndiceDomandaCorrente()];
	}
	
	// incrementa indice domandaCorrente (a seconda di chi stia giocando) e ritorna se il giocatore abbia risposto correttamente
	public void rispondiDomandaCorrente(String[] risposta) {
		if (statoSfida.equals(StatoSfida.INIZIATA)) {	// sta giocando lo sfidante
			punteggioSfidante += this.quiz.rispondiADomanda(risposta, indiceDomandaSfidante) ? 1 : 0;
			indiceDomandaSfidante++;
			if (indiceDomandaSfidante == 5) // la sfida è stata conclusa dallo sfidante
				this.statoSfida = StatoSfida.INVIATA;
		} else if (statoSfida.equals(StatoSfida.ACCETTATA)) {	// sta giocando lo sfidato
			punteggioSfidato += this.quiz.rispondiADomanda(risposta, indiceDomandaSfidato) ? 1 : 0;
			indiceDomandaSfidato++;
			if (indiceDomandaSfidato == 5) // la sfida è stata conclusa dallo sfidato
				this.statoSfida = StatoSfida.TERMINATA;
		}
		
	}
	
	public String getId() {
		return id;
	}
	
	public int getPunteggioSfidante() {
		return punteggioSfidante;
	}
	
	public int getPunteggioSfidato() {
		return punteggioSfidato;
	}
	
	public StatoSfida getStatoSfida() {
		return statoSfida;
	}
	
	public Utente getSfidante() {
		return sfidante;
	}
	
	public Utente getSfidato() {
		return sfidato;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setStatoSfida(StatoSfida statoSfida) {
		this.statoSfida = statoSfida;
	}
	// Aspettiamo a fare i getter e i setter: magari punteggioSfidante e punteggioSfidato vengono gestiti internamente
	
	
}

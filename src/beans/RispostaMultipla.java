package beans;

public class RispostaMultipla implements Risposta {
	private String[] opzioni;
	private String[] opzioniCorrette;
	
	public RispostaMultipla(String[] opzioni, String[] opzioniCorrette) {
		this.opzioni = opzioni;
		this.opzioniCorrette = opzioniCorrette;
	}
	
	@Override
	public TipoRisposta getTipo() {
		return TipoRisposta.MULTIPLA;
	}
	
	public String[] getOpzioni() {
		return opzioni;
	}
	
	public String[] getOpzioniCorrette() {
		return opzioniCorrette;
	}
	
}

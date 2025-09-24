package beans;

public class RispostaSingola implements Risposta {
	private String[] opzioni;
	private String opzioneCorretta;
	
	public RispostaSingola(String[] opzioni, String opzioneCorretta) {
		this.opzioni = opzioni;
		this.opzioneCorretta = opzioneCorretta;
	}
	
	@Override
	public TipoRisposta getTipo() {
		return TipoRisposta.SINGOLA;
	}

	public String[] getOpzioni() {
		return opzioni;
	}

	public String getOpzioneCorretta() {
		return opzioneCorretta;
	}
	
	
	
}

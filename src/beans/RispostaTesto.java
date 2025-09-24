package beans;

public class RispostaTesto implements Risposta {
	private String rispostaCorretta;
	
	public RispostaTesto(String rispostaCorretta) {
		this.rispostaCorretta = rispostaCorretta;
	}
	
	@Override
	public TipoRisposta getTipo() {
		return TipoRisposta.TESTO;
	}

	public String getRispostaCorretta() {
		return rispostaCorretta;
	}
	
}

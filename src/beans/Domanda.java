package beans;

import java.util.HashSet;
import java.util.Set;

public class Domanda {
	private Categoria categoria;
	private String quesito;
	private Risposta risposta;
	
	public Domanda(String quesito, Risposta risposta, Categoria categoria) {
		this.quesito = quesito;
		this.risposta = risposta;
		this.categoria = categoria;
	}
	
	
	/*
	 * @risposta ha un solo elemento se this.risposta è di tipo SINGOLA o TESTO 
	 */
	public boolean rispondi(String[] rispostaData) {
		try {
			boolean result = false;
			if (rispostaData == null) {
				return false;
			}
			
			switch (this.risposta.getTipo()) {
			case MULTIPLA:
				result = checkRispostaMultipla(rispostaData);
				break;
			case SINGOLA:
				result = checkRispostaSingola(rispostaData[0]);	// rispostaData contiene un solo elemento
				break;
			case TESTO:
				result = checkRispostaTesto(rispostaData[0]);	// rispostaData contiene un solo elemento
				break;
			}
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getQuesito() {
		return quesito;
	}

	public Risposta getRisposta() {
		return risposta;
	}
	
	private boolean checkRispostaMultipla(String[] rispostaData) {
        RispostaMultipla rm = (RispostaMultipla) risposta;

        // Normalizza le risposte dell'utente
        Set<String> rispostaDataSet = new HashSet<>();
        for (String r : rispostaData) {
            if (r != null && !r.trim().isEmpty()) {
                rispostaDataSet.add(r.trim().toLowerCase());
            }
        }

        // Normalizza le risposte corrette
        Set<String> rispostaCorrettaSet = new HashSet<>();
        for (String r : rm.getOpzioniCorrette()) {
            rispostaCorrettaSet.add(r.trim().toLowerCase());
        }

        return rispostaDataSet.equals(rispostaCorrettaSet);
    }

    /**
     * Controlla risposta singola
     */
    private boolean checkRispostaSingola(String rispostaUtente) {
        RispostaSingola rs = (RispostaSingola) risposta;
        return rs.getOpzioneCorretta().trim().equalsIgnoreCase(rispostaUtente.trim());
    }

    /**
     * Controlla risposta testuale
     */
    private boolean checkRispostaTesto(String rispostaUtente) {
        RispostaTesto rt = (RispostaTesto) risposta;
        return rt.getRispostaCorretta().trim().equalsIgnoreCase(rispostaUtente.trim());
    }
	

	
	
	
}

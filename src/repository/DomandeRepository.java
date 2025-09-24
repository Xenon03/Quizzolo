package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import beans.Categoria;
import beans.Domanda;
import beans.RispostaMultipla;
import beans.RispostaSingola;
import beans.RispostaTesto;

/*
 * Oggetto che rappresenta le domande presenti nel sistema e le memorizza in un'opportuna struttura dati
 */
public class DomandeRepository {
	private Map<Categoria, ArrayList<Domanda>> domande;
	
	
	public DomandeRepository() {
		this.domande = new HashMap<Categoria, ArrayList<Domanda>>();
		for (Categoria c : Categoria.values()) this.domande.put(c, new ArrayList<Domanda>());	// inizializzazione ArrayList
		
		
		ArrayList<Domanda> arr;
		RispostaSingola rs;
		RispostaMultipla rm;
		RispostaTesto rt;
		Domanda d;
		
		/***** DOMANDE *****/
		/*********** domande GEOGRAFIA *************/
		arr = this.domande.get(Categoria.GEOGRAFIA);
		// 1)
		rs = new RispostaSingola(new String[] {"Bologna", "Modena", "Padova", "Piacenza"}, "Padova");
		d = new Domanda("Quale di queste non e' una citta' dell'Emilia Romagna?", rs, Categoria.GEOGRAFIA);
		arr.add(d);
		// 2)
		rm = new RispostaMultipla(new String[] {"Inghilterra", "Francia", "Italia", "USA"}, new String[]{"Inghilterra", "USA"});
		d = new Domanda("In quali di questi paesi la lingua principale e' l'inglese?", rm, Categoria.GEOGRAFIA);
		arr.add(d);
		// 3)
		rt = new RispostaTesto("Roma");
		d = new Domanda("Qual'e' la capitale d'Italia?", rt, Categoria.GEOGRAFIA);
		arr.add(d);
		// 4)
		rt = new RispostaTesto("Cina");
		d = new Domanda("In quale nazione si trova Pechino?", rt, Categoria.GEOGRAFIA);
		arr.add(d);
		// 5)
		rm = new RispostaMultipla(new String[] {"Germania", "Francia", "Slovenia", "Svizzera"}, new String[]{"Francia", "Slovenia", "Svizzera"});
		d = new Domanda("Quale di queste nazioni non confina con l'Italia?", rm, Categoria.GEOGRAFIA);
		arr.add(d);
		
		
		/*********** domande CINEMA *************/
		arr = this.domande.get(Categoria.CINEMA);
		// 1)
		rs = new RispostaSingola(new String[] {"Il buio oltre la siepe", "Jurassik park", "La vita e' bella", "Jumanji"}, "La vita e' bella");
		d = new Domanda("Quale di questi e' un noto film di Roberto Benigni?", rs, Categoria.CINEMA);
		arr.add(d);
		// 2)
		rm = new RispostaMultipla(new String[] {"Quasi amici", "Profondo rosso", "The conjuring", "Ufficiale e gentiluomo"}, new String[]{"Inghilterra", "USA"});
		d = new Domanda("Quali di questi film sono nel genere horror?", rm, Categoria.CINEMA);
		arr.add(d);
		// 3)
		rt = new RispostaTesto("Leonardo");
		d = new Domanda("Qual'e' il nome dell'attore americano DiCaprio", rt, Categoria.CINEMA);
		arr.add(d);
		// 4)
		rs = new RispostaSingola(new String[] {"La stanza accanto", "Father Mother Sister Brother", "Il segreto dell'acqua"}, "Father Mother Sister Brother");
		d = new Domanda("Quale film ha vinto il leone d'oro al festival del cinema di venezia quest'anno?", rs, Categoria.CINEMA);
		arr.add(d);
		// 5)
		rm = new RispostaMultipla(new String[] {"Sylvester Stallone", "Marlon Brando", "Robert De Niro", "Robert Redford"}, new String[] {"Sylvester Stallone", "Robert De Niro"});
		d = new Domanda("Quali di questi attori americani sono ancora vivi?", rm, Categoria.CINEMA);
		arr.add(d);
		

		/*********** domande INFORMATICA *************/
		arr = this.domande.get(Categoria.INFORMATICA);
		// 1)
		rs = new RispostaSingola(new String[] {"Microsoft Word", "Windows 11", "Google Chrome", "MySQL"}, "Windows 11");
		d = new Domanda("Quale di questi e' un sistema operativo?", rs, Categoria.INFORMATICA);
		arr.add(d);
		// 2)
		rm = new RispostaMultipla(new String[] {"Python", "HTML", "Java", "CSS"}, new String[]{"Python", "Java"});
		d = new Domanda("Quali tra i seguenti sono linguaggi di programmazione?", rm, Categoria.INFORMATICA);
		arr.add(d);
		// 3)
		rt = new RispostaTesto("CPU");
		d = new Domanda("Il componente del computer che esegue i calcoli e le istruzioni e' chiamato _____.", rt, Categoria.INFORMATICA);
		arr.add(d);
		// 4)
		rs = new RispostaSingola(new String[] {"Kilobyte (KB)", "Megabyte (MB)", "Gigabyte (GB)", "Terabyte (TB)"}, "Gigabyte (GB)");
		d = new Domanda("Quale unita' di misura rappresenta un miliardo di byte?", rs, Categoria.INFORMATICA);
		arr.add(d);
		// 5)
		rm = new RispostaMultipla(new String[] {"Rilevamento di malware", "Ottimizzazione grafica della scheda video", "Protezione in tempo reale", "Backup automatico dei file"}, new String[] {"Rilevamento di malware", "Protezione in tempo reale"});
		d = new Domanda("Quali tra le seguenti sono funzioni di un antivirus?", rm, Categoria.INFORMATICA);
		arr.add(d);
		

		/*********** domande CUCINA *************/
		arr = this.domande.get(Categoria.CUCINA);
		// 1)
		rs = new RispostaSingola(new String[] {"Uova", "Pane", "Patate", "Mele"}, "Uova");
		d = new Domanda("Quale di questi alimenti e' ricco di proteine?", rs, Categoria.CUCINA);
		arr.add(d);
		// 2)
		rm = new RispostaMultipla(new String[] {"Riso", "Farro", "Lenticchie", "Mais"}, new String[]{"Riso", "Farro", "Mais"});
		d = new Domanda("Quali tra i seguenti ingredienti sono cereali?", rm, Categoria.CUCINA);
		arr.add(d);
		// 3)
		rt = new RispostaTesto("Emilia Romagna");
		d = new Domanda("Il formaggio Parmigiano Reggiano e' tipico della regione italiana _______.", rt, Categoria.CUCINA);
		arr.add(d);
		// 4)
		rs = new RispostaSingola(new String[] {"Julienne", "Rosolare", "Sbattere", "Gratinare"}, "Julienne");
		d = new Domanda("Quale tecnica si utilizza per tagliare le verdure in strisce sottili?", rs, Categoria.CUCINA);
		arr.add(d);
		// 5)
		rm = new RispostaMultipla(new String[] {"Fiorentina", "Filetto", "Spigola", "Costata"}, new String[] {"Fiorentina", "Filetto", "Costata"});
		d = new Domanda("Quali di questi sono tagli di carne bovina?", rm, Categoria.CUCINA);
		arr.add(d);
		
	}
	
	public ArrayList<Domanda> getDomandePerCategoria(Categoria c) {
		return domande.get(c);
	}
}

package model;

import java.io.Serializable;

/**
 * La classe Dato è la classe che rappresenta i dati necessari per salvare il profilo utente tra cui nickname, avatar, statistiche e i livelli creati nell'editor
 */
public class Dato implements Serializable{

	/**
	 * variavile utile per la serializzazione
	 */
	private static final long serialVersionUID = 2040332169201713138L;
	/**
	 * livelli superati
	 */
	private boolean[] livSuperati = {false, false, false, false, false, false, false, false};
	/**
	 * utente
	 */
	private Utente utente;
	/**
	 * se si gioca da tastiera
	 */
	private boolean tastiera = true;
    /**
     * livelli visibili
     */
	private boolean[] visibile = {false, false, false, false, false, false, false};
    /**
     * livelli creati
     */
    private LivelloCreato[] elencoLivCreati = {null, null, null, null, null, null, null, null};
	
    
    /**
     * Costruttore di dato che crea un'istanza di dato
     */
	public Dato()
	{
		
	}

	/**
	 * Metodo che restituisce una lista di variabili che indicano lo stato dei livelli
	 * @return lista di variabili che indicano lo stato dei livelli
	 */
	public boolean[] getLivSuperati() {
		return livSuperati;
	}

	/**
	 * Metodo che imposta una lista di variabili che indicano lo stato dei livelli
	 * @param livSuperati lista di variabili che indicano lo stato dei livelli
	 */
	public void setLivSuperati(boolean[] livSuperati) {
		this.livSuperati = livSuperati;
	}

	/**
	 * Metodo che restituisce un oggetto di tipo Utente 
	 * @return oggetto di tipo Utente
	 */
	public Utente getUtente() {
		return utente;
	}

	/**
	 * Metodo che imposta un oggetto di tipo Utente 
	 * @param utente oggetto di tipo Utente
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	/**
	 * Metodo che restituisce una variabile che indica se si sta usando la tastiera
	 * @return variabile che indica se si sta usando la tastiera
	 */
	public boolean getTastiera() {
		return tastiera;
	}

	/**
	 * Metodo che imposta una variabile che indica se si sta usando la tastiera
	 * @param tastiera variabile che indica se si sta usando la tastiera
	 */
	public void setTastiera(boolean tastiera) {
		this.tastiera = tastiera;
	}

	/**
	 * Metodo che restituisce una lista di variabili indicanti per ogni livello se è stato superato
	 * @return lista di variabili indicanti per ogni livello se è stato superato
	 */
	public boolean[] getVisibile() {
		return visibile;
	}

	/**
	 * Metodo che imposta una lista di variabili indicanti per ogni livello se è stato superato
	 * @param visibile lista di variabili indicanti per ogni livello se è stato superato
	 */
	public void setVisibile(boolean[] visibile) {
		this.visibile = visibile;
	}

	/**
	 * Metodo che restituisce una lista di livelli creati
	 * @return lista di livelli creati
	 */
	public LivelloCreato[] getElencoLivCreati() {
		return elencoLivCreati;
	}

	/**
	 * Metodo che imposta una lista di livelli creati
	 * @param elencoLivCreati lista di livelli creati
	 */
	public void setElencoLivCreati(LivelloCreato[] elencoLivCreati) {
		this.elencoLivCreati = elencoLivCreati;
	}
}

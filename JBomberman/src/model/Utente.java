package model;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * La classe Utente è la classe che tiene segno di tutte le informazioni relative all'utente
 */
public class Utente implements Serializable{
	
	/**
	 * variabile utile per la serializzazione
	 */
	private static final long serialVersionUID = -7032501964891680748L;
	/**
	 * nickname
	 */
	private String nickname;
	/**
	 * colore avatar
	 */
	private int avatar = 1;
	/**
	 * numero partite giocate
	 */
	private int partiteGiocate = 0;
	/**
	 * numero partite vinte
	 */
	private int partiteVinte = 0;
	/**
	 * numero partite perse
	 */
	private int partitePerse = 0;
	/**
	 * punteggio
	 */
	private int punteggio = 0;
	
	
	/**
	 * Costruttore di Utente che crea un'istanza di Utente per una nuova partita
	 * @param nickname nome utente
	 */
	public Utente(String nickname)
	{
		this.nickname = nickname;
	}
	
	/**
	 * Costruttore di Utente che crea un'istanza di utente con le informazioni già in possesso
	 * @param nickname nome utente
	 * @param avatar colore Bomberman scelto
	 * @param partiteGiocate numero di partite giocate
	 * @param partiteVinte numero di partite vinte
	 * @param partitePerse numero di partite perse
	 * @param punteggio punteggio ottenuto
	 */
	public Utente(String nickname, int avatar, int partiteGiocate, int partiteVinte, int partitePerse, int punteggio) {
		super();
		this.nickname = nickname;
		this.avatar = avatar;
		this.partiteGiocate = partiteGiocate;
		this.partiteVinte = partiteVinte;
		this.partitePerse = partitePerse;
		this.punteggio = punteggio;
	}

	/**
	 * Metodo che resituisce il colore scelto del personaggio
	 * @return colore scelto del personaggio
	 */
	public int getAvatar() {
		return avatar;
	}
	
	/**
	 * Metodo che imposta il colore del personaggio
	 * @param avatar colore del personaggio
	 */
	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}
	
	/**
	 * Metodo che restituisce il nome utente
	 * @return nome utente
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Metodo che imposta il nome utente
	 * @param nickname nome utente
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * Metodo che restituisce il numero di partite giocate
	 * @return partite giocate
	 */
	public int getPartiteGiocate() {
		return partiteGiocate;
	}
	
	/**
	 * Metodo che imposta il numero di partite giocate
	 * @param partiteGiocate numero di partite giocate
	 */
	public void setPartiteGiocate(int partiteGiocate) {
		this.partiteGiocate = partiteGiocate;
	}
	
	/**
	 * Metodo che restituisce il numero di partite vinte
	 * @return numero di partite vinte
	 */
	public int getPartiteVinte() {
		return partiteVinte;
	}
	
	/**
	 * Metodo che imposta il numero di partite vinte
	 * @param partiteVinte numero di partite vinte
	 */
	public void setPartiteVinte(int partiteVinte) {
		this.partiteVinte = partiteVinte;
	}
	
	/**
	 * Metodo che restituisce il numero di partite perse
	 * @return numero di partite perse
	 */
	public int getPartitePerse() {
		return partitePerse;
	}
	
	/**
	 * Metodo che imposta il numero di partite perse
	 * @param partitePerse numero di partite perse
	 */
	public void setPartitePerse(int partitePerse) {
		this.partitePerse = partitePerse;
	}
	
	/**
	 * Metodo che restituisce il punteggio accumulato
	 * @return punteggio
	 */
	public int getPunteggio() {
		return punteggio;
	}
	
	/**
	 * Metodo che imposta il punteggio accumulato
	 * @param punteggio nuovo punteggio 
	 */
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
}

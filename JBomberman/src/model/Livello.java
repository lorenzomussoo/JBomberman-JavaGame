package model;

import controller.ControllerPartita;
import javafx.application.Application;
import javafx.stage.Stage;
import view.AudioManager;
import java.util.*;

/**
 * La classe Livello è una classe che contiene tutto il necessario per definire e avviare un livello di gioco
 */
public class Livello {
	
	/**
	 * livello
	 */
	private int level;
	/**
	 * colore avatar
	 */
	private int color;
	/**
	 * nemici in gioco
	 */
	private List<Nemico> nemici = new ArrayList<>(); 
	/**
	 * se il livello è superato
	 */
	private boolean superato;
	/**
	 * se ci sono boss
	 */
	private boolean boss;
	/**
	 * modalità di gioco
	 */
	private boolean keyboard = true;
	/**
	 * dati di gioco
	 */
	private Dato dati;
	
	
	/**
	 * Metodo che fa partire il livello passandogli i parametri necessari per il funzionamento
	 * @param stage la finestra di gioco
	 * @param dati i dati necessari all'avvio per applicare le scelte dell'utente
	 * @param visibile la lista di livelli bloccati e sbloccati
	 * @throws Exception eccezione
	 */
	public void start(Stage stage, Dato dati, boolean[] visibile) throws Exception 
	{
		ControllerPartita controller = new ControllerPartita(color, stage, level, boss, keyboard, dati, visibile, nemici);
	}
	
	/**
	 * Costruttore di Livello che crea un'istanza di Livello
	 */
	public Livello()
	{
		
	}

	/**
	 * Costruttore di Livello che crea un'istanza di Livello
	 * @param level variabile che indica il livello della partita
	 * @param color variabile che indica il colore del personaggio
	 * @param nemici la lista di nemici presenti nel livello
	 * @param superato la variabile che indica se il livello è superato o meno
	 * @param boss la variabile che indica la presenza di boss
	 * @param keyboard la variabile che indica se si gioca da tastiera o da mouse
	 * @param dati i dati necessari all'avvio del livello
	 */
	public Livello(int level, int color, List<Nemico> nemici, boolean superato, boolean boss, boolean keyboard, Dato dati) {
		super();
		this.level = level;
		this.color = color;
		this.nemici = nemici;
		this.superato = superato;
		this.boss = boss;
		this.keyboard = keyboard;
		this.dati = dati;
	}

	/**
	 * Metodo che restituisce il livello
	 * @return livello
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Metodo che imposta il livello
	 * @param level livello
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Metodo che restituisce il colore del personaggio
	 * @return colore del personaggio
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Metodo che imposta il colore del personaggio
	 * @param color colore del personaggio
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * Metodo che restituisce la lista di nemici nel livello
	 * @return lista di nemici nel livello
	 */
	public List<Nemico> getNemici() {
		return nemici;
	}

	/**
	 * Metodo che imposta la lista di nemici nel livello
	 * @param nemici lista di nemici nel livello
	 */
	public void setNemici(List<Nemico> nemici) {
		this.nemici = nemici;
	}

	/**
	 * Metodo che restituisce la variabile che indica se il livello è superato
	 * @return variabile che indica se il livello è superato
	 */
	public boolean isSuperato() {
		return superato;
	}

	/**
	 * Metodo che imposta la variabile che indica se il livello è superato
	 * @param superato variabile che indica se il livello è superato
	 */
	public void setSuperato(boolean superato) {
		this.superato = superato;
	}

	/**
	 * Metodo che restituisce una variabile che indica se il boss è presente in partita o meno
	 * @return variabile che indica se il boss è presente in partita
	 */
	public boolean isBoss() {
		return boss;
	}

	/**
	 * Metodo che imposta una variabile che indica se il boss è presente in partita o meno
	 * @param boss variabile che indica se il boss è presente in partita
	 */
	public void setBoss(boolean boss) {
		this.boss = boss;
	}

	/**
	 * Metodo che restituisce una variabile che indica se si sta giocando da tastiera
	 * @return variabile che indica se si sta giocando da tastiera
	 */
	public boolean isKeyboard() {
		return keyboard;
	}
	
	/**
	 * Metodo che imposta una variabile che indica se si sta giocando da tastiera
	 * @param keyboard variabile che indica se si sta giocando da tastiera
	 */
	public void setKeyboard(boolean keyboard) {
		this.keyboard = keyboard;
	}
	
	/**
	 * Metodo che restituisce i dati di un livello
	 * @return dati di un livello
	 */
	public Dato getDati()
	{
		return dati;
	}
	
	/**
	 * Metodo che imposta i dati di un livello
	 * @param dati dati di un livello
	 */
	public void setDati(Dato dati)
	{
		this.dati = dati;
	}
}

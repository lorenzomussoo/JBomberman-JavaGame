package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.ControllerPartita;
import javafx.stage.Stage;

/**
 * La classe LivelloCreato è una classe che contiene tutto il necessario per definire e avviare un livello di gioco creato dall'editor
 */
public class LivelloCreato implements Serializable {
	
	/**
	 * variabile utile per la serializzazione
	 */
	private static final long serialVersionUID = -5316251192454886361L;
	/**
	 * id slot dell'editor
	 */
	private int editorId;
	/**
	 * livello
	 */
	private int level;
	/**
	 * colore avatar
	 */
	private int color; 
	/**
	 * modalità di gioco
	 */
	private boolean keyboard;
	/**
	 * presenza di boss
	 */
	private boolean boss;
	/**
	 * dati di gioco
	 */
	private Dato dati;
	/**
	 * coordinate x dei blocchi
	 */
	private List<Integer> blocksX = new ArrayList<>();
	/**
	 * coordinate y dei blocchi
	 */
	private List<Integer> blocksY = new ArrayList<>();
	/**
	 * tipo di blocchi
	 */
	private List<Integer> blocksType = new ArrayList<>();
	/**
	 * coordinate x dei nemici
	 */
	private List<Integer> enemyX = new ArrayList<>();
	/**
	 * cooridnate y dei nemici
	 */
	private List<Integer> enemyY = new ArrayList<>();
	/**
	 * tipi di nemici
	 */
	private List<Integer> enemyType = new ArrayList<>();
	
	
	/**
	 * Costruttore che crea un'istanza di LivelloCreato
	 */
	public LivelloCreato()
	{
		
	}
	
	/**
	 * Metodo che fa partire il livello creato
	 * @param stage la finestra della partita
	 * @param dati i dati necessari all'avvio della partita
	 * @throws Exception eccezione
	 */
	public void start(Stage stage, Dato dati) throws Exception 
	{
		ControllerPartita controller = new ControllerPartita(color, stage, level, editorId, boss, keyboard, dati, blocksX, blocksY, blocksType, enemyX, enemyY, enemyType);
	}
	
	/**
	 * Metodo che restituisce il colore selezionato del personaggio
	 * @return colore selezionato del personaggio
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Metodo che imposta il colore selezionato del personaggio
	 * @param color colore selezionato del personaggio
	 */
	public void setColor(int color) {
		this.color = color;
	}
	
	/**
	 * Metodo che restituisce una variabile che indica se si gioca da tastiera
	 * @return variabile che indica se si gioca da tastiera
	 */
	public boolean isKeyboard() {
		return keyboard;
	}

	/**
	 * Metodo che imposta una variabile che indica se si gioca da tastiera
	 * @param keyboard variabile che seleziona il gioco da tastiera
	 */
	public void setKeyboard(boolean keyboard) {
		this.keyboard = keyboard;
	}
	
	/**
	 * Metodo che restituisce l'Id del livello selezionato nell'editor
	 * @return Id dell'editor
	 */
	public int getEditorId() {
		return editorId;
	}

	/**
	 * Metodo che imposta l'Id del livello selezionato nell'editor
	 * @param editorId Id dell'editor
	 */
	public void setEditorId(int editorId) {
		this.editorId = editorId;
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
	 * Metodo che restituisce una variabile che indica se il livello ha un boss
	 * @return variabile che indica se il livello ha un boss
	 */
	public boolean isBoss() {
		return boss;
	}

	/**
	 * Metodo che imposta una variabile che indica se il livello ha un boss
	 * @param boss variabile che indica se il livello ha un boss
	 */
	public void setBoss(boolean boss) {
		this.boss = boss;
	}
	
	/**
	 * Metodo che restituisce i dati necessari all'avvio del livello
	 * @return dati necessari all'avvio del livello
	 */
	public Dato getDati()
	{
		return dati;
	}
	
	/**
	 * Metodo che imposta i dati necessari all'avvio del livello
	 * @param dati dati necessari all'avvio del livello
	 */
	public void setDati(Dato dati)
	{
		this.dati = dati;
	}

	/**
	 * Metodo che restituisce una lista di coordinate x dei blocchi
	 * @return lista di coordinate x dei blocchi
	 */
	public List<Integer> getBlocksX() {
		return blocksX;
	}

	/**
	 * Metodo che imposta una lista di coordinate x dei blocchi
	 * @param blocksX lista di coordinate x dei blocchi
	 */
	public void setBlocksX(List<Integer> blocksX) {
		this.blocksX = blocksX;
	}

	/**
	 * Metodo che restituisce una lista di coordinate y dei blocchi
	 * @return lista di coordinate y dei blocchi
	 */
	public List<Integer> getBlocksY() {
		return blocksY;
	}

	/**
	 * Metodo che imposta una lista di coordinate y dei blocchi
	 * @param blocksY lista di coordinate y dei blocchi
	 */
	public void setBlocksY(List<Integer> blocksY) {
		this.blocksY = blocksY;
	}

	/**
	 * Metodo che restituisce una lista che indica i tipi di blocchi da inserire
	 * @return lista che indica i tipi di blocchi da inserire
	 */
	public List<Integer> getBlocksType() {
		return blocksType;
	}

	/**
	 * Metodo che imposta una lista che indica i tipi di blocchi da inserire
	 * @param blocksType lista che indica i tipi di blocchi da inserire
	 */
	public void setBlocksType(List<Integer> blocksType) {
		this.blocksType = blocksType;
	}

	/**
	 * Metodo che restituisce una lista di coordinate x dei nemici
	 * @return lista di coordinate x dei nemici
	 */
	public List<Integer> getEnemyX() {
		return enemyX;
	}

	/**
	 * Metodo che imposta una lista di coordinate x dei nemici
	 * @param enemyX lista di coordinate x dei nemici
	 */
	public void setEnemyX(List<Integer> enemyX) {
		this.enemyX = enemyX;
	}

	/**
	 * Metodo che restituisce una lista di coordinate y dei nemici
	 * @return lista di coordinate y dei nemici
	 */
	public List<Integer> getEnemyY() {
		return enemyY;
	}

	/**
	 * Metodo che imposta una lista di coordinate y dei nemici
	 * @param enemyY lista di coordinate y dei nemici
	 */
	public void setEnemyY(List<Integer> enemyY) {
		this.enemyY = enemyY;
	}

	/**
	 * Metodo che restituisce una lista che indica i tipi di nemici presenti nel livello
	 * @return lista che indica i tipi di nemici presenti nel livello
	 */
	public List<Integer> getEnemyType() {
		return enemyType;
	}

	/**
	 * Metodo che imposta una lista che indica i tipi di nemici presenti nel livello
	 * @param enemyType lista che indica i tipi di nemici presenti nel livello
	 */
	public void setEnemyType(List<Integer> enemyType) {
		this.enemyType = enemyType;
	}
}
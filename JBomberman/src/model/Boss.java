package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * La classe Boss è una classe che estende Nemici e che rappresenta il Nemico di tipo Boss
 */
public class Boss extends Nemico {
	
	/**
	 * tipo di boss
	 */
	private int tipo; 
	/**
	 * punti all'uccisione
	 */
	private int punti;
	/**
	 * vite del boss
	 */
	private int vite;
	/**
	 * numero massimo di vite
	 */
	private int maxVite;
	/**
	 * dimensione boss
	 */
	private final int dimension = 130;
	/**
	 * velocità boss
	 */
	private double speed = 2;
	/**
	 * se il boss è in gioco
	 */
	private boolean inGioco;
	/**
	 * contenitore boss
	 */
	private ImageView skin = new ImageView();
	/**
	 * immagine 1
	 */
	private Image num1 = null;
	/**
	 * immagine 2
	 */
	private Image num2 = null; 
	/**
	 * immagine 3
	 */
	private Image num3 = null; 
	/**
	 * immagine 4
	 */
	private Image num4 = null;
	/**
	 * coordinate x e y del boss
	 */
	private double enemyX, enemyY;
	/**
	 * immagini animazione verso basso
	 */
	private List<Image> walkingDown = new ArrayList<>(); 
	/**
	 * immagini animazione verso alto
	 */
	private List<Image> walkingUp = new ArrayList<>();
	/**
	 * immagini animazione verso sinistra
	 */
	private List<Image> walkingLeft = new ArrayList<>();
	/**
	 * immagini animazione verso destra
	 */
	private List<Image> walkingRight = new ArrayList<>();

	
	/**
	 * Costruttore di boss che crea un'istanza di Boss
	 */
	public Boss() 
	{
		
	}
	
	/**
	 * Costruttore di Boss che crea un boss in base al tipo
	 * @param tipo tipo di boss da creare
	 */
	public Boss(int tipo) {
		super();
		this.tipo = tipo;
		switch (tipo)
		{
			case 5: default: vite = 20; maxVite = 20;
			 num1 = new Image(getClass().getResourceAsStream("/images/sprites/enemies/metal boss/m1.png")); 
			 num2 = new Image(getClass().getResourceAsStream("/images/sprites/enemies/metal boss/m2.png")); 
			 num3 = new Image(getClass().getResourceAsStream("/images/sprites/enemies/metal boss/m3.png")); 
			 for (int i = 0; i < 2; i++)
			 {
				 walkingUp.add(num1);
				 walkingUp.add(num2);
				 walkingUp.add(num3);
				 walkingUp.add(num2);
				 walkingDown = walkingLeft = walkingRight = walkingUp;
			 }
			 break;
			case 6: vite = 25; maxVite = 25;
			 num1 = new Image(getClass().getResourceAsStream("/images/sprites/enemies/clown boss/clown2.png")); 
			 num2 = new Image(getClass().getResourceAsStream("/images/sprites/enemies/clown boss/clown1.png")); 
			 num3 = new Image(getClass().getResourceAsStream("/images/sprites/enemies/clown boss/clown3.png")); 
			 num4 = new Image(getClass().getResourceAsStream("/images/sprites/enemies/clown boss/clown4.png")); 
			 for (int i = 0; i < 2; i++)
			 {
				 walkingUp.add(num1);
				 walkingUp.add(num2);
				 walkingUp.add(num3);
				 walkingUp.add(num4);
				 walkingDown = walkingLeft = walkingRight = walkingUp;
			 }
			 break;
		}
		skin.setImage(num1);
		skin.setPreserveRatio(true);
		skin.setFitWidth(dimension);
		skin.setFitHeight(dimension);
	}
	
	/**
	 * Metodo che diminuisce le vite del boss di una se viene colpito
	 */
	public void decreaseVite()
	{
		if (vite > 0)
		{
			vite--;
		}
	}

	/**
	 * Metodo che posiziona il boss alle coordinate x e y
	 * @param x la coordinata x
	 * @param y la coordinata y
	 */
	public void relocate(double x, double y)
	{
		skin.relocate(x, y);
	}

	/**
	 * Metodo che restituisce il quantitativo di punti che si ottiene quando si uccide il boss
	 */
	public int getPunti() {
		return punti;
	}

	/**
	 * Metodo che imposta il quantitativo di punti che si ottiene quando si uccide il boss
	 * @param punti punti che si ottengono alla sconfitta del boss
	 */
	public void setPunti(int punti) {
		this.punti = punti;
	}

	/**
	 * Metodo che restituisce l'ImageView che rappresenta il boss
	 * @return l'oggetto ImageView che rappresenta il boss
	 */
	public ImageView getEnemy(){
		return skin;
	}

	/**
	 * Metodo che restituisce l'immagine numero 1 dell'aspetto del boss
	 * @return immagine numero 1 dell'aspetto del boss
	 */
	public Image getNum1() {
		return num1;
	}

	/**
	 * Metodo che imposta l'immagine numero 1 dell'aspetto del boss
	 * @param num1 immagine numero 1 dell'aspetto del boss
	 */
	public void setNum1(Image num1) {
		this.num1 = num1;
	}

	/**
	 * Metodo che restituisce le dimensioni del boss
	 * @return dimensioni del boss
	 */
	public int getDimension() {
		return dimension;
	}

	/**
	 * Metodo che restituisce il tipo del boss
	 * @return tipo del boss
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Metodo che imposta il tipo del boss
	 * @param tipo tipo del boss
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Metodo che restituisce la velocità di movimento del boss
	 * @return velocità di movimento del boss
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Metodo che imposta la velocità di movimento del boss
	 * @param speed velocità di movimento del boss
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	/**
	 * Metodo che restituisce la coordinata x del boss
	 * @return x coordinata x del boss
	 */
	public double getX()
	{
		return enemyX;
	}
	
	/**
	 * Metodo che imposta la coordinata x del boss
	 * @param x coordinata x del boss
	 */
	public void setX(double x)
	{
		enemyX = x;
	}
	
	/**
	 * Metodo che restituisce la coordinata y del boss
	 * @return y coordinata y del boss
	 */
	public double getY() 
	{
		return enemyY;
	}
	
	/**
	 * Metodo che imposta la coordinata y del boss
	 * @param y coordinata y del boss
	 */
	public void setY(double y)
	{
		enemyY = y;
	}

	/**
	 * Metodo che restituisce la variabile che indica se il boss è in gioco
	 * @return variabile che indica se il boss è in gioco
	 */
	public boolean isInGioco() {
		return inGioco;
	}

	/**
	 * Metodo che imposta la variabile che indica se il boss è in gioco
	 * @param inGioco variabile che indica se il boss è in gioco
	 */
	public void setInGioco(boolean inGioco) {
		this.inGioco = inGioco;
	}
	
	/**
	 * Metodo che restituisce la lista di animazioni per il movimento a sinistra
	 * @return lista di animazioni per il movimento a sinistra
	 */
	public List<Image> getAnimationLeft()
	{
		return walkingLeft;
	}
	
	/**
	 * Metodo che restituisce la lista di animazioni per il movimento a destra
	 * @return lista di animazioni per il movimento a destra
	 */
	public List<Image> getAnimationRight()
	{
		return walkingRight;
	}
	
	/**
	 * Metodo che restituisce la lista di animazioni per il movimento in alto
	 * @return lista di animazioni per il movimento in alto
	 */
	public List<Image> getAnimationUp()
	{
		return walkingUp;
	}
	
	/**
	 * Metodo che restituisce la lista di animazioni per il movimento in basso
	 * @return lista di animazioni per il movimento in basso
	 */
	public List<Image> getAnimationDown()
	{
		return walkingDown;
	}
	
	/**
	 * Metodo che restituisce le vite rimaste al boss
	 * @return vite rimaste al boss
	 */
	public int getVite()
	{
		return vite;
	}
	
	/**
	 * Metodo che imposta le vite rimaste al boss
	 * @param vite vite rimaste al boss
	 */
	public void setVite(int vite)
	{
		this.vite = vite;
	}
	
	/**
	 * Metodo che restrituisce le vite massime del boss
	 * @return vite massime del boss
	 */
	public int getMaxVite()
	{
		return maxVite;
	}

	/**
	 * Metodo che imposta le vite massime del boss
	 * @param i vite massime del boss
	 */
	public void setMaxVite(int i) {
		maxVite = i;
	}
}
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * La classe PowerUp è la classe che crea i power up utilizzabili nel gioco
 */
public class PowerUp {

	/**
	 * contenitore powerup
	 */
	private ImageView icona; 
	/**
	 * id del powerup
	 */
	private int id;
	/**
	 * coordinata x del powerup
	 */
	private int x;
	/**
	 * coordinata y del powerup
	 */
	private int y;
	
	
	/**
	 * Costruttore di PowerUp che crea un'istanza di PowerUp un base al tipo
	 * @param id tipo di power up
	 */
	public PowerUp(int id) {
		super();
		this.id = id;
		this.icona = new ImageView();
		Image img;
		switch(id)
		{
			case 0 : default : img = new Image(getClass().getResourceAsStream("/images/sprites/powerup/botola.png")); break;
			case 1 : img = new Image(getClass().getResourceAsStream("/images/sprites/powerup/cake.png")); break;
			case 2 : img = new Image(getClass().getResourceAsStream("/images/sprites/powerup/extrabomb.png")); break;
			case 3 : img = new Image(getClass().getResourceAsStream("/images/sprites/powerup/heart.png")); break;
			case 4 : img = new Image(getClass().getResourceAsStream("/images/sprites/powerup/indestructible armor.png")); break;
			case 5 : img = new Image(getClass().getResourceAsStream("/images/sprites/powerup/remote control.png")); break;
			case 6 : img = new Image(getClass().getResourceAsStream("/images/sprites/powerup/skull.png")); break; 
			case 7 : img = new Image(getClass().getResourceAsStream("/images/sprites/powerup/time.png")); break;
			case 8 : img = new Image(getClass().getResourceAsStream("/images/sprites/powerup/accelerator.png")); break;
		}
		icona.setImage(img);
	}

	/**
	 * Metodo che restituisce l'ImageView che rappresenta il power up
	 * @return ImageView che rappresenta il power up
	 */
	public ImageView getIcona() {
		return icona;
	}

	/**
	 * Metodo che imposta l'ImageView che rappresenta il power up
	 * @param icona ImageView che rappresenta il power up
	 */
	public void setIcona(ImageView icona) {
		this.icona = icona;
	}

	/**
	 * Metodo che restituisce il tipo di un power up
	 * @return tipo di un power up
	 */
	public int getId() {
		return id;
	}

	/**
	 * Metodo che imposta il tipo di un power up
	 * @param id tipo di un power up
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Metodo che imposta la coordinata x del power up
	 * @param i coordinata x del power up
	 */
	public void setX(int i)
	{
		x = i;
	}
	
	/**
	 * Metodo che restituisce la coordinata x del power up
	 * @return  coordinata x del power up
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Metodo che imposta la coordinata y del power up
	 * @param i coordinata y del power up
	 */
	public void setY(int i)
	{
		y = i;
	}
	
	/**
	 * Metodo che restituisce la coordinata y del power up
	 * @return coordinata y del power up
	 */
	public int getY()
	{
		return y;
	}
}
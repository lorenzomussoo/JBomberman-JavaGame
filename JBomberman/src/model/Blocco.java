package model;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * La classe Blocco è la classe che rappresenta il blocco, un elemento di gioco
 */
public class Blocco implements Serializable {

	/**
	 * variabile utile per la serializzazione
	 */
	private static final long serialVersionUID = -5265865374002964341L;
	/**
	 * contenitore immagine del blocco
	 */
	private ImageView skin = new ImageView();
	/**
	 * immagine
	 */
	private Image img;
	/**
	 * dimensione del blocco
	 */
	private final int blockDimension = 40;
	/**
	 * variabile che indica la distruttibilità del blocco
	 */
	private boolean destructible = false;
	
	
	/**
	 * Costruttore della classe Blocco e serve per creare un'istanza di blocco 
	 */
	public Blocco()
	{
		
	}
	
	/**
	 * Costruttore della classe blocco che crea un blocco, seleziona l'immagine in base al tipo e decide se è distruttibile
	 * @param i livello in base al quale decidere il tipo di blocco
	 * @param d variabile che indica se il blocco è distruttibile
	 */
	public Blocco(int i, boolean d)
	{
		if (d)
		{
			switch(i)
			{
				case 1 : default : setSkin("/images/sprites/blocks/stage 1/breakable.png"); setDestructible(); break;
				case 2 : setSkin("/images/sprites/blocks/stage 2/breakable.png"); setDestructible(); break;
				case 3 : setSkin("/images/sprites/blocks/stage 3/breakable.png"); setDestructible(); break;
				case 4 : setSkin("/images/sprites/blocks/stage 4/breakable.png"); setDestructible(); break;
				case 5 : setSkin("/images/sprites/blocks/stage 5/breakable.png"); setDestructible(); break;
				case 6 : setSkin("/images/sprites/blocks/stage 6/breakable.png"); setDestructible(); break;
				case 7 : setSkin("/images/sprites/blocks/stage 7/breakable.png"); setDestructible(); break;
				case 8 : setSkin("/images/sprites/blocks/stage 8/breakable.png"); setDestructible(); break; 
			}
		}
		else
		{
			switch(i)
			{
				case 1 : default : setSkin("/images/sprites/blocks/stage 1/unbreakable.png"); break;
				case 2 : setSkin("/images/sprites/blocks/stage 2/unbreakable.png"); break;
				case 3 : setSkin("/images/sprites/blocks/stage 3/unbreakable.png"); break;
				case 4 : setSkin("/images/sprites/blocks/stage 4/unbreakable.png"); break;
				case 5 : setSkin("/images/sprites/blocks/stage 5/unbreakable.png"); break;
				case 6 : setSkin("/images/sprites/blocks/stage 6/unbreakable.png"); break;
				case 7 : setSkin("/images/sprites/blocks/stage 7/unbreakable.png"); break;
				case 8 : setSkin("/images/sprites/blocks/stage 8/unbreakable.png"); break;
			}
		}
	}
	
	/**
	 * Metodo che imposta la variabile che indica se un blocco è distruttibile a true
	 */
	public void setDestructible() 
	{
		destructible = true;
	}

	/**
	 * Metodo che imposta l'immagine che rappresenta l'aspetto del blocco
	 * @param image immagine aspetto del blocco
	 */
	public void setSkin(String image)
	{
		img = new Image(image);
	}
	
	/**
	 * Metodo che posiziona il blocco alle coordinate x e y
	 * @param x coordinata x
	 * @param y coordinata y
	 */
	public void relocate(double x, double y)
	{
		skin.relocate(x, y);
	}
	
	/**
	 * Metodo che restituisce l'ImageView che rappresenta il blocco
	 * @return ImageView che rappresenta il blocco
	 */
	public ImageView getBlock()
	{
		return skin;
	}
	
	/**
	 * Metoddo che restituisce l'immagine del blocco
	 * @return immagine del blocco
	 */
	public Image getImage()
	{
		return img;
	}

	/**
	 * Metodo che restituisce le dimensioni del blocco
	 * @return dimensione di un lato del blocco
	 */
	public int getBlockdimension() 
	{
		return blockDimension;
	}
}
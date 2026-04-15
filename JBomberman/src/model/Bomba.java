package model;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * La classe Bomba è la classe che crea l'oggetto bomba, elemento di gioco
 */
public class Bomba {

	/**
	 * contenitore immagine della bomba
	 */
	private ImageView skin = new ImageView();
	/**
	 * immagine
	 */
	private Image img;
	/**
	 * dimensione della bomba
	 */
	private final int blockDimension = 40;
	/**
	 * variabile che indica se la bomba è a comando
	 */
	private boolean aComando;
	/**
	 * lista di immagini per l'animazione
	 */
	private List<Image> anim = new ArrayList<>();
	
	
	/**
	 * Costruttore di Bomba che crea un'istanza di Bomba scegliendo in base al tipo l'aspetto
	 * @param i variabile che indica il tipo di bomba da creare
	 */
	public Bomba(int i)
	{
		switch(i)
		{
			case 1 : default : 
			img = new Image(getClass().getResourceAsStream("/images/sprites/bombs/black3.png")); skin.setImage(img);
			anim.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/black3.png")));
			anim.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/black2.png")));
			anim.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/black1.png")));
			break;
			case 2 : 
			img = new Image(getClass().getResourceAsStream("/images/sprites/bombs/bomb1SettableBlack.png")); skin.setImage(img); aComando = true;
			anim.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/bomb2SettableBlack.png")));
			anim.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/bomb1SettableBlack.png")));
			anim.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/bomb2SettableBlack.png")));
			break;
		}
	}
	
	/**
	 * Costruttore di bomba che crea un'istanza di bomba impostando i parametri riguardanti l'aspetto e il tipo
	 * @param skin imposta l'ImageView che rappresenta la bomba
	 * @param img immagine che rappresenta l'aspetto della bomba
	 * @param aComando variabile che indica se la bomba è a comando
	 */
	public Bomba(ImageView skin, Image img, boolean aComando) {
		super();
		this.skin = skin;
		this.img = img;
		this.aComando = aComando;
	}

	/**
	 * Metodo che restituisce l'ImageView che rappresenta la bomba
	 * @return oggetto ImageView che rappresenta la bomba
	 */
	public ImageView getSkin() {
		return skin;
	}

	/**
	 * Metodo che imposta l'ImageView che rappresenta la bomba
	 * @param skin oggetto ImageView che rappresenta la bomba
	 */
	public void setSkin(ImageView skin) {
		this.skin = skin;
	}

	/**
	 * Metodo che restituisce l'immagine che rappresenta l'aspetto della bomba
	 * @return oggetto Image che rappresenta l'aspetto della bomba
	 */
	public Image getImg() {
		return img;
	}

	/**
	 * Metodo che imposta l'Image che rappresenta l'aspetto della bomba
	 * @param img oggetto Image che rappresenta l'aspetto della bomba
	 */
	public void setImg(Image img) {
		this.img = img;
	}

	/**
	 * Metodo che restituisce la variabile che indica se la bomba è a comando
	 * @return variabile che indica se la bomba è a comando
	 */
	public boolean isaComando() {
		return aComando;
	}

	/**
	 * Metodo che imposta la variabile che indica se la bomba è a comando
	 * @param aComando variabile che indica se la bomba è a comando
	 */
	public void setaComando(boolean aComando) {
		this.aComando = aComando;
	}

	/**
	 * Metodo che restituisce le dimensioni della Bomba
	 * @return dimensioni della bomba
	 */
	public int getBlockDimension() {
		return blockDimension;
	}
	
	/**
	 * Metodo che restituisce la lista di immagini che formano le animazioni della bomba
	 * @return lista di immagini che formano le animazioni della bomba
	 */
	public List<Image> getAnimation()
	{
		return anim;
	}
}
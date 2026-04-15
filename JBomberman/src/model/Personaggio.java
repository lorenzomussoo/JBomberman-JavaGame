package model;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * La classe Personaggio è la classe che crea Bomberman nel gioco
 */
public class Personaggio {
	
	/**
	 * dimensione del personaggio
	 */
	private final int characterDimension = 55;
	/**
	 * velocità del personaggio
	 */
	private double speed = 2;
	/**
	 * coordinate x e y dei personaggi
	 */
	private double characterX = 0.0, characterY = 0.0;
	/**
	 * immagine frontale
	 */
	private Image front = null; 
	/**
	 * immagine retro
	 */
	private Image back = null; 
	/**
	 * immagine sinistra
	 */
	private Image leftProfile = null; 
	/**
	 * immagine destra
	 */
	private Image rightProfile = null; 
	/**
	 * animazioni camminata verso il basso
	 */
	private List<Image> walkingDown = new ArrayList<>(); 
	/**
	 * animazioni camminata verso il basso
	 */
	private List<Image> walkingUp = new ArrayList<>();
	/**
	 * animazioni camminata verso sinistra
	 */
	private List<Image> walkingLeft = new ArrayList<>(); 
	/**
	 * animazioni camminata verso destra
	 */
	private List<Image> walkingRight = new ArrayList<>(); 
	/**
	 * immagini 
	 */
	private List<Image> v = new ArrayList<>();
	/**
	 * contenitore personaggio
	 */
	private ImageView character = new ImageView();
	
	
	/**
	 * Costruttore di Personaggio che crea un'istanza di personaggio diversa in base al colore selezionato
	 * @param colorSelection colore selezionato
	 */
	public Personaggio(int colorSelection)
	{
		switch(colorSelection)
		{
			case 1 : default : 
				front = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/5 white.png")); //add image char
				back = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/14 white.png")); //add image char
				leftProfile = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/8 white.png")); //add image char
				rightProfile = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/11 white.png")); //add image char
				for (int i = 0; i < 2; i++)
				{
					walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/13 white.png")));
					walkingUp.add(back);
					walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/15 white.png")));
					walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/6 white.png")));
					walkingDown.add(front);
					walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/4 white.png")));
					walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/17 white.png")));
					walkingLeft.add(leftProfile);
					walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/7 white.png")));
					walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/10 white.png")));
					walkingRight.add(rightProfile);
					walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/18 white.png")));
				 }
				 v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/20 white.png")));
				 v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/21 white.png")));
				 v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/22 white.png")));
				 v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/23 white.png")));
				 
				break;
			case 2 : 
				front = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/5 black.png")); //add image char
	 		 	back = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/14 black.png")); //add image char
	 		    leftProfile = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/8 black.png")); //add image char
	 		    rightProfile = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/11 black.png")); //add image char
	 		    for (int i = 0; i < 2; i++)
				{
					walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/13 black.png")));
					walkingUp.add(back);
					walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/15 black.png"))); 
					walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/6 black.png")));
					walkingDown.add(front);
					walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/4 black.png")));
					walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/17 black.png")));
					walkingLeft.add(leftProfile);
					walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/7 black.png")));
					walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/10 black.png")));
					walkingRight.add(rightProfile);
					walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/18 black.png")));
				 }
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/20 black.png")));
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/21 black.png")));
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/22 black.png")));
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/23 black.png")));
				break;
			case 3 : 
				front = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/5 red.png")); //add image char
		 		back = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/14 red.png")); //add image char
		 		leftProfile = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/8 red.png")); //add image char
		 		rightProfile = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/11 red.png")); //add image char
		 		for (int i = 0; i < 2; i++)
				{
					walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/13 red.png")));
					walkingUp.add(back);
					walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/15 red.png")));
					walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/6 red.png")));
					walkingDown.add(front);
					walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/4 red.png")));
					walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/17 red.png")));
					walkingLeft.add(leftProfile);
					walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/7 red.png")));
					walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/10 red.png")));
					walkingRight.add(rightProfile);
					walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/18 red.png")));
				}
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/20 red.png")));
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/21 red.png")));
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/22 red.png")));
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/23 red.png")));
				break;
			case 4 : 
				front = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/5 blue.png")); //add image char
		 		back = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/14 blue.png")); //add image char
		 		leftProfile = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/8 blue.png")); //add image char
		 		rightProfile = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/11 blue.png")); //add image char
		 		for (int i = 0; i < 2; i++)
				{
					walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/13 blue.png")));
					walkingUp.add(back);
					walkingUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/15 blue.png")));
					walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/6 blue.png")));
					walkingDown.add(front);
					walkingDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/4 blue.png"))); 
					walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/17 blue.png")));
					walkingLeft.add(leftProfile);
					walkingLeft.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/7 blue.png"))); 
					walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/10 blue.png")));
					walkingRight.add(rightProfile);
					walkingRight.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/18 blue.png")));
					 
				}
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/20 blue.png")));
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/21 blue.png")));
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/22 blue.png")));
				v.add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/23 blue.png")));
				break;
		}
		character.setImage(front);
		character.setPreserveRatio(true);
		character.setFitWidth(characterDimension);
		character.setFitHeight(characterDimension);
	}
	
	/**
	 * Metodo che restituisce l'ImageView che rappresenta il personaggio
	 * @return oggetto ImageView che rappresenta il personaggio
	 */
	public ImageView getCharacter()
	{
		return character;
	}
	
	/**
	 * Metodo che restituisce le dimensioni del personaggio
	 * @return dimensioni del personaggio
	 */
	public int getDimension()
	{
		return characterDimension;
	}
	
	/**
	 * Metodo che restituisce la lista di immagini che forma i frame dell'animazione della camminata a sinistra
	 * @return lista di immagini che forma l'animazione della camminata a sinistra
	 */
	public List<Image> getAnimationLeft()
	{
		return walkingLeft;
	}
	
	/**
	 * Metodo che restituisce la lista di immagini che forma i frame dell'animazione della camminata a destra
	 * @return lista di immagini che forma l'animazione della camminata a destra
	 */
	public List<Image> getAnimationRight()
	{
		return walkingRight;
	}
	
	/**
	 * Metodo che restituisce la lista di immagini che forma i frame dell'animazione della camminata verso l'alto
	 * @return lista di immagini che forma l'animazione della camminata verso l'alto
	 */
	public List<Image> getAnimationUp()
	{
		return walkingUp;
	}
	
	/**
	 * Metodo che restituisce la lista di immagini che forma i frame dell'animazione della camminata verso il basso
	 * @return lista di immagini che forma l'animazione della camminata verso il basso
	 */
	public List<Image> getAnimationDown()
	{
		return walkingDown;
	}
	
	/**
	 * Metodo che restituisce la lista di immagini che forma i frame dell'animazione della morte
	 * @return lista di immagini che forma l'animazione della morte
	 */
	public List<Image> getAnimationDeath()
	{
		return v;
	}
	
	/**
	 * Metodo che restituisce l'immagine frontale del personaggio
	 * @return immagine frontale del personaggio
	 */
	public Image getFront()
	{
		return front;
	}
	
	/**
	 * Metodo che restituisce l'immagine posteriore del personaggio
	 * @return immagine posteriore del personaggio
	 */
	public Image getBack()
	{
		return back;
	}
	
	/**
	 * Metodo che restituisce l'immagine laterale sinistra del personaggio
	 * @return immagine laterale sinistra del personaggio
	 */
	public Image getLeft()
	{
		return leftProfile;
	}
	
	/**
	 * Metodo che restituisce l'immagine laterale destra del personaggio
	 * @return immagine laterale destra del personaggio
	 */
	public Image getRight()
	{
		return rightProfile;
	}
	
	/**
	 * Metodo che restituisce la coordinata x del personaggio
	 * @return coordinata x del personaggio
	 */
	public double getX()
	{
		return characterX;
	}
	
	/**
	 * Metodo che imposta la coordinata x del personaggio
	 * @param x coordinata x del personaggio
	 */
	public void setX(double x)
	{
		characterX = x;
	}
	
	/**
	 * Metodo che restituisce la coordinata y del personaggio
	 * @return coordinata y del personaggio
	 */
	public double getY() 
	{
		return characterY;
	}
	
	/**
	 * Metodo che imposta la coordinata y del personaggio
	 * @param y coordinata y del personaggio
	 */
	public void setY(double y)
	{
		characterY = y;
	}
	
	/**
	 * Metodo che restituisce la velocità del personaggio
	 * @return velocità del personaggio
	 */
	public double getSpeed()
	{
		return speed;
	}
	
	/**
	 * Metodo che incrementa la velocità del personaggio
	 * @param s incremento della velocità
	 */
	public void increaseSpeed(double s)
	{
		speed += s;
	}
	
	/**
	 * Metodo che decrementa la velocità del personaggio
	 * @param s decremento della velocità
	 */
	public void decreaseSpeed(double s)
	{
		speed -= s;
	}
}
package model;

import javafx.scene.image.Image;

/**
 * La classe Timer è la classe che permette di creare il timer che scorre durante la partita
 */
public class Timer {
	
	/**
	 * Costruttore del Timer, crea un'istanza di timer
	 */
	public Timer() {
		
	}
	/**
	 * Metodo che restituisce un'immagine del timer in base al numero
	 * @param i numero in base al quale selezionare l'immagine
	 * @return immagine del timer
	 */
	public Image getImage(int i)
	{
		switch(i)
		{
		case 0 : default : return new Image(getClass().getResourceAsStream("/images/sprites/timer/0.png"));
		case 1 : return new Image(getClass().getResourceAsStream("/images/sprites/timer/1.png"));
		case 2 : return new Image(getClass().getResourceAsStream("/images/sprites/timer/2.png"));
		case 3 : return new Image(getClass().getResourceAsStream("/images/sprites/timer/3.png"));
		case 4 : return new Image(getClass().getResourceAsStream("/images/sprites/timer/4.png"));
		case 5 : return new Image(getClass().getResourceAsStream("/images/sprites/timer/5.png"));
		case 6 : return new Image(getClass().getResourceAsStream("/images/sprites/timer/6.png"));
		case 7 : return new Image(getClass().getResourceAsStream("/images/sprites/timer/7.png"));
		case 8 : return new Image(getClass().getResourceAsStream("/images/sprites/timer/8.png"));
		case 9 : return new Image(getClass().getResourceAsStream("/images/sprites/timer/9.png"));
		}
	}
	
	/**
	 * Metodo che restituisce lo sfondo su cui applicare il timer
	 * @return sfondo su cui applicare il timer
	 */
	public Image getTimerBackground()
	{
		return new Image(getClass().getResourceAsStream("/images/sprites/timer/start.png"));
	}
}
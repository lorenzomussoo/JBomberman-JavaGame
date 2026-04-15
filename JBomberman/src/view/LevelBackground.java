package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * La classe LevelBackGround è la classe che permette di selezionare lo fondo dei livelli
 */
public class LevelBackground {

	/**
	 * contenitore immagine sfondo
	 */
	private ImageView wallpaper = new ImageView();
	
	
	/**
	 * Costruttore della Classe LevelBackGround che crea un'istanza di LevelBackGround e sceglie l'immagine di sfondo in base al livello
	 * @param livello livello in base al quale scegliere l'immagine
	 */
	public LevelBackground(int livello)
	{
		switch(livello)
		{
			case 1 : default : wallpaper.setImage(new Image(getClass().getResourceAsStream("/images/stage/void/stage 1.png"))); break;
			case 2 : wallpaper.setImage(new Image(getClass().getResourceAsStream("/images/stage/void/stage 2.png"))); break;
			case 3 : wallpaper.setImage(new Image(getClass().getResourceAsStream("/images/stage/void/stage 3.png"))); break;
			case 4 : wallpaper.setImage(new Image(getClass().getResourceAsStream("/images/stage/void/stage 4.png"))); break;
			case 5 : wallpaper.setImage(new Image(getClass().getResourceAsStream("/images/stage/void/stage 5.png"))); break;
			case 6 : wallpaper.setImage(new Image(getClass().getResourceAsStream("/images/stage/void/stage 6.png"))); break;
			case 7 : wallpaper.setImage(new Image(getClass().getResourceAsStream("/images/stage/void/stage 7.png"))); break;
			case 8 : wallpaper.setImage(new Image(getClass().getResourceAsStream("/images/stage/void/stage 8.png"))); break;
		}
	}
	
	/**
	 * Metodo che restituisce l'immagine di sfondo
	 * @return immagine di sfondo
	 */
	public ImageView getWallpaper()
	{
		return wallpaper;
	}
}

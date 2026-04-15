package view;

import java.util.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * La classe gioco è la classe che gestisce la parte grafica del gioco
 */
public class Gioco implements Observer { 
	
	/**
	 * larghezza stage
	 */
	private int stageWidth = 520;
	/**
	 * altezza stage
	 */
	private int stageHeight = 440;
	/**
	 * dimensioni spazio zona delle informazioni
	 */
	private int infoSpace = 85;
	/**
	 * larghezza bordi
	 */
	private int bordersWidth = 160;
	/**
	 * altezza bordi
	 */
	private int bordersHeight = 80;
	/**
	 * dimensione blocchi
	 */
	private int blockDimension = 40;
	/**
	 * stage
	 */
	private Stage stage;
	/**
	 * scena
	 */
	private Scene scene;
	/**
	 * pane delle informazioni
	 */
	private Pane info = new Pane();
	/**
	 * terreno di gioco
	 */
	private Pane stageGround;
	/**
	 * sfondo
	 */
	private Pane backGround;
	/**
	 * terreno piazzamento delle bombe
	 */
	private Pane bombGround;
	/**
	 * terreno piazzamento blocchi
	 */
	private Pane blockPane = new Pane();
	/**
	 * pane delle informazioni
	 */
	private ImageView infoPane = new ImageView();
	/**
	 * immagine sfondo delle informazioni
	 */
	private Image infoImage = new Image(getClass().getResourceAsStream("/images/sprites/timer/bar.png"));
	/**
	 * contenitore sfondo timer
	 */
	private ImageView timerBackground = new ImageView();
	/**
	 * contenitore sfondo timer inizio
	 */
	private Image tbackground = new Image(getClass().getResourceAsStream("/images/sprites/timer/start.png"));
	/**
	 * contenitore immagini vite
	 */
	private ImageView lives = new ImageView();
	/**
	 * immagine vite default
	 */
	private Image defaultLives = new Image(getClass().getResourceAsStream("/images/sprites/timer/5.png"));
	/**
	 * sfondo
	 */
	private LevelBackground wallpaper;
	/**
	 * contatore del punteggio
	 */
	private int punteggio = 0;
	/**
	 * punteggio massimo del livello
	 */
	private int maxPunteggio = 10000;
	/**
	 * immagine 0 del punteggio
	 */
	private ImageView p0 = new ImageView();
	/**
	 * immagine 1 del punteggio
	 */
	private ImageView p1 = new ImageView();
	/**
	 * immagine 2 del punteggio
	 */
	private ImageView p2 = new ImageView();
	/**
	 * immagine 3 del punteggio
	 */
	private ImageView p3 = new ImageView();
	/**
	 * immagine 4 del punteggio
	 */
	private ImageView p4 = new ImageView();
	/**
	 * immagine punteggio default
	 */
	private Image defaultPoints = new Image(getClass().getResourceAsStream("/images/sprites/timer/0.png"));
	
	
	/**
	 * Costuttore di gioco che crea un'istanza di gioco
	 */
	public Gioco()
	{
		
	}

	/**
	 * Costruttore di gioco che crea un'istanza di gioco
	 * @param stage finestra di gioco
	 * @param level livello
	 */
	public Gioco(Stage stage, int level)
	{	
		wallpaper = new LevelBackground(level);
		backGround = new Pane();
		backGround.getChildren().add(wallpaper.getWallpaper());
		backGround.setMaxWidth(stageWidth + bordersWidth);
		backGround.setMaxHeight(stageHeight + bordersHeight + infoSpace);
		wallpaper.getWallpaper().setFitWidth(stageWidth + bordersWidth);
		wallpaper.getWallpaper().setFitHeight(stageHeight + bordersHeight);
		wallpaper.getWallpaper().setLayoutY(infoSpace);
		bombGround = new Pane();
		backGround.getChildren().add(bombGround);
		bombGround.setLayoutX(bordersHeight);
		bombGround.setLayoutY(infoSpace + blockDimension);
		stageGround = new Pane();
		backGround.getChildren().add(stageGround);
		stageGround.setLayoutX(bordersHeight);
		stageGround.setLayoutY(infoSpace + blockDimension);
		backGround.getChildren().add(infoPane);
		infoPane.setImage(infoImage);
		infoPane.setFitWidth(stageWidth + bordersWidth);
		backGround.getChildren().add(timerBackground);
		timerBackground.setImage(tbackground);
		timerBackground.setFitHeight(40);
		timerBackground.setPreserveRatio(true);
		timerBackground.setLayoutX(460);
		timerBackground.setLayoutY(22);
		infoPane.setPreserveRatio(true);
		backGround.getChildren().add(blockPane);
		blockPane.setTranslateY(infoSpace);
		blockPane.setMaxWidth(stageWidth + bordersWidth);
		blockPane.setMaxHeight(stageHeight + bordersHeight);
		backGround.getChildren().add(info);
		info.relocate(0.0, 0.0);
		info.setMaxHeight(infoSpace);
		info.setMaxWidth(stageWidth + bordersWidth);
		backGround.getChildren().add(lives);
		lives.setImage(defaultLives);
		lives.setLayoutY(22);
		lives.setLayoutX(61);
		lives.setPreserveRatio(true);
		lives.setFitHeight(40);
		backGround.getChildren().add(p0);
		p0.setImage(defaultPoints);
		p0.setLayoutX(275);
		p0.setLayoutY(24);
		p0.setPreserveRatio(true);
		p0.setFitHeight(37);
		backGround.getChildren().add(p1);
		p1.setImage(defaultPoints);
		p1.setLayoutX(250);
		p1.setLayoutY(24);
		p1.setPreserveRatio(true);
		p1.setFitHeight(37);
		backGround.getChildren().add(p2);
		p2.setImage(defaultPoints);
		p2.setLayoutX(225);
		p2.setLayoutY(24);
		p2.setPreserveRatio(true);
		p2.setFitHeight(37);
		backGround.getChildren().add(p3);
		p3.setImage(defaultPoints);
		p3.setLayoutX(200);
		p3.setLayoutY(24);
		p3.setPreserveRatio(true);
		p3.setFitHeight(37);
		backGround.getChildren().add(p4);
		p4.setImage(defaultPoints);
		p4.setLayoutX(175);
		p4.setLayoutY(24);
		p4.setPreserveRatio(true);
		p4.setFitHeight(37);
		scene = new Scene(backGround, stageWidth + bordersWidth, stageHeight + bordersHeight + infoSpace);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	/**
	 * Metodo che aggiorna la variabile del punteggio
	 */
	public void score()
	{
		if(punteggio < maxPunteggio)
		{
			punteggio += 200;
			aggiornaPunteggio();
		}
		
	}
	
	/**
	 * Metodo che aggiorna il punteggio a schermo
	 */
	public void aggiornaPunteggio() 
	{
		String p = String.valueOf(punteggio);
		while (p.length() - 1 < 4)
		{
			p = "0" + p;
		}
		for(int i = 0; i < 4; i++)
		{
			char c = p.charAt(i);
			int x = Integer.parseInt("" + c);
			Image img;
			switch(x)
			{
				case 0 : default : img = new Image(getClass().getResourceAsStream("/images/sprites/timer/0.png")); break;
				case 1 : img = new Image(getClass().getResourceAsStream("/images/sprites/timer/1.png")); break;
				case 2 : img = new Image(getClass().getResourceAsStream("/images/sprites/timer/2.png")); break;
				case 3 : img = new Image(getClass().getResourceAsStream("/images/sprites/timer/3.png")); break;
				case 4 : img = new Image(getClass().getResourceAsStream("/images/sprites/timer/4.png")); break;
				case 5 : img = new Image(getClass().getResourceAsStream("/images/sprites/timer/5.png")); break;
				case 6 : img = new Image(getClass().getResourceAsStream("/images/sprites/timer/6.png")); break;
				case 7 : img = new Image(getClass().getResourceAsStream("/images/sprites/timer/7.png")); break;
				case 8 : img = new Image(getClass().getResourceAsStream("/images/sprites/timer/8.png")); break;
				case 9 : img = new Image(getClass().getResourceAsStream("/images/sprites/timer/9.png")); break;
			}
			switch(i)
			{
				case 0 : p4.setImage(img); break;
				case 1 : p3.setImage(img); break;
				case 2 : p2.setImage(img); break;
				case 3 : p1.setImage(img); break;
				case 4 : p0.setImage(img); break;
			}
		}
	}
	
	/**
	 * Metodo che restituisce il terreno di gioco
	 * @return terreno di gioco
	 */
	public Pane getStageGround()
	{
		return stageGround;
	}
	
	/**
	 * Metodo che restituisce i terreno su cui posizionare le bombe
	 * @return terreno su cui posizionare le bombe
	 */
	public Pane getBombGround()
	{
		return bombGround;
	}

	/**
	 * Metodo che restituisce la scena
	 * @return scena
	 */
	public Scene getScene() 
	{
		return scene;
	}
	
	/**
	 * Metodo che restituisce lo sfondo
	 * @return sfondo
	 */
	public Pane getBackGround()
	{
		return backGround;
	}
	
	/**
	 * Metodo ereditato dall'interfaccia Observer che viene richiamato quando si ha un cambiamento nell'Observable
	 */
	@Override
	public void update(Observable o, Object arg) {
		score();
	}
	
	/**
	 * Metodo che restituisce la larghezza del terreno di gioco
	 * @return larghezza del terreno di gioco
	 */
	public int getStageWidth() {
		return stageWidth;
	}

	/**
	 * Metodop che restituisce l'altezza del terreno di gioco
	 * @return altezza del terreno di gioco
	 */
	public int getStageHeight() {
		return stageHeight;
	}
}

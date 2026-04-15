package controller;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Personaggio;
import model.PowerUp;
import model.Bomba;
import model.Boss;
import model.Dato;
import model.Nemico;
import model.Partita;
import model.Timer;
import view.AudioManager;
import view.Gioco;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import javafx.scene.shape.Rectangle;

/**
 * La classe ControllerPartita è la classe che gestisce gli input dell'utente e gli elementi di gioco 
 */
public class ControllerPartita {
	
	/**
	 * personaggio
	 */
	private Personaggio character;
	/**
	 * istanza di gioco
	 */
	private Gioco partitaView = new Gioco();
	/**
	 * istanza di partita
	 */
	private Partita partitaModel;
	/**
	 * pane sfondo
	 */
	private Pane backGround;
	/**
	 * livello
	 */
	private int level;
	/**
	 * coordinate x e y del personaggio
	 */
	private double chX, chY;
	/**
	 * dati di gioco
	 */
	private Dato dati;
	/**
	 * elenco dei livelli
	 */
	private boolean[] elencoLiv = {false, false, false, false, false, false, false, false}; 
	/**
	 * lista di nemici
	 */
	private List<Nemico> nemici = new ArrayList<>();
	/**
	 * numero massimo di bombe
	 */
	private int bombe = 5;
	/**
	 * stato di gioco
	 */
	private boolean vittoria = true;
	/**
	 * contenitore immagini delle vite
	 */
	private ImageView lives = new ImageView();
	/**
	 * lista di livelli sbloccati
	 */
	private boolean[] visibile;
	/**
	 * identificativo schermata
	 */
	private int schermata;
	/**
	 * id slot
	 */
	private int id;
	/**
	 * array di AnimationTimer
	 */
	private List<AnimationTimer> animazioni = new ArrayList<>();
	/**
	 * lista di bombe piazzate
	 */
	private List<ImageView> listaBombe = new ArrayList<>();
	/**
	 * contatore di bombe piazzate
	 */
	private int placed = 0;
	/**
	 * contatore del punteggio
	 */
	private int punteggio = 0;
	/**
	 * contenitore immagine che segna i minuti
	 */
	private ImageView minutes = new ImageView();
	/**
	 * contenitore immagine che segna le decine di secondi
	 */
	private ImageView dSeconds = new ImageView();
	/**
	 * contenitore immagine che segna i secondi
	 */
	private ImageView seconds = new ImageView();
	/**
	 * timer di gioco
	 */
	private Timer timer = new Timer();
    
	
	/**
	 * Costruttore di ControllerPartita che crea un'istanza di ControllerPartita
	 * @param partitaView Istanza di gioco
	 * @param visibile lista che contiene le variabili che indicano lo stato dei livelli
	 */
    public ControllerPartita(Gioco partitaView, boolean[] visibile)
    {
    	this.partitaView = partitaView;
    	this.visibile = visibile;
    }
    
    /**
     * Costruttore di ControllerPartita che crea un'istanza di ControllerPartita per livelli normali
     * @param color colore avatar
     * @param stage finestra
     * @param level livello di gioco
     * @param boss variabile che indica se il livello contiene un boss
     * @param keyboard variabile che indica la modlaità di gioco
     * @param dati dati relativi all'utente
     * @param visibile lista di variabili che indicano lo stato dei livelli
     * @param nemici lista di nemici presenti nel livello
     */
    public ControllerPartita(int color, Stage stage, int level, boolean boss, boolean keyboard, Dato dati, boolean[] visibile, List<Nemico> nemici)
	{
    	schermata = 1;
    	this.level = level;
    	this.dati = dati;
    	this.nemici = nemici;
    	this.visibile = visibile;
    	vittoria = true;
		character = new Personaggio(color);
		partitaView = new Gioco(stage, level);
		backGround = partitaView.getBackGround();
		partitaModel = new Partita(character, backGround);
		partitaModel.setVisibile(visibile);
		partitaModel.addObserver(partitaView);
		Pane stageGround = partitaView.getStageGround();
		if (!boss)
		{
			partitaModel.setDBlocks(level, stageGround);
		}
		else
		{
			placeBotola();
		}
		partitaModel.setNDBlocks(level, stageGround);
		List<Nemico> onlyEnemy = nemici.stream()          
        .filter(n -> n instanceof Boss == false)  
        .collect(Collectors.toList());      
		partitaModel.setEnemies(onlyEnemy, stageGround);
		List<Nemico> onlyBoss = nemici.stream()          
		.filter(n -> n instanceof Boss)  
		.collect(Collectors.toList());  
		partitaModel.setBoss(onlyBoss, stageGround);
		for (Nemico n: nemici)
		{
		    if(n instanceof Boss)
		    {
			    Boss b = (Boss) n;
			    n.setVite(b.getMaxVite());
		    }
		    else
		    {
			    n.setVite(n.getMaxVite());
		    }
		}
		stageGround.getChildren().add(character.getCharacter());
		character.getCharacter().relocate((partitaView.getStageWidth() / 2 - character.getDimension() / 2) + 9,  partitaView.getStageHeight() / 2 - character.getDimension() / 2);
		changeVite();
		if(keyboard)
		{		
			setUpKeyListeners(); 
		}
		else 
		{
        	setUpMouseListener();
        	setUscitaBombe();
		}
		AnimationTimer animation = partitaModel.getAnimation(keyboard);
		AnimationTimer move = partitaModel.move(nemici);
		AnimationTimer timer = startTimer();
		AnimationTimer enemy = partitaModel.enemies(nemici);
		AnimationTimer counterVite = getLives();
		AnimationTimer fineLivello = fineLivello();
		fineLivello.start();
		animation.start();
		move.start();
		timer.start();
		enemy.start();
		counterVite.start();
		for (Nemico n: nemici)
		{
			if (n != null)
			{
				Duration walkDuration = null;
				if(n instanceof Boss == false) 
				{
					walkDuration = Duration.millis(100);
				}
				else 
				{
					walkDuration = Duration.millis(1000);
				}
				AnimationTimer animationEnemy = partitaModel.getAnimationEnemies(n, walkDuration);
				animationEnemy.start();
				animazioni.add(animationEnemy);
				n.setInGioco(true);
			}
		}
		animazioni.add(enemy);
		animazioni.add(animation);
		animazioni.add(move);
		animazioni.add(timer);
		animazioni.add(counterVite);
		animazioni.add(fineLivello);
		partitaModel.setAnimazioni(animazioni);
	}
    
    /**
     * Costruttore di ControllerPartita che crea un'istanza di ControllerPartita per livelli dell'editor
     * @param color colore dell'avatar
     * @param stage finestra
     * @param level livello
     * @param id Id selezione livello dall'editor
     * @param boss variabile che indica se sono presenti boss nel livello
     * @param keyboard variabile che indica la modalità di gioco
     * @param dati dati relativi all'utente
     * @param blocksX lista di coordinate x dei blocchi inseriti nell'editor
     * @param blocksY lista di coordinate y dei blocchi inseriti nell'editor
     * @param blocksType lista di interi che indicano il tipo di blocchi da inserire
     * @param enemyX lista di coordinate x dei nemici inseriti nell'editor
     * @param enemyY lista di coordinate y dei nemici inseriti nell'editor
     * @param enemyType lista di interi che indicano il tipo di nemici da inserire
     */
    public ControllerPartita(int color, Stage stage, int level, int id, boolean boss, boolean keyboard, Dato dati, List<Integer> blocksX, List<Integer> blocksY, List<Integer> blocksType, List<Integer> enemyX, List<Integer> enemyY, List<Integer> enemyType)
	{
    	schermata = 2;
    	this.id = id;
    	this.level = level;
    	this.dati = dati;
		if(!enemyType.isEmpty())
		{
			int y = 0;
			while (y < enemyType.size())
			{
				if(enemyType.get(y) < 5)
				{
					Nemico n = new Nemico(enemyType.get(y));
					ImageView imgEnemy = n.getEnemy();
					Image img = n.getFront();
					imgEnemy.setImage(img);
					n.relocate(enemyX.get(y) , enemyY.get(y));
					nemici.add(n);
				}
				else
				{
					Boss b = new Boss(enemyType.get(y));
					ImageView imgEnemy = b.getEnemy();
					Image img = b.getNum1();
					imgEnemy.setImage(img);
					b.relocate(enemyX.get(y) , enemyY.get(y));
					nemici.add(b);
				}
				y++;
			}
		}
    	vittoria = true; 
		character = new Personaggio(color);
		partitaView = new Gioco(stage, level);
		backGround = partitaView.getBackGround();
		partitaModel = new Partita(character, backGround);
		partitaModel.setVisibile(visibile);
		partitaModel.addObserver(partitaView);
		Pane stageGround = partitaView.getStageGround();
		placeBotola();
		partitaModel.setDBlocksEditor(blocksType, blocksX,  blocksY, stageGround);
		partitaModel.setNDBlocks(level, stageGround);
		partitaModel.setEnemiesEditor(nemici, enemyX, enemyY, stageGround);
		character.getCharacter().relocate((partitaView.getStageWidth() / 2 - character.getDimension() / 2) + 9,  partitaView.getStageHeight() / 2 - character.getDimension() / 2);
		changeVite();
		if(keyboard)
		{		
			setUpKeyListeners(); 
		}
		else 
		{
        	setUpMouseListener();
        	setUscitaBombe();
		}
		AnimationTimer animation = partitaModel.getAnimation(keyboard);
		AnimationTimer move = partitaModel.move(nemici);
		AnimationTimer timer = startTimer();
		AnimationTimer enemy = partitaModel.enemies(nemici);
		AnimationTimer counterVite = getLives();
		AnimationTimer fineLivello = fineLivello();
		fineLivello.start();
		animation.start();
		move.start();
		timer.start();
		enemy.start();
		counterVite.start();
		for (Nemico n: nemici)
		{
			if (n != null)
			{
				Duration walkDuration = null;
				if(n instanceof Boss == false) 
				{
					walkDuration = Duration.millis(100);
				}
				else 
				{
					walkDuration = Duration.millis(1000);
				}
				AnimationTimer animationEnemy = partitaModel.getAnimationEnemies(n, walkDuration);
				animationEnemy.start();
				animazioni.add(animationEnemy);
				n.setInGioco(true);
			}
		}
		animazioni.add(enemy);
		animazioni.add(animation);
		animazioni.add(move);
		animazioni.add(timer);
		animazioni.add(counterVite);
		animazioni.add(fineLivello);
		partitaModel.setAnimazioni(animazioni);
		stageGround.getChildren().add(character.getCharacter());
		character.getCharacter().relocate((partitaView.getStageWidth() / 2 - character.getDimension() / 2) + 9,  partitaView.getStageHeight() / 2 - character.getDimension() / 2);
	}

    /**
     * Metodo che associa un'azione ai comandi da tastiera
     */
	public void setUpKeyListeners() 
	{
		partitaView.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent e) 
		{
				switch(e.getCode()) 
				{
					case UP : case W : partitaModel.goNorth(); partitaModel.isWalking(); break;
					case LEFT : case A: partitaModel.goWest(); partitaModel.isWalking(); break;
					case DOWN : case S : partitaModel.goSouth(); partitaModel.isWalking(); break;
					case RIGHT : case D : partitaModel.goEast(); partitaModel.isWalking(); break;
					case B :	
					if(placed < partitaModel.getBombe())
					{
						if(!partitaModel.getComando())
						{
							Bomba b = new Bomba(1);
							bomba(character.getCharacter().getLayoutX(), character.getCharacter().getLayoutY(), b, partitaModel.getDBlocks(), character.getCharacter(), false);
							break;
						}
						else
						{
							Bomba b = new Bomba(2);
							bomba(character.getCharacter().getLayoutX(), character.getCharacter().getLayoutY(), b, partitaModel.getDBlocks(), character.getCharacter(), true);
							break;
						}
					} 
					break;
					case SPACE: 	
					if(partitaModel.getComando())
					{
						explodeAll(listaBombe, character.getCharacter());
					};
					break; 
					case ESCAPE: 
					{
						AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Pause_GameOver2.wav");
						if (schermata == 1)
						{
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Livelli.fxml"));
							Parent root = null;
							try {
								root = loader.load();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							Stage stage1 = (Stage) partitaView.getScene().getWindow();
							Scene scene = new Scene(root,680,605);
							stage1.setScene(scene);
							stage1.setResizable(false);
							stage1.show();
							ControllerLiv c = loader.getController();
							c.initialize(dati);
						}
						else 
						{
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
							Parent root = null;
							try {
								root = loader.load();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							Stage stage1 = (Stage) partitaView.getScene().getWindow();
							Scene scene = new Scene(root,680,605);
							stage1.setScene(scene);
							stage1.setResizable(false);
							stage1.show();
							ControllerCreaLiv c = loader.getController();
							c.setId(id);
							c.initialize(dati);
						}
						for (AnimationTimer a : animazioni)
						{
							a.stop();
						}
					}
					default : break;
				}
			}
		});	
		
		partitaView.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) 
			{
					switch(e.getCode()) 
					{
						case UP : case W : partitaModel.stopNorth(); partitaModel.setLastKey("w"); partitaModel.isNotWalking(); break;
						case LEFT : case A: partitaModel.stopWest(); partitaModel.setLastKey("a"); partitaModel.isNotWalking(); break;
						case DOWN : case S : partitaModel.stopSouth(); partitaModel.setLastKey("s"); partitaModel.isNotWalking(); break;
						case RIGHT : case D : partitaModel.stopEast(); partitaModel.setLastKey("d"); partitaModel.isNotWalking(); break;
						default : break;
					}
				}
			});
	}
	
	/**
     * Metodo che associa un'azione ai comandi da tastiera tasto ESCAPE e SPACE
	 */
	public void setUscitaBombe() 
	{
		partitaView.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent e) 
		{
				switch(e.getCode()) 
				{
					case SPACE : 
					if(partitaModel.getComando())
					{
						explodeAll(listaBombe, character.getCharacter());
					};
					break;
					case ESCAPE: 
					{
						AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Pause_GameOver2.wav");
						if (schermata == 1)
						{
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Livelli.fxml"));
							Parent root = null;
							try {
								root = loader.load();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							Stage stage1 = (Stage) partitaView.getScene().getWindow();
							Scene scene = new Scene(root,680,605);
							stage1.setScene(scene);
							stage1.setResizable(false);
							stage1.show();
							ControllerLiv c = loader.getController();
							c.initialize(dati);
						}
						else 
						{
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
							Parent root = null;
							try {
								root = loader.load();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							Stage stage1 = (Stage) partitaView.getScene().getWindow();
							Scene scene = new Scene(root,680,605);
							stage1.setScene(scene);
							stage1.setResizable(false);
							stage1.show();
							ControllerCreaLiv c = loader.getController();
							c.setId(id);
							c.initialize(dati);
						}
						for (AnimationTimer a : animazioni)
						{
							a.stop();
						}
					}
					default : break;
				}
			}
		});	
	}
	
	/**
	 * Metodo che associa un'azione ai comandi da mouse
	 */
	public void setUpMouseListener() 
	{
	    partitaView.getStageGround().setOnMousePressed(new EventHandler<MouseEvent>( ) {
	    	@Override
	    	public void handle(MouseEvent event) 
	    	{
	    		if (event.getButton() == MouseButton.PRIMARY)
	    		{
	    			double destX = event.getX();
		    		double destY = event.getY();
		    		chX = character.getCharacter().getLayoutX();
		    		chY = character.getCharacter().getLayoutY();
		    		partitaModel.isWalking();
		    		if(destX > chX - 10 && destX < chX + 40 + 10 && destY > 0 && destY < chY)
		    		{
		    			partitaModel.goNorth();
		    			partitaModel.setLastKeyM("nord");
		    		}
		    		else if (destX > chX - 10 && destX < chX + 40 + 10 && destY < 440 && destY > chY + 40)
		    		{
		    			partitaModel.goSouth();
		    			partitaModel.setLastKeyM("sud");
		    		}
		    		else if (destX > 0 && destX < chX && destY > chY - 10 && destY < chY + 40 + 10)
		    		{
		    			partitaModel.goWest();
		    			partitaModel.setLastKeyM("ovest");
		    		}
		    		else if (destX > chX + 40 && destX < 520 && destY > chY - 10 && destY < chY + 40 + 10)
		    		{
		    			partitaModel.goEast();
		    			partitaModel.setLastKeyM("est");
		    		}
	    		}
	    	}
	    });
    
	    partitaView.getScene().setOnMouseReleased(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) 
		    {
		    	partitaModel.isNotWalking();
		        partitaModel.stopNorth();
		        partitaModel.stopSouth();
		        partitaModel.stopEast();
		        partitaModel.stopWest();
		     }
	    });
	    
	    partitaView.getStageGround().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.SECONDARY)
				{
					if(placed <= bombe)
					{
						if(!partitaModel.getComando())
						{
							Bomba b = new Bomba(1);
							bomba(character.getCharacter().getLayoutX(), character.getCharacter().getLayoutY(), b, partitaModel.getDBlocks(), character.getCharacter(), false);
						}
						else
						{
							Bomba b = new Bomba(2);
							bomba(character.getCharacter().getLayoutX(), character.getCharacter().getLayoutY(), b, partitaModel.getDBlocks(), character.getCharacter(), true);
						}
					}
				}
			}
	    	
	    });
	}
	
	/**
	 * Metodo che gestisce la fine della partita
	 * @throws IOException eccezione
	 */
	public void fine() throws IOException
    {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FinePartita.fxml"));
		Parent root = loader.load();
		Stage stage = (Stage) partitaView.getScene().getWindow();
		Scene scene = new Scene(root,680,605);
		try
		{
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}
		catch (NullPointerException e){}
		ControllerEnd c = loader.getController();
		if (schermata == 1)
		{
			elencoLiv = dati.getLivSuperati();
			elencoLiv[level-1] = vittoria;
			if(level != 8)
			{
				if(visibile[level-1] == false)
				{
					visibile[level-1] = elencoLiv[level-1];
				}
			}
			dati.setLivSuperati(elencoLiv);
			c.setLevel(level);
			c.setVisibile(visibile);
		}
		else
		{
			c.setId(id);
			c.setVittoria(vittoria);
		}
		c.setNemici(nemici);
		c.setSchermata(schermata);
		c.setPunteggio(punteggio);
		c.initialize(dati);
		for (AnimationTimer a : animazioni)
		{
			a.stop();
		}
    }
	
	/**
	 * Metodo che cambia le vite del personaggio a schermo
	 */
	public void changeVite() 
	{
		switch(partitaModel.getVite())
		{
			case 0 : lives.setImage(new Image(getClass().getResourceAsStream("/images/sprites/timer/0.png"))); break;
			case 1 : lives.setImage(new Image(getClass().getResourceAsStream("/images/sprites/timer/1.png"))); break;
			case 2 : lives.setImage(new Image(getClass().getResourceAsStream("/images/sprites/timer/2.png"))); break;
			case 3 : lives.setImage(new Image(getClass().getResourceAsStream("/images/sprites/timer/3.png"))); break;
			case 4 : lives.setImage(new Image(getClass().getResourceAsStream("/images/sprites/timer/4.png"))); break;
			case 5 : lives.setImage(new Image(getClass().getResourceAsStream("/images/sprites/timer/5.png"))); break;
			case 6 : lives.setImage(new Image(getClass().getResourceAsStream("/images/sprites/timer/6.png"))); break;
			case 7 : lives.setImage(new Image(getClass().getResourceAsStream("/images/sprites/timer/7.png"))); break;
			case 8 : lives.setImage(new Image(getClass().getResourceAsStream("/images/sprites/timer/8.png"))); break;
			case 9 : default : lives.setImage(new Image(getClass().getResourceAsStream("/images/sprites/timer/9.png"))); break;
		}
		partitaView.getBackGround().getChildren().removeAll(lives);
		partitaView.getBackGround().getChildren().add(lives);
		lives.setLayoutY(22);
		lives.setLayoutX(61);
		lives.setPreserveRatio(true);
		lives.setFitHeight(40);
	}
	
	/**
	 * Metodo che restituiscce un AnimatioTimer che controlla lo stato delle vite del personaggio
	 * @return AnimationTimer che controlla lo stato delle vite del personaggio
	 */
	public AnimationTimer getLives() 
	{
		AnimationTimer liv = new AnimationTimer() {
			int counter = 0;
			@Override
			public void handle(long arg0) {
				changeVite();
				if (vittoria == false)
				{
					if (counter != 1)
					{
						counter++;
						azzeraSecondiRimanenti();
						partitaModel.setGameState();
					}
				}
			}
		};
		return liv;
	}
	
	/**
	 * Metodo che cambia le vite del personaggio in base ad una variabile i
	 * @param i numero di vite che va incrementato o decrementato
	 * @throws IOException eccezione
	 */
	public void decreaseVite(int i) throws IOException 
	{		
		if(partitaModel.getVite() > 0 && partitaModel.getVite() <= 9)
		{
			if(partitaModel.getVite() == 9 && i <= 0)
			{
				partitaModel.setVite(i);
			}
			else if (partitaModel.getVite() < 9)
			{
				partitaModel.setVite(i);
			}
		}
	}
	
	/**
	 * Metodo che restituisce un AnimationTimer che controlla le condizioni che portano alla fine della partita
	 * @return AnimationTimer che controlla le condizioni che portano alla fine della partita
	 */
	public AnimationTimer fineLivello() 
	{
			AnimationTimer fineLivello = new AnimationTimer() {
				@Override
				public void handle(long arg0) {
					if (partitaModel.getVite() == 0)
					{
						vittoria = false;
						for (AnimationTimer a : animazioni)
						{
							a.stop();
						}
						azzeraSecondiRimanenti();
						AnimationTimer death = partitaModel.getDeath();
						death.start();
						EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() 
						{
							@Override
							public void handle(ActionEvent event) 
							{
								try 
								{
									fine();
								} 
								catch (IOException e) {
									e.printStackTrace();
								}
							}	
						};
						KeyFrame keyframe = new KeyFrame(Duration.seconds(2), event, new KeyValue[0]);
						Timeline timeline = new Timeline(keyframe);
						timeline.play();
						AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Bomberman Dies2.wav");
					}
					else if (partitaModel.getVittoriaBotola() == true)
					{
						vittoria = true; 
						for (AnimationTimer a : animazioni)
						{
							a.stop();
						}
						azzeraSecondiRimanenti();
						EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() 
						{
							@Override
							public void handle(ActionEvent event) 
							{
								try 
								{
									fine();
								} 
								catch (IOException e) {
									e.printStackTrace();
								}
							}	
						};
						KeyFrame keyframe = new KeyFrame(Duration.seconds(2), event, new KeyValue[0]);
						Timeline timeline = new Timeline(keyframe);
						timeline.play();
					}
					if(partitaModel.getEsplodiTutte())
					{
						explodeAll(listaBombe, character.getCharacter());
						partitaModel.setEsplodiTutte(false);
					}
				}
			};
			return fineLivello;
	}		

	/**
	 * Metodo che controlla se avvengono collisioni con i nemici
	 * @param x coordinata x del personaggio
	 * @param y coordinata y del personaggio
	 */
	public void checkCollisionsEnemies(double x, double y) 
	{	
		int dim = 0;
        Rectangle hitbox = new Rectangle();
		for(Nemico n : nemici) 
		{
			double enX = 0;
		    double enY = 0;
			if(n instanceof Boss) 
			{
				dim = 140;	
				enX = n.getEnemy().getLayoutX() + 65;
		        enY = n.getEnemy().getLayoutY() + 65;
		        hitbox.setLayoutX(n.getEnemy().getLayoutX());
		        hitbox.setLayoutY(n.getEnemy().getLayoutY());
		        hitbox.setWidth(dim);
		        hitbox.setHeight(dim);
			}
			else 
			{
				dim = 40;
				enX = n.getEnemy().getLayoutX() + 20;
		        enY = n.getEnemy().getLayoutY() + 20;
			}
			enX = ((int)(enX / dim)) * dim;
			enY = ((int)(enY / dim)) * dim;
	        if(!(n instanceof Boss) && enX == x && enY == y && n.isInGioco())
	        {
	        	AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Combo2.wav");
	        	n.decreaseVite();
	        	if(n.getVite() == 0)
	        	{
	        		for(int i = 0; i < n.getMaxVite(); i++)
	        		{
	        			partitaModel.notifyObserver();
	        			punteggio += 200;
	        		}
	        		partitaView.getStageGround().getChildren().remove(n.getEnemy());
	        		if (n.isInGioco())
	        		{
	        			AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Enemy Dies2.wav");
	        		}
	        		n.setInGioco(false);
	        	}
	        }
	        if(n instanceof Boss && n.isInGioco() && x >= hitbox.getLayoutX() && x <= (hitbox.getLayoutX() + dim) && y >= hitbox.getLayoutY() && y <= (hitbox.getLayoutY() + dim))
        	{
	        	AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Combo2.wav");
	        	n.decreaseVite();
	        	if(n.getVite() == 0)
	        	{
	        		for(int i = 0; i < n.getMaxVite(); i++)
	        		{
	        			partitaModel.notifyObserver();
	        			punteggio += 200;
	        		}
	        		partitaView.getStageGround().getChildren().remove(n.getEnemy());
	        		if (n.isInGioco())
	        		{
	        			AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Enemy Dies2.wav");
	        		}
	        		n.setInGioco(false);
	        	}
        	}
	    }
	}
	
	/**
	 * Metodo che controlla i blocchi colpiti dalla bomba
	 * @param x coordinata x di un'area colpita dalla bomba
	 * @param y coordinata y di un'area colpita dalla bomba
	 * @param db lista di ImageView che rappresentano i blocchi distruttibili
	 * @param character personaggio
	 */
	public void checkHits(double x, double y, List<ImageView> db, ImageView character) 
	{
		double superiorY = y - 40;
		double inferiorY = y + 40;
		double leftX = x - 40;
		double rightX = x + 40;
		double chX = character.getLayoutX();
		double chY = character.getLayoutY();
		chX = ((int)(chX / 40)) * 40;
		chY = (((int)(chY / 40)) * 40) + 40;		
		for(ImageView block : db)
		{
			double blockX = block.getLayoutX();
			double blockY = block.getLayoutY();
			if((blockX == x && blockY == superiorY)||(blockX == x && blockY == inferiorY)||(blockX == leftX && blockY == y)||(blockX == rightX && blockY == y))
			{
				block.setVisible(false);
			}
		}
		AnimationTimer animation = new AnimationTimer()
		{
			ImageView up = null;
			ImageView down = null;
			ImageView center = new ImageView();
			ImageView sx = null;
			ImageView dx = null;
			List<Image> animationUp = new ArrayList<>();
			List<Image> animationDown = new ArrayList<>();
			List<Image> animationCenter = new ArrayList<>();
			List<Image> animationSx = new ArrayList<>();
			List<Image> animationDx = new ArrayList<>();
			Duration esplosionDuration = Duration.millis(100);
			long duration = (long)(esplosionDuration.toMillis() * 1000000.0);
			long lastUpdate= 0;
			int idxImg = 0;
			int counter = 0;
			public void getImages()
			{
				animationUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 3.png")));
				animationUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 4.png")));
				animationUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 6.png")));
				animationUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 4.png")));
				animationUp.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 3.png")));
				animationCenter.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 15.png")));
				animationCenter.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 14.png")));
				animationCenter.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 12.png")));
				animationCenter.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 14.png")));
				animationCenter.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 15.png")));
				animationDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 3b.png")));
				animationDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 4b.png")));
				animationDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 6b.png")));
				animationDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 4b.png")));
				animationDown.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 3b.png")));
				animationSx.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 3c.png")));
				animationSx.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 4c.png")));
				animationSx.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 6c.png")));
				animationSx.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 4c.png")));
				animationSx.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 3c.png")));
				animationDx.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 3d.png")));
				animationDx.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 4d.png")));
				animationDx.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 6d.png")));
				animationDx.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 4d.png")));
				animationDx.add(new Image(getClass().getResourceAsStream("/images/sprites/bombs/explosions/60462 copia 3d.png")));
				if(superiorY >= 0 && !(
						(superiorY == 40  && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) || 
						(superiorY == 120 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) || 
						(superiorY == 200 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) || 
						(superiorY == 280 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) || 
						(superiorY == 360 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440))))
				{
					up = new ImageView();
					partitaView.getStageGround().getChildren().add(up);
					up.relocate(x, superiorY);
					up.setFitWidth(40);
					up.setFitHeight(40);
					checkCollisionsEnemies(x, superiorY);
				}
				if(inferiorY < 440 && !(
						(inferiorY == 40  && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) || 
						(inferiorY == 120 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) || 
						(inferiorY == 200 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) || 
						(inferiorY == 280 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) || 
						(inferiorY == 360 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440))))
				{
					down = new ImageView();
					partitaView.getStageGround().getChildren().add(down);
					down.relocate(x, inferiorY);
					down.setFitWidth(40);
					down.setFitHeight(40);
					checkCollisionsEnemies(x, inferiorY);
				}
				if(leftX >= 0 && !(
						(y == 40  && (leftX == 40 || leftX == 120 || leftX == 200 || leftX == 280 || leftX == 360 || leftX == 440 || leftX == 480)) || 
						(y == 120 && (leftX == 40 || leftX == 120 || leftX == 200 || leftX == 280 || leftX == 360 || leftX == 440 || leftX == 480)) || 
						(y == 200 && (leftX == 40 || leftX == 120 || leftX == 200 || leftX == 280 || leftX == 360 || leftX == 440 || leftX == 480)) || 
						(y == 280 && (leftX == 40 || leftX == 120 || leftX == 200 || leftX == 280 || leftX == 360 || leftX == 440 || leftX == 480)) || 
						(y == 360 && (leftX == 40 || leftX == 120 || leftX == 200 || leftX == 280 || leftX == 360 || leftX == 440 || leftX == 480))))
				{
					sx = new ImageView();
					partitaView.getStageGround().getChildren().add(sx);
					sx.relocate(leftX, y);
					sx.setFitWidth(40);
					sx.setFitHeight(40);
					checkCollisionsEnemies(leftX, y);
				}
				if (rightX < 520 && !(
						(y == 40  && (rightX == 40 || rightX == 120 || rightX == 200 || rightX == 280 || rightX == 360 || rightX == 440 || rightX == 480)) || 
						(y == 120 && (rightX == 40 || rightX == 120 || rightX == 200 || rightX == 280 || rightX == 360 || rightX == 440 || rightX == 480)) || 
						(y == 200 && (rightX == 40 || rightX == 120 || rightX == 200 || rightX == 280 || rightX == 360 || rightX == 440 || rightX == 480)) || 
						(y == 280 && (rightX == 40 || rightX == 120 || rightX == 200 || rightX == 280 || rightX == 360 || rightX == 440 || rightX == 480)) || 
						(y == 360 && (rightX == 40 || rightX == 120 || rightX == 200 || rightX == 280 || rightX == 360 || rightX == 440 || rightX == 480))))
				{
					dx = new ImageView();
					partitaView.getStageGround().getChildren().add(dx);
					dx.relocate(rightX, y);
					dx.setFitWidth(40);
					dx.setFitHeight(40);
					checkCollisionsEnemies(rightX, y);
				}
				partitaView.getStageGround().getChildren().add(center);
				center.relocate(x, y);
				center.setFitWidth(40);
				center.setFitHeight(40);
				checkCollisionsEnemies(x, y);
			}
			@Override
			public void handle(long now) 
			{
				if(counter == 0)
				{
					getImages();
					counter++;
				}
				if(now - lastUpdate >= duration)
				{
					if (up != null) 
					{	
						up.setImage(animationUp.get(idxImg));
					}
					if (down != null) 
					{
						down.setImage(animationDown.get(idxImg));
					}
					if (sx != null)
					{
						sx.setImage(animationSx.get(idxImg));;
					}
					if (dx != null) 
					{
						dx.setImage(animationDx.get(idxImg));
					}
					center.setImage(animationCenter.get(idxImg));
					if(idxImg < 4)
					{
						idxImg++;
					}
					else 
					{
						stop();
						partitaView.getStageGround().getChildren().remove(center);
						partitaView.getStageGround().getChildren().remove(sx);
						partitaView.getStageGround().getChildren().remove(dx);
						partitaView.getStageGround().getChildren().remove(up);
						partitaView.getStageGround().getChildren().remove(down);
					}
					lastUpdate = now;
				}
			}
		};
		animation.start();
		if((chX == x && chY == superiorY)||(chX == x && chY == inferiorY)||(chX == leftX && chY == y)||(chX == rightX && chY == y)||(chX == x && chY == y))
		{
			try 
			{
				if(!partitaModel.getDistr())
				{
					decreaseVite(-1);
					partitaModel.removePowerUps();
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo che crea la bomba e la posiziona 
	 * @param x coordinata x della bomba
	 * @param y coordinata y della bomba
	 * @param b bomba
	 * @param db lista di blocchi distruttibili
	 * @param character personaggio
	 * @param command variabile che indica se le bombe sono a comando
	 */
	public void bomba(double x, double y, Bomba b, ArrayList<ImageView> db, ImageView character, boolean command) {
		int dimension = 40;
		ImageView bomb = b.getSkin();
		x = x + (55/2);
		y = y + (55/2) + 20;
		int newX = ((int)(x / dimension)) * dimension;
		int newY = ((int)(y / dimension)) * dimension;
		boolean canBePlaced = true;
		Iterator<ImageView> itI = listaBombe.iterator();
		while (itI.hasNext())
		{
	        ImageView bomb2 = itI.next();
			if(bomb2.getLayoutX() == newX && bomb2.getLayoutY() == newY)
			{
				canBePlaced = false;
			}
		}
		if (canBePlaced)
		{
			partitaView.getBombGround().getChildren().add(bomb);
			AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Place Bomb2.wav");
			bomb.setLayoutX(newX);
			bomb.setLayoutY(newY);
			bomb.setFitHeight(dimension);
			bomb.setFitWidth(dimension);
			AnimationTimer animationBomb = partitaModel.animationBomb(b);
			animationBomb.start();
			EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) 
				{
					placed--;
					removeBombaD(animationBomb);
					checkHits(newX, newY, db, character);
				}
			};
			if (!command)
			{
				KeyFrame keyframe = new KeyFrame(Duration.seconds(2.5), event, new KeyValue[0]);
				Timeline timeline = new Timeline(keyframe);
				timeline.play();
			}
			listaBombe.add(bomb);
			placed ++;
		}
	}
	
	/**
	 * Metodo che rimuove la bomba una volta esplosa
	 * @param b bomba
	 */
	public void removeBombaD(AnimationTimer b)
	{
		if (!listaBombe.isEmpty())
		{
			ImageView bomb = listaBombe.get(0);
			listaBombe.remove(0);
			partitaView.getBombGround().getChildren().remove(bomb);
			AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Bomb Explodes2.wav");
		}
		b.stop();
	}
	
	/**
	 * Metodo che fa esplodere tutte le bombe sul campo se sono a comando
	 * @param lbombe2 lista che contiene tutte le bombe a comando in gioco
	 * @param character personaggio
	 */
	public void explodeAll(List<ImageView> lbombe2, ImageView character) {
		if(!listaBombe.isEmpty())
		{
			for(ImageView bomba : listaBombe)
			{
				partitaView.getBombGround().getChildren().remove(bomba);
				checkHits(bomba.getLayoutX(), bomba.getLayoutY(), partitaModel.getDBlocks(), character);
				placed--;
				AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Bomb Explodes2.wav");
			}
			listaBombe.clear();
		}
	}
	
	/**
	 * Metodo che crea un AnimationTimer che avvia il timer all'inizio dalla partita
	 * @return AnimationTimer che avvia il timer all'inizio dalla partita
	 */
	public AnimationTimer startTimer()
	{
		partitaModel.resetTimer();
		AnimationTimer timer = new AnimationTimer() {
			private long lastUpdate = 0;
			@Override
			public void handle(long now) 
			{
				if(now - lastUpdate >= partitaModel.getIntervallo() * 1000000000)
				{
					try 
					{
						updateTimer();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					catch (NullPointerException a) 
					{
						a.printStackTrace();
					}
					lastUpdate = now;
				}
			}
		};
		return timer;
	}
	
	/**
	 * Metodo che aggiorna il timer ogni secondo
	 * @throws IOException eccezione
	 */
	public void updateTimer() throws IOException
	{
		partitaModel.setSecondiRimanenti(-1);
		if(partitaModel.getSecondiRimanenti() == 0)
		{
			try
			{
				vittoria = false; 
				fine();
			}
			catch(NullPointerException e)
			{
				e.printStackTrace();
			}
		}
		int iMinutes = partitaModel.getSecondiRimanenti() / 60;
        int seconds = partitaModel.getSecondiRimanenti() % 60;
        int secondsTens = seconds / 10;
        int secondsOnes = seconds % 10;
        if(backGround.getChildren().contains(minutes) || backGround.getChildren().contains(dSeconds) || backGround.getChildren().contains(this.seconds))
        {
        	backGround.getChildren().removeAll(minutes, dSeconds, this.seconds);
        }
        minutes.setImage(timer.getImage(iMinutes));
        backGround.getChildren().add(minutes);
        minutes.setLayoutX(460);
        minutes.setLayoutY(22);
        minutes.setFitHeight(40);
        minutes.setPreserveRatio(true);
        dSeconds.setImage(timer.getImage(secondsTens));
        backGround.getChildren().add(dSeconds);
        dSeconds.setLayoutX(505);
        dSeconds.setLayoutY(22);
        dSeconds.setFitHeight(40);
        dSeconds.setPreserveRatio(true);
        this.seconds.setImage(timer.getImage(secondsOnes));
        backGround.getChildren().add(this.seconds);
        this.seconds.setLayoutX(530);
        this.seconds.setLayoutY(22);
        this.seconds.setFitHeight(40);
        this.seconds.setPreserveRatio(true);
	}

	/**
	 * Metodo che azzera il timer
	 */
	public void azzeraSecondiRimanenti()
	{
		partitaModel.setSecondiRimanenti(2);
	}
	
	/**
	 * Metodo che piazza la botola in gioco
	 */
	public void placeBotola() {
		PowerUp p = new PowerUp(0);
		p.setX(240);
		p.setY(200);
		ImageView img = p.getIcona();
		img.setFitHeight(40);
		img.setFitWidth(40);
		img.relocate(240, 200);
		partitaView.getStageGround().getChildren().add(img);
		partitaModel.addBotola(p);
	}
}
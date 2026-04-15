package model;

import java.util.List;
import java.util.Observer;
import java.util.Random;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.AudioManager;

/**
 * La classe Partita è la classe che gestisce le meccaniche di gioco
 */
public class Partita implements Observable{
	
	/**
	 * larghezza terreno di gioco
	 */
	private int stageWidth = 520;
	/**
	 * altezza terreno di gioco
	 */
	private int stageHeight = 440;
	/**
	 * altezza bordi
	 */
	private int bordersHeight = 80;
	/**
	 * personaggio
	 */
	private Personaggio character;
	/**
	 * contatore delle vite
	 */
	private int vite = 5;
	/**
	 * blocco
	 */
	private Blocco blocco = new Blocco();
	/**
	 * nemico
	 */
	private Nemico nemico = new Nemico();
	/**
	 * boss
	 */
	private Boss boss = new Boss();
	/**
	 * pane
	 */
	private Pane backGround;
	/**
	 * ultima direzione
	 */
	private String lastKey = "s";
	/**
	 * observer (Gioco)
	 */
	private Observer observer;
	/**
	 * variabili direzionali
	 */
	private boolean north = false, south = false, east = false, west = false;
	/**
	 * variabile di movimento
	 */
	private boolean walking = false;
	/**
	 * array di blocchi distruttibili
	 */
	private List<Blocco> blocksD = new ArrayList<>();
	/**
	 * array di blocchi indistruttibili
	 */
	private List<Blocco> blocksND = new ArrayList<>();
	/**
	 * random
	 */
	private final Random random = new Random();
	/**
	 * durata del timer
	 */
	private final int timerDuration = 120;
	/**
	 * intervallo secondi del timer
	 */
	private final int intervallo = 1;
	/**
	 * inzio
	 */
	private long start;
	/**
	 * tempo rimanente
	 */
	private int secondiRimanenti;
	/**
	 * stato di gioco
	 */
	private boolean vittoria = true; 
	/**
	 * vittoria dalla botola
	 */
	private boolean vittoriaBotola = false; 
	/**
	 * visibilità livelli
	 */
	private boolean[] visibile;
	/**
	 * AnimationTimer delle varie animazioni
	 */
	private List<AnimationTimer> animazioni = new ArrayList<>();
	/**
	 * collisioni
	 */
	private List<Integer> collisione = new ArrayList<>();
	/**
	 * non contatti
	 */
	private List<Integer> nonContatto = new ArrayList<>();
	/**
	 * collisione
	 */
	private int c = 1;
	/**
	 * lista di powerup in gioco
	 */
	private List<PowerUp> powerup = new ArrayList<>();
	/**
	 * massimo numero di bombe piazzabili
	 */
	private int maxbombe = 5;
	/**
	 * modalità bombe
	 */
	private boolean comando = false;
	/**
	 * indistruttibilità
	 */
	private boolean indistruttibile = false;
	/**
	 * tipi di powerup
	 */
	private List<Integer> pTypes = new ArrayList<>();
	/**
	 * se bisogna far esplodere tutte le bombe
	 */
	private boolean esplodiTutte = false;
	
	/**
	 * Costruttore di Partita che crea una nuova istanza della classe partita
	 * @param character personaggio della partita
	 * @param backGround sfondo della partita
	 */
	public Partita (Personaggio character, Pane backGround) 
	{
		this.character = character;
		this.backGround = backGround;
		vittoria = true;
	}
	
	/**
	 * Metodo che restituisce l'oggetto AnimationTimer che gestisce le animazioni del personaggio
	 * @param keyboard indica se si sta usando la tastiera per muovere il personaggio
	 * @return oggetto AnimationTimer
	 */
	public AnimationTimer getAnimation(boolean keyboard) {
		AnimationTimer animation = new AnimationTimer()
		{
			Duration walkDuration = Duration.millis(100);
			long duration = (long)(walkDuration.toMillis() * 1000000.0);
			long lastUpdate= 0;
			int idxImg = 0;
			@Override
			public void handle(long now) 
			{
					if(walking)
					{
						if(now - lastUpdate >= duration)
						{
							if (east) character.getCharacter().setImage(character.getAnimationRight().get(idxImg));
							if (west) character.getCharacter().setImage(character.getAnimationLeft().get(idxImg));
							if (north) character.getCharacter().setImage(character.getAnimationUp().get(idxImg));
							if (south) character.getCharacter().setImage(character.getAnimationDown().get(idxImg));
							idxImg = (idxImg + 1) % 4;
							lastUpdate = now;
						}
					}
				}
			};
		return animation;
	}
	
	/**
	 * Metodo che restituisce l'oggetto AnimationTimer che gestisce le animazioni dei nemici
	 * @param n nemico da animare
	 * @param walkDuration durata dei frame dell'animazione
	 * @return oggetto AnimationTimer che gestisce l'animazione
	 */
	public AnimationTimer getAnimationEnemies (Nemico n, Duration walkDuration) {
		
			AnimationTimer animation = new AnimationTimer()
			{
				long duration = (long)(walkDuration.toMillis() * 1000000.0);
				long lastUpdate = 0;
				int idxImg = 0;
				@Override
				public void handle(long now) 
				{
					if(n.isWalkingEnemy())
					{
						if(now - lastUpdate >= duration)
						{
							if (n.isEastEnemy())
							{
								n.getEnemy().setImage(n.getAnimationRight().get(idxImg));
							}
							if (n.isWestEnemy())
							{
								n.getEnemy().setImage(n.getAnimationLeft().get(idxImg));
							}
							if (n.isNorthEnemy())
							{
								n.getEnemy().setImage(n.getAnimationUp().get(idxImg));
							}
							if (n.isSouthEnemy())
							{
								n.getEnemy().setImage(n.getAnimationDown().get(idxImg));
							}
							
							if(idxImg < 4)
							{
								idxImg++;
							}
							else
							{
								idxImg = 0;
							}
							lastUpdate = now;
						}
					}
				}
			};
			return animation;
	}
	
	/**
	 * Metodo che restituisce l'oggetto AnimationTimer che gestisce il movimento dei nemici
	 * @param nemici lista di nemici in gioco in un determinato livello di cui gestire il movimento
	 * @return oggetto AnimationTimer che gestisce il movimento dei nemici
	 */
	public AnimationTimer enemies(List<Nemico> nemici) {

		AnimationTimer move = new AnimationTimer() {
			@Override
			public void handle(long now) {
				for (Nemico n: nemici)
				{
					if(n != null)
					{
						List<Integer> movimento = null;
						Rectangle hitbox = null;
						double offsetX = 0;
						double offsetY = 0;
						double x = 0;
						double y = 0;
						if (n instanceof Boss == false)
						{
							double hitboxHeight = 20;
							double hitboxWidth = 25;
							if(n.getTipo() == 0)
							{
								hitboxHeight = 25;
								offsetX = ((n.getDimension() - hitboxWidth) / 2) - 8;
								offsetY = ((n.getDimension() - hitboxHeight) / 2 + 12);
								n.muovi(new Comportamento1());
								movimento = n.getMovimento();
							}
							else if(n.getTipo() == 1)
							{
								hitboxWidth = 30;
								offsetX = ((n.getDimension() - hitboxWidth) / 2) - 7;
								offsetY = ((n.getDimension() - hitboxHeight) / 2 + 2);
								n.muovi(new Comportamento2());
								movimento = n.getMovimento();
							}
							else if(n.getTipo() == 2)
							{
								hitboxWidth = 25; //26
								offsetX = ((n.getDimension() - hitboxWidth) / 2) - 1;
								offsetY = ((n.getDimension() - hitboxHeight) / 2 + 12);
								n.muovi(new Comportamento3());
								movimento = n.getMovimento();
							}
							else if(n.getTipo() == 3)
							{
								hitboxWidth = 35;
								offsetX = ((n.getDimension() - hitboxWidth) / 2) - 8;
								offsetY = ((n.getDimension() - hitboxHeight) / 2 + 2);
								n.muovi(new Comportamento4());
								movimento = n.getMovimento();
							}
							else if(n.getTipo() == 4)
							{
								hitboxHeight = 25;
								hitboxWidth = 30;
								offsetX = ((n.getDimension() - hitboxWidth) / 2) - 10;
								offsetY = ((n.getDimension() - hitboxHeight) / 2) + 13;
								n.muovi(new Comportamento5());
								movimento = n.getMovimento();
							}
							
							hitbox = new Rectangle(hitboxWidth, hitboxHeight);
							
							x = n.getEnemy().getLayoutX();
							y = n.getEnemy().getLayoutY();
							
							if (!movimento.isEmpty()) 
							{
								n.setWalkingEnemy(true);
							    int currentDirectionIndex = n.getCurrentDirectionIndex();
							    int currentDirection = movimento.get(currentDirectionIndex);
							    switch (currentDirection) {
							        case 1:
							            n.setY(-n.getSpeed());
							            n.setSouthEnemy(false);
							            n.setNorthEnemy(true);
							            n.setEastEnemy(false);
							            n.setWestEnemy(false);
							            break;
							        case 2:
							            n.setY(n.getSpeed()); 
							            n.setSouthEnemy(true);
							            n.setNorthEnemy(false);
							            n.setEastEnemy(false);
							            n.setWestEnemy(false);
							            break;
							        case 3:
							            n.setX(n.getSpeed());
							            n.setSouthEnemy(false);
							            n.setNorthEnemy(false);
							            n.setEastEnemy(true);
							            n.setWestEnemy(false);
							            break;
							        case 4:
							            n.setX(-n.getSpeed());
							            n.setSouthEnemy(false);
							            n.setNorthEnemy(false);
							            n.setEastEnemy(false);
							            n.setWestEnemy(true);
							            break;
							        default:
							            n.setY(0.0);
							            n.setX(0.0);
							            break;
							    }
							    if (currentDirectionIndex == movimento.size() - 1) 
							    {
							        n.setCurrentDirectionIndex(0);
							    } 
							    else 
							    {
							        n.setCurrentDirectionIndex(currentDirectionIndex + 1);
							    }
							}
							n.getEnemy().setLayoutX(n.getEnemy().getLayoutX() + n.getX()); 
							n.getEnemy().setLayoutY(n.getEnemy().getLayoutY() + n.getY()); 
							double enemyX = n.getEnemy().getLayoutX();
							double externalX = enemyX + n.getDimension();
							double enemyY = n.getEnemy().getLayoutY();
							double externalY = enemyY + n.getDimension();
							if (enemyX < 0)
							{
								n.getEnemy().setLayoutX(0);
							}
							else if (externalX > stageWidth + 15)
							{
								n.getEnemy().setLayoutX(stageWidth + 15 - n.getDimension());
							}
							if (enemyY < -15)
							{
								n.getEnemy().setLayoutY(-15);
							}
							else if (externalY > stageHeight)
							{
								n.getEnemy().setLayoutY(stageHeight - n.getDimension());
							}
							hitbox.setX(n.getEnemy().getLayoutX() + offsetX);
							hitbox.setY(n.getEnemy().getLayoutY() + offsetY);
						    Iterator<Blocco> itD = blocksD.iterator();
						    while (itD.hasNext()) {
						        Blocco block = itD.next();
						        if (hitbox.getBoundsInParent().intersects(block.getBlock().getBoundsInParent())&& block.getBlock().isVisible()) {
						        	n.getEnemy().setLayoutX(x);
						        	n.getEnemy().setLayoutY(y);
						            break;
						        }
						    }
						    for (Blocco block : blocksND) {
						        if (hitbox.getBoundsInParent().intersects(block.getBlock().getBoundsInParent())) {
						        	n.getEnemy().setLayoutX(x);
						        	n.getEnemy().setLayoutY(y);
						            break;
						        }
						    }
						}
						else 
						{
							Boss b = (Boss) n;
							if(b.getTipo() == 5)
							{
								b.muovi(new Comportamento6());
								movimento = b.getMovimento();
							}
							else if(b.getTipo() == 6)
							{
								b.muovi(new Comportamento7());
								movimento = b.getMovimento();
							}
							
							x = b.getEnemy().getLayoutX();
							y = b.getEnemy().getLayoutY();
							
							if (!movimento.isEmpty()) 
							{
								b.setWalkingEnemy(true);
							    int currentDirectionIndex = b.getCurrentDirectionIndex();
							    int currentDirection = movimento.get(currentDirectionIndex);
							    switch (currentDirection) {
							        case 1:
							            b.setY(-b.getSpeed()); 
							            b.setSouthEnemy(false);
							            b.setNorthEnemy(true);
							            b.setEastEnemy(false);
							            b.setWestEnemy(false);
							            break;
							        case 2:
							            b.setY(b.getSpeed()); 
							            b.setSouthEnemy(true);
							            b.setNorthEnemy(false);
							            b.setEastEnemy(false);
							            b.setWestEnemy(false);
							            break;
							        case 3:
							            b.setX(b.getSpeed()); 
							            b.setSouthEnemy(false);
							            b.setNorthEnemy(false);
							            b.setEastEnemy(true);
							            b.setWestEnemy(false);
							            break;
							        case 4:
							            b.setX(-b.getSpeed());
							            b.setSouthEnemy(false);
							            b.setNorthEnemy(false);
							            b.setEastEnemy(false);
							            b.setWestEnemy(true);
							            break;
							        default:
							            b.setY(0.0);
							            b.setX(0.0);
							            break;
							    }
							    if (currentDirectionIndex == movimento.size() - 1) 
							    {
							        b.setCurrentDirectionIndex(0);
							    } 
							    else 
							    {
							        b.setCurrentDirectionIndex(currentDirectionIndex + 1);
							    }
							}
							b.getEnemy().setLayoutX(b.getEnemy().getLayoutX() + b.getX()); 
							b.getEnemy().setLayoutY(b.getEnemy().getLayoutY() + b.getY()); 
							double enemyX = b.getEnemy().getLayoutX();
							double externalX = enemyX + b.getDimension();
							double enemyY = b.getEnemy().getLayoutY();
							double externalY = enemyY + b.getDimension();
							if (enemyX < 0)
							{
								b.getEnemy().setLayoutX(0);
							}
							else if (externalX > stageWidth + 15)
							{
								b.getEnemy().setLayoutX(stageWidth + 15 - b.getDimension());
							}
							if (enemyY < -15)
							{
								b.getEnemy().setLayoutY(-15);
							}
							else if (externalY > stageHeight)
							{
								b.getEnemy().setLayoutY(stageHeight - b.getDimension());
							}
						}
					}
				}
			}
		};
		return move;
	}
	
	/**
	 * Metodo che restituisce l'oggetto AnimationTimer per la gestione del movimento del personaggio, collisioni con blocchi e power up
	 * @param nemici lista dei nemici in gioco per controllare lo stato della partita e attivare la botola
	 * @return oggetto AnimationTimer che gestisce il movimento del personaggio
	 */
	public AnimationTimer move(List<Nemico> nemici) {
		AnimationTimer move = new AnimationTimer() {
			
			double hitboxHeight = 20;
			double hitboxWidth = 25;
			double offsetX = ((character.getDimension() - hitboxWidth) / 2) - 8;
			double offsetY = ((character.getDimension() - hitboxHeight) / 2) + 15; //15
			Rectangle hitbox = new Rectangle(hitboxWidth, hitboxHeight);
			@Override
			public void handle(long now) {
				
				double posX = character.getCharacter().getLayoutX();
				double posY = character.getCharacter().getLayoutY();
				if(north) 
				{
					character.setY(- character.getSpeed());
				}
				else if (south) 
				{
					character.setY(character.getSpeed());
				}
				else 
				{
					character.setY(0.0);
				}
				if(west) 
				{
					character.setX(- character.getSpeed());
				}
				else if (east) 
				{
					character.setX(character.getSpeed());
				}
				else 
				{
					character.setX(0.0);
				}
				
				character.getCharacter().setLayoutX(character.getCharacter().getLayoutX() + character.getX()); 
				character.getCharacter().setLayoutY(character.getCharacter().getLayoutY() + character.getY()); 
				double xCharacter = character.getCharacter().getLayoutX();
				double externalXPersonaggio = xCharacter + character.getDimension();
				double yPersonaggio = character.getCharacter().getLayoutY();
				double externalYPersonaggio = yPersonaggio + character.getDimension();
				if (xCharacter < 0)
				{
					character.getCharacter().setLayoutX(0);
				}
				else if (externalXPersonaggio > stageWidth + 15)
				{
					character.getCharacter().setLayoutX(stageWidth + 15 - character.getDimension());
				}
				if (yPersonaggio < -15)
				{
					character.getCharacter().setLayoutY(-15);
				}
				else if (externalYPersonaggio > stageHeight)
				{
					character.getCharacter().setLayoutY(stageHeight - character.getDimension());
				}
				hitbox.setX(character.getCharacter().getLayoutX() + offsetX);
				hitbox.setY(character.getCharacter().getLayoutY() + offsetY);
			    Iterator<Blocco> itD = blocksD.iterator();
			    while (itD.hasNext()) {
			        Blocco block = itD.next();
			        if (hitbox.getBoundsInParent().intersects(block.getBlock().getBoundsInParent()) && block.getBlock().isVisible()) {
			        	character.getCharacter().setLayoutX(posX);
			            character.getCharacter().setLayoutY(posY);
			            break;
			        }
			    }
			    for (Blocco block : blocksND) {
			        if (hitbox.getBoundsInParent().intersects(block.getBlock().getBoundsInParent())) {
			            character.getCharacter().setLayoutX(posX);
			            character.getCharacter().setLayoutY(posY);
			            break;
			        }
			    }
			    Iterator<PowerUp> p = powerup.iterator();
			    while (p.hasNext()) {
			        PowerUp q = p.next();
			        Rectangle r = new Rectangle();
			        r.setWidth(30);
			        r.setHeight(30);
			        r.setLayoutX(q.getX() + 5);
			        r.setLayoutY(q.getY() + 5);
			        if (hitbox.getBoundsInParent().intersects(r.getBoundsInParent()) && q.getIcona().isVisible()) 
			        {
			        	int v = q.getId();
			        	pTypes.add(v);
			        	switch(v)
			        	{
			        	case 0 : List<Nemico> remainingEnemy = nemici.stream().filter(n -> n.isInGioco() == true).collect(Collectors.toList()); 
			        			 if(remainingEnemy.isEmpty())
			        			 {
			        				 vittoriaBotola = true;
			        				 q.getIcona().setVisible(false);
			        			 } break;
			        	case 1 : notifyObserver(); 
			        			 q.getIcona().setVisible(false); 
			        			 AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Item Get2.wav"); 
			        			 break;
			        	case 2 : maxbombe++; 
			        			 q.getIcona().setVisible(false); 
			        			 AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Item Get2.wav"); 
			        			 break;
			        	case 3 : vite++; 
			        			 q.getIcona().setVisible(false); 
			        			 AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Item Get2.wav"); 
			        			 break;
			        	case 4 : indistruttibile = true;
				        		 EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() 
								 {
									@Override
									public void handle(ActionEvent event) 
									{
										indistruttibile = false;
										AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Item Get2.wav");
										
									}	
								 };
								 KeyFrame keyframe = new KeyFrame(Duration.seconds(10), event, new KeyValue[0]);
								 Timeline timeline = new Timeline(keyframe);
								 timeline.play();
								 q.getIcona().setVisible(false);
				        		 AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Item Get2.wav"); 
				        		 break;
			        	case 5 : comando = true; 
			        			 q.getIcona().setVisible(false); 
			        			 AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Item Get2.wav"); 
			        			 break;
			        	case 6 : switch(random.ints(0, 3 ).limit(1).findFirst().getAsInt())
				        		 {
						        	case 0 : if(vite > 0 && vite <= 9)
						        		{
						        			if(vite <= 9 && vite != 0)
						        			{
						        				vite--;
						        			}
						        		}; break;
						        	case 1 : character.decreaseSpeed(1); break;
						        	case 2 : maxbombe --; break;
				        		 }
			        			 q.getIcona().setVisible(false); 
			        			 AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Skull Item2.wav"); 
			        			 break;
			        	case 7 : resetTimer(); 
			        	         q.getIcona().setVisible(false); 
			        	         AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Item Get2.wav"); 
			        	         break;
			        	case 8 : character.increaseSpeed(0.3); 
			        			 q.getIcona().setVisible(false); 
			        			 AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Item Get2.wav"); 
			        			 break;
			        	}
			        }
			    }
			    for (Nemico n : nemici)
			    {
			    	collisione.add(0);
			    	nonContatto.add(1);
			    }
			    for (int i = 0; i < nemici.size(); i++) 
			    {
			        if (hitbox.getBoundsInParent().intersects(nemici.get(i).getEnemy().getBoundsInParent()) && nemici.get(i).isInGioco()) 
			        {
			        	collisione.set(i, 1);
			        }
			        else
			        {
			        	nonContatto.set(i, 1);
			        }
			    }
			    for (int i = 0; i < nemici.size(); i++) 
			    {
			    	if (collisione.get(i) == 1 && nonContatto.get(i) == 1 && !indistruttibile)
			    	{
			    		collisione.set(i, 0);
			    		nonContatto.set(i, 0);
			    		c++;
			    		if(c%2 == 0)
			    		{
			    			if(vite > 0 && vite <= 9)
				    		{
				    			if (vite <= 9)
				    			{
				    				vite --;
				    				removePowerUps();
				    			}
				    		}
			    		}
			    	}
			    }
			}
		};
		return move;
	}
	
	/**
	 * Metodo che esegue il reset del timer impostando l'inizio del timer al tempo di sistema corrente in nanosecondi e reimposta i secondi rimanenti alla durata iniziale del timer
	 */
	public void resetTimer()
	{
		start = System.nanoTime();
		secondiRimanenti = timerDuration;
	}

	/**
	 * Metodo che imposta la posizione dei nemici nel terreno di gioco
	 * @param nemici nemici da posizionare
	 * @param stageGround terreno di gioco
	 */
	public void setEnemies(List<Nemico> nemici, Pane stageGround)
	{
		int[] listaX = {0, 40, 80, 120, 160, 200, 240, 280, 320, 360, 400, 440, 480};
		int[] listaY = {0, 40, 80, 120, 160, 200, 240, 280, 320, 360, 400};
		int i = 0;
		int numeroNemici = 0;
		for (Nemico n : nemici)
		{
			if(n != null)
			{
				numeroNemici++;
			}
		}
		boolean posizionabile = false;
		while (i < numeroNemici)
		{
			int randomIndex = random.nextInt(listaX.length);
	        int x = listaX[randomIndex];
	        int randomIndex2 = random.nextInt(listaY.length);
	        int y = listaY[randomIndex2];
	        posizionabile = true;
	        for (Blocco b: blocksD)
	        {
	        	if(b.getBlock().getLayoutX() == x && b.getBlock().getLayoutY() == y)
	        	{
	        		posizionabile = false;
	        	}
	        }
	        for (Blocco b: blocksND)
	        {
	        	if(b.getBlock().getLayoutX() == x && b.getBlock().getLayoutY() == y)
	        	{
	        		posizionabile = false;
	        	}
	        }
	        if(x == 240 && y == 200)
	        {
	        	posizionabile = false;
	        }
	        if(posizionabile)
	        {
	        	ImageView imgEnemy = nemici.get(i).getEnemy();
				imgEnemy.setFitHeight(nemico.getDimension());
				imgEnemy.setFitWidth(nemico.getDimension());
				imgEnemy.relocate(x, y - 15);
				if (!stageGround.getChildren().contains(imgEnemy)) {
					if(nemici.get(i).getTipo() == 1 || nemici.get(i).getTipo() == 3)
					{
						imgEnemy.setFitWidth(40);
						imgEnemy.setFitHeight(40);
					}
					stageGround.getChildren().add(imgEnemy);
					i++;
                }
	        }
		}
	}
	
	/**
	 * Metodo che imposta la posizione del boss all'interno del terreno di gioco
	 * @param nemici lista di nemici che contiene il boss da posizionare
	 * @param stageGround terreno di gioco in cui posizionare  il boss
	 */
	public void setBoss(List<Nemico> nemici, Pane stageGround)
	{
		int[] listaX = {0, 40, 80, 120, 160, 200, 240, 280, 320, 360, 400, 440, 480};
		int[] listaY = {0, 40, 80, 120, 160, 200, 240, 280, 320, 360, 400};
		int i = 0;
		int numeroNemici = 0;
		for (Nemico n : nemici)
		{
			if(n != null)
			{
				numeroNemici++;
			}
		}
		boolean posizionabile = false;
		while (i < numeroNemici)
		{
			int randomIndex = random.nextInt(listaX.length);
	        int x = listaX[randomIndex];
	        int randomIndex2 = random.nextInt(listaY.length);
	        int y = listaY[randomIndex2];
	        posizionabile = true;
	        if(x == 240 && y == 200)
	        {
	        	posizionabile = false;
	        }
	        if(posizionabile)
	        {
	        	ImageView imgEnemy = nemici.get(i).getEnemy();
				imgEnemy.setFitHeight(boss.getDimension());
				imgEnemy.setFitWidth(boss.getDimension());
				imgEnemy.relocate(x, y - 15);
				if (!stageGround.getChildren().contains(imgEnemy)) {
					stageGround.getChildren().add(imgEnemy);
					i++;
                }
	        }
		}
	}
	
	/**
	 * Metodo che imposta la posizione dei nemici sul terreno di gioco utilizzando delle coordinate personalizzate
	 * @param nemici lista di nemici da posizionare
	 * @param enemyX lista delle coordinate X dei nemici
	 * @param enemyY lista delle coordinate Y dei neici
	 * @param stageGround terreno di gioco
	 */
	public void setEnemiesEditor (List<Nemico> nemici, List<Integer> enemyX, List<Integer> enemyY, Pane stageGround)
	{
		int i = 0;
		while (i < nemici.size())
		{
			ImageView imgEnemy = null;
			if (nemici.get(i) instanceof Boss == false)
			{
				imgEnemy = nemici.get(i).getEnemy();
				imgEnemy.setFitHeight(nemico.getDimension());
				imgEnemy.setFitWidth(nemico.getDimension());
				if(nemici.get(i).getTipo() == 1 || nemici.get(i).getTipo() == 3)
				{
					imgEnemy.setFitWidth(40);
					imgEnemy.setFitHeight(40);
				}
			}
			else
			{
				imgEnemy = nemici.get(i).getEnemy();
				imgEnemy.setFitHeight(boss.getDimension());
				imgEnemy.setFitWidth(boss.getDimension());
			}
			imgEnemy.relocate(enemyX.get(i), enemyY.get(i) - 15);
			stageGround.getChildren().add(nemici.get(i).getEnemy()); 
			i++;
		}
	}
	
	/**
	 * Metodo che posiziona i blocchi distruttibli sul terreno di gioco in modo randomico
	 * @param level livello per cui impostare i blocchi
	 * @param stageGround terreno di gioco su cui posizionare i blocchi
	 */
	public void setDBlocks(int level, Pane stageGround) 
	{
		try
		{
			int counter = 0;
			for (int x = 0; x < stageWidth / blocco.getBlockdimension(); x++)
			{
				int bx = x * 40;
				
				for (int y = 0; y < stageHeight / blocco.getBlockdimension(); y++)
				{
					int by = y * 40;
					
					if(!(bx == 240 && (by == 160 || by == 200 || by == 240)) && 
				       !(by == 40 && (bx == 40 || bx == 120 || bx == 200 || bx == 280 || bx == 360 || bx == 440)) && 
				       !(by == 120 && (bx == 40 || bx == 120 || bx == 200 || bx == 280 || bx == 360 || bx == 440)) && 
				       !(by == 200 && (bx == 40 || bx == 120 || bx == 200 || bx == 280 || bx == 360 || bx == 440)) && 
				       !(by == 280 && (bx == 40 || bx == 120 || bx == 200 || bx == 280 || bx == 360 || bx == 440)) && 
				       !(by == 360 && (bx == 40 || bx == 120 || bx == 200 || bx == 280 || bx == 360 || bx == 440)))
					{
						if (random.nextDouble() <  0.3 &&
					      !((x == stageWidth / (2 * blocco.getBlockdimension()) && y == stageHeight / (2 * blocco.getBlockdimension())) || 
					        (x == stageWidth / (2 * blocco.getBlockdimension()) && y == stageHeight / (2 * blocco.getBlockdimension()) - 1) || 
					        (x == stageWidth / (2 * blocco.getBlockdimension()) && y == stageHeight / (2 * blocco.getBlockdimension()) + 1)))
						{
							Blocco block = new Blocco(level, true);
							ImageView imgBlocco = block.getBlock();
							Image img = block.getImage();
							imgBlocco.setImage(img);
							imgBlocco.setFitHeight(block.getBlockdimension());
							imgBlocco.setFitWidth(block.getBlockdimension());
							block.relocate(bx, by);
							if(random.nextDouble() <  0.3)
							{
								if(counter == 2)
								{
									PowerUp p = new PowerUp(0);
									p.setX(bx);
									p.setY(by);
									ImageView skin = p.getIcona();
									skin.setFitHeight(40);
									skin.setFitWidth(40);
									skin.relocate(bx, by);
									powerup.add(p);
									stageGround.getChildren().add(skin); 
								}
								else
								{
									int b = random.ints(1, 9).limit(1).findFirst().getAsInt();
									PowerUp p = new PowerUp(b);
									p.setX(bx);
									p.setY(by);
									ImageView skin = p.getIcona();
									skin.setFitHeight(40);
									skin.setFitWidth(40);
									skin.relocate(bx, by);
									powerup.add(p);
									stageGround.getChildren().add(skin); 
								}
								counter ++;
							}
							blocksD.add(block);
							stageGround.getChildren().add(block.getBlock()); 
						}
					}
				}
			}
		}
		catch(IndexOutOfBoundsException e) {}
	}
	
	/**
	 * Metodo che posiziona i blocchi distruttibili impostati nell'editor sul terreno di gioco 
	 * @param blocksType tipo di blocco da inserire
	 * @param blocksX lista con le coordinate X dei blocchi
	 * @param blocksY lista con le coordinate Y dei blocchi
	 * @param stageGround terreno di gioco su cui posizionare i blocchi
	 */
	public void setDBlocksEditor (List<Integer> blocksType, List<Integer> blocksX, List<Integer> blocksY, Pane stageGround)
	{
	    try
	    {
	    	int dim = 40;
	    	if(blocksType.isEmpty() == false)
	    	{
	    		int i = 0;
	    		while (i < blocksType.size())
		        {
			    	Blocco blocco = new Blocco(blocksType.get(i), true);
			    	ImageView imgBlocco = blocco.getBlock();
			    	Image img = blocco.getImage();
			        imgBlocco.setImage(img);
			        imgBlocco.setFitHeight(dim);
			        imgBlocco.setFitWidth(dim);
			        blocco.relocate(blocksX.get(i) , blocksY.get(i));
			        blocksD.add(blocco);
			        stageGround.getChildren().add(blocco.getBlock()); 
			        i++;
			        if(random.nextDouble() <  0.2)
			        {
			            if(blocksX.get(i) != null && blocksY.get(i) != null)
			            {
			            	int b = random.ints(1, 9).limit(1).findFirst().getAsInt();
			            	PowerUp p = new PowerUp(b);
			            	p.setX(blocksX.get(i));
			            	p.setY(blocksY.get(i));
			            	ImageView skin = p.getIcona();
			            	skin.setFitHeight(40);
			            	skin.setFitWidth(40);
			            	skin.relocate(blocksX.get(i), blocksY.get(i));
			            	powerup.add(p);
			            	stageGround.getChildren().add(skin); 
			            }
			        }
		        }
	    	}
	   }
	   catch(IndexOutOfBoundsException e) {}
	}
	
	/**
	 * Metodo che resitituisce un ArrayList che contiene le ImageView dei blocchi
	 * @return ArrayList con i blocchi distruttibili in gioco
	 */
	public ArrayList getDBlocks()
	{
		ArrayList blocks = new ArrayList();
		
		for(Blocco blocco : blocksD)
		{
			blocks.add(blocco.getBlock());
		}
		
		return blocks;
	}
	
	/**
	 * Metodo che posiziona i blocchi indistruttibili in determinate poszioni del terreno di gioco
	 * @param level livello che indica il tipo di blocchi da posizionare
	 * @param stageGround terreno di gioco su cui posizionare i blocchi
	 */
	public void setNDBlocks(int level, Pane stageGround)
	{
		for (int y = blocco.getBlockdimension(); y < stageHeight ; y += bordersHeight)
		{
			for (int x = blocco.getBlockdimension(); x < stageWidth; x += bordersHeight)
			{
				Blocco block = new Blocco(level, false);
				ImageView imgBlocco = block.getBlock();
				Image img = block.getImage();
				imgBlocco.setImage(img);
				imgBlocco.setFitHeight(block.getBlockdimension());
				imgBlocco.setFitWidth(block.getBlockdimension());
				block.relocate(x, y);
				blocksND.add(block);
				stageGround.getChildren().add(block.getBlock());
			}
		}
	}
	
	/**
	 * Metodo che imposta lo stato di gioco a vittoria
	 */
	public void setGameState()
	{
		vittoria = true; 
	}
	
	/**
	 * Metodo che crea l'AnimationTimer che gestisce l'animazione di morte del personaggio
	 * @return oggetto AnimationTimer che gestisce la morte del personaggio
	 */
	public AnimationTimer getDeath()
	{
		AnimationTimer death = new AnimationTimer()
		{
			Duration deathDuration = Duration.millis(200);
			long duration = (long)(deathDuration.toMillis() * 1000000.0);
			long lastUpdate = 0;
			List<Image> deatha = character.getAnimationDeath();
			int idx = 0;
			@Override
			public void handle(long now) 
			{
				if(now - lastUpdate >= duration)
				{
					if(idx < 4)
					{
						character.getCharacter().setImage(deatha.get(idx));
						idx++;
					}
					lastUpdate = now;
				}
			}
		};
		return death;
	}
	
	/**
	 * Metodo che imposta una variabile che ricorda l'ultima direzione di movimento
	 * @param direction ultima direzione presa dal personaggio
	 */
	public void setLastKeyM(String direction)
	{
		switch(direction)
	    {
	      case "sud" : default : lastKey = "s"; break;
	      case "nord" : lastKey = "w"; break;
	      case "est" : lastKey = "d"; break;
	      case "ovest" : lastKey = "a"; break;
	    }
	}

	/**
	 * Metodo che restituisce lo stato del gioco
	 * @return variabile vittoria che indica se si è vinta la partita
	 */
	public boolean getGameState() 
	{
		return vittoria;
	}
	
	/**
	 * Metodo che restituisce l'oggetto AnimationTimer che gestisce l'animazione delle bombe
	 * @param bomb bomba per la quale creare l'AnimationTimer
	 * @return oggetto AnimationTimer che gestisce l'animazione delle bombe
	 */
	public AnimationTimer animationBomb(Bomba bomb) {
		AnimationTimer animationBomb = new AnimationTimer() {

			Duration aDuration = Duration.millis(300);
			long duration = (long)(aDuration.toMillis() * 1000000.0);
			long lastUpdate= 0;
			int idxImg = 0;
			@Override
			public void handle(long now) {
				if(now - lastUpdate >= duration)
				{
					bomb.getSkin().setImage(bomb.getAnimation().get(idxImg));
					if (idxImg == 2)
					{
						idxImg = 0;
					}
					else
					{
						idxImg ++;
					}
					lastUpdate = now;
				}
			}
		};
		return animationBomb;
	}
	
	/**
	 * Metodo che rimuove i power up ogni volta che il personaggio prende danno
	 */
	public void removePowerUps()
	{
		if(!pTypes.isEmpty())
		{
			for(int i : pTypes)
			{
				switch(i)
				{
					case 0 : case 1 : case 3 : case 4 : case 6 : case 7 : break;
					case 2 : maxbombe-- ; 
							 break;
					case 5 : comando = false;
							 esplodiTutte = true;
							 break;
					case 8 : character.decreaseSpeed(0.3);
							 break;
				}
			}
			pTypes.clear();
		}
	}

	/**
	 * Metodo ereditato dall'interfaccia Observable per aggiungere un listener
	 */
	@Override
	public void addListener(InvalidationListener o) {
	
	}
	
	/**
	 * Metodo per aggiungere un Observer
	 * @param o Observer da aggiungere
	 */
	public void addObserver(Observer o) {
		observer = o;	
	}

	/**
	 * Metodo ereditato dall'interfaccia Observable per rimuovere un listener
	 */
	@Override
	public void removeListener(InvalidationListener o) {
		
	}

	/**
	 * Metodo che avvisa l'Observer di un cambiamento
	 */
	public void notifyObserver()
	{
		observer.update(null, null);
	}
	
	/**
	 * Metodo che restituisce una variabile che indica se il personaggio sta camminando
	 * @return variabile che indica se il personaggio è in movimento
	 */
	public boolean walks()
	{
		return walking;
	}
	
	/**
	 * Metodo che imposta la variabile che indica se il personaggio sta cammiando a true
	 */
	public void isWalking()
	{
		walking = true;
	}
	
	/**
	 * Metodo che imposta la variabile che indica se il personaggio sta cammiando a false
	 */
	public void isNotWalking()
	{
		walking = false;
	}
	
	/**
	 * Metodo che imposta la variabile che indica se il personaggio sta cammiando verso nord a true
	 */
	public void goNorth() 
	{
		north = true;
	}
	
	/**
	 * Metodo che imposta la variabile che indica se il personaggio sta cammiando verso sud a true
	 */
	public void goSouth()
	{
		south = true;
	}
	
	/**
	 * Metodo che imposta la variabile che indica se il personaggio sta cammiando verso est a true
	 */
	public void goEast()
	{
		east = true;
	}
	
	/**
	 * Metodo che imposta la variabile che indica se il personaggio sta cammiando verso ovest a true
	 */
	public void goWest()
	{
		west = true;
	}
	
	/**
	 * Metodo che imposta la variabile che indica se il personaggio sta cammiando verso nord a false
	 */
	public void stopNorth() 
	{
		north = false;
	}
	
	/**
	 * Metodo che imposta la variabile che indica se il personaggio sta cammiando verso sud a false
	 */
	public void stopSouth()
	{
		south = false;
	}
	
	/**
	 * Metodo che imposta la variabile che indica se il personaggio sta cammiando verso est a false
	 */
	public void stopEast()
	{
		east = false;
	}
	
	/**
	 * Metodo che imposta la variabile che indica se il personaggio sta cammiando verso ovest a false
	 */
	public void stopWest()
	{
		west = false;
	}
	
	/**
	 * Metodo che imposta la variabile che tiene memoria dell'ultima direzione presa
	 * @param s ultima direzione
	 */
	public void setLastKey(String s)
	{
		lastKey = s;
	}

	/**
	 * Metodo che restituisce la lista di variabili associate ai livelli visibili(sbloccati) e non
	 * @return lista di variabili booleane che indicano se i livelli sono visibili o no
	 */
	public boolean[] getVisibile() 
	{
		return visibile;
	}

	/**
	 * Metodo che imposta la lista di variabili che indicano se i livelli sono visibili(sbloccati) o no
	 * @param visibile lista di variabili booleane che indicano se i livelli sono visibili o no
	 */
	public void setVisibile(boolean[] visibile) 
	{
		this.visibile = visibile;
	}

	/**
	 * Metodo che restituisce la lista di oggetti AnimationTimer creati che gestiscono le animazioni
	 * @return lista di oggetti AnimationTimer che gestiscono le animazioni
	 */
	public List<AnimationTimer> getAnimazioni() 
	{
		return animazioni;
	}

	/**
	 * Metodo che imposta la lista di oggetti AnimationTimer che gestiscono le animazioni
	 * @param animazioni lista di oggetti AnimationTimer che gestiscono le animazioni
	 */
	public void setAnimazioni(List<AnimationTimer> animazioni) 
	{
		this.animazioni = animazioni;
	}
	
	/**
	 * Metodo che restituisce il numero massimo di bombe piazzabili sul terreno di gioco
	 * @return numero massimo di bombe posizionabili
	 */
	public int getBombe() 
	{
		return maxbombe;
	}

	/**
	 * Metodo che restituisce il numero di vite rimaste al personaggio
	 * @return numero di vite del personaggio
	 */
	public int getVite() 
	{
		return vite;
	}

	/**
	 * Metodo che imposta il numero di vite rimaste al personaggio
	 * @param vite numero di vite rimaste al personaggio
	 */
	public void setVite(int vite) 
	{
		this.vite += vite;
	}
	
	/**
	 * Metodo che restituisce la variabile che indica se sono attive le bombe con esplosione a comando
	 * @return variabile che indica se sono attive le bombe a comando
	 */
	public boolean getComando()
	{
		return comando;
	}

	/**
	 * Metodo che imposta la variabile che indica quanti secondi rimangono alla fine della partita
	 * @param i il numero di secondi rimanenti allo scadere del timer
	 */
	public void setSecondiRimanenti(int i) 
	{
		secondiRimanenti+= i;
	}

	/**
	 * Metodo che restituisce i secondi che rimangono allo scadere del timer
	 * @return secondi che rimangono allo scadere del timer
	 */
	public int getSecondiRimanenti() 
	{
		return secondiRimanenti;
	}

	/**
	 * Metodo che restituisce l'intervallo di aggiornamento del timer
	 * @return durata dell'intervallo di aggiornamento del timer
	 */
	public int getIntervallo() {
		return intervallo;
	}
	
	/**
	 * Metodo che restituisce una variabile che indica se il personaggio ha il power up dell'indistruttibilità
	 * @return variabile che indica se il personaggio è indistruttibile
	 */
	public boolean getDistr()
	{
		return indistruttibile;
	}
	
	/**
	 * Metodo che restituisce una variabile che indica se si è vinta la partita e si può attivare la botola
	 * @return variabile che indica lo stato del gioco
	 */
	public boolean getVittoriaBotola()
	{
		return vittoriaBotola;
	}
	
	/**
	 * Metodo che restituisce una variabile che indica se vanno fatte esplodere tutte le bombe in gioco
	 * @return variabile che indica se vanno fatte esplodere tutte le bombe
	 */
	public boolean getEsplodiTutte()
	{
		return esplodiTutte;
	}
	
	/**
	 * Metodo che imposta la variabile che indica se vanno fatte esplodere tutte le bombe in gioco
	 * @param b variabile che indica se vanno fatte esplodere tutte le bombe
	 */
	public void setEsplodiTutte(boolean b)
	{
		esplodiTutte = b;
	}
	
	/**
	 * Metodo che aggiunge alla lista di PowerUp la botola in gioco
	 * @param p power up che rappresenta la botola
	 */
	public void addBotola(PowerUp p)
	{
		powerup.add(p);
	}
}
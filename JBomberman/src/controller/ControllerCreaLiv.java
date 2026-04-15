package controller;

import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Blocco;
import model.Boss;
import model.Dato;
import model.LivelloCreato;
import model.Utente;
import model.Nemico;
import view.AudioManager;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * La classe ControllerCreaLiv è una classe che gestisce la creazione di un livello personalizzato
 */
public class ControllerCreaLiv {
	
	/**
	 * 	sfondo
	 */
	@FXML
	private Pane backPane;
	/**
	 * 	pane di appoggio
	 */
	@FXML
	private ImageView stage;
	/**
	 * menù a barra	
	 */
	@FXML
	private MenuBar menu;
	/**
	 * scelte del menù
	 */
	@FXML
	private Menu salva, indietro, gioca;
	/**
	 * oggetti del menù	
	 */
	@FXML
	private MenuItem bottoneSalva, bottoneIndietro, bottoneGioca, menuBlocco, pakupa, bakuda, cuppen, puropen, senshiyan, bigaron, clownMask, stage1m, stage2m, stage3m, stage4m, stage5m, stage6m, stage7m, stage8m;
	/**
	 * menù di scelta	
	 */
	@FXML
	private Menu mappa, blocchi, nemici, boss;
	/**
	 * 	stage di appoggio nuovo
	 */
	private Stage stageNew;
	/**
	 * scena della finestra	
	 */
	private Scene scene;
	/**
	 * array di blocchi distruttibili
	 */
	private List<Blocco> blocksD = new ArrayList<>();
	/**
	 * array di nemici	
	 */
	private List<Nemico> enemies = new ArrayList<>();
	/**
	 * array di coordinate x
	 */
	private List<Integer> blocksX = new ArrayList<>();
	/**
	 * array di coordinate y 
	 */
	private List<Integer> blocksY = new ArrayList<>();
	/**
	 * array di interi che indicano i tipi dei blocchi
	 */
	private List<Integer> blocksType = new ArrayList<>();
	/**
	 * array di coordinate x	
	 */
	private List<Integer> enemyX = new ArrayList<>();
	/**
	 * array di coordinate y	
	 */
	private List<Integer> enemyY = new ArrayList<>();
	/**
	 * array di interi che  indicano il tipo dei nemici
	 */
	private List<Integer> enemyType = new ArrayList<>();
	/**
	 * id dello slot	
	 */
	private int id;
	/**
	 * livello	
	 */
	private int level = 1;	
	/**
	 * entità	
	 */
	private int entita = -1;
	/**
	 * dati di gioco	
	 */
	private Dato dati;
	/**
	 * utente
	 */
	private Utente u;
	/**
	 * elenco di livelli creati	
	 */
	private LivelloCreato[] elencoLivCreati;
	/**
	 * immagine nemico 1	
	 */
	private ImageView pakupaImage = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p2.png")));
	/**
	 * immagine nemico 2
	 */
	private ImageView bakudaImage = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b5.png")));
	/**
	 * immagine nemico 3
	 */
	private ImageView cuppenImage = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f1.png")));
	/**
	 * immagine nemico 4
	 */
	private ImageView senshiyanImage = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank4.png")));
	/**
	 * immagine nemico 5	
	 */
	private ImageView bigaronImage = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/enemies/metal boss/m1.png")));
	/**
	 * immagine nemico 6	
	 */
	private ImageView clownMaskImage = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/enemies/clown boss/clown2.png")));
	/**
	 * immagine nemico 7	
	 */
	private ImageView puropenImage = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r4.png")));
	/**
	 * immagine blocco 1	
	 */
	private ImageView block1Image = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/blocks/stage 1/breakable.png")));
	/**
	 * immagine blocco 2	
	 */
	private ImageView block2Image = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/blocks/stage 2/breakable.png")));
	/**
	 * immagine blocco 3	
	 */
	private ImageView block3Image = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/blocks/stage 3/breakable.png")));
	/**
	 * immagine blocco 4	
	 */
	private ImageView block4Image = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/blocks/stage 4/breakable.png")));
	/**
	 * immagine blocco 5	
	 */
	private ImageView block5Image = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/blocks/stage 5/breakable.png")));
	/**
	 * immagine blocco 6	
	 */
	private ImageView block6Image = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/blocks/stage 6/breakable.png")));
	/**
	 * immagine blocco 7
	 */
	private ImageView block7Image = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/blocks/stage 7/breakable.png")));
	/**
	 * immagine blocco 8	
	 */
	private ImageView block8Image = new ImageView(new Image(getClass().getResourceAsStream("/images/sprites/blocks/stage 8/breakable.png")));
	/**
	 * 	immagine sfondo 1
	 */
	private Image stage1 = new Image(getClass().getResourceAsStream("/images/stage/void/stage 1.png"));
	/**
	 * immagine sfondo 2	
	 */
	private Image stage2 = new Image(getClass().getResourceAsStream("/images/stage/void/stage 2.png"));
	/**
	 * immagine sfondo 3	
	 */
	private Image stage3 = new Image(getClass().getResourceAsStream("/images/stage/void/stage 3.png"));
	/**
	 * immagine sfondo 4	
	 */
	private Image stage4 = new Image(getClass().getResourceAsStream("/images/stage/void/stage 4.png"));
	/**
	 * immagine sfondo 5	
	 */
	private Image stage5 = new Image(getClass().getResourceAsStream("/images/stage/void/stage 5.png"));
	/**
	 * immagine sfondo 6	
	 */
	private Image stage6 = new Image(getClass().getResourceAsStream("/images/stage/void/stage 6.png"));
	/**
	 * 	immagine sfondo 7
	 */
	private Image stage7 = new Image(getClass().getResourceAsStream("/images/stage/void/stage 7.png"));
	/**
	 * immagine sfondo 8	
	 */
	private Image stage8 = new Image(getClass().getResourceAsStream("/images/stage/void/stage 8.png"));
	/**
	 * contenitore immagine 1
	 */
	private ImageView s1Image = new ImageView(stage1);
	/**
	 * contenitore immagine 2
	 */
	private ImageView s2Image = new ImageView(stage2);
	/**
	 * contenitore immagine 3
	 */
	private ImageView s3Image = new ImageView(stage3);
	/**
	 * contenitore immagine 4
	 */
	private ImageView s4Image = new ImageView(stage4);
	/**
	 * contenitore immagine 5
	 */
	private ImageView s5Image = new ImageView(stage5);
	/**
	 * contenitore immagine 6
	 */
	private ImageView s6Image = new ImageView(stage6);
	/**
	 * contenitore immagine 7
	 */
	private ImageView s7Image = new ImageView(stage7);
	/**
	 * contenitore immagine 8
	 */
	private ImageView s8Image = new ImageView(stage8);
	/**
	 * immagine nemico
	 */
	private Image nemico;
	/**
	 * immagine boss
	 */
	private Image bossImage;
	/**
	 * variabile che indica se il boss è presente
	 */
	private boolean bossBool = false;
	/**
	 * valore che indica il tipo
	 */
	private int tipo;
	
	/**
	 * costruttore
	 */
	public ControllerCreaLiv() {
		
	}
	/**
	 * Metodo che riproduce l'audio associato al click dei bottoni
	 */
	public void suonoSelezione()
	{
		AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Title Screen Cursor2.wav");
	}
	
	/**
	 * Metodo che al cambio di schermata inizializza alcuni degli elementi di una schermata
	 * @param dati dati necessari per l'inizializzazione
	 */
	@FXML
	public void initialize(Dato dati)
	{
		menu.setStyle("-fx-alignment: center; -fx-font-size: 22px; -fx-background-color: WHITE;");
		pakupaImage.setFitHeight(40); pakupaImage.setFitWidth(40); bakudaImage.setFitHeight(40); bakudaImage.setFitWidth(40); cuppenImage.setFitHeight(40); cuppenImage.setFitWidth(30);
		senshiyanImage.setFitHeight(40); senshiyanImage.setFitWidth(40); puropenImage.setFitHeight(40); puropenImage.setFitWidth(30); bigaronImage.setFitHeight(40); bigaronImage.setFitWidth(40);
		clownMaskImage.setFitHeight(40); clownMaskImage.setFitWidth(40);
		block1Image.setFitHeight(40); block1Image.setFitWidth(40); block2Image.setFitHeight(40); block2Image.setFitWidth(40); block3Image.setFitHeight(40); block3Image.setFitWidth(40); 
		block4Image.setFitHeight(40); block4Image.setFitWidth(40); block5Image.setFitHeight(40); block5Image.setFitWidth(40); block6Image.setFitHeight(40); block6Image.setFitWidth(40);
		block7Image.setFitHeight(40); block7Image.setFitWidth(40); block8Image.setFitHeight(40); block8Image.setFitWidth(40);
		s1Image.setFitHeight(40); s1Image.setFitWidth(50); s2Image.setFitHeight(40); s2Image.setFitWidth(50); s3Image.setFitHeight(40); s3Image.setFitWidth(50); 
		s4Image.setFitHeight(40); s4Image.setFitWidth(50); s5Image.setFitHeight(40); s5Image.setFitWidth(50); s6Image.setFitHeight(40); s6Image.setFitWidth(50);
		s7Image.setFitHeight(40); s7Image.setFitWidth(50); s8Image.setFitHeight(40); s8Image.setFitWidth(50);
		pakupa.setGraphic(pakupaImage); bakuda.setGraphic(bakudaImage); cuppen.setGraphic(cuppenImage); senshiyan.setGraphic(senshiyanImage);
		puropen.setGraphic(puropenImage); bigaron.setGraphic(bigaronImage); clownMask.setGraphic(clownMaskImage); menuBlocco.setGraphic(block1Image);
		stage1m.setGraphic(s1Image); stage2m.setGraphic(s2Image); stage3m.setGraphic(s3Image); stage4m.setGraphic(s4Image);
		stage5m.setGraphic(s5Image); stage6m.setGraphic(s6Image); stage7m.setGraphic(s7Image); stage8m.setGraphic(s8Image);
		this.dati = dati;
		u = dati.getUtente();
		elencoLivCreati = dati.getElencoLivCreati();
		dati = deserializza("./src/saves/" + u.getNickname() + ".bomb");
		LivelloCreato[] elenco = dati.getElencoLivCreati();
		if(elenco[id-1] != null)
		{
			LivelloCreato liv = elenco[id-1];
			bossBool = liv.isBoss();
			level = liv.getLevel();
			switch(level)
			{
				case 1: default: stage.setImage(stage1); menuBlocco.setGraphic(block1Image); break;
				case 2: stage.setImage(stage2); menuBlocco.setGraphic(block2Image); break;
				case 3: stage.setImage(stage3); menuBlocco.setGraphic(block3Image); break;
				case 4: stage.setImage(stage4); menuBlocco.setGraphic(block4Image); break;
				case 5: stage.setImage(stage5); menuBlocco.setGraphic(block5Image); break;
				case 6: stage.setImage(stage6); menuBlocco.setGraphic(block6Image); break;
				case 7: stage.setImage(stage7); menuBlocco.setGraphic(block7Image); break;
				case 8: stage.setImage(stage8); menuBlocco.setGraphic(block8Image); break;
			}
			blocksX = liv.getBlocksX();
			blocksY = liv.getBlocksY();
			blocksType = liv.getBlocksType();
			enemyX = liv.getEnemyX();
			enemyY = liv.getEnemyY();
			enemyType = liv.getEnemyType();
			int dim = 0;
			if(blocksX.isEmpty() == false)
			{
				int i = 0;
				while (i < blocksX.size())
				{
					dim = 40;
					Blocco blocco = new Blocco(blocksType.get(i), true);
					ImageView imgBlocco = blocco.getBlock();
					Image img = blocco.getImage();
					imgBlocco.setImage(img);
					imgBlocco.setFitHeight(dim);
					imgBlocco.setFitWidth(dim);
					blocco.relocate(blocksX.get(i) , blocksY.get(i));
					blocksD.add(blocco);
					backPane.getChildren().add(blocco.getBlock()); 
					i++;
				}
			}
			if(!enemyType.isEmpty())
			{
				int y = 0;
				while (y < enemyType.size())
				{
					if(enemyType.get(y) < 5)
					{
						dim = 40;
						Nemico n = new Nemico(enemyType.get(y));
						ImageView imgEnemy = n.getEnemy();
						Image img = n.getFront();
						imgEnemy.setImage(img);
						imgEnemy.setFitHeight(dim);
						imgEnemy.setFitWidth(dim);
						n.relocate(enemyX.get(y) , enemyY.get(y));
						enemies.add(n);
						backPane.getChildren().add(n.getEnemy()); 
					}
					else
					{
						dim = 130;
						Boss b = new Boss(enemyType.get(y));
						ImageView imgEnemy = b.getEnemy();
						Image img = b.getNum1();
						imgEnemy.setImage(img);
						imgEnemy.setFitHeight(dim);
						imgEnemy.setFitWidth(dim);
						b.relocate(enemyX.get(y) , enemyY.get(y));
						enemies.add(b);
						backPane.getChildren().add(b.getEnemy()); 
					}
					y++;
				}
			}
		}
	}
	
	/**
	 * Metodo che salva il contenuto di un livello creato per renderlo giocabile
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void salva(ActionEvent e) 
	{
		suonoSelezione();
		LivelloCreato livello = new LivelloCreato();
		livello.setEditorId(id);
		livello.setLevel(level);
		livello.setColor(dati.getUtente().getAvatar());
		livello.setKeyboard(dati.getTastiera());
		for (Nemico n: enemies)
		{
			if(n instanceof Boss)
			{
				bossBool = true;
				break;
			}
		}
		livello.setBoss(bossBool);
		livello.setDati(dati);
		livello.setBlocksType(blocksType);
		livello.setBlocksX(blocksX);
		livello.setBlocksY(blocksY);
		livello.setEnemyType(enemyType);
		livello.setEnemyX(enemyX);
		livello.setEnemyY(enemyY);
		elencoLivCreati[id-1] = livello;
		dati.setElencoLivCreati(elencoLivCreati);
		serializza();
	}
	
	/**
	 * Metodo che permette di giocare un livello creato
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception lancia l'eccezione
	 */
	public void gioca(ActionEvent e) throws Exception 
	{
		salva(e);
		LivelloCreato livello = elencoLivCreati[id-1];
		MenuItem menuItem = (MenuItem) e.getSource();
		ContextMenu contextMenu = menuItem.getParentPopup();
	    Node anchor = contextMenu.getOwnerNode();
	    scene = anchor.getScene();
	    double anchorX = anchor.localToScene(anchor.getBoundsInLocal()).getMinX();
	    stageNew = (Stage) scene.getWindow();
		stageNew.setScene(scene);
		livello.start(stageNew, dati);
	}
		
	/**
	 * Metodo che permette di tornare alla schermata precedente
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void back(ActionEvent e) throws IOException 
	{
		suonoSelezione();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Editor.fxml"));
		Parent root = loader.load();
		MenuItem menuItem = (MenuItem) e.getSource();
		ContextMenu contextMenu = menuItem.getParentPopup();
	    Node anchor = contextMenu.getOwnerNode();
	    scene = anchor.getScene();
	    double anchorX = anchor.localToScene(anchor.getBoundsInLocal()).getMinX();
	    stageNew = (Stage) scene.getWindow();
		scene = new Scene(root,680,605);
		stageNew.setScene(scene);
		stageNew.setResizable(false);
		stageNew.show();
		ControllerEditor c = loader.getController();
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di scegliere quale nemico inserire nel livello
	 * @param a evento che scatena l'esecuzione della funzione
	 */
	public void sceltaEntita(ActionEvent a)
	{
	    if (a.getSource() == menuBlocco) 
	    {
	    	entita = 0;
	    	MenuItem b = (MenuItem)a.getSource();
			blocchi.setText(b.getText());
			for (Nemico n: enemies)
			{
				if (n != null) 
				{
					if(n instanceof Boss)
					{
						bossBool = true;
						backPane.getChildren().remove(n.getEnemy());
						enemyX.remove((Object)n.getX());
						enemyY.remove((Object)n.getY());
						enemyType.remove((Object)n.getTipo());
					}	
				}
			}
			enemies.clear();
	    }
	    else if (a.getSource() == pakupa)
	    {
	    	entita = 1;
	    	tipo = 3;
	    	nemico = new Image(getClass().getResourceAsStream("/images/sprites/enemies/blue pacman enemy/p2.png"));
	    }
	    else if (a.getSource() == bakuda)
	    {
	    	entita = 1;
	    	tipo = 1;
	    	nemico = new Image(getClass().getResourceAsStream("/images/sprites/enemies/bomb enemy/b5.png"));
	    }
	    else if (a.getSource() == cuppen)
	    {
	    	entita = 1;
	    	tipo = 4;
	    	nemico = new Image(getClass().getResourceAsStream("/images/sprites/enemies/floating enemy/f1.png"));
	    }
	    else if (a.getSource() == puropen)
	    {
	    	entita = 1;
	    	tipo = 0;
	    	nemico = new Image(getClass().getResourceAsStream("/images/sprites/enemies/red enemy/r4.png"));
	    }
	    else if (a.getSource() == senshiyan)
	    {
	    	entita = 1;
	    	tipo = 2;
	    	nemico = new Image(getClass().getResourceAsStream("/images/sprites/enemies/tank enemy/tank4.png"));
	    }
	    else if (a.getSource() == bigaron)
	    {
	    	entita = 2;
	    	tipo = 5;
	    	bossImage = new Image(getClass().getResourceAsStream("/images/sprites/enemies/metal boss/m1.png"));
	    	rimuoviBlocchi();
	    }
	    else if (a.getSource() == clownMask)
	    {
	    	entita = 2;
	    	tipo = 6;
	    	bossImage = new Image(getClass().getResourceAsStream("/images/sprites/enemies/clown boss/clown2.png"));
	    	rimuoviBlocchi();
	    }
	}
	
	/**
	 * Metodo che posiziona l'elemento selezionato nel punto in cui avviene il click del mouse
	 * @param event evento che scatena l'esecuzione della funzione
	 */
	public void handleMouseClick(MouseEvent event) 
	{
		int dim = 40;
		if (entita == 0) 
		{
			boolean isPlaced = false;
			int x = ((int)(event.getX() / dim)) * dim;
			int y = ((int)(event.getY() / dim)) * dim;
			if(x >= 0 && x <= 520 && y>= 0 && y <= 440) 
			{
				if (!(x == 240 && (y == 160 || y == 200 || y == 240)) && !(y == 40 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 120 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 200 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 280 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 360 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)))
				{
					if(blocksD.isEmpty() == false)
					{
						int i = blocksD.size()-1;
						while (i >= 0)
						{
							if (blocksD.get(i) != null)
							{
								Blocco block = blocksD.get(i);
								if(block.getBlock().getLayoutX() == x && block.getBlock().getLayoutY() == y)
								{
									isPlaced = true;
									backPane.getChildren().remove(block.getBlock());
									blocksD.remove(i);
									blocksX.remove(i);
									blocksY.remove(i);
									blocksType.remove(i);
								}
							}
							i--;
						}	
					}
					if(!isPlaced)
					{
						Blocco blocco = new Blocco(level, true);
						ImageView imgBlocco = blocco.getBlock();
						Image img = blocco.getImage();
						imgBlocco.setImage(img);
						imgBlocco.setFitHeight(dim);
						imgBlocco.setFitWidth(dim);
						blocco.relocate(x , y);
						blocksD.add(blocco);
						backPane.getChildren().add(blocco.getBlock()); 
						blocksX.add(x);
						blocksY.add(y);
						blocksType.add(level);
					}
				}
			}
		}
		else if (entita == 1)
		{
			boolean isPlaced = false;
			int x = ((int)(event.getX() / dim)) * dim;
			int y = ((int)(event.getY() / dim)) * dim;
			if(x >= 0 && x <= 520 && y>= 0 && y <= 440) 
			{
				if (!(x == 240 && (y == 160 || y == 200 || y == 240)) && !(y == 40 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 120 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 200 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 280 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 360 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)))
				{
					if(enemies.isEmpty() == false)
					{
						int i = enemies.size()-1;
						while (i >= 0)
						{
							if (enemies.get(i) != null)
							{
								Nemico n = enemies.get(i);
								if(n.getEnemy().getLayoutX() == x && n.getEnemy().getLayoutY() == y)
								{
									isPlaced = true;
									backPane.getChildren().remove(n.getEnemy());
									enemies.remove(i);
									enemyX.remove(i);
									enemyY.remove(i);
									enemyType.remove(i);
								}
							}
							i--;
						}	
					}
					if(!isPlaced)
					{
						Nemico n = new Nemico(tipo);
						ImageView imgEnemy = n.getEnemy();
						imgEnemy.setImage(nemico);
						imgEnemy.setFitHeight(dim);
						imgEnemy.setFitWidth(dim);
						n.relocate(x , y);
						enemies.add(n);
						backPane.getChildren().add(n.getEnemy()); 
						enemyX.add(x);
						enemyY.add(y);
						enemyType.add(tipo);
					}
				}
			}		
		}
		else if (entita == 2)
		{
			dim = 130;
			boolean isPlaced = false;
			int x = ((int)(event.getX() / dim)) * dim;
			int y = ((int)(event.getY() / dim)) * dim;
			if(x >= 0 && x <= 520 && y>= 0 && y <= 440) 
			{
				if (!(x == 240 && (y == 160 || y == 200 || y == 240)) && !(y == 40 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 120 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 200 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 280 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(y == 360 && (x == 40 || x == 120 || x == 200 || x == 280 || x == 360 || x == 440)) && !(x == 480) && !(y == 390))
				{
					if(enemies.isEmpty() == false)
					{
						int i = enemies.size()-1;
						while (i >= 0)
						{
							if (enemies.get(i) != null)
							{
								if(enemies.get(i) instanceof Boss)
								{
									Boss n = (Boss) enemies.get(i);
									if(n.getEnemy().getLayoutX() == x && n.getEnemy().getLayoutY() == y)
									{
										isPlaced = true;
										backPane.getChildren().remove(n.getEnemy());
										enemies.remove(i);
										enemyX.remove(i);
										enemyY.remove(i);
										enemyType.remove(i);
									}
								}
							}
							i--;
						}	
					}
					if(!isPlaced)
					{
						Boss b = new Boss(tipo);
						ImageView imgEnemy = b.getEnemy();
						imgEnemy.setImage(bossImage);
						imgEnemy.setFitHeight(dim);
						imgEnemy.setFitWidth(dim);
						b.relocate(x , y);
						enemies.add(b);
						backPane.getChildren().add(b.getEnemy()); 
						enemyX.add(x);
						enemyY.add(y);
						enemyType.add(tipo);
					}
				}
			}		
		}
	}
	
	/**
	 * Metodo che rimuove i blocchi posizionati nel livello durante la creazione se assi cambia il livello
	 */
	public void rimuoviBlocchi()
	{
		if(blocksD.isEmpty() == false)
		{
			int i = blocksD.size()-1;
			while (i >= 0)
			{
				if (blocksD.get(i) != null)
				{
					Blocco block = blocksD.get(i);
					backPane.getChildren().remove(block.getBlock());
					blocksD.remove(i);
					blocksX.remove(i);
					blocksY.remove(i);
					blocksType.remove(i);
				}
				i--;
			}	
		}
	}
	
	/**
	 * Metodo che permette il cambio di stile con quello del livello 1
	 * @param a evento che scatena l'esecuzione della funzione
	 */
	public void cambiaStage1(ActionEvent a)
	{
		rimuoviBlocchi();
		MenuItem m = (MenuItem)a.getSource();
		mappa.setText(m.getText());
		menuBlocco.setGraphic(block1Image);
		stage.setImage(stage1);
		level = 1;	
	}
	
	/**
	 * Metodo che permette il cambio di stile con quello del livello 2
	 * @param a evento che scatena l'esecuzione della funzione
	 */
	public void cambiaStage2(ActionEvent a)
	{
		rimuoviBlocchi();
		MenuItem m = (MenuItem)a.getSource();
		mappa.setText(m.getText());
		menuBlocco.setGraphic(block2Image);
		stage.setImage(stage2);
		level = 2;
	}
	
	/**
	 * Metodo che permette il cambio di stile con quello del livello 3
	 * @param a evento che scatena l'esecuzione della funzione
	 */
	public void cambiaStage3(ActionEvent a)
	{
		rimuoviBlocchi();
		MenuItem m = (MenuItem)a.getSource();
		mappa.setText(m.getText());
		menuBlocco.setGraphic(block3Image);
		stage.setImage(stage3);
		level = 3;
	}
	
	/**
	 * Metodo che permette il cambio di stile con quello del livello 4
	 * @param a evento che scatena l'esecuzione della funzione
	 */
	public void cambiaStage4(ActionEvent a)
	{
		rimuoviBlocchi();
		MenuItem m = (MenuItem)a.getSource();
		mappa.setText(m.getText());
		menuBlocco.setGraphic(block4Image);
		stage.setImage(stage4);
		level = 4;
	}
	
	/**
	 * Metodo che permette il cambio di stile con quello del livello 5
	 * @param a evento che scatena l'esecuzione della funzione
	 */
	public void cambiaStage5(ActionEvent a)
	{
		rimuoviBlocchi();
		MenuItem m = (MenuItem)a.getSource();
		mappa.setText(m.getText());
		menuBlocco.setGraphic(block5Image);
		stage.setImage(stage5);
		level = 5;
	}
	
	/**
	 * Metodo che permette il cambio di stile con quello del livello 6
	 * @param a evento che scatena l'esecuzione della funzione
	 */
	public void cambiaStage6(ActionEvent a)
	{
		rimuoviBlocchi();
		MenuItem m = (MenuItem)a.getSource();
		mappa.setText(m.getText());
		menuBlocco.setGraphic(block6Image);
		stage.setImage(stage6);
		level = 6;
	}
	
	/**
	 * Metodo che permette il cambio di stile con quello del livello 7
	 * @param a evento che scatena l'esecuzione della funzione
	 */
	public void cambiaStage7(ActionEvent a)
	{
		rimuoviBlocchi();
		MenuItem m = (MenuItem)a.getSource();
		mappa.setText(m.getText());
		menuBlocco.setGraphic(block7Image);
		stage.setImage(stage7);
		level = 7;
	}
	
	/**
	 * Metodo che permette il cambio di stile con quello del livello 8
	 * @param a evento che scatena l'esecuzione della funzione
	 */
	public void cambiaStage8(ActionEvent a)
	{
		rimuoviBlocchi();
		MenuItem m = (MenuItem)a.getSource();
		mappa.setText(m.getText());
		menuBlocco.setGraphic(block8Image);
		stage.setImage(stage8);
		level = 8;
	}
	
	/**
	 * Metodo che serializza i dati ed esegue il salvataggio
	 */
	public void serializza()
	{
		ObjectOutputStream out = null;
		try {
			 FileOutputStream fos = new FileOutputStream("./src/saves/"+u.getNickname()+".bomb");
			 out = new ObjectOutputStream(fos);
			 out.writeObject(dati);	
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Metodo che deserializza i dati e ne permette l'apertura e l'utilizzo
	 * @param percorso percorso del file di salvataggio
	 * @return dati utili per l'apertura
	 * @throws NullPointerException lancia l'eccezione
	 */
	public Dato deserializza(String percorso) throws NullPointerException 
	{
		Dato ris = null;
		ObjectInputStream in = null;
		try {
			 FileInputStream fin = new FileInputStream(percorso);
			 in = new ObjectInputStream(fin);
			 ris = (Dato) in.readObject();
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ris;
	}

	/**
	 * Metodo che restituisce il livello
	 * @return livello
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Metodo che imposta il livello
	 * @param level livello
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Metodo che restituisce l'Id del livello selezionato dall'editor
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Metodo che imposta l'Id del livello selezionato dall'editor
	 * @param id id selezionato dall'editor
	 */
	public void setId(int id) {
		this.id = id;
	}
}

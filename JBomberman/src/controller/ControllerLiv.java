package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import model.Boss;
import model.Dato;
import model.Livello;
import model.Nemico;
import model.Utente;
import view.AudioManager;

/**
 * La classe ControllerLiv è la classe che gestisce la schermata con i livelli
 */
public class ControllerLiv {
	
	/**
	 * contenitori immagini
	 */
	@FXML
	private ImageView immagineLv1, immagineLv2, immagineLv3, immagineLv4, immagineLv5, immagineLv6, immagineLv7, immagineLv8;
	/**
	 * bottoni livelli
	 */
	@FXML
	private Button bottoneLv1, bottoneLv2, bottoneLv3, bottoneLv4, bottoneLv5, bottoneLv6, bottoneLv7, bottoneLv8, frecciaButton;
    /**
     * freccia escape
     */
	@FXML
    private ImageView freccia;
    /**
     * etichette livelli
     */
	@FXML
    private Label title, l1, l2, l3, l4, l5, l6, l7, l8, b1, b2;
    /**
     * anchorpane
     */
	@FXML
    private AnchorPane scenePane;
	/**
	 * stage
	 */
	private Stage stage;
	/**
	 * scena
	 */
	private Scene scene;
	/**
	 * dati di gioco
	 */
	private Dato dati;
	/**
	 * utente
	 */
	private Utente u;
	/**
	 * elenco livelli
	 */
	private boolean[] elencoLiv = {false, false, false, false, false, false, false};
	/**
	 * elenco stati dei livelli
	 */
	private boolean[] visibile = {false, false, false, false, false, false, false};
	
	/**
	 * costruttore
	 */
	public ControllerLiv() {
		
	}
	/**
	 * Metodo che riproduce l'audio associato al click dei bottoni
	 */
	public void suonoSelezione()
	{
		AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Title Screen Cursor2.wav");
	}
	
	/**
	 * Metodo che riproduce l'audio associato all'inizio della partita
	 */
	public void suonoInizio()
	{
		AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Title Screen Select2.wav");
	}
	
	/**
	 * Metodo che al cambio di schermata inizializza alcuni degli elementi di una schermata
	 * @param dati dati necessari per l'inizializzazione
	 */
	@FXML
	public void initialize(Dato dati)
	{
		dati = deserializza("./src/saves/" + dati.getUtente().getNickname() + ".bomb");
		Font font30 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 30);
		Font font18 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 18);
		title.setFont(font30);
		l1.setFont(font18); l2.setFont(font18); l3.setFont(font18); l4.setFont(font18); l5.setFont(font18);
		l6.setFont(font18); l7.setFont(font18); l8.setFont(font18); b1.setFont(font18); b2.setFont(font18);
		this.dati = dati;
		elencoLiv = dati.getLivSuperati();
		visibile = dati.getVisibile();
		u = dati.getUtente();
		sbloccaLivello(elencoLiv[0], elencoLiv[1], elencoLiv[2], elencoLiv[3], elencoLiv[4], elencoLiv[5], elencoLiv[6]);
		dati.setVisibile(visibile);
		serializza();
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da tastiera
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception eccezione
	 */
	public void gestisciTastiera(KeyEvent e) throws Exception
	{
		if(e.getSource().equals(frecciaButton))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoSelezione();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuSecondario.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerMS c = loader.getController();
				c.initialize(dati);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				if(!bottoneLv8.isDisabled())
				{
					bottoneLv8.requestFocus();
					bottoneLv8.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneLv7.isDisabled())
				{
					bottoneLv7.requestFocus();
					bottoneLv7.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneLv6.isDisabled())
				{
					bottoneLv6.requestFocus();
					bottoneLv6.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneLv5.isDisabled())
				{
					bottoneLv5.requestFocus();
					bottoneLv5.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneLv4.isDisabled())
				{
					bottoneLv4.requestFocus();
					bottoneLv4.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneLv3.isDisabled())
				{
					bottoneLv3.requestFocus();
					bottoneLv3.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneLv2.isDisabled())
				{
					bottoneLv2.requestFocus();
					bottoneLv2.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else 
				{
					bottoneLv1.requestFocus();
					bottoneLv1.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				suonoSelezione();
				Image image = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
				freccia.setImage(image);
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneLv1.requestFocus();
				suonoSelezione();
				bottoneLv1.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				Image image = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
				freccia.setImage(image);
			}
		}
		else if(e.getSource().equals(bottoneLv1))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				Livello liv1 = new Livello();
				liv1.setLevel(1);
				liv1.setColor(dati.getUtente().getAvatar());
				liv1.setKeyboard(dati.getTastiera());
				liv1.setBoss(false);
				liv1.setDati(dati);
			    List<Nemico> elencoNemici = liv1.getNemici();
			    Nemico n1 = new Nemico(0);
			    n1.setInGioco(true);
			    elencoNemici.add(n1);
			    Nemico n2 = new Nemico(0);
			    n2.setInGioco(true);
			    elencoNemici.add(n2);
			    liv1.setNemici(elencoNemici);
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				stage.setScene(n.getScene());
				liv1.start(stage, dati, visibile);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneLv1.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				frecciaButton.requestFocus();
				suonoSelezione();
				Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
				freccia.setImage(image);
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneLv1.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneLv2.isDisabled())
				{
					bottoneLv2.requestFocus();
					bottoneLv2.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneLv2))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				Livello liv2 = new Livello();
				liv2.setColor(dati.getUtente().getAvatar());
				liv2.setLevel(2);
				liv2.setKeyboard(dati.getTastiera());
				liv2.setBoss(false);
				liv2.setDati(dati);
			    List<Nemico> elencoNemici = liv2.getNemici();
			    Nemico n1 = new Nemico(4);
			    n1.setInGioco(true);
			    elencoNemici.add(n1);
			    Nemico n2 = new Nemico(4);
			    n2.setInGioco(true);
			    elencoNemici.add(n2);
			    Nemico n3 = new Nemico(4);
			    n3.setInGioco(true);
			    elencoNemici.add(n3);
			    liv2.setNemici(elencoNemici);
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				stage.setScene(n.getScene());
				liv2.start(stage, dati, visibile);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneLv2.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				bottoneLv1.requestFocus();
				suonoSelezione();
				bottoneLv1.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneLv2.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneLv3.isDisabled())
				{
					bottoneLv3.requestFocus();
					bottoneLv3.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneLv3))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				Livello liv3 = new Livello();
				liv3.setLevel(3);
				liv3.setColor(dati.getUtente().getAvatar());
				liv3.setKeyboard(dati.getTastiera());
				liv3.setBoss(false);
				liv3.setDati(dati);
				List<Nemico> elencoNemici = liv3.getNemici();
			    Nemico n1 = new Nemico(2); //punti e tipo
			    n1.setInGioco(true);
			    elencoNemici.add(n1);
			    Nemico n2 = new Nemico(2);
			    n2.setInGioco(true);
			    elencoNemici.add(n2);
			    Nemico n3 = new Nemico(2);
			    n3.setInGioco(true);
			    elencoNemici.add(n3);
			    liv3.setNemici(elencoNemici);
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				stage.setScene(n.getScene());
				liv3.start(stage, dati, visibile);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneLv3.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				bottoneLv2.requestFocus();
				suonoSelezione();
				bottoneLv2.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneLv3.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneLv4.isDisabled())
				{
					bottoneLv4.requestFocus();
					bottoneLv4.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneLv4))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				Livello liv4 = new Livello();
				liv4.setLevel(4);
				liv4.setColor(dati.getUtente().getAvatar());
				liv4.setKeyboard(dati.getTastiera());
				liv4.setBoss(true);
				liv4.setDati(dati);
				List<Nemico> elencoNemici = liv4.getNemici();
			    Boss b = new Boss(5);
			    b.setInGioco(true);
			    elencoNemici.add(b);
			    liv4.setNemici(elencoNemici);
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				stage.setScene(n.getScene());
				liv4.start(stage, dati, visibile);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneLv4.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				bottoneLv3.requestFocus();
				suonoSelezione();
				bottoneLv3.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneLv4.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneLv5.isDisabled())
				{
					bottoneLv5.requestFocus();
					bottoneLv5.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneLv5))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				  suonoInizio();
				  Livello liv5 = new Livello();
				  liv5.setLevel(5);
				  liv5.setColor(dati.getUtente().getAvatar());
				  liv5.setKeyboard(dati.getTastiera());
				  liv5.setBoss(false);
				  liv5.setDati(dati);
				  List<Nemico> elencoNemici = liv5.getNemici();
  			      Nemico n1 = new Nemico(0);
			      n1.setInGioco(true);
			      elencoNemici.add(n1);
			      Nemico n2 = new Nemico(0);
			      n2.setInGioco(true);
			      elencoNemici.add(n2);
			      Nemico n3 = new Nemico(3);
			      n3.setInGioco(true);
			      elencoNemici.add(n3);
			      Nemico n4 = new Nemico(3);
			      n4.setInGioco(true);
			      elencoNemici.add(n4);
			      liv5.setNemici(elencoNemici);
				  Node n = (Node) e.getSource();
				  stage = (Stage) n.getScene().getWindow();
				  stage.setScene(n.getScene());
				  liv5.start(stage, dati, visibile);

			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneLv5.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				bottoneLv4.requestFocus();
				suonoSelezione();
				bottoneLv4.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneLv5.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneLv6.isDisabled())
				{
					bottoneLv6.requestFocus();
					bottoneLv6.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneLv6))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
			    Livello liv6 = new Livello();
			    liv6.setLevel(6);
			    liv6.setColor(dati.getUtente().getAvatar());
			    liv6.setKeyboard(dati.getTastiera());
			    liv6.setBoss(false);
			    liv6.setDati(dati);
			    List<Nemico> elencoNemici = liv6.getNemici();
		        Nemico n1 = new Nemico(1); //punti e tipo
		        n1.setInGioco(true);
		        elencoNemici.add(n1);
		        Nemico n2 = new Nemico(1);
		        n2.setInGioco(true);
		        elencoNemici.add(n2);
		        Nemico n3 = new Nemico(2);
		        n3.setInGioco(true);
		        elencoNemici.add(n3);
		        Nemico n4 = new Nemico(2);
		        n4.setInGioco(true);
		        elencoNemici.add(n4);
		        liv6.setNemici(elencoNemici);
			    Node n = (Node) e.getSource();
			    stage = (Stage) n.getScene().getWindow();
			    stage.setScene(n.getScene());
			    liv6.start(stage, dati, visibile);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneLv6.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				bottoneLv5.requestFocus();
				suonoSelezione();
				bottoneLv5.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneLv6.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneLv7.isDisabled())
				{
					bottoneLv7.requestFocus();
					bottoneLv7.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneLv7))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				Livello liv7 = new Livello();
				liv7.setLevel(7);
				liv7.setColor(dati.getUtente().getAvatar());
				liv7.setKeyboard(dati.getTastiera());
				liv7.setBoss(false);
				liv7.setDati(dati);
				List<Nemico> elencoNemici = liv7.getNemici();
			    Nemico n1 = new Nemico(0); //punti e tipo
			    n1.setInGioco(true);
			    elencoNemici.add(n1);
			    Nemico n2 = new Nemico(1);
			    n2.setInGioco(true);
			    elencoNemici.add(n2);
			    Nemico n3 = new Nemico(2);
			    n3.setInGioco(true);
			    elencoNemici.add(n3);
			    Nemico n4 = new Nemico(3);
			    n4.setInGioco(true);
			    elencoNemici.add(n4);
			    Nemico n5 = new Nemico(4);
			    n5.setInGioco(true);
			    elencoNemici.add(n5);
			    liv7.setNemici(elencoNemici);
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				stage.setScene(n.getScene());
				liv7.start(stage, dati, visibile);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneLv7.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				bottoneLv6.requestFocus();
				suonoSelezione();
				bottoneLv6.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneLv7.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneLv8.isDisabled())
				{
					bottoneLv8.requestFocus();
					bottoneLv8.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneLv8))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				Livello liv8 = new Livello();
				liv8.setLevel(8);
				liv8.setColor(dati.getUtente().getAvatar());
				liv8.setKeyboard(dati.getTastiera());
				liv8.setBoss(true);
				liv8.setDati(dati);
				List<Nemico> elencoNemici = liv8.getNemici();
			    Boss b = new Boss(6);
			    b.setInGioco(true);
			    elencoNemici.add(b);
			    liv8.setNemici(elencoNemici);
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				stage.setScene(n.getScene());
				liv8.start(stage, dati, visibile);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneLv8.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				bottoneLv7.requestFocus();
				suonoSelezione();
				bottoneLv7.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneLv8.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				frecciaButton.requestFocus();
				suonoSelezione();
				Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
				freccia.setImage(image);
			}
		}
	}
	
	/**
	 * Metodo che permette di tornare alla schermata precedente
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void back(ActionEvent e) throws IOException
	{
		suonoSelezione();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuSecondario.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerMS c = loader.getController();
		c.initialize(dati);
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da mouse nella schermata di fine partita quando il cursore va sopra al bottone
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void enter(MouseEvent e)
	{
		Image im = new Image(getClass().getResourceAsStream("/images/background/white.png"));
		freccia.setImage(im);
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da mouse nella schermata di fine partita quando il cursore esce dal bottone
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void exit(MouseEvent e)
	{
		Image image = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
		freccia.setImage(image);
	}
	
	/**
	 * Metodo che permette l'inizio della partita nel livello 1
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception eccezione
	 */
	public void Lv1(ActionEvent e) throws Exception 
	{
		suonoInizio();
		Livello liv1 = new Livello();
		liv1.setLevel(1);
		liv1.setColor(dati.getUtente().getAvatar());
		liv1.setKeyboard(dati.getTastiera());
		liv1.setBoss(false);
		liv1.setDati(dati);
	    List<Nemico> elencoNemici = liv1.getNemici();
	    Nemico n1 = new Nemico(0);
	    n1.setInGioco(true);
	    elencoNemici.add(n1);
	    Nemico n2 = new Nemico(0);
	    n2.setInGioco(true);
	    elencoNemici.add(n2);
	    liv1.setNemici(elencoNemici);
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		stage.setScene(n.getScene());
		liv1.start(stage, dati, visibile);
	}
	
	/**
	 * Metodo che permette l'inizio della partita nel livello 2
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception eccezione
	 */
	public void Lv2(ActionEvent e) throws Exception 
	{
		suonoInizio();
		Livello liv2 = new Livello();
		liv2.setLevel(2);
		liv2.setColor(dati.getUtente().getAvatar());
		liv2.setKeyboard(dati.getTastiera());
		liv2.setBoss(false);
		liv2.setDati(dati);
	    List<Nemico> elencoNemici = liv2.getNemici();
	    Nemico n1 = new Nemico(4);
	    n1.setInGioco(true);
	    elencoNemici.add(n1);
	    Nemico n2 = new Nemico(4);
	    n2.setInGioco(true);
	    elencoNemici.add(n2);
	    Nemico n3 = new Nemico(4);
	    n3.setInGioco(true);
	    elencoNemici.add(n3);
	    liv2.setNemici(elencoNemici);
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		stage.setScene(n.getScene());
		liv2.start(stage, dati, visibile);
	}
	
	/**
	 * Metodo che permette l'inizio della partita nel livello 3
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception eccezione
	 */
	public void Lv3(ActionEvent e) throws Exception 
	{
		suonoInizio();
		Livello liv3 = new Livello();
		liv3.setLevel(3);
		liv3.setColor(dati.getUtente().getAvatar());
		liv3.setKeyboard(dati.getTastiera());
		liv3.setBoss(false);
		liv3.setDati(dati);
		List<Nemico> elencoNemici = liv3.getNemici();
	    Nemico n1 = new Nemico(2); 
	    n1.setInGioco(true);
	    elencoNemici.add(n1);
	    Nemico n2 = new Nemico(2);
	    n2.setInGioco(true);
	    elencoNemici.add(n2);
	    Nemico n3 = new Nemico(2);
	    n3.setInGioco(true);
	    elencoNemici.add(n3);
	    liv3.setNemici(elencoNemici);
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		stage.setScene(n.getScene());
		liv3.start(stage, dati, visibile);
	}
	
	/**
	 * Metodo che permette l'inizio della partita nel livello 4
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception eccezione
	 */
	public void Lv4(ActionEvent e) throws Exception 
	{
		suonoInizio();
		Livello liv4 = new Livello();
		liv4.setLevel(4);
		liv4.setColor(dati.getUtente().getAvatar());
		liv4.setKeyboard(dati.getTastiera());
		liv4.setBoss(true);
		liv4.setDati(dati);
		List<Nemico> elencoNemici = liv4.getNemici();
	    Boss b = new Boss(5);
	    b.setInGioco(true);
	    elencoNemici.add(b);
	    liv4.setNemici(elencoNemici);
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		stage.setScene(n.getScene());
		liv4.start(stage, dati, visibile);
	}
	
	/**
	 * Metodo che permette l'inizio della partita nel livello 5
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception eccezione
	 */
	public void Lv5(ActionEvent e) throws Exception 
	{
		suonoInizio();
		Livello liv5 = new Livello();
		liv5.setLevel(5);
		liv5.setColor(dati.getUtente().getAvatar());
		liv5.setKeyboard(dati.getTastiera());
		liv5.setBoss(false);
		liv5.setDati(dati);
		List<Nemico> elencoNemici = liv5.getNemici();
		Nemico n1 = new Nemico(0);
		n1.setInGioco(true);
		elencoNemici.add(n1);
		Nemico n2 = new Nemico(0);
		n2.setInGioco(true);
		elencoNemici.add(n2);
		Nemico n3 = new Nemico(3);
		n3.setInGioco(true);
		elencoNemici.add(n3);
		Nemico n4 = new Nemico(3);
		n4.setInGioco(true);
		elencoNemici.add(n4);
		liv5.setNemici(elencoNemici);
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		stage.setScene(n.getScene());
		liv5.start(stage, dati, visibile);
	}
	
	/**
	 * Metodo che permette l'inizio della partita nel livello 6
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception eccezione
	 */
	public void Lv6(ActionEvent e) throws Exception 
	{
		suonoInizio();
	    Livello liv6 = new Livello();
	    liv6.setLevel(6);
	    liv6.setColor(dati.getUtente().getAvatar());
	    liv6.setKeyboard(dati.getTastiera());
	    liv6.setBoss(false);
	    liv6.setDati(dati);
	    List<Nemico> elencoNemici = liv6.getNemici();
	    Nemico n1 = new Nemico(1);
	    n1.setInGioco(true);
	    elencoNemici.add(n1);
	    Nemico n2 = new Nemico(1);
	    n2.setInGioco(true);
	    elencoNemici.add(n2);
	    Nemico n3 = new Nemico(2);
	    n3.setInGioco(true);
	    elencoNemici.add(n3);
	    Nemico n4 = new Nemico(2);
	    n4.setInGioco(true);
	    elencoNemici.add(n4);
	    liv6.setNemici(elencoNemici);
	    Node n = (Node) e.getSource();
	    stage = (Stage) n.getScene().getWindow();
	    stage.setScene(n.getScene());
	    liv6.start(stage, dati, visibile);
	}
	
	/**
	 * Metodo che permette l'inizio della partita nel livello 7
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception eccezione
	 */
	public void Lv7(ActionEvent e) throws Exception 
	{
		suonoInizio();
		Livello liv7 = new Livello();
		liv7.setLevel(7);
		liv7.setColor(dati.getUtente().getAvatar());
		liv7.setKeyboard(dati.getTastiera());
		liv7.setBoss(false);
		liv7.setDati(dati);
		List<Nemico> elencoNemici = liv7.getNemici();
	    Nemico n1 = new Nemico(0);
	    n1.setInGioco(true);
	    elencoNemici.add(n1);
	    Nemico n2 = new Nemico(1);
	    n2.setInGioco(true);
	    elencoNemici.add(n2);
	    Nemico n3 = new Nemico(2);
	    n3.setInGioco(true);
	    elencoNemici.add(n3);
	    Nemico n4 = new Nemico(3);
	    n4.setInGioco(true);
	    elencoNemici.add(n4);
	    Nemico n5 = new Nemico(4);
	    n5.setInGioco(true);
	    elencoNemici.add(n5);
	    liv7.setNemici(elencoNemici);
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		stage.setScene(n.getScene());
		liv7.start(stage, dati, visibile);
	}
	
	/**
	 * Metodo che permette l'inizio della partita nel livello 8
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception eccezione
	 */
	public void Lv8(ActionEvent e) throws Exception 
	{
		suonoInizio();
		Livello liv8 = new Livello();
		liv8.setLevel(8);
		liv8.setColor(dati.getUtente().getAvatar());
		liv8.setKeyboard(dati.getTastiera());
		liv8.setBoss(true);
		liv8.setDati(dati);
		List<Nemico> elencoNemici = liv8.getNemici();
	    Boss b = new Boss(6);
	    b.setInGioco(true);
	    elencoNemici.add(b);
	    liv8.setNemici(elencoNemici);
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		stage.setScene(n.getScene());
		liv8.start(stage, dati, visibile);
	}
	
	/**
	 * Metodo che sblocca il livello successivo quando un livello viene superato
	 * @param superato1 variabile che indica se il livello 1 è superato
	 * @param superato2 variabile che indica se il livello 2 è superato
	 * @param superato3 variabile che indica se il livello 3 è superato
	 * @param superato4 variabile che indica se il livello 4 è superato
	 * @param superato5 variabile che indica se il livello 5 è superato
	 * @param superato6 variabile che indica se il livello 6 è superato
	 * @param superato7 variabile che indica se il livello 7 è superato
	 */
	public void sbloccaLivello (boolean superato1, boolean superato2, boolean superato3, boolean superato4, boolean superato5, boolean superato6, boolean superato7) 
	{
		if(superato1 || visibile[0]) 
		{
			immagineLv2.setEffect(null);
			bottoneLv2.setDisable(false);
			visibile[0] = true;
		}
		if(superato2 || visibile[1]) 
		{
			immagineLv3.setEffect(null);
			bottoneLv3.setDisable(false);
			visibile[1] = true;
		}
		if(superato3 || visibile[2]) 
		{
			immagineLv4.setEffect(null);
			bottoneLv4.setDisable(false);
			visibile[2] = true;
		}
		if(superato4 || visibile[3]) 
		{
			immagineLv5.setEffect(null);
			bottoneLv5.setDisable(false);
			visibile[3] = true;
		}
		if(superato5 || visibile[4]) 
		{
			immagineLv6.setEffect(null);
			bottoneLv6.setDisable(false);
			visibile[4] = true;
		}
		if(superato6 || visibile[5]) 
		{
			immagineLv7.setEffect(null);
			bottoneLv7.setDisable(false);
			visibile[5] = true;
		}
		if(superato7 || visibile[6]) 
		{
			immagineLv8.setEffect(null);
			bottoneLv8.setDisable(false);
			visibile[6] = true;
		}
	}

	/**
	 * Metodo che restituisce una lista di variabili che indicano lo stato dei livelli
	 * @return lista di variabili che indicano lo stato dei livelli
	 */
	public boolean[] getVisibile() {
		return visibile;
	}

	/**
	 * Metodo che imposta una lista di variabili che indicano lo stato dei livelli
	 * @param visibile variabili che indicano lo stato dei livelli
	 */
	public void setVisibile(boolean[] visibile) {
		this.visibile = visibile;
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
	 * @throws NullPointerException eccezione
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
}

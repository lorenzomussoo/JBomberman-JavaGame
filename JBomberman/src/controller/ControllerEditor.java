package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.stage.Stage;
import model.Dato;
import model.LivelloCreato;
import view.AudioManager;

/**
 * La classe ControllerEditor è la classe che gestisce la selezione dei livelli creabili dell'editor
 */
public class ControllerEditor {
	
	/**
	 * bottoni degli slot
	 */
	@FXML
	private Button bottoneSlot1, bottoneSlot2, bottoneSlot3, bottoneSlot4, bottoneSlot5, bottoneSlot6, bottoneSlot7, bottoneSlot8, frecciaButton;
	/**
	 * contenitori immagini
	 */
	@FXML
	private ImageView im1, im2, im3, im4, im5, im6, im7, im8, freccia;
	/**
	 * etichette
	 */
	@FXML
	private Label title, l1, l2, l3, l4, l5, l6, l7, l8;
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
	 * elenco di livelli creati
	 */
	private LivelloCreato[] elencoLivCreati;
	
	/**
	 * costruttore
	 */
	public ControllerEditor() {
		
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
		Font font30 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 30);
		Font font19 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 19);
		title.setFont(font30);
		l1.setFont(font19);
		l2.setFont(font19);
		l3.setFont(font19);
		l4.setFont(font19);
		l5.setFont(font19);
		l6.setFont(font19);
		l7.setFont(font19);
		l8.setFont(font19);
		this.dati = dati;
		elencoLivCreati = dati.getElencoLivCreati();
		if(elencoLivCreati[0]!= null)
		{
			Image im = stage(elencoLivCreati[0].getLevel());
			im1.setImage(im);
			im1.setPreserveRatio(true);
		}
		if(elencoLivCreati[1]!= null)
		{
			Image im = stage(elencoLivCreati[1].getLevel());
			im2.setImage(im);
			im2.setPreserveRatio(true);
		}
		if(elencoLivCreati[2]!= null)
		{
			Image im = stage(elencoLivCreati[2].getLevel());
			im3.setImage(im);
			im3.setPreserveRatio(true);
		}
		if(elencoLivCreati[3]!= null)
		{
			Image im = stage(elencoLivCreati[3].getLevel());
			im4.setImage(im);
			im4.setPreserveRatio(true);
		}
		if(elencoLivCreati[4]!= null)
		{
			Image im = stage(elencoLivCreati[4].getLevel());
			im5.setImage(im);
			im5.setPreserveRatio(true);
		}
		if(elencoLivCreati[5]!= null)
		{
			Image im = stage(elencoLivCreati[5].getLevel());
			im6.setImage(im);
			im6.setPreserveRatio(true);
		}
		if(elencoLivCreati[6]!= null)
		{
			Image im = stage(elencoLivCreati[6].getLevel());
			im7.setImage(im);
			im7.setPreserveRatio(true);
		}
		if(elencoLivCreati[7]!= null)
		{
			Image im = stage(elencoLivCreati[7].getLevel());
			im8.setImage(im);
			im8.setPreserveRatio(true);
		}
	}
	
	/**
	 * Metodo che seleziona l'immagine di sfondo del livello 
	 * @param num valore che seleziona l'immagine corretta
	 * @return Image da applicare come sfondo
	 */
	public Image stage(int num)
	{
		Image img = null;
		if(num == 1)
		{
			img = new Image(getClass().getResourceAsStream("/images/stage/void/stage 1.png"));
		}
		else if(num == 2)
		{
			img = new Image(getClass().getResourceAsStream("/images/stage/void/stage 2.png"));
		}
		else if(num == 3)
		{
			img = new Image(getClass().getResourceAsStream("/images/stage/void/stage 3.png"));
		}
		else if(num == 4)
		{
			img = new Image(getClass().getResourceAsStream("/images/stage/void/stage 4.png"));
		}
		else if(num == 5)
		{
			img = new Image(getClass().getResourceAsStream("/images/stage/void/stage 5.png"));
		}
		else if(num == 6)
		{
			img = new Image(getClass().getResourceAsStream("/images/stage/void/stage 6.png"));
		}
		else if(num == 7)
		{
			img = new Image(getClass().getResourceAsStream("/images/stage/void/stage 7.png"));
		}
		else if(num == 8)
		{
			img = new Image(getClass().getResourceAsStream("/images/stage/void/stage 8.png"));
		}
		return img;
	}
	
	/**
	 * Metodo che permette la selezione dei livelli dell'editor da tastiera
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void gestisciTastiera(KeyEvent e) throws IOException 
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
			else if(e.getCode() == KeyCode.UP)
			{
				if(!bottoneSlot8.isDisabled())
				{
					bottoneSlot8.requestFocus();
					bottoneSlot8.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneSlot7.isDisabled())
				{
					bottoneSlot7.requestFocus();
					bottoneSlot7.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneSlot6.isDisabled())
				{
					bottoneSlot6.requestFocus();
					bottoneSlot6.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneSlot5.isDisabled())
				{
					bottoneSlot5.requestFocus();
					bottoneSlot5.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneSlot4.isDisabled())
				{
					bottoneSlot4.requestFocus();
					bottoneSlot4.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneSlot3.isDisabled())
				{
					bottoneSlot3.requestFocus();
					bottoneSlot3.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else if(!bottoneSlot2.isDisabled())
				{
					bottoneSlot2.requestFocus();
					bottoneSlot2.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else 
				{
					bottoneSlot1.requestFocus();
					bottoneSlot1.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				suonoSelezione();
				Image image = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
				freccia.setImage(image);
			}
			else if(e.getCode() == KeyCode.DOWN)
			{
				bottoneSlot1.requestFocus();
				suonoSelezione();
				bottoneSlot1.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				Image image = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
				freccia.setImage(image);
			}
		}
		else if(e.getSource().equals(bottoneSlot1))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerCreaLiv c = loader.getController();
				c.setId(1);
				c.initialize(dati);
			}
			else if(e.getCode() == KeyCode.UP)
			{
				suonoSelezione();
				bottoneSlot1.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				frecciaButton.requestFocus();
				Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
				freccia.setImage(image);
			}
			else if(e.getCode() == KeyCode.DOWN)
			{
				bottoneSlot1.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneSlot2.isDisabled())
				{
					bottoneSlot2.requestFocus();
					bottoneSlot2.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneSlot2))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerCreaLiv c = loader.getController();
				c.setId(2);
				c.initialize(dati);
			}
			else if(e.getCode() == KeyCode.UP)
			{
				bottoneSlot2.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				bottoneSlot1.requestFocus();
				suonoSelezione();
				bottoneSlot1.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if(e.getCode() == KeyCode.DOWN)
			{
				bottoneSlot2.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneSlot3.isDisabled())
				{
					bottoneSlot3.requestFocus();
					bottoneSlot3.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneSlot3))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerCreaLiv c = loader.getController();
				c.setId(3);
				c.initialize(dati);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneSlot3.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				bottoneSlot2.requestFocus();
				suonoSelezione();
				bottoneSlot2.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneSlot3.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneSlot4.isDisabled())
				{
					bottoneSlot4.requestFocus();
					bottoneSlot4.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneSlot4))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerCreaLiv c = loader.getController();
				c.setId(4);
				c.initialize(dati);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneSlot4.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				bottoneSlot3.requestFocus();
				suonoSelezione();
				bottoneSlot3.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneSlot4.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneSlot5.isDisabled())
				{
					bottoneSlot5.requestFocus();
					bottoneSlot5.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneSlot5))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerCreaLiv c = loader.getController();
				c.setId(5);
				c.initialize(dati);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneSlot5.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				bottoneSlot4.requestFocus();
				bottoneSlot4.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneSlot5.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneSlot6.isDisabled())
				{
					bottoneSlot6.requestFocus();
					bottoneSlot6.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneSlot6))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerCreaLiv c = loader.getController();
				c.setId(6);
				c.initialize(dati);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneSlot6.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				bottoneSlot5.requestFocus();
				bottoneSlot5.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneSlot6.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneSlot7.isDisabled())
				{
					bottoneSlot7.requestFocus();
					bottoneSlot7.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneSlot7))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerCreaLiv c = loader.getController();
				c.setId(7);
				c.initialize(dati);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneSlot7.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				bottoneSlot6.requestFocus();
				bottoneSlot6.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneSlot7.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				if(!bottoneSlot8.isDisabled())
				{
					bottoneSlot8.requestFocus();
					bottoneSlot8.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				}
				else
				{
					frecciaButton.requestFocus();
					Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
					freccia.setImage(image);
				}
			}
		}
		else if(e.getSource().equals(bottoneSlot8))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerCreaLiv c = loader.getController();
				c.setId(8);
				c.initialize(dati);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				bottoneSlot8.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				bottoneSlot7.requestFocus();
				bottoneSlot7.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bottoneSlot8.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				suonoSelezione();
				frecciaButton.requestFocus();
				Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
				freccia.setImage(image);
			}
		}
	}
	
	/**
	 * Metodo abilita il bottone che permette di tornare alla schermata precedente
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
	 * Metodo che gestisce l'immagine del bottone che permette di tornare alla schermata precedente
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void enter(MouseEvent e)
	{
		Image im = new Image(getClass().getResourceAsStream("/images/background/white.png"));
		freccia.setImage(im);
	}
	
	/**
	 * Metodo che gestisce l'immagine del bottone che permette di tornare alla schermata precedente
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void exit(MouseEvent e)
	{
		Image image = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
		freccia.setImage(image);
	}
	
	/**
	 * Metodo che permette di aprire il livello 1 dell'editor
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void Lv1(ActionEvent e) throws IOException 
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerCreaLiv c = loader.getController();
		c.setId(1);
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di aprire il livello 2 dell'editor
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void Lv2(ActionEvent e) throws IOException 
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerCreaLiv c = loader.getController();
		c.setId(2);
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di aprire il livello 3 dell'editor
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void Lv3(ActionEvent e) throws IOException 
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerCreaLiv c = loader.getController();
		c.setId(3);
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di aprire il livello 4 dell'editor
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void Lv4(ActionEvent e) throws IOException 
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerCreaLiv c = loader.getController();
		c.setId(4);
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di aprire il livello 5 dell'editor
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void Lv5(ActionEvent e) throws IOException 
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerCreaLiv c = loader.getController();
		c.setId(5);
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di aprire il livello 6 dell'editor
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void Lv6(ActionEvent e) throws IOException 
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerCreaLiv c = loader.getController();
		c.setId(6);
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di aprire il livello 7 dell'editor
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void Lv7(ActionEvent e) throws IOException 
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerCreaLiv c = loader.getController();
		c.setId(7);
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di aprire il livello 8 dell'editor
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void Lv8(ActionEvent e) throws IOException 
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerCreaLiv c = loader.getController();
		c.setId(8);
		c.initialize(dati);
	}
}

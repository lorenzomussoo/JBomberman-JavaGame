package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Dato;
import model.Utente;
import view.AudioManager;

/**
 * La classe ControllerOpz è la classe che gestisce la schermata delle opzioni
 */
public class ControllerOpz {
	
	/**
	 * bottoni di scelta opzioni
	 */
	@FXML
	private Button frecciaButton, salvaButton;
	/**
	 * anchorpane
	 */
	@FXML
	private AnchorPane scenePane;
	/**
	 * contenitore immagine escape
	 */
	@FXML
	private ImageView freccia;
	/**
	 * etichette
	 */
	@FXML
	private Label title, com;
	/**
	 * gruppo di comandi del menù
	 */
	@FXML
	private Group comandiMenu;
	/**
	 * radio button menù
	 */
	@FXML
	private RadioButton mouse, tastiera;
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
	 * variabile che segna la modalità di gioco
	 */
	private boolean tastieraa;
	/**
	 * utente
	 */
	private Utente u;
	
	/**
	 * costruttore
	 */
	public ControllerOpz() {
		
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
		Font font30 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 30);
		Font font23 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 23);
		Font font17 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 17);
		Font font22 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 22);
		title.setFont(font30);
		com.setFont(font23);
		mouse.setFont(font17);
		tastiera.setFont(font17);
		salvaButton.setFont(font22);
		this.dati = dati;
		u = dati.getUtente();
		tastieraa = dati.getTastiera();
		if(tastieraa)
		{
			tastiera.setSelected(true);
			mouse.setSelected(false);
		}
		else
		{
			tastiera.setSelected(false);
			mouse.setSelected(true);
		}
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da tastiera
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void gestisciTastiera(KeyEvent e) throws IOException
	{
		if(e.getSource().equals(tastiera))
		{
			if (e.getCode() == KeyCode.UP)
			{
				Image im = new Image(getClass().getResourceAsStream("/images/background/white.png"));
				freccia.setImage(im);
				frecciaButton.requestFocus();
				suonoSelezione();
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				mouse.requestFocus();
				suonoSelezione();
			}
			else if (e.getCode() == KeyCode.ENTER)
			{
				tastiera.setSelected(true);
				mouse.setSelected(false);
			}
		}
		else if(e.getSource().equals(mouse))
		{
			if (e.getCode() == KeyCode.UP)
			{
				tastiera.requestFocus();
				suonoSelezione();
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				salvaButton.requestFocus();
				suonoSelezione();
				salvaButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			if (e.getCode() == KeyCode.ENTER)
			{
				tastiera.setSelected(false);
				mouse.setSelected(true);
			}
		}
		else if(e.getSource().equals(frecciaButton))
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
			else if (e.getCode() == KeyCode.DOWN)
			{
				Image im = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
				freccia.setImage(im);
				tastiera.requestFocus();
				suonoSelezione();
			}
			else if (e.getCode() == KeyCode.UP)
			{
				Image im = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
				freccia.setImage(im);
				salvaButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				salvaButton.requestFocus();
				suonoSelezione();
			}
		}
		else if(e.getSource().equals(salvaButton))
		{
			if(e.getCode() == KeyCode.ENTER)
			{
				if(tastiera.isSelected())
				{
					dati.setTastiera(true);
				}
				else
				{
					dati.setTastiera(false);
				}
				serializza();
			}
			else if(e.getCode() == KeyCode.UP)
			{
				salvaButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				mouse.requestFocus();
				suonoSelezione();
			}
			else if(e.getCode() == KeyCode.DOWN)
			{
				salvaButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				Image im = new Image(getClass().getResourceAsStream("/images/background/white.png"));
				freccia.setImage(im);
				frecciaButton.requestFocus();
				suonoSelezione();
			}
		}
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da mouse nella schermata di fine partita quando il cursore va sopra al bottone
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void enter(MouseEvent e)
	{
		if(e.getSource().equals(salvaButton))
		{
			salvaButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
		}
		else
		{
			Image im = new Image(getClass().getResourceAsStream("/images/background/white.png"));
			freccia.setImage(im);
		}
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da mouse nella schermata di fine partita quando il cursore esce dal bottone
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void exit(MouseEvent e)
	{
		if(e.getSource().equals(salvaButton))
		{
			salvaButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
		}
		else
		{
			Image image = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
			freccia.setImage(image);
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
	 * Metodo che permette di selezionare la modalità di gioco
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void sceltaComandi(ActionEvent e)
	{
		suonoSelezione();
		if (e.getSource().equals(tastiera))
		{
			tastiera.setSelected(true);
			mouse.setSelected(false);
		}
		else if (e.getSource().equals(mouse))
		{
			tastiera.setSelected(false);
			mouse.setSelected(true);
		}
	}
	
	/**
	 * Metodo che salva l'opzione scelta
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void save(ActionEvent e)
	{
		suonoSelezione();
		if(tastiera.isSelected())
		{
			dati.setTastiera(true);
		}
		else
		{
			dati.setTastiera(false);
		}
		serializza();
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
}
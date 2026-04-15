package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import view.AudioManager;
/**
 * La classe ControllerMS è la classe che gestisce il menù secondario
 */
public class ControllerMS {
	
	/**
	 * bottoni menù secondario
	 */
	@FXML
	private Button giocaButton, profiloButton, opzioniButton, editorButton, frecciaButton;
	/**
	 * anchorpane
	 */
	@FXML
	private AnchorPane scenePane;
	/**
	 * contenitori di immagini 
	 */
	@FXML
	private ImageView bomb1, bomb2, bomb3, bomb4, freccia;
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
	 * costruttore
	 */
	public ControllerMS() {
		
	}
	/**
	 * Metodo che riproduce l'audio associato al click dei bottoni
	 */
	public void suonoSelezione()
	{
		AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Title Screen Cursor2.wav");
	}
	
	/**
	 * Metodo che riproduce l'audio associato alla conferma
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
		Font font = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 22);
		giocaButton.setFont(font);
		profiloButton.setFont(font);
		opzioniButton.setFont(font);
		editorButton.setFont(font);
		this.dati = dati;
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da tastiera
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void gestisciTastiera(KeyEvent e) throws IOException
	{
		if(e.getSource().equals(giocaButton))
		{
			bomb1.setVisible(true);
			if (e.getCode() == KeyCode.UP) 
			{
				Image im = new Image(getClass().getResourceAsStream("/images/background/white.png"));
				freccia.setImage(im);
				frecciaButton.requestFocus();
				suonoSelezione();
				bomb1.setVisible(false);
				bomb2.setVisible(false);
				bomb3.setVisible(false);
				bomb4.setVisible(false);
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bomb1.setVisible(false);
				bomb3.setVisible(false);
				bomb4.setVisible(false);
				editorButton.requestFocus();
				suonoSelezione();
				bomb2.setVisible(true);
			}
			else if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Livelli.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerLiv c = loader.getController();
				c.initialize(dati);
			}
		}
		else if(e.getSource().equals(profiloButton))
		{
			bomb3.setVisible(true);
			if (e.getCode() == KeyCode.UP) 
			{
				bomb4.setVisible(false);
				bomb2.setVisible(false);
				bomb3.setVisible(false);
				editorButton.requestFocus();
				suonoSelezione();
				bomb2.setVisible(true);
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bomb2.setVisible(false);
				bomb1.setVisible(false);
				bomb3.setVisible(false);
				opzioniButton.requestFocus();
				suonoSelezione();
				bomb4.setVisible(true);
			}
			else if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Profilo.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerProf c = loader.getController();
				c.initialize(dati);
			}
		}
		else if(e.getSource().equals(opzioniButton))
		{
			bomb4.setVisible(true);
			if (e.getCode() == KeyCode.UP) 
			{
				bomb1.setVisible(false);
				bomb2.setVisible(false);
				bomb4.setVisible(false);
				profiloButton.requestFocus();
				suonoSelezione();
				bomb3.setVisible(true);
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				Image im = new Image(getClass().getResourceAsStream("/images/background/white.png"));
				freccia.setImage(im);
				frecciaButton.requestFocus();
				suonoSelezione();
				bomb1.setVisible(false);
				bomb2.setVisible(false);
				bomb3.setVisible(false);
				bomb4.setVisible(false);
			}
			else if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Opzioni.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerOpz c = loader.getController();
				c.initialize(dati);
			}
		}
		else if(e.getSource().equals(editorButton))
		{
			bomb2.setVisible(true);
			if (e.getCode() == KeyCode.UP) 
			{
				bomb4.setVisible(false);
				bomb2.setVisible(false);
				bomb3.setVisible(false);
				giocaButton.requestFocus();
				suonoSelezione();
				bomb1.setVisible(true);
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bomb1.setVisible(false);
				bomb2.setVisible(false);
				bomb4.setVisible(false);
				profiloButton.requestFocus();
				suonoSelezione();
				bomb3.setVisible(true);
			}
			else if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Editor.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerEditor c = loader.getController();
				c.initialize(dati);
			}
		}
		else if(e.getSource().equals(frecciaButton))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Logout");
				alert.setContentText("Sei sicuro di voler tornare indietro? \nDovrai rieffetturare il login");
				if(alert.showAndWait().get() == ButtonType.OK)
				{
					suonoSelezione();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuPrincipale.fxml"));
					Parent root = loader.load();
					Node n = (Node) e.getSource();
					stage = (Stage) n.getScene().getWindow();
					scene = new Scene(root,680,605);
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
					ControllerMP c = loader.getController();
					c.initialize();
				}
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				Image im = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
				freccia.setImage(im);
				bomb2.setVisible(false);
				bomb3.setVisible(false);
				bomb4.setVisible(false);
				giocaButton.requestFocus();
				suonoSelezione();
				bomb1.setVisible(true);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				Image im = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
				freccia.setImage(im);
				bomb2.setVisible(false);
				bomb3.setVisible(false);
				bomb1.setVisible(false);
				opzioniButton.requestFocus();
				suonoSelezione();
				bomb4.setVisible(true);
			}
		}
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da mouse nella schermata di fine partita quando il cursore va sopra al bottone
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void enter(MouseEvent e)
	{
		if(e.getSource().equals(giocaButton))
		{
			bomb1.setVisible(true);
		}
		else if(e.getSource().equals(editorButton))
		{
			bomb2.setVisible(true);
		}
		else if(e.getSource().equals(profiloButton))
		{
			bomb3.setVisible(true);
		}
		else if(e.getSource().equals(opzioniButton))
		{
			bomb4.setVisible(true);
		}
		else if(e.getSource().equals(frecciaButton))
		{
			Image im = new Image(getClass().getResourceAsStream("/images/background/white.png"));
			freccia.setImage(im);
		}
		else
		{
			bomb1.setVisible(false);
			bomb2.setVisible(false);
			bomb3.setVisible(false);
			bomb4.setVisible(false);
		}
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da mouse nella schermata di fine partita quando il cursore esce dal bottone
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void exit(MouseEvent e)
	{
		if(e.getSource().equals(giocaButton))
		{
			bomb1.setVisible(false);
		}
		else if(e.getSource().equals(editorButton))
		{
			bomb2.setVisible(false);
		}
		else if(e.getSource().equals(profiloButton))
		{
			bomb3.setVisible(false);
		}
		else if(e.getSource().equals(opzioniButton))
		{
			bomb4.setVisible(false);
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
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setContentText("Sei sicuro di voler tornare indietro? \nDovrai rieffetturare il login");
		if(alert.showAndWait().get() == ButtonType.OK)
		{
			suonoSelezione();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuPrincipale.fxml"));
			Parent root = loader.load();
			Node n = (Node) e.getSource();
			stage = (Stage) n.getScene().getWindow();
			scene = new Scene(root,680,605);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			ControllerMP c = loader.getController();
			c.initialize();
		}
	}
	
	/**
	 * Metodo che permette di accedere ai livelli e giocare un livello
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void gioca(ActionEvent e) throws IOException
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Livelli.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerLiv c = loader.getController();
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di accedere all'editor e giocare un livello creato
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void editor(ActionEvent e) throws IOException
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Editor.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerEditor c = loader.getController();
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di accedere alle opzioni
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void opzioni(ActionEvent e) throws IOException
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Opzioni.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerOpz c = loader.getController();
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di accedere al profilo
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void profilo(ActionEvent e) throws IOException
	{
		suonoInizio();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Profilo.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerProf c = loader.getController();
		c.initialize(dati);
	}
}

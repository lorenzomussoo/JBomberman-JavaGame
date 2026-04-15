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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
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
 * La classe ControllerMP è la classe che gestisce il menù principale
 */
public class ControllerMP {
	
	/**
	 * bottoni prima schermata 
	 */
	@FXML
	private Button logoutButton, caricaPartita, nuovaPartita, okay1, okay2;
	/**
	 * anchorpane
	 */
	@FXML
	private AnchorPane scenePane;
	/**
	 * gruppo di bottoni
	 */
	@FXML
	private Group carica, nuova;
	/**
	 * campo di testo
	 */
	@FXML
	private TextField nick1, nick2;
	/**
	 * contenitori immagini
	 */
	@FXML
	private ImageView bomb1, bomb2, bomb3;
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
	public ControllerMP() {
		
	}
	/**
	 * Metodo che al cambio di schermata inizializza alcuni degli elementi di una schermata
	 */
	@FXML
	public void initialize()
	{
		Font font22 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 22);
		Font font13 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 13);
		logoutButton.setFont(font22);
		caricaPartita.setFont(font22);
		nuovaPartita.setFont(font22);
		okay1.setFont(font13);
		okay2.setFont(font13);
		dati = new Dato();
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
	 * Metodo che gestisce la selezione dei bottoni da mouse nella schermata di fine partita quando il cursore va sopra al bottone
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void enter(MouseEvent e)
	{
		if(e.getSource().equals(nuovaPartita))
		{
			bomb1.setVisible(true);
		}
		else if(e.getSource().equals(caricaPartita))
		{
			bomb2.setVisible(true);
		}
		else if(e.getSource().equals(logoutButton))
		{
			bomb3.setVisible(true);
		}
		else
		{
			bomb1.setVisible(false);
			bomb2.setVisible(false);
			bomb3.setVisible(false);
		}
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da mouse nella schermata di fine partita quando il cursore esce dal bottone
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void exit(MouseEvent e)
	{
		if(e.getSource().equals(nuovaPartita))
		{
			bomb1.setVisible(false);
		}
		else if(e.getSource().equals(caricaPartita))
		{
			bomb2.setVisible(false);
		}
		else
		{
			bomb3.setVisible(false);
		}
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da tastiera
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void gestioneTastiera(KeyEvent e) throws IOException
	{
		if(e.getSource().equals(nuovaPartita))
		{
			bomb1.setVisible(true);
			if (e.getCode() == KeyCode.UP) 
			{
				bomb1.setVisible(false);
				bomb2.setVisible(false);
				logoutButton.requestFocus();
				suonoSelezione();
				bomb3.setVisible(true);
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				bomb1.setVisible(false);
				bomb3.setVisible(false);
				caricaPartita.requestFocus();
				suonoSelezione();
				bomb2.setVisible(true);
			}
			else if (e.getCode() == KeyCode.ENTER)
			{
				if(nuova.isVisible())
				{
					nuova.setVisible(false);
				}
				else
				{
					carica.setVisible(false);
					nuova.setVisible(true);
				}
			}
			else if (e.getCode() == KeyCode.RIGHT)
			{
				nick1.requestFocus();
			}
		}
		else if(e.getSource().equals(caricaPartita))
		{
			bomb2.setVisible(true);
			if (e.getCode() == KeyCode.UP) 
			{
				bomb2.setVisible(false);
				bomb3.setVisible(false);
				nuovaPartita.requestFocus();
				suonoSelezione();
				bomb1.setVisible(true);
			}
			else if (e.getCode() == KeyCode.DOWN) 
			{
				bomb1.setVisible(false);
				bomb2.setVisible(false);
				logoutButton.requestFocus();
				suonoSelezione();
				bomb3.setVisible(true);
			}
			else if (e.getCode() == KeyCode.ENTER)
			{
				if(carica.isVisible())
				{
					carica.setVisible(false);
				}
				else
				{
					nuova.setVisible(false);
					carica.setVisible(true);
				}
			}
			else if (e.getCode() == KeyCode.RIGHT)
			{
				nick2.requestFocus();
			}
		}
		else if(e.getSource().equals(logoutButton))
		{
			bomb3.setVisible(true);
			if (e.getCode() == KeyCode.UP) 
			{
				bomb1.setVisible(false);
				bomb3.setVisible(false);
				caricaPartita.requestFocus();
				suonoSelezione();
				bomb2.setVisible(true);
			}
			else if (e.getCode() == KeyCode.DOWN) 
			{
				bomb2.setVisible(false);
				bomb3.setVisible(false);
				nuovaPartita.requestFocus();
				suonoSelezione();
				bomb1.setVisible(true);
			}
			else if (e.getCode() == KeyCode.ENTER)
			{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Logout");
				alert.setContentText("Sei sicuro di voler uscire?");
				if(alert.showAndWait().get() == ButtonType.OK)
				{
					stage = (Stage) scenePane.getScene().getWindow();
					stage.close();
				}
			}
	    }
		else if(e.getSource().equals(nuova))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				if (!nick1.isFocused())
				{
					nick1.requestFocus();
				}
				else
				{
					if(nick1.getLength() != 0)
					{
						File f = new File("./src/saves/" + nick1.getText().trim() + ".bomb");
						if(f.exists())
						{
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("Nickname!");
							alert.setContentText("Un file con questo nickname esiste già! \nVuoi sovrascrivere il file?");
							if(alert.showAndWait().get() == ButtonType.OK)
							{
								suonoInizio();
								Utente u = new Utente(nick1.getText().trim());
								dati.setUtente(u);
								serializza();
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
						}
						else
						{
							suonoInizio();
							Utente u = new Utente(nick1.getText().trim());
							dati.setUtente(u);
							serializza();
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
					}
					else
					{
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Nickname!");
						alert.setContentText("Devi inserire un nickname valido!");
						alert.showAndWait().get();
					}
				}
			}
			else if (e.getCode() == KeyCode.ESCAPE)
			{
				nuovaPartita.requestFocus();
			}
		}
		else if(e.getSource().equals(carica))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				if (!nick2.isFocused())
				{
					nick2.requestFocus();
				}
				else
				{
					if (nick2.getLength() != 0)
					{
						File f = new File ("./src/saves/" + nick2.getText().trim() + ".bomb");
						if(!f.exists())
						{
							Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("Nickname!");
							alert.setContentText("Devi inserire un nickname valido!");
							alert.showAndWait().get();
						}
						else
						{
							suonoInizio();
							Dato dati2 = deserializza(f.getPath());
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuSecondario.fxml"));
							Parent root = loader.load();
							Node n = (Node) e.getSource();
							stage = (Stage) n.getScene().getWindow();
							scene = new Scene(root,680,605);
							stage.setScene(scene);
							stage.setResizable(false);
							stage.show();
							ControllerMS c = loader.getController();
							c.initialize(dati2);
						}
					}
				}
			}
			else if (e.getCode() == KeyCode.ESCAPE)
			{
				caricaPartita.requestFocus();
			}
		}
	}
	
	/**
	 * Metodo che permette di eseguire il logout dal gioco
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void logout(ActionEvent e)
	{
		suonoSelezione();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setContentText("Sei sicuro di voler uscire?");
		if(alert.showAndWait().get() == ButtonType.OK)
		{
			stage = (Stage) scenePane.getScene().getWindow();
			stage.close();
		}
	}
	
	/**
	 * Metodo che permette la visualizzazione della barra per il caricamento dei dati
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void caricaDati(ActionEvent e)
	{
		suonoSelezione();
		if(carica.isVisible())
		{
			carica.setVisible(false);
		}
		else
		{
			nuova.setVisible(false);
			carica.setVisible(true);
		}
	}
	
	/**
	 * Metodo che permette la visualizzazione della barra per la creazione di nuovi dati
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void nuoviDati(ActionEvent e)
	{
		suonoSelezione();
		if(nuova.isVisible())
		{
			nuova.setVisible(false);
		}
		else
		{
			carica.setVisible(false);
			nuova.setVisible(true);
		}
	}
	
	/**
	 * Metodo che permette l'accesso al gioco
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void inizia(ActionEvent e) throws IOException
	{
		if(nick1.getLength() != 0 || nick2.getLength() != 0)
		{
			if(e.getSource().equals(okay1))
			{
				File f = new File("./src/saves/" + nick1.getText().trim() + ".bomb");
				if(f.exists())
				{
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Nickname!");
					alert.setContentText("Un file con questo nickname esiste già! \nVuoi sovrascrivere il file?");
					if(alert.showAndWait().get() == ButtonType.OK)
					{
						suonoInizio();
						Utente u = new Utente(nick1.getText().trim());
						dati.setUtente(u);
						serializza();
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
				}
				else
				{
					suonoInizio();
					Utente u = new Utente(nick1.getText().trim());
					dati.setUtente(u);
					serializza();
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
			}
			else
			{
				if (nick2.getLength() != 0)
				{
					File f = new File ("./src/saves/" + nick2.getText().trim() + ".bomb");
					if(!f.exists())
					{
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Nickname!");
						alert.setContentText("Devi inserire un nickname valido!");
						alert.showAndWait().get();
					}
					else
					{
						suonoInizio();
						Dato dati2 = deserializza(f.getPath());
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuSecondario.fxml"));
						Parent root = loader.load();
						Node n = (Node) e.getSource();
						stage = (Stage) n.getScene().getWindow();
						scene = new Scene(root,680,605);
						stage.setScene(scene);
						stage.setResizable(false);
						stage.show();
						ControllerMS c = loader.getController();
						c.initialize(dati2);
					}
				}
			}
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Nickname!");
			alert.setContentText("Devi inserire un nickname valido!");
			alert.showAndWait().get();
		}
	}
	
	/**
	 * Metodo che serializza i dati ed esegue il salvataggio
	 */
	public void serializza()
	{
		ObjectOutputStream out = null;
		try {
			 FileOutputStream fos = new FileOutputStream("./src/saves/"+nick1.getText().trim()+".bomb");
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

	/**
	 * Metodo che restituisce i dati Utente
	 * @return dati Utente
	 */
	public Dato getDati() {
		return dati;
	}
	
	/**
	 * Metodo che imposta i dati utente
	 * @param dati dati utente
	 */
	public void setElenco(Dato dati) {
		this.dati = dati;
	}
}
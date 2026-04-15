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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Dato;
import model.Utente;
import view.AudioManager;

/**
 * La classe ControllerProf è la classe che gestisce la schermata del profilo
 */
public class ControllerProf {
	
	/**
	 * bottone scelte nel profilo
	 */
	@FXML
	private Button statisticheButton, cambiaNickButton, frecciaSinistra, frecciaDestra, salvaButton, frecciaButton;
	/**
	 * contenitori immagini
	 */
	@FXML
	private ImageView avatar, freccia, destra, sinistra;
	/**
	 * campo di testo
	 */
	@FXML
	private TextField nickname;
	/**
	 * gruppo con campo
	 */
	@FXML
	private Group field;
	/**
	 * etichetta titolo
	 */
	@FXML
	private Label title;
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
	 * int identificatore avatar
	 */
	private int avat;
	/**
	 * immagine avatar bianco
	 */
	private Image imWhite = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/white character.png")); 
	/**
	 * immagine avatar blu
	 */
	private Image imBlue = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/blue/blue character.png"));
	/**
	 * immagine avatar nero
	 */
	private Image imBlack = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/black/black character.png"));
	/**
	 * immagine avatar rosso
	 */
	private Image imRed = new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/red/red character.png"));

	/**
	 * costruttore
	 */
	public ControllerProf() {
		
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
		Font font15 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 15);
		Font font22 = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 22);
		title.setFont(font30);
		statisticheButton.setFont(font22);
		cambiaNickButton.setFont(font15);
		salvaButton.setFont(font22);
		this.dati = dati;
		this.u = dati.getUtente();
		this.avat = u.getAvatar();
		nickname.setText(u.getNickname());
		if(avat == 1)
		{
			avatar.setImage(imWhite);
		}
		else if(avat == 4)
		{
			avatar.setImage(imBlue);
		}
		else if(avat == 3)
		{
			avatar.setImage(imRed);
		}
		else if(avat == 2)
		{
			avatar.setImage(imBlack);
		}
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da tastiera
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void gestisciTastiera(KeyEvent e) throws IOException
	{
		if(e.getSource().equals(frecciaButton))
		{
			suonoSelezione();
			if (e.getCode() == KeyCode.ENTER)
			{
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
				salvaButton.requestFocus();
				salvaButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				Image image = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
				freccia.setImage(image);
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				cambiaNickButton.requestFocus();
				cambiaNickButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				Image image = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
				freccia.setImage(image);
			}
		}
		else if(e.getSource().equals(cambiaNickButton))
		{
			suonoSelezione();
			if (e.getCode() == KeyCode.ENTER)
			{
				if(field.isVisible())
				{
					field.setVisible(false);
				}
				else
				{
					field.setVisible(true);
					nickname.requestFocus();
				}
			}
			else if (e.getCode() == KeyCode.UP)
			{
				cambiaNickButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				frecciaButton.requestFocus();
				Image image = new Image(getClass().getResourceAsStream("/images/background/white.png"));
				freccia.setImage(image);
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				cambiaNickButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				frecciaSinistra.requestFocus();
				frecciaSinistra.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
		}
		else if(e.getSource().equals(field))
		{
			suonoSelezione();
			if (e.getCode() == KeyCode.UP)
			{
				nickname.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				cambiaNickButton.requestFocus();
				cambiaNickButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				nickname.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				frecciaSinistra.requestFocus();
				frecciaSinistra.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.ESCAPE)
			{
				cambiaNickButton.requestFocus();
				cambiaNickButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
		}
		else if(e.getSource().equals(frecciaSinistra))
		{
			suonoSelezione();
			if (e.getCode() == KeyCode.ENTER)
			{
				if(avat == 1)
				{
					avatar.setImage(imBlue);
					avat = 4;
				}
				else if(avat == 4)
				{
					avatar.setImage(imRed);
					avat = 3;
				}
				else if(avat == 3)
				{
					avatar.setImage(imBlack);
					avat = 2;
				}
				else if(avat == 2)
				{
					avatar.setImage(imWhite);
					avat = 1;
				}
			}
			else if (e.getCode() == KeyCode.UP)
			{
				frecciaSinistra.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				cambiaNickButton.requestFocus();
				cambiaNickButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				frecciaSinistra.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				frecciaDestra.requestFocus();
				frecciaDestra.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
		}
		else if(e.getSource().equals(frecciaDestra))
		{
			suonoSelezione();
			if (e.getCode() == KeyCode.ENTER)
			{
				if(avat == 1)
				{
					avatar.setImage(imBlack);
					avat = 2;
				}
				else if(avat == 2)
				{
					avatar.setImage(imRed);
					avat = 3;
				}
				else if(avat == 3)
				{
					avatar.setImage(imBlue);
					avat = 4;
				}
				else if(avat == 4)
				{
					avatar.setImage(imWhite);
					avat = 1;
				}
			}
			else if (e.getCode() == KeyCode.UP)
			{
				frecciaDestra.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				frecciaSinistra.requestFocus();
				frecciaSinistra.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				frecciaDestra.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
				statisticheButton.requestFocus();
				statisticheButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
		}
		else if(e.getSource().equals(statisticheButton))
		{
			suonoSelezione();
			if (e.getCode() == KeyCode.ENTER)
			{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Statistiche.fxml"));
				Parent root = loader.load();
				Node n = (Node) e.getSource();
				stage = (Stage) n.getScene().getWindow();
				scene = new Scene(root,680,605);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ControllerStat c = loader.getController();
				c.initialize(dati);
			}
			else if (e.getCode() == KeyCode.UP)
			{
				statisticheButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				frecciaDestra.requestFocus();
				frecciaDestra.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				statisticheButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				salvaButton.requestFocus();
				salvaButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
		}
		else if(e.getSource().equals(salvaButton))
		{
			suonoSelezione();
			if (e.getCode() == KeyCode.ENTER)
			{
				dati.getUtente().setNickname(nickname.getText());
				dati.getUtente().setAvatar(avat);
				serializza();
			}
			else if (e.getCode() == KeyCode.UP)
			{
				salvaButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				statisticheButton.requestFocus();
				statisticheButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.DOWN)
			{
				salvaButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				frecciaButton.requestFocus();
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
		if(e.getSource().equals(salvaButton))
		{
			salvaButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
		}
		else if(e.getSource().equals(statisticheButton))
		{
			statisticheButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
		}
		else if(e.getSource().equals(cambiaNickButton))
		{
			cambiaNickButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
		}
		else if(e.getSource().equals(frecciaSinistra))
		{
			frecciaSinistra.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
		}
		else if(e.getSource().equals(frecciaDestra))
		{
			frecciaDestra.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
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
		else if(e.getSource().equals(statisticheButton))
		{
			statisticheButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
		}
		else if(e.getSource().equals(cambiaNickButton))
		{
			cambiaNickButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
		}
		else if(e.getSource().equals(frecciaSinistra))
		{
			frecciaSinistra.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
		}
		else if(e.getSource().equals(frecciaDestra))
		{
			frecciaDestra.setStyle("-fx-border-color: TRANSPARENT; -fx-background-color: TRANSPARENT");
		}
		else
		{
			Image image = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
			freccia.setImage(image);
		}
	}

	/**
	 * Metodo che rende visibile il campo in cui scrivere per cambiare nickname
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void nick(ActionEvent e)
	{
		suonoSelezione();
		if(field.isVisible())
		{
			field.setVisible(false);
		}
		else
		{
			field.setVisible(true);
		}
	}
	
	/**
	 * Metodo che porta alla schermata delle statistiche
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void stat(ActionEvent e) throws IOException
	{
		suonoSelezione();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Statistiche.fxml"));
		Parent root = loader.load();
		Node n = (Node) e.getSource();
		stage = (Stage) n.getScene().getWindow();
		scene = new Scene(root,680,605);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		ControllerStat c = loader.getController();
		c.initialize(dati);
	}
	
	/**
	 * Metodo che permette di cambiare l'avatar selezionato
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void cambiaAvatar(ActionEvent e)
	{
		suonoSelezione();
		if(e.getSource().equals(frecciaDestra))
		{
			if(avat == 1)
			{
				avatar.setImage(imBlack);
				avat = 2;
			}
			else if(avat == 2)
			{
				avatar.setImage(imRed);
				avat = 3;
			}
			else if(avat == 3)
			{
				avatar.setImage(imBlue);
				avat = 4;
			}
			else if(avat == 4)
			{
				avatar.setImage(imWhite);
				avat = 1;
			}
		}
		else
		{
			if(avat == 1)
			{
				avatar.setImage(imBlue);
				avat = 4;
			}
			else if(avat == 4)
			{
				avatar.setImage(imRed);
				avat = 3;
			}
			else if(avat == 3)
			{
				avatar.setImage(imBlack);
				avat = 2;
			}
			else if(avat == 2)
			{
				avatar.setImage(imWhite);
				avat = 1;
			}
		}
	}
	
	/**
	 * Metodo che salva i cambiamenti effettuati
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void save(ActionEvent e)
	{
		suonoSelezione();
		dati.getUtente().setNickname(nickname.getText());
		dati.getUtente().setAvatar(avat);
		serializza();
	}
	
	/**
	 * Metodo che serializza i dati ed esegue il salvataggio
	 */
	public void serializza()
	{
		ObjectOutputStream out = null;
		try {
			 FileOutputStream fos = new FileOutputStream("./src/saves/"+nickname.getText().trim()+".bomb");
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

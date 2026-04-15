package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Boss;
import model.Dato;
import model.Livello;
import model.Nemico;
import model.Utente;
import view.AudioManager;

/**
 * La classe ControllerEnd gestisce le schermate di fine partita
 */
public class ControllerEnd {
	
	/**
	 * bottoni di fine partita
	 */
	@FXML
	private Button continuaButton, riprovaButton, livelliButton;
	/**
	 * contenitore immagine esito
	 */
	@FXML
	private ImageView vintoPerso;
	/**
	 * pane
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
	 * varabile che indica l'esito della partita
	 */
	private boolean vittoria;
	/**
	 * livello
	 */
	private int level;
	/**
	 * dati di gioco
	 */
	private Dato dati;
	/**
	 * utente
	 */
	private Utente u;
	/**
	 * array con lo stato dei livelli
	 */
	private boolean[] visibile;
	/**
	 * schermata
	 */
	private int schermata;
	/**
	 * id dello slot
	 */
	private int id;
	/**
	 *lista di nemici
	 */
	private List<Nemico> nemici;
	/**
	 * punteggio
	 */
	private int punteggio;
	
	/**
	 * costruttore
	 */
	public ControllerEnd() {
		
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
		Font font = Font.loadFont(getClass().getResourceAsStream("/controller/myFont.TTF"), 22);
		continuaButton.setFont(font);
		riprovaButton.setFont(font);
		livelliButton.setFont(font);
		Image vinto = new Image(getClass().getResourceAsStream("/images/text/victory.png"));
		Image perso = new Image(getClass().getResourceAsStream("/images/text/gameover.png"));
		this.dati = dati;
		u = dati.getUtente();
		visibile = dati.getVisibile();
		boolean[] livSuperati = dati.getLivSuperati();
		if (schermata == 1)
		{
			vittoria = livSuperati[level-1];
		}
		if(vittoria == false)
		{
			int p = dati.getUtente().getPartitePerse();
			dati.getUtente().setPartitePerse(p+1);
			vintoPerso.setImage(perso);
			riprovaButton.setVisible(true);
			riprovaButton.requestFocus();
			livelliButton.setVisible(true);
			AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Pause_GameOver2.wav");
		}
		else 
		{
			int v = dati.getUtente().getPartiteVinte();
			dati.getUtente().setPartiteVinte(v+1);
			dati.setVisibile(visibile);
			dati.getUtente().setPunteggio(dati.getUtente().getPunteggio() + punteggio);
			vintoPerso.setImage(vinto);
			continuaButton.setVisible(true);
			continuaButton.requestFocus();
			AudioManager.getInstance().play("./data/Super Bomberman Sound Effects/Stage Clear.wav");
		}
		int partite = dati.getUtente().getPartiteGiocate();
		dati.getUtente().setPartiteGiocate(partite+1);
		serializza(); 
		File f = new File("./src/saves/" + u.getNickname() + ".bomb");
		dati = deserializza(f.getPath());
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da mouse nella schermata di fine partita quando il cursore va sopra al bottone
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void enter(MouseEvent e)
	{
		if(e.getSource().equals(continuaButton) || e.getSource().equals(riprovaButton))
		{
			continuaButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			riprovaButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
		}
		else 
		{
			livelliButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
		}
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da mouse nella schermata di fine partita quando il cursore esce dal bottone
	 * @param e evento che scatena l'esecuzione della funzione
	 */
	public void exit(MouseEvent e)
	{
		if(e.getSource().equals(continuaButton) || e.getSource().equals(riprovaButton))
		{
			continuaButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
			riprovaButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
		}
		else 
		{
			livelliButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
		}
	}
	
	/**
	 * Metodo che abilita il bottone che permette di tornare ai livelli dopo una partita
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws IOException eccezione
	 */
	public void livelli(ActionEvent e) throws IOException
	{
		suonoInizio();
		if (schermata == 1)
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Livelli.fxml"));
			Parent root = loader.load();
			Node n = (Node) e.getSource();
			stage = (Stage) n.getScene().getWindow();
			scene = new Scene(root,680,605);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			ControllerLiv c = loader.getController();
			c.setVisibile(visibile);
			c.initialize(dati);
		}
		else
		{
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
	
	/**
	 * Metodo che abilita il bottone che permette di riprovare il livello in caso di sconfitta
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception eccezione
	 */
	public void riprova(ActionEvent e) throws Exception
	{
		suonoInizio();
		if (schermata == 1)
		{
			Livello liv = new Livello();
			liv.setLevel(level);
			liv.setKeyboard(true);
			List<Nemico> onlyBoss = nemici.stream()          
					.filter(n -> n instanceof Boss)  
					.collect(Collectors.toList());  
			if(onlyBoss.isEmpty())
			{
				liv.setBoss(false);
			}
			else
			{
				liv.setBoss(true);
			}
			liv.setColor(dati.getUtente().getAvatar());
			liv.setNemici(nemici);
			liv.setDati(dati);
			Node n = (Node) e.getSource();
			stage = (Stage) n.getScene().getWindow();
			stage.setScene(n.getScene());
			liv.start(stage, dati, visibile);
		}
		else
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
			Parent root = loader.load();
			Node n = (Node) e.getSource();
			stage = (Stage) n.getScene().getWindow();
			scene = new Scene(root,680,605);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			ControllerCreaLiv c = loader.getController();
			c.setId(id);
			c.initialize(dati);
		}
	}
	
	/**
	 * Metodo che gestisce la selezione dei bottoni da tastiera
	 * @param e evento che scatena l'esecuzione della funzione
	 * @throws Exception eccezione
	 */
	public void gestisciTastiera(KeyEvent e) throws Exception
	{
		if(e.getSource().equals(continuaButton))
		{
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				continuaButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				if (schermata == 1)
				{
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Livelli.fxml"));
					Parent root = loader.load();
					Node n = (Node) e.getSource();
					stage = (Stage) n.getScene().getWindow();
					scene = new Scene(root,680,605);
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
					ControllerLiv c = loader.getController();
					c.setVisibile(visibile);
					c.initialize(dati);
				}
				else
				{
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
		}
		else if(e.getSource().equals(riprovaButton))
		{
			if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) 
			{
				riprovaButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
				livelliButton.requestFocus();
				suonoSelezione();
				livelliButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				if (schermata == 1)
				{
					Livello liv = new Livello();
					liv.setLevel(level);
					liv.setKeyboard(true);
					List<Nemico> onlyBoss = nemici.stream()          
							.filter(n -> n instanceof Boss)  
							.collect(Collectors.toList());  
					if(onlyBoss.isEmpty())
					{
						liv.setBoss(false);
					}
					else
					{
						liv.setBoss(true);
					}
					liv.setColor(dati.getUtente().getAvatar());
					liv.setNemici(nemici);
					liv.setDati(dati);
					Node n = (Node) e.getSource();
					stage = (Stage) n.getScene().getWindow();
					stage.setScene(n.getScene());
					liv.start(stage, dati, visibile);
				}
				else
				{
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CreaLivello.fxml"));
					Parent root = loader.load();
					Node n = (Node) e.getSource();
					stage = (Stage) n.getScene().getWindow();
					scene = new Scene(root,680,605);
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
					ControllerCreaLiv c = loader.getController();
					c.setId(id);
					c.initialize(dati);
				}
			}
		}
		else if(e.getSource().equals(livelliButton))
		{
			if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) 
			{
				riprovaButton.setStyle("-fx-border-color: BLUE; -fx-background-color: TRANSPARENT");
				riprovaButton.requestFocus();
				suonoSelezione();
				livelliButton.setStyle("-fx-border-color: BLACK; -fx-background-color: TRANSPARENT");
			}
			else if (e.getCode() == KeyCode.ENTER)
			{
				suonoInizio();
				if (schermata == 1)
				{
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Livelli.fxml"));
					Parent root = loader.load();
					Node n = (Node) e.getSource();
					stage = (Stage) n.getScene().getWindow();
					scene = new Scene(root,680,605);
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
					ControllerLiv c = loader.getController();
					c.setVisibile(visibile);
					c.initialize(dati);
				}
				else
				{
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
		}
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

	/**
	 * Metodo che restituisce il livello scelto
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
	 * Metodo che restituisce la lista di stati dei livelli 
	 * @return lista di stati dei livelli 
	 */
	public boolean[] getVisibile() {
		return visibile;
	}

	/**
	 * Metodo che imposta la lista di stati dei livelli 
	 * @param visibile lista di stati dei livelli 
	 */
	public void setVisibile(boolean[] visibile) {
		this.visibile = visibile;
	}

	/**
	 * Metodo che restituisce la variabile che ricorda da che schermata è stato aperto il livello
	 * @return variabile che ricorda da che schermata è stato aperto il livello
	 */
	public int getSchermata() {
		return schermata;
	}

	/**
	 * Metodo che imposta la variabile che ricorda da che schermata è stato aperto il livello
	 * @param schermata variabile che ricorda da che schermata è stato aperto il livello
	 */
	public void setSchermata(int schermata) {
		this.schermata = schermata;
	}

	/**
	 * Metodo che restituisce l'Id del livello selezionato nell'editor
	 * @return Id del livello selezionato nell'editor
	 */
	public int getId() {
		return id;
	}

	/**
	 * Metodo che imposta l'Id del livello selezionato nell'editor
	 * @param id Id del livello selezionato nell'editor
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Metodo che restituisce la variabile che indica se la partita è vinta
	 * @return variabile che indica se la partita è vinta
	 */
	public boolean isVittoria() {
		return vittoria;
	}

	/**
	 * Metodo che imposta la variabile che indica se la partita è vinta
	 * @param vittoria variabile che indica se la partita è vinta
	 */
	public void setVittoria(boolean vittoria) {
		this.vittoria = vittoria;
	}

	/**
	 * Metodo che restituisce una lista di nemici
	 * @return lista di nemici
	 */
	public List<Nemico> getNemici() {
		return nemici;
	}

	/**
	 * Metodo che imposta una lista di nemici
	 * @param nemici lista di nemici
	 */
	public void setNemici(List<Nemico> nemici) {
		this.nemici = nemici;
	}

	/**
	 * Metodo che restituisce il punteggio ottenuto nel livello
	 * @return punteggio ottenuto
	 */
	public int getPunteggio() {
		return punteggio;
	}

	/**
	 * Metodo che imposta il punteggio ottenuto nel livello
	 * @param punteggio punteggio ottenuto
	 */
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
}
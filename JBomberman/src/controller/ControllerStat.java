package controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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
import model.Utente;
import view.AudioManager;

/**
 * La classe ControllerStat è la classe che gestisce la schermata che mostra le statistiche
 */
public class ControllerStat {
	
	/**
	 * anchorpane
	 */
	@FXML
    private AnchorPane scenePane;
	/**
	 * grafico a torta
	 */
	@FXML
    private PieChart chart;
	/**
	 * etichette
	 */
	@FXML
    private Label numPartite, title, punti;
	/**
	 * bottone escape
	 */
	@FXML
    private Button frecciaButton;
	/**
	 * contenitore immagine escape
	 */
	@FXML
    private ImageView freccia;
	/**
	 * partite vinte
	 */
    private int v = 0;
    /**
	 * pertite perse
	 */
    private int p = 0;
    /**
	 * totale partite
	 */
    private int tot = 0;
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
    private boolean[] elencoLiv = {false, false, false, false, false, false, false, false};
    /**
	 * stage
	 */
    private Stage stage;
    /**
	 * scena
	 */
    private Scene scene;
	
    /**
	 * costruttore
	 */
	public ControllerStat() {
		
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
		title.setFont(font30);
		this.dati = dati;
		u = dati.getUtente();
		tot = u.getPartiteGiocate();
		v = u.getPartiteVinte();
		p = u.getPartitePerse();
		elencoLiv = dati.getLivSuperati();
		numPartite.setText(""+tot);
		punti.setText(""+u.getPunteggio());
		creaDiagramma();
		frecciaButton.requestFocus();
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
			if (e.getCode() == KeyCode.ENTER)
			{
				suonoSelezione();
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
			else if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN)
			{
				chart.requestFocus();
				Image image = new Image(getClass().getResourceAsStream("/images/background/pink.png"));
				freccia.setImage(image);
			}
		}
		else if(e.getSource().equals(chart))
		{
			if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN)
			{
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
	 * Metodo che crea il diagramma che mostra le statistiche
	 */
	public void creaDiagramma() {
	    PieChart chart = new PieChart();
	    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
	        new PieChart.Data("Partite vinte: "+v, v),
	        new PieChart.Data("Partite perse: "+p, p)
	    );
	    chart.setData(pieChartData);
	    AnchorPane.setTopAnchor(chart, 170.0);
	    AnchorPane.setBottomAnchor(chart, 10.0);
	    AnchorPane.setLeftAnchor(chart, 75.0);
	    AnchorPane.setRightAnchor(chart, 75.0);
	    chart.setLegendVisible(false);
	    pieChartData.get(0).getNode().setStyle("-fx-pie-color: #ff7f50"); 
	    pieChartData.get(1).getNode().setStyle("-fx-pie-color: #CC3399");
	    scenePane.getChildren().add(chart);
	}	
}

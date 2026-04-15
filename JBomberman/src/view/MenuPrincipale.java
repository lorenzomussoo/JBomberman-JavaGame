package view;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import controller.ControllerMP;

/**
 * La classe MenuPrincipale la classe che crea il menù principale 
 */
public class MenuPrincipale extends Application {

	/**
	 * costruttore
	 */
	public MenuPrincipale() {
		
	}
	/**
	 * Metodo che inizializza il menù principale
	 * @param primaryStage finestra su cui applicare il menù
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MenuPrincipale.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,680,605);
		scene.getStylesheets().add(getClass().getResource("/model/application.css").toExternalForm());
		primaryStage.setTitle("JBomberman"); 
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/sprites/bomberMan/white/1 white.png")));
		primaryStage.show();
		ControllerMP c = loader.getController();
		c.initialize();
	}
}
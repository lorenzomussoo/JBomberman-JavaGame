package model;

import javafx.application.Application;
import view.*;

/**
 * La classe JBomberman è la classe che contiene il main del gioco
 */
public class JBomberMan {
	
	/**
	 * costruttore
	 */
	public JBomberMan() {
		
	}
	/**
	   * Il metodo init() richiama il metodo launch di Application, passando come parametro la classe MenuPrincipale, 
	   * ossia inizializza la finestra grafica del gioco 
	*/
	public void init()
	{
		Application.launch(MenuPrincipale.class);
	}

	/**
	   * Il metodo main() inizia il gioco, di preciso richiama la funzione init()
	   * @param args di default per il main
	*/
	public static void main(String[] args)
	{
		JBomberMan j = new JBomberMan();
		j.init();
	}
}
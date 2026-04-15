package model;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *La Classe Comportamento1 è una classe che implementa l'interfaccia ComportamentoNemico rappresenta il movimento di una tipologia di nemico
 */
public class Comportamento1 implements ComportamentoNemico {
	
	/**
	 * array di interi che definiscono il movimento
	 */
	private List<Integer> movimento = new ArrayList<>();
	
	/**
	 * costruttore
	 */
	public Comportamento1(){
		
	}
	
	/**
	 * Metodo ereditato dall'interfaccia ComportamentoNemico che restituisce una lista di interi che definiscono il movimento
	 * @return lista di interi che definiscono il movimento di un nemico
	 */
	@Override
	public List<Integer> moveEnemy() {
		int i = 0;
		int j = 0;
		int z = 0;
		while (i<100)
		{
			movimento.add(3);
			i++;
		}
		while (j<50)
		{
			movimento.add(0);
			j++;
		}
		while (z<100)
		{
			movimento.add(4);
			z++;
		}
		return movimento;
	}
}
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *La Classe Comportamento2 è una classe che implementa l'interfaccia ComportamentoNemico rappresenta il movimento di una tipologia di nemico
 */
public class Comportamento2 implements ComportamentoNemico {
	
	/**
	 * array di interi che definiscono il movimento
	 */
	private List<Integer> movimento = new ArrayList<>();
	
	/**
	 * costruttore
	 */
	public Comportamento2(){
		
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
			movimento.add(1);
			i++;
		}
		while (j<50)
		{
			movimento.add(0);
			j++;
		}
		while (z<100)
		{
			movimento.add(2);
			z++;
		}
		return movimento;
	}
}
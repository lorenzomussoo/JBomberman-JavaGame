package model;

import java.util.ArrayList;
import java.util.List;

/**
 *La Classe Comportamento3 è una classe che implementa l'interfaccia ComportamentoNemico rappresenta il movimento di una tipologia di nemico
 */

public class Comportamento3 implements ComportamentoNemico {

	/**
	 * array di interi che definiscono il movimento
	 */
	private List<Integer> movimento = new ArrayList<>();
	
	/**
	 * costruttore
	 */
	public Comportamento3(){
		
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
		j = 0;
		while (j<50)
		{
			movimento.add(0);
			j++;
		}
		i = 0;
		while (i<70)
		{
			movimento.add(3);
			i++;
		}
		j = 0;
		while (j<50)
		{
			movimento.add(0);
			j++;
		}
		z = 0;
		while (z<70)
		{
			movimento.add(4);
			z++;
		} 
		return movimento;
	}
}
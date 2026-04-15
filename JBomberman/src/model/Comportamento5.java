package model;

import java.util.ArrayList;
import java.util.List;

/**
 *La Classe Comportamento5 è una classe che implementa l'interfaccia ComportamentoNemico rappresenta il movimento di una tipologia di nemico
 */
public class Comportamento5 implements ComportamentoNemico {

	/**
	 * array di interi che definiscono il movimento
	 */
	private List<Integer> movimento = new ArrayList<>();
	
	/**
	 * costruttore
	 */
	public Comportamento5(){
		
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
		int w = 0;
		int q = 0;
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
			movimento.add(3);
			z++;
		}
		j = 0;
		while (j<50)
		{
			movimento.add(0);
			j++;
		}
		while (q<100)
		{
			movimento.add(4);
			q++;
		}
		j = 0;
		while (j<50)
		{
			movimento.add(0);
			j++;
		}
		while (w<100)
		{
			movimento.add(2);
			w++;
		}
		j = 0;
		while (j<50)
		{
			movimento.add(0);
			j++;
		}
		i = 0;
		j = 0;
		z = 0;
		w = 0;
		q = 0;
		while (i<100)
		{
			movimento.add(2);
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
		j = 0;
		while (j<50)
		{
			movimento.add(0);
			j++;
		}
		while (q<100)
		{
			movimento.add(3);
			q++;
		}
		j = 0;
		while (j<50)
		{
			movimento.add(0);
			j++;
		}
		while (w<100)
		{
			movimento.add(1);
			w++;
		}
		j = 0;
		while (j<50)
		{
			movimento.add(0);
			j++;
		}
		return movimento;
	}

}
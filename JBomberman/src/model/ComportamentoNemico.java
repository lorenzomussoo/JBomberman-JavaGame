package model;

import java.util.List;

/**
 * ComportamentoNemico è un interfacccia che fa implementare alle classi che la implementano un metodo che definisce una tipologia di comportamento
 */
public interface ComportamentoNemico {
	
	/**
	 * Metodo che definisce il comportamento di un nemico
	 * @return lista di interi che definiscono il comportamento di un nemico
	 */
	public List<Integer> moveEnemy();
}

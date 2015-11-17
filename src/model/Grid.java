package model;

import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/*
 * This class represents a Grid played by a player for the loto game
 */
public class Grid {
	
	private Vector<Integer> numeros;	// Vector is used to store numeros instead of Set to avoid syncronisation problems
	private Vector<Integer> n_chances;
	private Integer price;

	public Grid() {
		this.numeros = new Vector<Integer>();
		this.n_chances = new Vector<Integer>();
	}
	
	public Vector<Integer> getNumeros() {
		return numeros;
	}

	public Vector<Integer> getN_chances() {
		return n_chances;
	}

	public Integer getPrice() {
		return price;
	}

	/*
	 * Take a Set, transform it to Vector before storing it
	 */
	private void setNumeros(Set<Integer> numeros) {
		this.numeros = new Vector<Integer>(numeros);
	}

	/*
	 * Take a Set, transform it to Vector before storing it
	 */
	private void setN_chances(Set<Integer> n_chances) {
		this.n_chances = new Vector<Integer>(n_chances);
	}

	/*
	 * Checks grid validity
	 * numeros must be between 1 and 49 without doublons
	 * n_chances must be between 1 and 10 without doublons
	 * There is a limited number of numeros and n_chances that can be played in one grid
	 */
	public boolean checkGrid(Set<Integer> numeros, Set<Integer> n_chances) {
		
		// The set structure assures there are no doublons
		
		// Checks the allowed combinaisons
		Integer nb_numeros = numeros.size();
		Integer nb_chances = n_chances.size();
		
		if ((nb_numeros > 9) || (nb_numeros < 5)) {
			return false;
		}
		else if ((nb_chances > 10) || (nb_chances < 1)) {
			return false;
		}
		else if (nb_numeros == 9) {
			if (nb_chances > 1) {
				return false;
			}
		}
		else if (nb_numeros == 8) {
			if (nb_chances > 3) {
				return false;
			}
		}
		else if (nb_numeros == 7) {
			if (nb_chances > 8) {
				return false;
			}
		}
		
		//Checks if numeros are between 1 and 49 and n_chances are between 1 and 10
		Iterator<Integer> iterator1 = numeros.iterator();
		Iterator<Integer> iterator2 = n_chances.iterator();
		
		Integer setElement = null;
		while(iterator1.hasNext()) {
	    	setElement = iterator1.next();
	    	if((setElement < 1) || (setElement > 49)) {
	    		return false;
	    	}
	    }
		
		setElement = null;
	    while(iterator2.hasNext()) {
	    	setElement = iterator2.next();
	    	if((setElement < 1) || (setElement > 10)) {
	    		return false;
	    	}
	    }
		
		return true;
	}
	
	/*
	 * Sets the price corresponding to the played grid
	 */
	private void setPrice(Integer nb_numeros, Integer nb_n_chances) {
		Integer base = null;
		if (nb_numeros == 5) {
			base = 2;
		}
		else if (nb_numeros == 6) {
			base = 12;
		}
		else if (nb_numeros == 7) {
			base = 42;
		}
		else if (nb_numeros == 8) {
			base = 112;
		}
		else if (nb_numeros == 9) {
			base = 252;
		}
		else {
			base = 0;
		}
		this.price = base*nb_n_chances;
	}
	
	/*
	 * Sets a grid after checking it
	 */
	public boolean setGrid(Set<Integer> numeros, Set<Integer> n_chances) {
		if(this.checkGrid(numeros, n_chances)) {
			Integer nb_numeros = numeros.size();
			Integer nb_n_chances = n_chances.size();
			this.setNumeros(numeros);
			this.setN_chances(n_chances);
			this.setPrice(nb_numeros, nb_n_chances);
			return true;
		}
		return false;
	}
	
}

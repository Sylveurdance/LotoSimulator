package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

/*
 * This class represents a User that plays the loto game
 */
public class User {
	
	public final static int RANG1 = 5500000;
	public final static int RANG2 = 100000;
	public final static int RANG3 = 1000;
	public final static int RANG4 = 10;
	public final static int RANG5 = 5;
	public final static int MISE = 2;
	
	private String login;
	private Integer money;
	private Vector<Grid> grids;

	public User(String login, Integer money) {
		this.login = login;
		this.money = money;
	}

	public String getLogin() {
		return login;
	}

	public Integer getMoney() {
		return money;
	}
	
	public Vector<Grid> getGrids() {
		return grids;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setMoney(Integer money) {
		this.money = money;
	}

	public void setGrids(Vector<Grid> grids) {
		this.grids = grids;
		Integer cost = 0;
		for (int i=0; i<grids.size(); i++) {
			cost += grids.get(i).getPrice();
		}
		this.setMoney(this.getMoney()-cost);
	}
	
	/*
	 * Sets wins to the user for a specific draw
	 */
	public void setWins(RandomDrawing randDraw) {
		Integer wins = User.calcWins(this.grids, randDraw);
		this.setMoney(this.getMoney()+wins);
	}
	
	/*
	 * Calculates how much the user win or loose with his grids
	 */
	public static Integer calcWins(Vector<Grid> grids, RandomDrawing randDraw) {
		Integer wins = 0; // money won
		List<Integer> mergedListNumeros = new ArrayList<Integer>();
		List<Integer> mergedListNChance = new ArrayList<Integer>();
		Integer nb_good_numbers = 0; // number of good numbers for a specific grid
		boolean n_chance = false; // has the user the number chance for a specific grid
		
		for (int i=0; i<grids.size(); i++) {
			// Finds how many close the grid is from the draw
			mergedListNumeros.addAll(grids.get(i).getNumeros());
			mergedListNumeros.addAll(randDraw.getNumeros());
			mergedListNChance.addAll(grids.get(i).getN_chances());
			mergedListNChance.add(randDraw.getN_chance());
			nb_good_numbers = User.getNbDoublons(mergedListNumeros);
			if (User.getNbDoublons(mergedListNChance) == 1) {
				n_chance = true;
			}
			// Calculates wins
			if ((!n_chance) && (nb_good_numbers < 2)) {
				break;
			}
			else if ((n_chance) && (nb_good_numbers == 5)) {
				wins += RANG1;
			}
			else if (nb_good_numbers == 5) {
				wins+=RANG2;
			}
			else {
				if (n_chance) {
					wins+= MISE;
				}
				if (nb_good_numbers == 4) {
					wins+=RANG3;
				}
				else if (nb_good_numbers == 3) {
					wins+=RANG4;
				}
				else if (nb_good_numbers == 2) {
					wins+=RANG5;
				}
			}
			// Reset variables
			mergedListNumeros.clear();
			mergedListNChance.clear();
			n_chance = false;
			nb_good_numbers = 0;
		}
		
		return wins;
	}
	
	/*
	 * Gets the number of doublons in a list
	 */
	public static int getNbDoublons(List<Integer> tab)
	{
		Set<Integer> unset = new TreeSet<Integer>(tab);
		return tab.size() - unset.size();
	}
	
	
}

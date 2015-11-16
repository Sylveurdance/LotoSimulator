package main;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;

import view.*;
import model.*;

public class LotoSimulator {

	/**
	 * Main class: launchs the app
	 */
	public static void main(String[] args) {
		
		Cookies cookie = new Cookies();
		
		String login = "toto";
		cookie.readCookie(login);
		User user1 = Cookies.getCurrentUser();
		
		LotoGui app = new LotoGui();
		
		cookie.updateCookie(user1);
		
		// Testing process (model)
		/*
		User user1 = new User("toto", 1000);
		User user2 = new User("toto2", 1000);
		
		Grid grid1 = new Grid();
		Set<Integer> num_serie_1 = new LinkedHashSet<Integer>();
		for (int i=1; i<6; i++) {
			num_serie_1.add(i);
		}
		Set<Integer> num_chance_1 = new LinkedHashSet<Integer>();
		num_chance_1.add(3);
		grid1.setGrid(num_serie_1, num_chance_1);
		
		Grid grid2 = new Grid();
		Set<Integer> num_serie_2 = new LinkedHashSet<Integer>();
		for (int i=1; i<7; i++) {
			num_serie_2.add(i+10);
		}
		Set<Integer> num_chance_2 = new LinkedHashSet<Integer>();
		num_chance_2.add(4);
		num_chance_2.add(5);
		grid2.setGrid(num_serie_2, num_chance_2);
		
		Vector<Grid> gridsU1 = new Vector<Grid>();
		Vector<Grid> gridsU2 = new Vector<Grid>();
		gridsU1.add(grid1);
		gridsU1.add(grid2);
		gridsU2.add(grid1);
		
		user1.setGrids(gridsU1);
		user2.setGrids(gridsU2);
		System.out.println(user1.getMoney());
		System.out.println(user2.getMoney());
		
		RandomDrawing draw = new RandomDrawing();
		System.out.println("Numéro chance : "+draw.getN_chance());
		System.out.println("Les bons numéros : "+draw.getNumeros());
		
		user1.setWins(draw);
		user2.setWins(draw);
		
		System.out.println(user1.getMoney());
		System.out.println(user2.getMoney());
		*/
		
	}

}

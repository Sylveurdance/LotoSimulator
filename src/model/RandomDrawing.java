package model;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/*
 * This class represents the random drawing during the loto game
 */
public class RandomDrawing {

	private Set<Integer> numeros;
	private Integer n_chance;
	
	public RandomDrawing() {}
	
	public Set<Integer> getNumeros() {
		return numeros;
	}

	public Integer getN_chance() {
		return n_chance;
	}
	
	private void setNumeros(Set<Integer> numeros) {
		this.numeros = numeros;
	}

	private void setN_chance(Integer n_chance) {
		this.n_chance = n_chance;
	}

	/*
	 * Generates a random draw to draw 5 different numbers
	 */
	private Set<Integer> generateNumeros() {
		return RandomDrawing.generateRandom(5, 49);
	}
	
	/*
	 * Generates a random number for the N chance
	 */
	private Integer generateNChance() {
		Set<Integer> n_chance = RandomDrawing.generateRandom(1, 10);
		return n_chance.iterator().next();
	}
	
	/*
	 * Generates loto draw and save it into RandomDrawing object
	 */
	public void setRandomDrawing() {
		Set<Integer> draw_numbers = generateNumeros();
		Integer draw_n_chance = generateNChance();
		this.setNumeros(draw_numbers);
		this.setN_chance(draw_n_chance);
	}
	
	/*
	 * Static function used to generate random numbers
	 * NumbersNeeded is 5 for numeros and 1 for the N chance
	 * Max is 49 for numeros and 10 for N chance
	 */
	public static Set<Integer> generateRandom(Integer numbersNeeded, Integer max) {
		Random rng = new Random();
		Set<Integer> generated = new LinkedHashSet<Integer>();
		while (generated.size() < numbersNeeded)
		{
		    Integer next = rng.nextInt(max) + 1;
		    generated.add(next);
		}
		
		return generated;
	}
}

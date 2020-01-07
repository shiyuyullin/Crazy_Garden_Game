/*
 * This is a class named Dice which have two private attributes dice1 and dice2 with
 *two access method to get the number of dice1 and dice2, also a rollDice method to 
 *generate result randomly for dice1 and dice2.
 * 
 */

public class Dice {
	
	private int dice1;
	private int dice2;
	
	public int getDice1() {
		return(dice1);
	}
	public int getDice2() {
		return(dice2);
	}
	
	//Using math.random to generate value for dice1 and dice2 randomly
	public int rollDice() {
		this.dice1 = (int)((Math.random()+0.2)*5.8);
		this.dice2 = (int)((Math.random()+0.2)*5.8);
		return(dice1+dice2);
	}
	
	public String toString() {
		return("Dice1 has the value of " + dice1 + " Dice2 has the value of " + dice2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}

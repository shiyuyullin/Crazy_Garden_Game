/*
 *   This class allows players to interact with game 
 *  by entering coordinates. They can plant what they
 *  are asked to plant in their desired location.
 */

public class Player {
	
	private String name;
	private Garden garden;
	
	//Constructor that takes players' name and the size of their garden.
	//After a player set the size of garden, rest of the players have the same size.
	public Player(String name, int size) {
		this.name = name;
		garden = new Garden(size);
	}
	
	public String getName() {
		return(name);
	}
	
	//Calling methods in Garden class since it is a private class
	public int howManyFlowersPossible() {
		return(garden.countPossibleFlowers());
	}
	
	public int howManytreesPossible() {
		return(garden.countPossibleTrees());
	}
	
	public char whatIsPlanted(int r, int c) {
		return(garden.getInLocation(r, c));
	}
	
	public void plantTreeInGarden(int r, int c) {
		garden.plantTree(r, c);
	}
	
	public void plantFlowerInGarden(int r, int c) {
		garden.plantFlower(r, c);
	}
	
	public void eatHere(int r, int c) {
		garden.removeFlower(r, c);
	}
	
	public boolean isGardenFull() {
		return(garden.gardenFull());
	}
	
	public String showGarden() {
		return(garden.toString());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}

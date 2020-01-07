//*************************************************
//Assignment 4
//Written by : Shiyu Lin 40050199
//For COMP 248 P - Fall 2018
//*************************************************

/*
 *  This is a driver class and where all action happens. 
 * 	Let's play! :)
 */

import java.util.Scanner;
public class LetsPlay {
	
	//Static variable that stores the result of roll dice
	public static int diceNumber;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		
			
	//Display welcome banner
		System.out.println("--------********--------********--------********--------********--------");
		System.out.println("                Welcome to Crazy Nancy's Garden Game                   ");
		System.out.println("--------********--------********--------********--------********--------");
		
	//Display general rules of the game
		System.out.println("To win this game you need some luck and a bit strategy.");
		System.out.println("Your garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. A tree takes 4 spots(2x2)");
		System.out.println("You roll the dice and based on the outcome you get to plant pre-set number of trees and flowers in you garden.\n");
		System.out.println("Rolls and their outcomes: ");
		System.out.println("3: Plant a tree(2x2) and a flower(1x1).");
		System.out.println("6: Plant 2 flowers(2 times 1x1).");
		System.out.println("12: Plant 2 trees (2 times 2x2).");
		System.out.println("5 or 10: the rabbit will eat one spot(1x1) that you have planted - might be a flower or part of a tree(1x1). ");
		System.out.println("Any other EVEN number: Plant a tree(2x2).");
		System.out.println("Any other ODD number: Plant a flower(1x1).\n");
		System.out.println("Minimum number of players: 2");
		System.out.println("Minimum size of garden: 3x3");
		System.out.println("You can only plant on empty spot. To plant a tree you need to give the top left coordinate of a 2x2 space");
		System.out.println("and I will check to make sure all 4 locations are free.");
		System.out.println("These are rules you need to remember. Now let's play!!!!!\n");
		
	//Ask the user the size of garden
		System.out.println("The default garden size is 3x3. You may use this default garden size or change the size.\n");
		System.out.print("Enter 3 to use the default garden size or to enter your own size: ");
		int sizeOfGarden = keyboard.nextInt();
		
	//Determining the size of user's garden
		
		//If the user's input is neither 3(default size) nor greater than 3 display error message and ask again
		if(sizeOfGarden != 3 && sizeOfGarden < 4) {
			do {
			System.out.print("Sorry " + sizeOfGarden + " is not a legal choice." + " Enter your choice: ");
			sizeOfGarden = keyboard.nextInt();
			 } while ((sizeOfGarden != 3) && (sizeOfGarden < 4));	
		 }
		
	 //Ask user number of players
		
		int numberOfPlayers = 0;
		//The do while loop ask user for number of players, and check if the number entered by user is between 2 and 10
		//If not, it will display an error message and ask again until an valid number is entered
		do {
		System.out.print("How many players will there be(Minimum of 2, and maximum of 10)? ");
		numberOfPlayers = keyboard.nextInt();
		if(numberOfPlayers < 2 || numberOfPlayers > 10) {
			System.out.println("Sorry but " + numberOfPlayers + " is not legal number of players.");
			}
		else {
			System.out.println("Great, " + numberOfPlayers + " players there will be.\n");
		}
		}while(numberOfPlayers < 2 || numberOfPlayers > 10);
		
		
	 //Store players information
		
		//Creating an object array, and the size of it is the number of players entered by user
		//example. 2 players the size of array will be 2 and so on.
		Player[] thePlayers = new Player[numberOfPlayers];
		//The loop ask the player for their name and store their name into corresponding part of the array
		for(int i = 0; i < thePlayers.length; i++) {
			System.out.print("Name of Player " + (i+1) + " (no space allowed): ");
			String nameOfPlayer = keyboard.next();
			thePlayers[i] = new Player(nameOfPlayer,sizeOfGarden);//also it creates garden for each player based on the size they have entered.
		}
		
	 //Each player rolls dice
		
		Dice dice = new Dice();
		int[][] playersDice = new int[numberOfPlayers][2];
		//This loop store each player's result of roll dice, and will be used later
		//Also it give each player a number for example, player 1 is bob, then we can use 1 later to get bob's information stored in the player array
		for(int i = 0; i < playersDice.length; i++) {
			playersDice[i][1] = dice.rollDice();
			playersDice[i][0] = i;
			System.out.println(thePlayers[i].getName() + " rolled a " + playersDice[i][1]);
		}
		
		//Decide whether we need to roll the dice again
		
		boolean decide = false;
		//These loops check if there are two players have the same result, if there is roll the dice again.
		for(int i = 0; i < playersDice.length;i++) {
			for(int j = i+1; j < playersDice.length;j++) {
				if(playersDice[i][1] == playersDice[j][1]) {
					decide = true;//Two players have the same result change the value
					playersDice[i][1] = dice.rollDice();
					System.out.println("We need to roll the dice again for each player\n");
				}
			}
		}
		//Display the result after re-roll the dice
		if(decide == true) {
			for(int i = 0; i < playersDice.length; i++) {
				System.out.println(thePlayers[i].getName() + " rolled a " + playersDice[i][1]);
			}
		}
		
		//Determine who goes first
		
		//Loops bring the highest result with the corresponding number to the first place in that array
		for(int min = 0; min < playersDice.length; min++) {
			for(int temp = min+1; temp < playersDice.length; temp++) {
				if(playersDice[min][1] < playersDice[temp][1]) {
					//Bring the highest result to the first place
					int store = playersDice[min][1];
					playersDice[min][1] = playersDice[temp][1];
					playersDice[temp][1] = store;
					//Bring the corresponding number to the first place
					int store2 = playersDice[min][0];
					playersDice[min][0] = playersDice[temp][0];
					playersDice[temp][0] = store2;
				}
			}
		}
		//Display who goes first.
		System.out.println(thePlayers[playersDice[0][0]].getName() + " goes first.\n");
		
		
		//Game Begin
		System.out.println("Time to Play!!!");
		System.out.println("---------------");
		
		boolean gardenFull = false;
		int rounds = 0;
		String winner = "";//Final winner will be stored in here
		
		while(gardenFull == false) {//This while loop can be thought like the rounds of our game, it continues to run until there is a winner.
			rounds++;
		for(int i = 0; i < numberOfPlayers; i++) {
			diceNumber = dice.rollDice();
			System.out.println(thePlayers[playersDice[i][0]].getName() + " you rolled a " + diceNumber + " (Dice 1: " + dice.getDice1() + " Dice 2: " +dice.getDice2());
			
		//if a player rolled a 3, display the following
		if(diceNumber == 3) {
			System.out.println("You have to plant a tree(2x2) and a flower(1x1)");
			System.out.println(thePlayers[playersDice[i][0]].showGarden());
			System.out.println("Let's start with the tree. You have " + thePlayers[i].howManytreesPossible() + " places to do this.");
			
			//Check if there are more places to plant a tree if there is.....
			if(thePlayers[playersDice[i][0]].howManytreesPossible() != 0) {
			int rowT = 0;
			int columnT = 0;
			boolean check1 = false;
			do {
			System.out.print("Enter coordinates as row column: ");
			 rowT = keyboard.nextInt();
			 columnT = keyboard.nextInt();
			 //Validate the coordinate entered by user
			if((rowT < sizeOfGarden) && (columnT < sizeOfGarden) && ((rowT+1) < sizeOfGarden) && ((columnT+1) < sizeOfGarden)) {
				//validate that all 4 spaces are free
				if((thePlayers[playersDice[i][0]].whatIsPlanted(rowT, columnT) == '-')&&(thePlayers[playersDice[i][0]].whatIsPlanted(rowT, columnT+1) == '-')&&(thePlayers[playersDice[i][0]].whatIsPlanted(rowT+1, columnT)=='-')&&
					(thePlayers[playersDice[i][0]].whatIsPlanted(rowT+1, columnT+1)=='-')) {
				thePlayers[playersDice[i][0]].plantTreeInGarden(rowT, columnT);
				check1 = true;
				}
				else {
				System.out.println("Sorry you can not plant a tree on that location.");
				}
			  }
			else {
				System.out.println("Invalid input.");
			 }
			}while(check1 == false);
			System.out.println(thePlayers[playersDice[i][0]].showGarden());
			}
			//If there is not enough place to plant a tree
			else {
				System.out.println("You don't have any places to plant a tree. Let's see whether you can plant a flower.");
			}
			
			//Check if a garden is full or not after planting
			if(thePlayers[playersDice[i][0]].isGardenFull() == true) {
				gardenFull = true;//Change the value to true which means leave the while loop after there is a winner
				winner = thePlayers[playersDice[i][0]].getName();//record the winner's name
				break;//break out of the inner for loop
			}
			
			//A flower needs to be planted also
			System.out.println("You have a flower to plant.");
			System.out.println(thePlayers[playersDice[i][0]].showGarden());
			System.out.println("You have " + thePlayers[playersDice[i][0]].howManyFlowersPossible() + " places to do this.");
			//Checking if there are more spots to plant a flower
			if(thePlayers[playersDice[i][0]].howManyFlowersPossible() != 0) {
			int rowF = 0;
			int columnF = 0;
			boolean check2 = false;
			do {
			System.out.print("Enter coordinates as row column: ");
			 rowF = keyboard.nextInt();
			 columnF = keyboard.nextInt();
			 //validation
			if((rowF < sizeOfGarden) && (columnF < sizeOfGarden)) {
				//validation
				if((thePlayers[playersDice[i][0]].whatIsPlanted(rowF, columnF) == '-')) {
					thePlayers[playersDice[i][0]].plantFlowerInGarden(rowF, columnF);;
					check2 = true;
				 }
				else {
					System.out.println("Sorry that location is already taken up by a " + thePlayers[playersDice[i][0]].whatIsPlanted(rowF, columnF));
				}
			 }
			else {
				System.out.println("Invalid input");
			}
			}while(check2 == false);
			System.out.println(thePlayers[playersDice[i][0]].showGarden());
			}
			else {
				System.out.println("You don't have enough space to plant a flower. You miss a turn.");
			}
			//Check if a garden is full or not after planting
			if(thePlayers[playersDice[i][0]].isGardenFull() == true) {
				gardenFull = true;
				winner = thePlayers[playersDice[i][0]].getName();
				break;
			}
		}
		
		//Players rolled 6
		if(diceNumber == 6) {
			System.out.println("You must plant 2 flowers(2 times 1x1).");
			System.out.println("You have " + thePlayers[playersDice[i][0]].howManyFlowersPossible() + " places to do this.");
			System.out.println(thePlayers[playersDice[i][0]].showGarden());
			
			//validation
			if(thePlayers[playersDice[i][0]].howManyFlowersPossible() != 0) {
				int rowF1 = 0;
				int columnF1= 0;
				boolean check3 = false;
				do {
					System.out.print("First flower - Enter coordinates as row column: ");
					 rowF1 = keyboard.nextInt();
					 columnF1 = keyboard.nextInt();
					 //validation
					if((rowF1 < sizeOfGarden) && (columnF1 < sizeOfGarden)) {
						//validation
						 if((thePlayers[playersDice[i][0]].whatIsPlanted(rowF1, columnF1) == '-')) {
							 thePlayers[playersDice[i][0]].plantFlowerInGarden(rowF1, columnF1);;
							 check3 = true;
						 }
						 else {
							 System.out.println("Sorry that location is already taken up by a " + thePlayers[playersDice[i][0]].whatIsPlanted(rowF1, columnF1));
						 }
					 }
					else {
						System.out.println("Invalid Input");
					}
					}while(check3 == false);
					System.out.println(thePlayers[playersDice[i][0]].showGarden());
				}
					//Check if a garden is full or not after planting
					if(thePlayers[playersDice[i][0]].isGardenFull() == true) {
						gardenFull = true;
						winner = thePlayers[playersDice[i][0]].getName();
						break;
					}
			//validation
			if(thePlayers[playersDice[i][0]].howManyFlowersPossible() >= 1) {
				int rowF2 = 0;
				int columnF2= 0;
				boolean check4 = false;
				do {
					System.out.print("Second flower - Enter coordinates as row column: ");
					 rowF2 = keyboard.nextInt();
					 columnF2 = keyboard.nextInt();
					 //validation
					if((rowF2 < sizeOfGarden) && (columnF2 < sizeOfGarden)) {
						//validation
						if((thePlayers[playersDice[i][0]].whatIsPlanted(rowF2, columnF2) == '-')) {
							thePlayers[playersDice[i][0]].plantFlowerInGarden(rowF2, columnF2);;
							check4 = true;
						}
						else {
							System.out.println("Sorry that location is already taken up by a " + thePlayers[playersDice[i][0]].whatIsPlanted(rowF2, columnF2));
						}
					 }
					else {
						System.out.println("Invalid input.");
					}
					}while(check4 == false);
					System.out.println(thePlayers[playersDice[i][0]].showGarden());
				}
					else {
						System.out.println("You don't have enough space to plant a flower. You lose a turn.");
					}
					//Check if a garden is full or not after planting
					if(thePlayers[playersDice[i][0]].isGardenFull() == true) {
						gardenFull = true;
						winner = thePlayers[playersDice[i][0]].getName();
						break;
					}
			}
	
			//Players rolled 12
			if(diceNumber == 12) {
				System.out.println("You must plant two trees(2 times 2x2).");
				System.out.println("You have " + thePlayers[playersDice[i][0]].howManytreesPossible() + " places to do this.");
				System.out.println(thePlayers[playersDice[i][0]].showGarden());
				//validation
				if(thePlayers[playersDice[i][0]].howManytreesPossible() != 0) {
					int rowT1 = 0;
					int columnT1 = 0;
					boolean check5 = false;
					//Validation
					do {
						System.out.print("First tree - Enter coordinates as row column: ");
						rowT1 = keyboard.nextInt();
						columnT1 = keyboard.nextInt();
						if((rowT1 < sizeOfGarden) && (columnT1 < sizeOfGarden) && ((rowT1+1) < sizeOfGarden) && ((columnT1+1) < sizeOfGarden)) {
							if((thePlayers[playersDice[i][0]].whatIsPlanted(rowT1, columnT1) == '-') && (thePlayers[playersDice[i][0]].whatIsPlanted(rowT1, columnT1+1) == '-')
									&&(thePlayers[playersDice[i][0]].whatIsPlanted(rowT1+1, columnT1)=='-') && (thePlayers[playersDice[i][0]].whatIsPlanted(rowT1+1, columnT1+1)=='-')) {
								thePlayers[playersDice[i][0]].plantTreeInGarden(rowT1, columnT1);
								check5 = true;
							}
							else {
								System.out.println("Sorry that location is already taken up by a " + thePlayers[playersDice[i][0]].whatIsPlanted(rowT1, columnT1));
							}
						}
						else {
							System.out.println("Invalid Input");
						}
					}while(check5 == false);
					System.out.println(thePlayers[playersDice[i][0]].showGarden());
				}
					//Check if a garden is full or not after planting
					if(thePlayers[playersDice[i][0]].isGardenFull() == true) {
						gardenFull = true;
						winner = thePlayers[playersDice[i][0]].getName();
						break;
					}

					if(thePlayers[playersDice[i][0]].howManytreesPossible() >= 1) {
						int rowT2 = 0;
						int columnT2 =0;
						boolean check6 = false;
						do {
							System.out.print("Second tree - Enter coordinates as row column: ");
							rowT2 = keyboard.nextInt();
							columnT2 = keyboard.nextInt();
							if((rowT2 < sizeOfGarden) && (columnT2 < sizeOfGarden) && ((rowT2 < sizeOfGarden) && ((columnT2+1) < sizeOfGarden))) {
								if((thePlayers[playersDice[i][0]].whatIsPlanted(rowT2, columnT2) == '-') && (thePlayers[playersDice[i][0]].whatIsPlanted(rowT2, columnT2+1) == '-')&& 
									(thePlayers[playersDice[i][0]].whatIsPlanted(rowT2+1, columnT2)=='-') && (thePlayers[playersDice[i][0]].whatIsPlanted(rowT2+1, columnT2+1)=='-')) {
									thePlayers[playersDice[i][0]].plantTreeInGarden(rowT2, columnT2);
									check6 = true;
								}	
								else {
									System.out.println("Sorry that location is already taken up by a " + thePlayers[playersDice[i][0]].whatIsPlanted(rowT2, columnT2));
								}
							}
							else {
								System.out.println("Invalid Input");
							}
						}while(check6 == false);
						System.out.println(thePlayers[playersDice[i][0]].showGarden());
					}
					else {
						System.out.println("You don't have enought place to plant more trees.");
					}
					//Check if a garden is full or not after planting
					if(thePlayers[playersDice[i][0]].isGardenFull() == true) {
						gardenFull = true;
						winner = thePlayers[playersDice[i][0]].getName();
						break;
					}
				}
		
			//Players rolled 5 or 10
			if(diceNumber == 5) {
				System.out.println(thePlayers[playersDice[i][0]].showGarden());
				int eatX = 0;
				int eatY = 0;
				for(int in = 0; in < sizeOfGarden; in++) {
					for(int j = 0; j < sizeOfGarden; j++) {
						if(thePlayers[playersDice[i][0]].whatIsPlanted(in, j) != '-') {
							eatX = in;
							eatY = j;
						}
					}
				}
				thePlayers[playersDice[i][0]].eatHere(eatX, eatY);
				System.out.println("The rabbit ate whatever was planted in location (" + eatX + "," + eatY + ")");
			}
		
			if(diceNumber == 10) {
				System.out.println(thePlayers[playersDice[i][0]].showGarden());
				int eatA = 0;
				int eatB = 0;
				for(int g = 0; g < sizeOfGarden; g++) {
					for(int j = 0; j < sizeOfGarden; j++) {
						if(thePlayers[playersDice[i][0]].whatIsPlanted(g, j) != '-') {
							eatA = g;
							eatB = j;
						}
					}	
				}
				thePlayers[playersDice[i][0]].eatHere(eatA, eatB);
				System.out.println("The rabbit ate whatever was planted in location (" + eatA + "," + eatB + ")");
			}
		
			//Players rolled an even number(other than 6, 10, 12)
		
			if(((diceNumber%2) == 0) && (diceNumber != 6) && (diceNumber != 10) && (diceNumber != 12)) {
				System.out.println("You must plant a tree(2x2)");
				System.out.println(thePlayers[playersDice[i][0]].showGarden());
				System.out.println("You have " + thePlayers[playersDice[i][0]].howManytreesPossible() + " places to do this.");
			
				if(thePlayers[playersDice[i][0]].howManytreesPossible() != 0) {
						int rowTT = 0;
						int columnTT = 0;
						boolean check7 = false;
						//Validate coordinate to plant a tree
						do {
							System.out.print("Enter coordinates as row column: ");
							rowTT = keyboard.nextInt();
							columnTT = keyboard.nextInt();
							if((rowTT < sizeOfGarden) && (columnTT < sizeOfGarden) && ((rowTT+1)< sizeOfGarden) && ((columnTT+1) < sizeOfGarden)) {
								if((thePlayers[playersDice[i][0]].whatIsPlanted(rowTT, columnTT) == '-') && (thePlayers[playersDice[i][0]].whatIsPlanted(rowTT, columnTT+1) == '-')&&
									(thePlayers[playersDice[i][0]].whatIsPlanted(rowTT+1, columnTT)=='-') && (thePlayers[playersDice[i][0]].whatIsPlanted(rowTT+1, columnTT+1)=='-')) {
									thePlayers[playersDice[i][0]].plantTreeInGarden(rowTT, columnTT);
									check7 = true;
								}
								else {
									System.out.println("Sorry that location is already taken up by a " + thePlayers[playersDice[i][0]].whatIsPlanted(rowTT, columnTT));
								}
							}
							else {
								System.out.println("Invalid Input");
							}
						}while(check7 == false);
						System.out.println(thePlayers[playersDice[i][0]].showGarden());
				}
				else{
					System.out.println("There is no place to plant a tree. You miss a turn.");
				}
				//Check if a garden is full or not after planting
				if(thePlayers[playersDice[i][0]].isGardenFull() == true) {
					gardenFull = true;
					winner = thePlayers[playersDice[i][0]].getName();
					break;
				}
			}
		
			//Players rolled an odd number(other than 3, 5)
		
			if(((diceNumber%2) != 0) && (diceNumber != 3) && (diceNumber != 5)) {
				System.out.println("You must plant a flower(1x1)");
				System.out.println(thePlayers[playersDice[i][0]].showGarden());
				System.out.println("You have " + thePlayers[playersDice[i][0]].howManyFlowersPossible() + " places to do this");
				if(thePlayers[playersDice[i][0]].howManyFlowersPossible() != 0) {
					int rowFF = 0;
					int columnFF = 0;
					boolean check8 = false;
					//Validate coordinate to plant a flower
					do {
						System.out.print("Enter coordinates as row column: ");
						rowFF = keyboard.nextInt();
						columnFF = keyboard.nextInt();
						if((rowFF < sizeOfGarden) && (columnFF< sizeOfGarden)) {
							if((thePlayers[playersDice[i][0]].whatIsPlanted(rowFF, columnFF) == '-')) {
								thePlayers[playersDice[i][0]].plantFlowerInGarden(rowFF, columnFF);;
								check8 = true;
							}
							else {
								System.out.println("Sorry that location is already taken up by a " + thePlayers[playersDice[i][0]].whatIsPlanted(rowFF, columnFF));
							}
						}
						else {
							System.out.println("Invalid Input");
						}
					}while(check8 == false);
					System.out.println(thePlayers[playersDice[i][0]].showGarden());
				}
				else{
					System.out.println("You don't have enough space to plant a flower. You miss a turn.");
					}
				//Check if a garden is full or not after planting
				if(thePlayers[playersDice[i][0]].isGardenFull() == true) {
					gardenFull = true;
					winner = thePlayers[playersDice[i][0]].getName();
					break;
				}
					}
			}//End of inner for loop
		}//End of while loop
		
		//Display the Final result
		System.out.println(" ");
		System.out.println("FINAL RESULTS");
		System.out.println("-------------");
		System.out.println("Here are the gardens after " + rounds + " rounds.");//Display rounds
		//Display each player with their garden
		for(int fin = 0; fin < numberOfPlayers; fin++) {
			System.out.println(thePlayers[fin].getName() + "'s garden");
			System.out.println(thePlayers[fin].showGarden());
		}
		System.out.println("And the winner is......." + winner);//Display winner.
		System.out.println("What a wonderful garden!!!");
		System.out.println("I hope you have fun");
		System.out.println("Thank you for playing Crazy Nancy's Garden!!!");
		
		
		


 }
}

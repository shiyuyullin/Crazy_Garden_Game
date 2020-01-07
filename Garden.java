/*
 *  In this game each player has their own garden and
 * they can also choose the size they want(at least 3x3)
 * this class generate the garden for each player, and provide 
 * several methods that allow players to access and manipulate
 * their garden.
 */

public class Garden {
	
	private char[][] Garden;
	
	//default constructor (Give a 3x3 garden)
	public Garden() {
		Garden = new char[3][3];
		for(int i = 0; i < 3; i++) {
			for(int x = 0; x < 3; x++) {
				Garden[i][x] = '-';
			}
		}
	}
	//constructor with customized size.
	public Garden(int c) {
		Garden = new char[c][c];
		for(int y = 0; y < c; y++) {
			for(int p = 0; p < c; p++) {
				Garden[y][p] = '-';
			}
		}
	}
	
	//Method to initialize garden, all components be '-'
	private void intiializeGarden() {
		for(int b = 0; b < Garden.length; b++) {
			for(int m = 0; m < Garden.length; m++) {
				Garden[b][m] = '-';
			}
		}
	}
	
	//Method to get information of a spot in garden
	public char getInLocation(int r, int c) {
		return(Garden[r][c]);
	}
	
	//Method to plant a flower in garden(takes one space)
	public void plantFlower(int r, int c) {
		Garden[r][c] = 'f';
	}
	
	//Method to plant a tree in garden(a 2x2 square)
	public void plantTree(int r, int c) {
		this.Garden[r][c] = 't';
		this.Garden[r][c+1] = 't';
		this.Garden[r+1][c] = 't';
		this.Garden[r+1][c+1] = 't';
	}
	
	//Method to remove a flower in garden
	public void removeFlower(int r, int c) {
		this.Garden[r][c] = '-';
	}
	
	//Method to count possible positions to plant a tree(2x2 square)
	public int countPossibleTrees() {
		int spotToPlantTrees = 0;
		for(int i = 0; i < (Garden.length-1); i++) {
			for(int j = 0; j < (Garden.length-1); j++) {
				//Checking the spot that besides the entered spot
				if((Garden[i][j] == Garden[i][j+1]) && (Garden[i][j] == '-')) {
					//Checking the spot that is below the entered spot, and the one on the right.
					if((Garden[i+1][j] == Garden[i+1][j+1]) && (Garden[i+1][j] == '-'))
						spotToPlantTrees++;
				}
			  }
			}
			return(spotToPlantTrees);
		}
	
	//Method to count possible positions to plant a flower
	public int countPossibleFlowers() {
		int placesToPlantFlower = 0;
		for(int f = 0; f < Garden.length; f++) {
			for(int p = 0; p < Garden.length; p++) {
				if(Garden[f][p] == '-') {
					placesToPlantFlower++;
				}
			}
		}
		return(placesToPlantFlower);
	}
	
	//Method to check if Garden is full or not
	public boolean gardenFull() {
		boolean t = true;
		int keepTr = 0;
		//Checking every spot in the garden
		for(int x = 0; x < Garden.length; x++) {
			for(int y = 0; y < Garden.length; y++) {
				//If there is a spot that is not be used, return false.
				if(Garden[x][y] == '-') {
					t = false;
					break;
				}
				//If all spots are used increment keepTr by 1.
				else if(Garden[x][y] != '-') {
					keepTr++;
				}
				}
			}
		//if keepTr equals the number of positions in the garden then the garden is full.
		if(keepTr == (Garden.length * Garden.length)) {
			t = true;
		}
		return(t);
	}
	
	//Display garden like a web
	public String toString() {
		String aString = "";
		//Get rows
		for(int m = 0; m < Garden.length; m++) {
			aString += "\n";
			//Get contents
			for(int n = 0; n< Garden.length; n++) {
				aString += " " + Garden[m][n];
			}
		}
		return(aString);
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}

Crazy garden is a simple game that is written in java.

To win this game you need some luck and a bit strategy.
	Your garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. A tree takes 4 spots(2x2)
	You roll the dice and based on the outcome you get to plant pre-set number of trees and flowers in you garden.
	Rolls and their outcomes:
		  3: Plant a tree(2x2) and a flower(1x1).
		  6: Plant 2 flowers(2 times 1x1).
		  12: Plant 2 trees (2 times 2x2).
		  5 or 10: the rabbit will eat one spot(1x1) that you have planted - might be a flower or part of a tree(1x1).
		  Any other EVEN number: Plant a tree(2x2).
		  Any other ODD number: Plant a flower(1x1).
		  Minimum number of players: 2.
		  Minimum size of garden: 3x3
		  You can only plant on empty spot. To plant a tree you need to give the top left coordinate of a 2x2 space
    and the program will check to make sure all 4 locations are free.
    
    The "LetsPlay.java" is the driver for the entire game

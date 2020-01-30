import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> computerPositions = new ArrayList<Integer>();
	

	public static void main(String[] args) {
		
		// Make the game board
		char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, 
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},}; 
		
		printGameBoard(gameBoard); // Method that prints the gameboard
		
		while(true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter your placement (1-9)");
			int playerPos = input.nextInt();
			while(playerPositions.contains(playerPos) || computerPositions.contains(playerPositions)) { // While the player chooses a used spot it will tell the player to choose a open spot
				System.out.println("Position taken! Enter a correct position.");
				playerPos = input.nextInt();
			}
			
			placePiece(gameBoard, playerPos, "player");
			
			String result = checkWinner();
			if(result.length() > 0) { 
				printGameBoard(gameBoard);
				System.out.println(result); 
				break;
			}
			
			Random rand = new Random();
			int computerPos = rand.nextInt(9) + 1;
			while(playerPositions.contains(computerPos) || computerPositions.contains(computerPos)) { // While computer chooses a used spot it will loop until free spot
				computerPos = rand.nextInt(9) + 1;
			}
			placePiece(gameBoard, computerPos, "computer");
			
			result = checkWinner();
			if(result.length() > 0) {
				printGameBoard(gameBoard);
				System.out.println(result);
				break;
			}
			
			printGameBoard(gameBoard);
			

		}

	}
	
	public static void printGameBoard(char [] [] gameBoard) {
		
		for(char[] row : gameBoard) { // For each row inside of the gameboard
			for(char c : row) { // For each symbol inside of the row
				System.out.print(c);
			}
			System.out.println();
		}
	}
	// Places piece on the gameboard
	public static void placePiece(char[][] gameBoard, int position, String user) {
		
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(position);
		} else if(user.equals("computer")) {
			symbol = 'O';
			computerPositions.add(position);
		}
		
		// In a 2d array the array starts with the row then column: gameboard[row][column].
		switch(position) {
			case 1: 
				gameBoard[0][0] = symbol;
				break;
			case 2: 
				gameBoard[0][2] = symbol;
				break;
			case 3: 
				gameBoard[0][4] = symbol;
				break;
			case 4: 
				gameBoard[2][0] = symbol;
				break;
			case 5: 
				gameBoard[2][2] = symbol;
				break;
			case 6: 
				gameBoard[2][4] = symbol;
				break;
			case 7: 
				gameBoard[4][0] = symbol;
				break;
			case 8: 
				gameBoard[4][2] = symbol;
				break;
			case 9: 
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	public static String checkWinner() {
		
		// Make list for the winning moves
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List diag1 = Arrays.asList(1, 5, 9);
		List diag2 = Arrays.asList(7, 5, 3);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(diag1);
		winning.add(diag2);
		
		// Go through each list to see if player or computer won
		for(List l : winning) { 
			if(playerPositions.containsAll(l)) {
				return "Congratulations player won!";
			} else if(computerPositions.containsAll(l)) {
				return "Computer won!";
			} else if(playerPositions.size() + computerPositions.size() == 9) {
				return "DRAW!";
			}
		}
		
		return ""; // Nothing happened so the string length will be 0
	}


}

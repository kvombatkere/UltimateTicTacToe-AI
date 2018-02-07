import java.util.Scanner;

public class AdvancedTTTGame {
	
	public AdvancedTTTBoard board;
	public char p2Char = ' ';
	
	public AdvancedTTTGame() {
		board = new AdvancedTTTBoard();
		char p2Char = ' ';
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		AdvancedTTTGame game = new AdvancedTTTGame();
		
		//Player 1: human player
		//Player 2: computer player
		
		while(true) {
			//boolean to keep track of who plays first
			boolean p1First = true;
			
			//String to record the board (index 0 of string) and position (index 2 of string) specified by the opponent
			String p1Move = "";
			
			//integer array to record board and position of computer's move (set to default optimal first move)
			int[] p2Move = {2,2};
			
			//Boolean to check if a move is legal before playing it
			boolean isMoveAllowed;
			
			//Set up input stream using System in
			Scanner playerInput = new Scanner(System.in);
			
			//Print Basic Introduction messages
			System.err.println("===============================================================================================================");
			System.err.println("Initialized a new game of Tic Tac Toe! (Note that X always plays first)");
			System.err.println("Please choose if you want to play \"X\" or \"O\" by typing the desired character:");

			char p1Char = playerInput.next().charAt(0); //Read the first character of any input string
			boolean legalInput = false; //Boolean to keep track of input legality
			
			if((p1Char == 'o') || (p1Char == 'O') || (p1Char == 'x') || (p1Char == 'X')) {
				legalInput = true;
			}
			
			//Handle illegal inputs
			while(!legalInput) {
				System.err.println("Illegal Input. Please try again.");
				p1Char = playerInput.next().charAt(0); //Read the first character of any input string
				if((p1Char == 'o') || (p1Char == 'O') || (p1Char == 'x') || (p1Char == 'X')) {
					legalInput = true;
				}
			}
			
			p1Char = Character.toUpperCase(p1Char); //Make input upper case in case input was lower case		
			
			if(p1Char == 'O') {
				p1First = false;
				game.p2Char = 'X';
			}
			
			else if (p1Char == 'X') {
				p1First = true;
				game.p2Char = 'O';
			}
			
			else {
				System.err.println("Oh god this should never happen please help"); //lol
				game.p2Char = 'N';
			}
			
			
			//Instantiate Minimax computer player
			AdvancedMinimaxPlayer compPlayer = new AdvancedMinimaxPlayer(game);
			
			//use random computer player for testing game functionality
			//AdvancedMinimaxPlayer compPlayer = new AdvancedMinimaxPlayer(game);
			
			if(!p1First) {
				//computer makes first move
				System.err.println("Computer playing first");
				game.board.moveResult(game.p2Char, p2Move[0], p2Move[1]);
				
			}
			
			Scanner input = new Scanner(System.in);
			while(game.board.overallGameStatus == 'n') {
				
				game.board.displayBoard();
				//Human player plays					

				int boardIndex;
				int boardPos;

				//build string to tell you what board to play on
				String helpfulHint = "";
				if(game.board.firstMove || game.board.getNextBoard().isBoardFull()) {
					helpfulHint = "You can play on any board.";
				}
				else {
					helpfulHint = "You should play on board " + game.board.nextBoardIndex + ".";
				}
				
				System.err.println("It is your turn to play, please enter the board(1-9) you would like to play on followed by"
						+ " the position on that board(1-9). " + helpfulHint);
				///read user input
				p1Move = input.nextLine();
				
				//check that user input is in a valid move format
				legalInput = false;
				while(!legalInput) {
					if(p1Move.length()<3) {
						System.err.println("Invalid Input. Moves should be entered as two digits from 1-9 separated by a space");
						p1Move = input.nextLine();
						legalInput = false;
					}
					else {
						//pull values from player input
						boardIndex = Character.getNumericValue(p1Move.charAt(0));
						boardPos = Character.getNumericValue(p1Move.charAt(2));
						
						//check if entered move is a valid move
						if(!game.board.isMoveAllowed( boardIndex, boardPos)) {
							System.err.println("Illegal Input. Please try again.");
							p1Move = input.nextLine();
							legalInput = false;
						}
						else {
							game.board.moveResult(p1Char, boardIndex, boardPos);		
							System.err.println( "You just made move " + boardIndex + " " + boardPos);
							legalInput = true;
						}
					}
				}
				
				if(game.board.overallGameStatus != 'n') {
					break;
				}
				
				//Computer makes move
				int [] computerMove = compPlayer.hMinimaxDecision();
				
				game.board.moveResult(game.p2Char, computerMove[0], computerMove[1]);
				
				System.err.println( "Computer player just made move " + computerMove[0] + " " + computerMove[1]);				
				
			}
			
			//after loop terminates, print the game result
			game.board.printGameResult();
			game.board.displayBoard();
			
			//clear board for next game
			game.board.clearBoard();

		}
		
	
	}
}

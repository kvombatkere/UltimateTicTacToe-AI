//Karan Vombatkere, Rebecca Ho Van Dyke + Avram Webberman
//February 2018
//CSC 442: AI Project 01 - Tic Tac Toe


//Automate games between bots
public class RobotBattle {

	
	public static void main(String[] args) {
		
		int hWin = 0;
		int numDraws = 0;
		
		for(int i=0; i<10; i++) {
			
			System.out.println("Instantiated Robot Battle " + (i+1));

			AdvancedTTTGame robotGame = new AdvancedTTTGame();
			
			AdvancedMinimaxPlayer heuristicBot = new AdvancedMinimaxPlayer(robotGame);
			
			AdvancedRandomPlayer rBot = new AdvancedRandomPlayer(robotGame.board);
			
			char randChar = 'X';
			robotGame.p2Char = 'O';
			
			robotGame.board.moveResult(randChar, 2, 2);
	
			//Inner loop to play the game
			while(robotGame.board.overallGameStatus == 'n') {
				//robotGame.board.displayBoard();
				
				int[] hMove = heuristicBot.hMinimaxDecision();
				robotGame.board.moveResult(robotGame.p2Char, hMove[0], hMove[1]);
				
				int[] randMove = rBot.randomMove();
				robotGame.board.moveResult(randChar, randMove[0], randMove[1]);
				
			}	
			
			if(robotGame.board.overallGameStatus == robotGame.p2Char) {
				hWin += 1;
			}
			
			if(robotGame.board.overallGameStatus == 'd') {
				numDraws += 1;
			}
			//robotGame.board.displayBoard();
			//robotGame.board.printGameResult();
		}
		
		System.out.println("Heuristic Bots Wins = " + hWin +", Draws = "+numDraws);
	}

}

import java.io.*;
import java.util.*;
import java.awt.*;
public class MainGame {
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Scanner input = new Scanner(System.in);
		char gameVersion = ' ';
		
		//main loop containing option to play all three games
		while(true) {

			//loop to ensure valid input from user
			while(gameVersion != '1' && gameVersion != '2' && gameVersion != '3' && gameVersion != 'q') {
				
				System.err.println("What type of Tic-Tac-Toe would you like to play? (Enter 'q' to quit)");
				System.err.println("(1) Traditional");
				System.err.println("(2) Advanced");
				System.err.println("(3) Ultimate");
				
				gameVersion = input.next().charAt(0);
	
				if(gameVersion != '1' && gameVersion != '2' && gameVersion != '3' && gameVersion != 'q') {
					System.err.println("Please enter a valid option");
				}
			
			}
		
			//makes call to play selected game or quit
			if (gameVersion == '1') {
				TTTGame.main(null);
			}
			
			else if (gameVersion == '2') {
				//Call main method of version 2 
				
			}
			
			else if (gameVersion == '3') {
				//Call main method of version 3
			}
			
			else if (gameVersion == 'q') {
				System.err.println("Thanks for playing!..Quitting...");
				break;
			}

				
		}
	}
	
}

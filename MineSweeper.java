import java.util.Scanner;
import java.util.Random;
// =================================================================================================================================



// =================================================================================================================================
public class MineSweeper {
// =================================================================================================================================



    // =============================================================================================================================
    public static Scanner keyboard = new Scanner(System.in);

    public static final int REVEAL = 1;
    public static final int FLAG   = 2;
    // =============================================================================================================================

    

    // =============================================================================================================================
    public static void main (String[] args) {

	if (args.length != 2) {
	    showUsageAndExit();
	}

	int numMines = 0;
	int sideSize = 0;
	try {
	    numMines = Integer.parseInt(args[0]);
	    sideSize = Integer.parseInt(args[1]);
	} catch (NumberFormatException e) {
	    showUsageAndExit();
	}

	if ((numMines <= 0) || (sideSize <= 0)) {
	    showUsageAndExit();
	}

	Cell[][] grid = populate(sideSize, numMines);

	showGrid(grid);
	int gameCount = 0;
	int [] choice = new int [3];
	
		
	while(gameCount == 0) {
		
		 choice = getCommand(sideSize);
		 
		 if (choice[0] == REVEAL) {
			 
			 grid[choice[1]][choice[2]].setVisible();
						
		 
			 if (grid[choice[1]][choice[2]].getMined() == true) {
				 
				 System.out.println("\nBOOOOOM! \nI AM SORRY YOU HAVE LOST THE GAME!");
				 gameCount = 1;
			 } 
			 // THIS PART SHOWS EXPANDS THE SURROUNDING BLOCKS OF AN EMPTY SPOT UNTIL WE HIT SPOT THAT NEIGHBOR MINES
			 if(grid[choice[1]][choice[2]].getMinedNeighbors() == 0) {
				revealneighbors(grid, choice[1], choice[2], sideSize);
				 		 				
			}
			 
			 
			 showGrid(grid);
		 }
		if (choice[0] == FLAG) {
			
			
			grid[choice[1]][choice[2]].toggleFlagged ();
			grid[choice[1]][choice[2]].getFlagged();
			
			showGrid(grid);
		}
		
	
		
	}
	
	
	//play(mines);
	
    } // main ()
    // =============================================================================================================================



    // =============================================================================================================================
    public static void showUsageAndExit () {

	System.err.println("USAGE: java Minesweeper <number of mines> <grid size on each side>");
	System.exit(1);
	
    } // showUsageAndExit
    // =============================================================================================================================



    // =============================================================================================================================
    public static Cell[][] populate (int sideSize, int numMines) {

	// SET UP THE GRID.
	Cell grid [] [] = new Cell [sideSize][sideSize];
	for (int row = 0; row < sideSize; row++){
	    for (int col =0; col < sideSize; col++){
		grid[row][col] = new Cell();

	    }
	}// SET UP` MINES 
	for (int i = 0; i < numMines; i++) {
	    int s = (int)(Math.random() * (sideSize));
	    int t = (int)(Math.random() * (sideSize));
	    grid[s][t].setMined();
	}
	//SHOWS PLACED MINES 
	/*for (int row = 0; row < sideSize; row++){
	    for (int col =0; col < sideSize; col++){
		if(grid[row][col].getMined() == true) {
			
			grid[row][col].setVisible();
		}
	   }
	 }*/
	  //CALLS METHOD TO CHECK FOR NEIGHBOR MINES
	    for (int rows = 0; rows < sideSize; rows++){
		    for (int cols =0; cols < sideSize; cols++){
		    	
		    	neighbor(grid, rows, cols, sideSize);
		    }
			 				
	}

	return grid;
    } // populate ()
    // =============================================================================================================================

    
    //RECURSIVE METHOD THAT REVEALS ALL EMPTY AND MINE-NEIGHBORING CELLS
    
    public static void revealneighbors (Cell [][] grid, int row, int col, int size) {
    	
    	System.out.println("Running on: " + row + " | " + col );
    	if(grid[row][col].getMinedNeighbors() != 0 && grid[row][col].getMined() == false) {
    		grid[row][col].setVisible();
    		return;
    	}
    	else {
    		for (int i = row - 1; i <= row + 1; i++){
    			for (int k = col -1; k <= col + 1; k++){
    				
    				if(i == row && k == col ) {
						continue;
				}
    				  		
    				if (i <= -1 || k <= -1 || i >=size || k >= size) {
    					continue;
       				}
    				
    				if(grid[i][k].getMinedNeighbors() == 0 && grid[i][k].getVisibility() == false) {
    		    		grid[i][k].setVisible();
    		    		revealneighbors(grid, i, k, size);
    		    	
    				}		
    				grid[i][k].setVisible();
    				
    				
    		 		}
       	}
    	}
    	}
    	
    	
    
    
    //SHOWS # OF ADJACENT MINES
    public static int neighbor (Cell [][] grid, int row, int col, int size) {

		for (int i = row - 1; i <= row + 1; i++){
			for (int k = col -1; k <= col + 1; k++){
	    	
				if (i <= -1 || k <= -1 || i >=size || k >= size) {
					continue;
				}
				
				if(grid[i][k].getMined() == true) {	
					
						
							if(i == row && k == col ) {
									continue;
							}
			
							grid[row][col].countLiveNeighbor();
				}
		
	    }

	}
	
		return grid[row][col].getMinedNeighbors();
}

    // =============================================================================================================================
    public static void play (Cell[][] mines) {

	// PLAY THE GAME.
    	
    		
    } // play ()
    // =============================================================================================================================



    // =============================================================================================================================
    public static void showGrid (Cell[][] mines) {

    	System.out.println();

    	// Show the column numbers
    	System.out.print("\t");
    	for (int col = 0; col < mines[0].length; col = col + 1) {
    	    System.out.print("\t" + col);
    	}
    	System.out.println();
    	System.out.print("\t");
    	for (int col = 0; col < mines[0].length; col = col + 1) {
    	    System.out.print("--------");
    	}
    	System.out.println();

    	// Show the grid...
    	for (int row = 0; row < mines.length; row = row + 1) {

    	    // ...with leading row numbers.
    	    System.out.print(row + "\t|\t");
    	    for (int col = 0; col < mines[row].length; col = col + 1) {
    		System.out.print(mines[row][col].toString() + '\t');
    	    }
    	    System.out.println();
    	}

    	System.out.println();

        } // showGrid ()
    // =============================================================================================================================



    // =============================================================================================================================
    public static int[] getCommand (int gridSize) {

	System.out.print("Enter a move (H for help): ");
	String[] entry = keyboard.nextLine().toUpperCase().split("\\s+");

	if (entry.length != 3 || entry[0].length() != 1) {
	    printMenu();
	    return getCommand(gridSize);
	}

	int op = -1;
	if (entry[0].charAt(0) == 'R') {
	    op = REVEAL;
	} else if (entry[0].charAt(0) == 'F') {
	    op = FLAG;
	} else {
	    printMenu();
	    return getCommand(gridSize);
	}

	int row = -1;
	int col = -1;
	try {
	    row = Integer.parseInt(entry[1]);
	    col = Integer.parseInt(entry[2]);
	} catch (NumberFormatException e) {
	    printMenu();
	    return getCommand(gridSize);
	}

	if (! (1 <= row && row <= gridSize) && (1 <= col && col <= gridSize) ) {
	    printMenu();
	    return getCommand(gridSize);
	}

	return new int[] { op, row, col };
	
    }
    // =============================================================================================================================



    // =============================================================================================================================
    public static void printMenu () {
	System.out.println("Command format: [R|F|H] [row #] [col #]\n" +
			   "\tR = Reveal a cell\n" +
			   "\tF = Flag a cell\n" +
			   "\tH = Help (this info)\n" +
			   "\tExample: R 3 6 (to reveal the cell in row 3, column 6");
    }
    // =============================================================================================================================


    
// =================================================================================================================================
} // class Minesweeper
// =================================================================================================================================

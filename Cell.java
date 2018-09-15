public class Cell {

    private int     minedNeighbors;
    private boolean isMine;
    private boolean isVisible;
    private boolean isFlagged;

    private final static String HIDDEN  = "H";
    private final static String EMPTY   = " ";
    private final static String FLAGGED = "f";
    private final static String MINED   = "!";

    public Cell () {

	minedNeighbors = 0;
	isMine         = false;
	isVisible      = false;
	isFlagged      = false;

    }

    public int getMinedNeighbors () {
	return minedNeighbors;
    }

    public void countLiveNeighbor () {
	minedNeighbors = minedNeighbors + 1;
    }

    public boolean getMined () {
	return isMine;
    }

    public void setMined () {
	isMine = true;
    }

    public boolean getVisibility () {
	return isVisible;
    }

    public void setVisible () {
	isVisible = true;
    }

    public boolean getFlagged () {
	return isFlagged;
    }

    public void toggleFlagged () {
	isFlagged = !isFlagged;
    }

    public String toString () {

	if (isVisible) {
	    
	    if (isMine) {
		return MINED;
	    } else if (minedNeighbors == 0) {
		return EMPTY;
	    } else {
		return Integer.toString(minedNeighbors);
	    }

	} else {

	    if (isFlagged) {
		return FLAGGED;
	    } else {
		return HIDDEN;
	    }
	    
	}
	
    }
    
} // class Cell

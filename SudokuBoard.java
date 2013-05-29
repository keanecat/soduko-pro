public class SudokuBoard {
    final int EMPTY = 0;      // Empty cells marker
    final int size;           // Size of the board (number of rows and columns)
    final int box_size;       // Size of the inner boxes

    private int[][] board;    // 2D array representing the game board

    /**
     * Creates an empty board.
     * @param size Number of rows and columns of the board.
     */
    public SudokuBoard(int size) {
        board = new int[size][size];
        this.size = size;
        this.box_size = (int) Math.sqrt(size);
        
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
        		board[i][j]=0;
        	}
        }
    }
    
    /**
     * Creates and initializes the board.
     * @param board Array to initialize the board contents.
     */
    public SudokuBoard(int[][] board) {
        this(board.length);
        this.board = board;
    }
    
    private void copyBoard(int[][] from){
    	for(int i=0;i<size;i++){
    		for(int j=0;j<size;j++){
    			this.board[i][j] = from[i][j];
    		}
    	}
    }
    
    public SudokuBoard(SudokuBoard board) {
        this.size = board.size;
        this.box_size = board.box_size;
    	this.board = new int[size][size];
    	copyBoard(board.board);
    }
    
    
    /**
     * Puts a number into a specific cell.
     * @param num Number to put into the board cell.
     * @param row Cell's row.
     * @param col Cell's column.
     */
    public void setCell(int num, int row, int col) {
        board[row][col] = num;
    }

    /**
     * Returns the number contained in a specific cell.
     * @param row Cell's row.
     * @param col Cell's column.
     * @return The number contained in the cell.
     */
    public int getCell(int row, int col) {
        return board[row][col];
    }
    
    public boolean isValid(int num, int row, int col){
    	
    	int quadrant = 0;
    	
    	//Ascertain which quadrant row,col fall into
    	if(row < box_size){
    		if(col < box_size){ //3
        		quadrant = 1;
        	} else if (col >= box_size && col <(box_size*2)){ //3, 6 
        		quadrant = 2;
        	} else if (col >= (box_size*2) && col < size){ // 6,9
        		quadrant = 3;
        	}
    	} else if (row >= box_size && row <(box_size*2)){
    		if(col < box_size){
        		quadrant = 4;
        	} else if (col >= box_size && col <(box_size*2)){
        		quadrant = 5;
        	} else if (col >= (box_size*2) && col < size){
        		quadrant = 6;
        	}
    	} else if (row >= (box_size*2) && row < size){
    		if(col < box_size){
    			quadrant = 7;
        	} else if (col >= box_size && col <(box_size*2)){
        		quadrant = 8;
        	} else if (col >= (box_size*2) && col < size){
        		quadrant = 9;
        	}
    	}
    	
    	//If it's not a valid move in the quadrant
    	if(!validInQuadrant(quadrant,num)){
    		return false;
    	} 
    	
    	//If it's not a valid move in the row & column it's in
    	if(!validInRowCol(num,row,col)){
    		return false;
    	}
    	
    	return true;
    }
    
    private boolean validInRowCol(int num, int row, int col){
    	for(int i=0;i<size;i++){
    		if(board[i][col] == num){
    			return false;
    		}
    		if(board[row][i] == num){
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean validInQuadrant(int quad, int num){
    	
    	if (quad <= 3){
    		for(int row=0;row<3;row++){
    			for(int col=box_size*(quad-1);col<quad*box_size;col++){
    				if(num == board[row][col]){
    					return false;
    				}
    			}
    		}
    	} else if (quad >=4 && quad <=6){
    		for(int row=3;row<6;row++){
    			for(int col=box_size*(quad-4);col<(quad-3)*box_size;col++){
    				if(num == board[row][col]){
    					return false;
    				}
    			}
    		}
    	} else {
    		for(int row=6;row<9;row++){
    			for(int col=box_size*(quad-7);col<(quad-6)*box_size;col++){
    				if(num == board[row][col]){
    					return false;
    				}
    			}
    		}
    	}
    	return true;
    }
    
	public int[] findBlankSquare(){
		int[] retVal = {-1,-1};
		//For each row
		for(int row=0;row<size;row++){
			//And each column
			for(int col=0;col<size;col++){
				//If square is blank
				if(board[row][col]==0){
					retVal[0]=row;
					retVal[1]=col;
					return retVal;
				}
			}
		}
		return retVal;
	}
    
	
	public void printBoard(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(board[i][j]!= 0){
					System.out.print(board[i][j]+" ");
				} else {
					System.out.println("   ");
				}
				
			}
			System.out.print("\n");
		}
	}
}

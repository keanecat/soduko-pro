import java.util.ArrayList;

/**
 * 
 * @author Karn
 *
 */

public class Solver {
  ArrayList<SudokuBoard> pQueue;
	
	public Solver (SudokuBoard startBoard){
		pQueue = new ArrayList<SudokuBoard>();
		pQueue.add(startBoard);
		solve();
	}
	
	private void solve(){
		//findBlankSquare 
		//insert a valid number 
		//put board on stack
		//pop and repeat
		
		boolean found = false;
		while(!pQueue.isEmpty() ){

			//Pop queue
			SudokuBoard board = pQueue.remove(0);
			//Find co-ordinates of first blank square
			int[] blankSq= board.findBlankSquare();
			if(blankSq[0] == -1){
				board.printBoard();
				found = true;
				break;
			}
			//Insert a valid number
			getChildren(blankSq[0],blankSq[1],board);
		}
		if(!found){
			System.out.println("No solution found.");
		}
	}
	
	private void getChildren(int row, int col, SudokuBoard parent){

		for(int num=1;num<=9;num++){
			if(parent.isValid(num, row, col)){
				//Create new SudokuBoard
				SudokuBoard child = new SudokuBoard(parent);
				//Set valid value in blank square
				child.setCell(num, row, col);
				//Add child to data structure
				pQueue.add(0,child);
			}
		}
	}
	
}

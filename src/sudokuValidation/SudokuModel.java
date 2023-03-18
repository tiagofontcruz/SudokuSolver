package sudokuValidation;

import java.util.HashSet;
import java.util.Scanner;

public class SudokuModel extends SudokuFile {
                                                                                                        
	private Integer value;                                                                              //Declares an integer for current value
	private Integer boardArray[][] = new Integer[9][9];                                                 //Declares and instantiates a global static array 9 by 9
                                                                                                        
	public void populateArray(String data) {                                                            
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	populateArray()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method populates the multidimensional array.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		@SuppressWarnings("resource")                                                                   
		Scanner input = new Scanner(data);                                                              //Declares and instantiates a Scanner object
		for (int i = 0; i < 9; i++) {                                                                   //Loops for rows
			for (int j = 0; j < 9; j++) {                                                               //Loops for columns
				try {                                                                                   
					boardArray[i][j] = Integer.parseInt(input.next());					                //Inserts each element into the static array
				} catch (Exception ex) {                                                                
					boardArray[i][j] = 0;                                                               //If insertion fails adds a zero on that position.
				}                                                                                       
			}
		}
	}

	public boolean isSudokuRowValid(Integer[][] board, int row, int col, HashSet<Integer> rowHash) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	isSudokuRowValid()
		//
		// Method parameters	:	Integer[][] board, int row, int col, HashSet<Integer> rowHash
		//
		// Method return		:	boolean
		//
		// Synopsis				:   This method receives a multidimensional array and tries to add each number into a HashSet							
		//							if HashSet adding fails, the number is not unique in a row. Therefore the grid is not valid.
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		boolean valid = true;                                                                           //Declares and sets true to a boolean for validation state 
		value = board[row][col];		                                                                //Sets the current value of local static array into a variable
		if (value != null && value > 0 && value < 10) {                                                 //Verifies if the variable is not null and belongs to a range
			if (!rowHash.add(value)) {				                                                    //Verifies if adding into a HashSet is successful
					valid = false;					                                                    //If can not add into HashSet turns into invalid row
			}                                                                                           
		}                                                                                               
		return valid;                                                                                   //Return the result
	}

	public boolean isSudokuColValid(Integer[][] board, int col, int row, HashSet<Integer> colHash) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	isSudokuColValid()
		//
		// Method parameters	:	Integer[][] board, int col, int row, HashSet<Integer> colHash
		//
		// Method return		:	boolean
		//
		// Synopsis				:   This method receives a multidimensional array and tries to add each number into a HashSet							
		//							if HashSet adding fails, the number is not unique in a col. Therefore the grid is not valid.
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		boolean valid = true;                                                                           //Declares and sets true to a boolean for validation state 
		value = board[col][row];                                                                        //Sets the current value of local static array into a variable
		if (value != null && value > 0 && value < 10) {                                                 //Verifies if the variable is not null and belongs to a range
			if (!colHash.add(value)) {                                                                  //Verifies if adding into a HashSet is successful
				valid = false;                                                                          //If can not add into HashSet turns into invalid column
			}                                                                                           
		}                                                                                               
		return valid;                                                                                   //Return the result
	}

	public boolean isSudokuBlockValid(Integer[][] board, int row, int col, HashSet<Integer> blockHash) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	isSudokuBlockValid()
		//
		// Method parameters	:	Integer[][] board, int row, int col, HashSet<Integer> rowHash
		//
		// Method return		:	boolean
		//
		// Synopsis				:   This method receives a multidimensional array and tries to add each number into a HashSet							
		//							if HashSet adding fails, the number is not unique in a block. Therefore the grid is not valid.
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		boolean valid = true;                                                                           //Declares and sets true to a boolean for validation state 
		int x = (int) (3 * Math.floor(row / 3) + Math.floor(col / 3));                                  //Declares and sets the x (row) coordinates for the block 
		int y = ((row * 3) % 9) + (col % 3);                                                            //Declares and sets the y (column) coordinates for the block
		value = board[x][y];                                                                            //Sets the current value of local static array into a variable
		if (value != null && value > 0 && value < 10) {                                                 //Verifies if the variable is not null and belongs to a range
			if (!blockHash.add(value)) {                                                                //Verifies if adding into a HashSet is successful
				valid = false;                                                                          //If can not add into HashSet turns into invalid column
			}                                                                                           
		}                                                                                               
		return valid;                                                                                   //Returns the result
	}

	public Integer[][] getBoardArray() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	getBoardArray()
		//
		// Method parameters	:	none
		//
		// Method return		:	Integer[][]
		//
		// Synopsis				:   This method returns the multidimensional array of Integers.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		return boardArray;                                                                              //Returns the static array
	}                                                                                                   

	public Integer getBoardArray(int row, int col) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	getBoardArray()
		//
		// Method parameters	:	int row, int col
		//
		// Method return		:	Integer
		//
		// Synopsis				:   This method returns a Integer in a certain position of a multidimensional array.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		return boardArray[row][col];                                                                     //Returns a certain position of the static array
	}                                                                                                    
}

package sudokuValidation;

public class SudokuMain {

	public static void main(String[] args) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	void main()
		//
		// Method parameters	:	String[] args - the method permits String command line parameters to be entered
		//
		// Method return		:	void
		//
		// Synopsis				:   This main method instantiates the classes and uses the objects as a parameter to controller class. 							
		//							
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				Initial setup
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		
		SudokuView theView = new SudokuView();														//Declares and instantiates the class SudokuView
		SudokuModel theModel = new SudokuModel();								                    //Declares and instantiates the class SudokuModel
		@SuppressWarnings("unused")                                                                 
		SudokuController theController = new SudokuController(theView, theModel);		            //Declares and instantiates the class SudokuController
		theView.setVisible(true);                                                                   //Sets the form visible 
	}

}

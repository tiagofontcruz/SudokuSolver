package sudokuValidation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class SudokuController {                                                                                    
	private SudokuView theView;                                                                                    //Declares an object from SudokuView
	private SudokuModel theModel;                                                                                  //Declares an object from SudokuModel  
	private HashSet<String> filePath;                                                                              //Declares a HashSet for the file's path
	private String fileName;                                                                                       //Declares a String for the file's path
	private String selection;                                                                                      //Declares a String for the jList item
	private Highlighter hl;                                                                                        //Declares a Highlighter object 

	public SudokuController(SudokuView theView, SudokuModel theModel) {                                            
		filePath = new HashSet<String>();                                                                          //Declares and instantiates a HashSet for file's path
		this.theView = theView;                                                                                    //Sets the parameter by value
		this.theModel = theModel;                                                                                  //Sets the parameter by value
		this.theView.addLoadFile(new LoadFile());                                                                  //Call a method and instantiates a object to load file
		this.theView.addSaveFile(new SaveFile());                                                                  //Call a method and instantiates a object to save file
		this.theView.help(new HelpMenu());                                                                         //Call a method and instantiates a object to use help menu
		this.theView.itemListener(new ItemFileBoxListener());                                                      //Call a method and instantiates a object to check a jComboBox
		this.theView.addValidate(new ValidadeSudoku());		                                                       //Call a method and instantiates a object to validate the grid
		this.theView.keyListener(new KeyTextAreaListener());                                                       //Call a method and instantiates a object to check key typed 
		this.theView.listSelection(new ListErrorsSelectionListener());		                                       //Call a method and instantiates a object to check a jList
		this.theView.setDisableValidateButton();                                                                   //Set validation button to disable state mode
	}
	
	class ItemFileBoxListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			// Method				:	itemStateChanged()
			//
			// Method parameters	:	ItemEvent e
			//
			// Method return		:	void
			//
			// Synopsis				:   This method verifies if user clicked in a combo box list and opens the file.							
			//
			// Modifications		:
			//							Date			Developer				Notes
			//							----			---------				-----
			//							2022-09-21		Tiago   				ItemListener method type.
			//
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			if(e.getStateChange() == ItemEvent.SELECTED) {                                                         //Verifies if the selection was changed 
				try {                                                                                              
					fileName = theView.getFileBox();                                                               //Sets the file's path
					hl = theView.getTextArea().getHighlighter();                                                   //Collects the area highlighted
					if (hl != null) {                                                                              //Verifies if highlights exists in jTextArea
						hl.removeAllHighlights();						                                           //Remove the highlights
					}                                                                                              
					theModel.openFile(fileName, theView.getTextArea());                                            //Opens a file content in the jTextArea
				} catch (IOException ex) {                                                                         
					theView.displayMessage("Something went wrong!");                                               //Displays a message if it fails
					ex.printStackTrace();                                                                          //Tracks the error in debug display
				}                                                                                                  
			}			
		}		
	}

	class KeyTextAreaListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			// Method				:	keyTyped()
			//
			// Method parameters	:	KeyEvent e
			//
			// Method return		:	void
			//
			// Synopsis				:   This method verifies if user pressed a key different from 0 to 9 and consumes the character.							
			//
			// Modifications		:
			//							Date			Developer				Notes
			//							----			---------				-----
			//							2022-09-21		Tiago   				KeyAdapter method type.
			//
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			try {
				if (!String.valueOf(e.getKeyChar()).matches("[0-9]")) {                                            //Verifies key typed matches with a range
					e.consume();                                                                                   //Consumes different key typed
				}				                                                                                   
			} catch (Exception ex) {                                                                               
				theView.displayMessage("Something went wrong!");                                                   //Displays a message
			}                                                                                                      
		}
	}
	
	class ListErrorsSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			// Method				:	valueChanged()
			//
			// Method parameters	:	ListSelectionEvent e
			//
			// Method return		:	void
			//
			// Synopsis				:   This method extracts numbers from a text in a jList, split the numbers turning into positions							
			//							and highlights the position in the board.
			// Modifications		:
			//							Date			Developer				Notes
			//							----			---------				-----
			//							2022-09-21		Tiago   				ListSelectionListener method type.
			//
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			try {                                                                                                  
				selection = theView.getJListErrors();                                                              //Sets a text from a position in jList
				if (selection != null) {				                                                           //Verifies if is not null
					selection = selection.replaceAll("[^1-9]", "");			                                       //Replaces all characters where not in the range for empty (remove them all)
					int i = Integer.parseInt(String.valueOf(String.valueOf(selection).charAt(0)));                 //Parses collects the first number of a String (example: 12) it gets 1
					int j = Integer.parseInt(String.valueOf(String.valueOf(selection).charAt(1)));                 //Parses collects the second number of a String (example: 12) it gets 2
					int row = (i - 1) * 18;                                                                        //Row is calculated prediction 17 positions in the jTextArea
			        int posInit = j - 1;                                                                           //Initial position is calculated (-1) to jump for zero position in jTextArea
			        posInit = 2 * posInit + row;                                                                   //Initial position is calculated in a sum of Initial position + row to jump rows if necessary
			        int posFin = posInit + 1;                                                                      //Final position is the next position of Initial position.
					hl = theView.getTextArea().getHighlighter();                                                   //Collects the area highlighted
					hl.removeAllHighlights();											                           //Remove the highlights
					HighlightPainter red = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);              //Declares and instantiates a highlight red
					hl.addHighlight(posInit, posFin, red);                                                         //Paints the red color in the selected position
				}                                                                                                  
			} catch (BadLocationException ex) {				                                                       
				ex.printStackTrace();                                                                              //Tracks the error in debug display
			}
		}		
	}
	
	class LoadFile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			// Method				:	actionPerformed()
			//
			// Method parameters	:	ActionEvent e
			//
			// Method return		:	void
			//
			// Synopsis				:   This method loads a file.							
			//
			// Modifications		:
			//							Date			Developer				Notes
			//							----			---------				-----
			//							2022-09-21		Tiago   				ActionListener method type.
			//
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			try {                                                                                                  
				theView.clearJListErrors();				                                                           //Clears the jList
				JFileChooser chooser = new JFileChooser(".");                                                      //Open the file box to choose a file on the same directory of the project
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");         //Filter to show only text files
				chooser.setFileFilter(filter);                                                                     //Sets the filter
				chooser.showOpenDialog(null);                                                                      //Empty dialog
				if (chooser.getSelectedFile() != null) {					                                       //Verifies if file was selected
					File f = chooser.getSelectedFile();					                                           //Declares and instantiates the file
					fileName = f.getAbsolutePath();					                                               //Sets the absolute file's path
					theModel.openFile(fileName, theView.getTextArea());                                            //Opens the file into jTextArea
					hl = theView.getTextArea().getHighlighter();                                                   //Collects the area highlighted             
					if (hl != null) {                                                                              //Verifies if highlights exists in jTextArea
						hl.removeAllHighlights();						                                           //Remove the highlights                     
					}                                                                                              
				}                                                                                                  
				if (!theView.getTextAreaText().isEmpty()) {                                                        //Verifies if jTextArea content is not empty
					theModel.populateArray(theView.getTextAreaText());                                             //Populates the static array
					theView.setEnableValidateButton();                                                             //Enables the validation button
				}                                                                                                  
			} catch (NumberFormatException | IOException ex) {                                                     
				theView.displayMessage("Something went wrong!");                                                   //Displays a message
			}
		}
	}

	class SaveFile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			// Method				:	actionPerformed()
			//
			// Method parameters	:	ActionEvent e
			//
			// Method return		:	void
			//
			// Synopsis				:   This method saves a file.							
			//
			// Modifications		:
			//							Date			Developer				Notes
			//							----			---------				-----
			//							2022-09-21		Tiago   				ActionListener method type.
			//
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			try {                                                                                                   
				theView.clearJListErrors();                                                                         //Clears the jList
				if(theView.getFileBox() != null && theView.getFileBox() == fileName) {                              //Verifies if there is a file's path into the combo box and the file's path is different 
					fileName = theView.getFileBox();					                                            //Sets the new file's path
				}                                                                                                   
				theModel.saveFile(fileName, theView.getTextAreaText());                                             //Saves the file's path
				if(filePath.add(fileName)) {                                                                        //Verifies if the file's path was add successfully
					theView.addFileBox(fileName);					                                                //Adds file's path into the combo box
				}                                                                                                   
				theView.displayMessage("File saved successfully.");                                                 //Display a message
			} catch (NumberFormatException | IOException ex) {                                                      
				theView.displayMessage("Something went wrong!");                                                    //Display a message
			}                                                                                                       
		}                                                                                                           
	}
	
	class HelpMenu implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			// Method				:	actionPerformed()
			//
			// Method parameters	:	ActionEvent e
			//
			// Method return		:	void
			//
			// Synopsis				:   This method displays the software's instructions.							
			//
			// Modifications		:
			//							Date			Developer				Notes
			//							----			---------				-----
			//							2022-09-21		Tiago   				ActionListener method type.
			//
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			theView.displayMessage("- To validate the Sudoku board, you must load a file.\n"                        //Display a message in Menu "Help" option
					+ "- To switch files, you must load and save them.\n"                                           
					+ "- The ComboBox will be filled up after saving a file.\n"					                    
					+ "- The user can save many files.\n"                                                           
					+ "- The board accepts only 0 for empty spots and 1 to 9 for validation.");                     
		}		
	}

	class ValidadeSudoku implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			// Method				:	actionPerformed()
			//
			// Method parameters	:	ActionEvent e
			//
			// Method return		:	void
			//
			// Synopsis				:   This method validates the board, displays the result and errors in a jList (if exist).							
			//
			// Modifications		:
			//							Date			Developer				Notes
			//							----			---------				-----
			//							2022-09-21		Tiago   				ActionListener method type.
			//
			// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
			theView.clearJListErrors();                                                                                                    //Clear the jList
			try {                                                                                                                          
				boolean validation = false;                                                                                                //Declares and sets a boolean for validation status
				HashSet<Integer> rowHash = new HashSet<Integer>();                                                                         //Declares and instantiates a HashSet for rows
				HashSet<Integer> colHash = new HashSet<Integer>();                                                                         //Declares and instantiates a HashSet for columns
				HashSet<Integer> blockHash = new HashSet<Integer>();                                                                       //Declares and instantiates a HashSet for blocks
				                                                                                                                           
				if (!theView.getTextAreaText().isEmpty()) {                                                                                //Verifies if jTextArea content is not empty   
					theModel.populateArray(theView.getTextAreaText());                                                                     //Populates the static array                   
					validation = true;                                                                                                     //Enables the validation button                
					                                                                                                                       
					if(theView.getTextAreaText().length() == 161) {                                                                        //Verifies if the length of the jTextArea's content is correct
						for (int i = 0; i < 9; i++) {                                                                                      //Loop for rows
							for (int j = 0; j < 9; j++) {								                                                   //Loop for columns
								if (!theModel.isSudokuRowValid(theModel.getBoardArray(), i, j, rowHash)) {                                 //Verifies if there is no unique values in the row
									theView.addJListErrors("Row invalid in position " + (i + 1) + " and col: " + (j + 1));                 //Sets a item on a jList
									validation = false;                                                                                    //Sets validation to false (invalid)
								}								                                                                           
							}                                                                                                              
							rowHash.clear();                                                                                               //Clears each row in the HashSet
						}				                                                                                                   
						for (int i = 0; i < 9; i++) {                                                                                      //Loop for rows                            
							for (int j = 0; j < 9; j++) {						                                                           //Loop for columns                         
								if (!theModel.isSudokuColValid(theModel.getBoardArray(), j, i, colHash)) {                                 //Verifies if there is no unique values in the column  
									theView.addJListErrors("Column invalid in position " + (i + 1) + " and row: " + (j + 1));              //Sets a item on a jList                   
									validation = false;                                                                                    //Sets validation to false (invalid)       
								}                                                                                                                                                     
							}                                                                                                                                                         
							colHash.clear();                                                                                               //Clears each column in the HashSet           
						}                                                                                                                  
						for (int i = 0; i < 9; i++) {                                                                                      //Loop for rows
							for (int j = 0; j < 9; j++) {								                                                   //Loop for columns 
								if (!theModel.isSudokuBlockValid(theModel.getBoardArray(), i, j, blockHash)) {                             //Verifies if there is no unique values in the block
									int x = (int) (3 * Math.floor(i / 3) + Math.floor(j / 3));                                             //Declares and sets the x (row) coordinates for the block
									int y = ((i * 3) % 9) + (j % 3);                                                                       //Declares and sets the y (column) coordinates for the block
									theView.addJListErrors("Block invalid in row " + (x + 1) + " and col: " + (y + 1));                    //Sets a item on a jList
									validation = false;                                                                                    //Sets validation to false (invalid)
								}                                                                                                          
							}                                                                                                              
							blockHash.clear();                                                                                             //Clears each block in the HashSet
						}                                                                                                                  
					}else {                                                                                                                //else
						theView.displayMessage("You must enter integer numbers\nbetween 1 and 9 (inclusive)\nor ZEROS for empty spots.");  //Displays a message if the numbers are incorrect
						validation = false;                                                                                                //Sets validation to false (invalid)
					}					                                                                                                   
				}				                                                                                                           
				if (validation) {										                                                                   //Verifies if validation is true
					hl = theView.getTextArea().getHighlighter();                                                                           //Collects the area highlighted                 
					hl.removeAllHighlights();											                                                   //Remove the highlights                         
					HighlightPainter green = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);                                  //Declares and instantiates a highlight green     
					hl.addHighlight(0, 161, green);					                                                                       //Paints the green color all the board 
					theView.displayMessage("The Sudoku board is VALID!");                                                                  //Displays a message
				} else {                                                                                                                   //else
					hl = theView.getTextArea().getHighlighter();                                                                           //Collects the area highlighted
					if (hl != null) {                                                                                                      //Verifies if highlights exists in jTextArea
						hl.removeAllHighlights();						                                                                   //Removes the highlights
					}                                                                                                                      
					theView.displayMessage("The Sudoku board is INVALID!");                                                                //Displays a message
				}                                                                                                                          
			} catch (NumberFormatException | BadLocationException ex) {                                                                    
				theView.displayMessage("Something went wrong!");                                                                           //Displays a message
			}                                                                                                                              
		}
	}
}

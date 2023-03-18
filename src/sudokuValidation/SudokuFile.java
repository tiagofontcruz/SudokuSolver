package sudokuValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextArea;

public class SudokuFile {

	public void openFile(String fileName, JTextArea textArea) throws IOException {	
    	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	openFile()
		//
		// Method parameters	:	String fileName, JTextArea textArea
		//
		// Method return		:	void
		//
		// Synopsis				:   This method opens a file an loads the text content into an object.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-04-06		Tiago   				Second method called.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		FileReader reader = new FileReader(fileName);                                                  //Declares and instantiates to reads the file's path
		BufferedReader br = new BufferedReader(reader);                                                //Declares and instantiates to buffered the content
		textArea.read(br, null);                                                                       //The object reads the content
		br.close();                                                                                    //Closes the content
		textArea.requestFocus();                                                                       //Focuses on the object
	}                                                                                                  
	                                                                                                   
	public void saveFile(String fileName, String textAreaText) throws IOException {
    	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	saveFile()
		//
		// Method parameters	:	String fileName, String textAreaText
		//
		// Method return		:	void
		//
		// Synopsis				:   This method saves a file opened.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-04-06		Tiago   				Second method called.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		FileWriter file = new FileWriter(fileName);                                                    //Declares and instantiates to writes the file's path
		file.write(textAreaText);                                                                      //Save the content into a file
		file.close();	                                                                               //Closes the file
	}
	
	
}

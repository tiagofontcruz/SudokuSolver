package sudokuValidation;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SudokuLoadGrid extends JTextArea {

	private static final long serialVersionUID = 1L;                                          //Serializes the object when instantiated
	private Image image;                                                                      //Declares an image object
                                                                                              
    public SudokuLoadGrid(int a, int b) {                                                     //Constructor
        try{                                                                                  
            image = ImageIO.read(getClass().getClassLoader().getResource("grid.png"));        //Instantiates and returns a bufferedImage from a file 
        } catch(IOException e) {                                                              
            JOptionPane.showMessageDialog(null, e.toString());                                //Prints an exception message
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
    	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	paintComponent()
		//
		// Method parameters	:	Graphics g
		//
		// Method return		:	void
		//
		// Synopsis				:   This method loads an image in the background used into jTextArea.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-04-06		Tiago   				Second method called.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        g.drawImage(image,0,0,null);                                                          //Draws an image in x 0 and y 0 coordinates
        super.paintComponent(g);                                                              //Uses super class behavior
    }
}

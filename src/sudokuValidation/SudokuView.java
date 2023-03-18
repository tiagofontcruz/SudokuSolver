package sudokuValidation;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class SudokuView extends JFrame {
	
	//DECLARATION OF FORM COMPONENTS ***************************************************** 	
	private static final long serialVersionUID = 1L;
	
	protected static JTextArea textArea;
	private static JLayeredPane layeredPane;
	private static JButton btnValidate;
	
	private static JMenuBar menuBar;
	private static JMenu fileMenu;
	private static JMenu helpMenu;
	
	private static JMenuItem loadSudoku;
	private static JMenuItem saveSudoku;
	private static JMenuItem helpSudoku;
		
	private static DefaultComboBoxModel<String> modelBox;
	private static JComboBox<String> fileBox;
	
	private static DefaultListModel<String> modelList;
	private static JList<String> listErrors;
	private static JScrollPane scrollListErrors;
	
	private static JLabel lblCol02;
	private static JLabel lblCol03;
	private static JLabel lblCol04;
	private static JLabel lblCol05;
	private static JLabel lblCol06;
	private static JLabel lblCol07;
	private static JLabel lblCol08;
	private static JLabel lblCol09;
	private static JLabel lblRow01;
	private static JLabel lblRow02;
	private static JLabel lblRow03;
	private static JLabel lblRow04;
	private static JLabel lblRow05;
	private static JLabel lblRow06;
	private static JLabel lblRow07;
	private static JLabel lblRow08;
	private static JLabel lblRow09;
	private static JLabel lblComboBox;
	private static JLabel lblErrorBox;
	
	//INSTANTIATION AND DEFINITION OF FORM COMPONENTS INTO THE CONSTRUCTOR ******************		
	public SudokuView() {
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(480, 200, 932, 650);		
		
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);	
		
		loadSudoku = new JMenuItem("Load File");
		saveSudoku = new JMenuItem("Save File");
		helpSudoku = new JMenuItem("How it works");
				
		fileMenu.add(loadSudoku);
		fileMenu.add(saveSudoku);
		helpMenu.add(helpSudoku);
		
		setJMenuBar(menuBar);
		
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);		
		
		textArea = new SudokuLoadGrid(30, 460);
		textArea.setFont(new Font("LucidaSans", Font.PLAIN, 40));		
		textArea.setBackground(new Color(1,1,1, (float) 0.01));
		textArea.setBounds(66, 63, 292, 461);
		layeredPane.add(textArea);

		btnValidate = new JButton("Validate Sudoku");		
		btnValidate.setBounds(450, 123, 375, 23);
		layeredPane.add(btnValidate);
		
		modelList = new DefaultListModel<>();
		
		listErrors = new JList<>(modelList);		
		listErrors.setBounds(450, 204, 375, 288);
		
		scrollListErrors = new JScrollPane(listErrors);
		scrollListErrors.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollListErrors.setBounds(450, 204, 375, 288);
		layeredPane.add(scrollListErrors);
		
		modelBox = new DefaultComboBoxModel<>();
		
		fileBox = new JComboBox<>(modelBox);
		fileBox.setBounds(390, 63, 500, 23);		
		layeredPane.add(fileBox);
		
		JLabel lblCol01 = new JLabel("1");
		lblCol01.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCol01.setBounds(76, 38, 17, 14);
		layeredPane.add(lblCol01);
		
		lblCol02 = new JLabel("2");
		lblCol02.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCol02.setBounds(107, 38, 17, 14);
		layeredPane.add(lblCol02);
		
		lblCol03 = new JLabel("3");
		lblCol03.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCol03.setBounds(136, 38, 17, 14);
		layeredPane.add(lblCol03);
		
		lblCol04 = new JLabel("4");
		lblCol04.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCol04.setBounds(174, 38, 17, 14);
		layeredPane.add(lblCol04);
		
		lblCol05 = new JLabel("5");
		lblCol05.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCol05.setBounds(205, 38, 17, 14);
		layeredPane.add(lblCol05);
		
		lblCol06 = new JLabel("6");
		lblCol06.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCol06.setBounds(234, 38, 17, 14);
		layeredPane.add(lblCol06);
		
		lblCol07 = new JLabel("7");
		lblCol07.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCol07.setBounds(273, 38, 17, 14);
		layeredPane.add(lblCol07);
		
		lblCol08 = new JLabel("8");
		lblCol08.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCol08.setBounds(304, 38, 17, 14);
		layeredPane.add(lblCol08);
		
		lblCol09 = new JLabel("9");
		lblCol09.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCol09.setBounds(333, 38, 17, 14);
		layeredPane.add(lblCol09);
		
		lblRow01 = new JLabel("1");
		lblRow01.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRow01.setBounds(39, 81, 17, 14);
		layeredPane.add(lblRow01);
		
		lblRow02 = new JLabel("2");
		lblRow02.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRow02.setBounds(39, 132, 17, 14);
		layeredPane.add(lblRow02);
		
		lblRow03 = new JLabel("3");
		lblRow03.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRow03.setBounds(39, 185, 17, 14);
		layeredPane.add(lblRow03);
		
		lblRow04 = new JLabel("4");
		lblRow04.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRow04.setBounds(39, 238, 17, 14);
		layeredPane.add(lblRow04);
		
		lblRow05 = new JLabel("5");
		lblRow05.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRow05.setBounds(39, 289, 17, 14);
		layeredPane.add(lblRow05);
		
		lblRow06 = new JLabel("6");
		lblRow06.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRow06.setBounds(39, 342, 17, 14);
		layeredPane.add(lblRow06);
		
		lblRow07 = new JLabel("7");
		lblRow07.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRow07.setBounds(39, 392, 17, 14);
		layeredPane.add(lblRow07);
		
		lblRow08 = new JLabel("8");
		lblRow08.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRow08.setBounds(39, 443, 17, 14);
		layeredPane.add(lblRow08);
		
		lblRow09 = new JLabel("9");
		lblRow09.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRow09.setBounds(39, 496, 17, 14);
		layeredPane.add(lblRow09);
		
		lblComboBox = new JLabel("The ComboBox will be filled up after saving a file.");
		lblComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComboBox.setBounds(496, 27, 313, 23);
		layeredPane.add(lblComboBox);
		
		lblErrorBox = new JLabel("Click on a list item to highlight the position.");
		lblErrorBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErrorBox.setBounds(471, 181, 354, 23);
		layeredPane.add(lblErrorBox);
	}
	
	public void addFileBox(String fileName) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	addFileBox()
		//
		// Method parameters	:	String fileName
		//
		// Method return		:	void
		//
		// Synopsis				:   This method adds the file's path into a combo box.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		fileBox.addItem(fileName);
	}
	
	public String getFileBox() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	addFileBox()
		//
		// Method parameters	:	none
		//
		// Method return		:	String
		//
		// Synopsis				:   This method returns the file's path in a String.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		return (String) fileBox.getSelectedItem();
	}
	
	public JTextArea getTextArea() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	getTextArea()
		//
		// Method parameters	:	none
		//
		// Method return		:	JTextArea
		//
		// Synopsis				:   This method returns the object JTextArea.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		return textArea;																	//Returns a jTextArea object
	}
	
	public String getTextAreaText() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	getTextAreaText()
		//
		// Method parameters	:	none
		//
		// Method return		:	String
		//
		// Synopsis				:   This method returns the text of a JTextArea in a String.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		return textArea.getText();															//Returns a jTextArea content
	}
	
	public void addJListErrors(String message) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	addJListErrors()
		//
		// Method parameters	:	String message
		//
		// Method return		:	void
		//
		// Synopsis				:   This method adds an text in a jList describing where is the error.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		modelList.addElement(message);														//Adds an element into a jList
	}
	
	public String getJListErrors() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	getJListErrors()
		//
		// Method parameters	:	none
		//
		// Method return		:	String
		//
		// Synopsis				:   This method gets a selected row in a jList.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		return listErrors.getSelectedValue();												//Gets a selected row in a jList
	}
	
	public void clearJListErrors() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	clearJListErrors()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method clears the jList.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		modelList.clear();																	//Clears the jList
	}
	
	public void setEnableValidateButton() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	setEnableValidateButton()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method enables the validation button.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		btnValidate.setEnabled(true);														//Enables the validation button
	}
	
	public void setDisableValidateButton() {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	setEnableValidateButton()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method disables the validation button.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		btnValidate.setEnabled(false);														//Disables the validation butt
	}
	
	public void displayMessage(String errorMessage) {
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Method				:	displayMessage()
		//
		// Method parameters	:	none
		//
		// Method return		:	void
		//
		// Synopsis				:   This method displays messages.							
		//
		// Modifications		:
		//							Date			Developer				Notes
		//							----			---------				-----
		//							2022-09-21		Tiago   				none.
		//
		// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		JOptionPane.showMessageDialog(this, errorMessage);									//Displays a messages
	}
	
	//THE METHODS BELLOW ARE IMPLEMENTED IN THE CONTROLLER CLASS.
	
	public void itemListener(ItemListener listenerForItem) {
		fileBox.addItemListener(listenerForItem);
	}
	
	public void keyListener(KeyListener listenerForKeyTyped) {
		textArea.addKeyListener(listenerForKeyTyped);	
	}
	
	public void listSelection(ListSelectionListener listenerForListSelection) {
		listErrors.addListSelectionListener(listenerForListSelection);
	}

	public void addLoadFile(ActionListener listenerForLoadFile) {
		loadSudoku.addActionListener(listenerForLoadFile);
	}
	
	public void addSaveFile(ActionListener listenerForSaveFile) {
		saveSudoku.addActionListener(listenerForSaveFile);
	}
	
	public void help(ActionListener listenerForHelp) {
		helpSudoku.addActionListener(listenerForHelp);
	}
	
	public void addValidate(ActionListener listenerForValidateSudoku) {
		btnValidate.addActionListener(listenerForValidateSudoku);
	}
}

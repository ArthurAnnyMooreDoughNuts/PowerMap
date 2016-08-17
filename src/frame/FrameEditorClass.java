package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FrameEditorClass extends JFrame implements ActionListener, KeyListener{

private static final long serialVersionUID = 1L;
	
	private static final String HOUSE = "H";
	private static final String POWER = "P";
	private static final String CORNER = "CORNER";
	private static final String T_TYPE = "T TYPE";
	private static final String LINE = "LINE";
	private static final String NONE = "";
	
	private static final char HOUSE_ = 'H';
	private static final char POWER_ = 'P';
	private static final char CORNER_ = 'C';
	private static final char T_TYPE_ = 'T';
	private static final char LINE_ = '.';
	private static final char NONE_ = ' ';
	
	private int[] locationButton;
	private ArrayList<ArrayList<JButton>> buttons;
	private JButton selectedButton;
	private boolean save = false;
	private String fileName = "";
	private JPanel[][] panelHolder;
	
	public FrameEditorClass(InputStream file){
		super("PowerMap Editor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		setLocationRelativeTo(null);
		setResizable(false);
		
		Scanner input = new Scanner(file);
		
		int l = 0;
		int c = 0;
		
		try{
			l = input.nextInt();
			input.next();	
			c = input.nextInt(); input.nextLine(); 
		} catch(Exception e){
			e.printStackTrace();
		}
		
		setLayout(new GridLayout(l+1, c));
		
		panelHolder = new JPanel[l+1][c];
		
		
		getContentPane().removeAll();
		
		setSize(600, 600);
		setResizable(true);
		
		buttons = new ArrayList<ArrayList<JButton>>();
		populatePanelHolder(l+1, c);
		
		
		for(int i = 0; i < l; i++){
			ArrayList<JButton> line = new ArrayList<JButton>();
			String lineText = input.nextLine();
			
			for(int j = 0; j < c; j++){
				
				JButton button = new JButton();
				button.setText(resolveChar(lineText.charAt(j)));
				button.addKeyListener(this);
				button.addActionListener(this);
				button.setBackground(Color.WHITE);
				button.setMinimumSize(new Dimension(100, 100));
				line.add(button);
				panelHolder[i][j].add(button);
			}
			
			buttons.add(line);	
		}
		
		selectedButton = buttons.get(0).get(0);
		
		
		
		JButton saveButton = new JButton("SAVE");
		panelHolder[l][0].add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save = true;
			}
		});
		
		loadAppearance();
		
		setVisible(true);
		
		while(!save){
			try{
				Thread.sleep(200);
			} catch(InterruptedException e){
			}
		}
		
		saveMap();
		
		input.close();
		
	}
	
	private void populatePanelHolder(int i , int j){
		for(int m = 0; m < i; m++) {
		   for(int n = 0; n < j; n++) {
			  JPanel panel = new JPanel();
			  panel.setLayout(new BorderLayout(0, 0));
		      panelHolder[m][n] = panel;
		      getContentPane().add(panelHolder[m][n], BorderLayout.CENTER);
		   }
		}
	}
	
	private void loadAppearance(){
		selectedButton = buttons.get(0).get(0);
		selectedButton.setBackground(Color.RED);
		locationButton = new int[2];
		locationButton[0] = 0;
		locationButton[1] = 0;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)(e.getSource());
		
		switch(button.getText()){
			case "": button.setText("H"); break;
			case "H": button.setText("P"); break;
			case "P": button.setText("LINE"); break;
			case "LINE": button.setText("CORNER"); break;
			case "CORNER": button.setText("T TYPE"); break;
			case "T TYPE": button.setText(""); break;
		}
	}

	private void saveMap(){
		setSize(300, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		setResizable(false);
		setLayout(new GridLayout(3, 1));
		getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.WHITE));
		getContentPane().removeAll();
		
		JLabel saveAs = new JLabel("Save as:");
		add(saveAs);
		
		JTextField fileField = new JTextField();
		add(fileField);
		
		JButton confirm = new JButton("Save");
		add(confirm);
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileName = fileField.getText();
			}
		});
		
		setVisible(true);
		
		while(fileName.equals("")){
			try{
				Thread.sleep(200);
			} catch(InterruptedException e){
			}
		}
		
		fileName = fileName.concat(".pmm");
		
		PrintWriter outStream = null;
		
		try {
			outStream = new PrintWriter(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		int lines = buttons.size();
		int cols = buttons.get(0).size();
		
		outStream.println(lines + " x " + cols);
		
		for(int i = 0; i < lines; i++){
			ArrayList<JButton> line = buttons.get(i);
			
			for(int j = 0; j < cols; j++){
				outStream.print(resolveCoding(line.get(j).getText()));
			}
			
			outStream.print('\n');
		}
		
		outStream.close();
	}
	
	private char resolveCoding(String str){
		
		switch(str){
			case HOUSE: return 'H';
			case POWER: return 'P';
			case CORNER: return 'C';
			case T_TYPE: return 'T';
			case LINE: return '.';
			case NONE: return ' ';
		}
		
		return 'E';
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int id = e.getID();
		
		if(id == KeyEvent.KEY_PRESSED){
			int c = e.getKeyCode();
			switch(c){
				case KeyEvent.VK_UP:
				case KeyEvent.VK_NUMPAD8:
				case KeyEvent.VK_W: moveSelectedUp(); break;
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_NUMPAD2:
				case KeyEvent.VK_S: moveSelectedDown(); break;
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_NUMPAD4:
				case KeyEvent.VK_A: moveSelectedLeft(); break;
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_NUMPAD6:
				case KeyEvent.VK_D: moveSelectedRight(); break;
				case KeyEvent.VK_P: selectedButton.setText(POWER); break;
				case KeyEvent.VK_C: selectedButton.setText(CORNER); break;
				case KeyEvent.VK_T: selectedButton.setText(T_TYPE); break;
				case KeyEvent.VK_H: selectedButton.setText(HOUSE); break;
				case KeyEvent.VK_L: selectedButton.setText(LINE); break;
				case KeyEvent.VK_SPACE: selectedButton.setText(NONE); break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	private void moveSelectedUp(){
		if(!(locationButton[0] == 0)){
			selectedButton.setBackground(Color.WHITE);
			locationButton[0]--;
			selectedButton = buttons.get(locationButton[0]).get(locationButton[1]);
			selectedButton.setBackground(Color.RED);
		}
	}
	
	private void moveSelectedDown(){
		if(!(locationButton[0] == (buttons.size()-1))){
			selectedButton.setBackground(Color.WHITE);
			locationButton[0]++;
			selectedButton = buttons.get(locationButton[0]).get(locationButton[1]);
			selectedButton.setBackground(Color.RED);
		}
	}
	
	private void moveSelectedLeft(){
		if(!(locationButton[1] == 0)){
			selectedButton.setBackground(Color.WHITE);
			locationButton[1]--;
			selectedButton = buttons.get(locationButton[0]).get(locationButton[1]);
			selectedButton.setBackground(Color.RED);
		}
	}
	
	private void moveSelectedRight(){
		if(!(locationButton[1] == (buttons.get(0).size() - 1))){
			selectedButton.setBackground(Color.WHITE);
			locationButton[1]++;
			selectedButton = buttons.get(locationButton[0]).get(locationButton[1]);
			selectedButton.setBackground(Color.RED);
		}
	}
	
	private String resolveChar(char c){
		switch(c){
			case HOUSE_: return HOUSE;
			case POWER_: return POWER;
			case CORNER_: return CORNER;
			case T_TYPE_: return T_TYPE;
			case LINE_: return LINE;
			case NONE_: return NONE;
		}
	
		return "E";
	}
	
}

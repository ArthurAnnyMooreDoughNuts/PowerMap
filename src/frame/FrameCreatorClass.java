package frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class FrameCreatorClass extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static final String HOUSE = "H";
	private static final String POWER = "P";
	private static final String ELBOW = "ELBOW";
	private static final String T_TYPE = "T TYPE";
	private static final String LINE = "LINE";
	private static final String NONE = "";
	
	
	private ArrayList<ArrayList<JButton>> buttons;
	private boolean save = false;
	private String fileName = "";
	
	public FrameCreatorClass(){
		super("PowerMap Creator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		setLocationRelativeTo(null);
		setResizable(false);
		
		int[] nums = getNumElements();
		int l = nums[0];
		int c = nums[1];

		getContentPane().removeAll();
		
		setSize(600, 600);
		setLayout(new GridLayout((l+1), c));
		setResizable(true);
		
		buttons = new ArrayList<ArrayList<JButton>>();
		
		for(int i = 0; i < l; i++){
			ArrayList<JButton> line = new ArrayList<JButton>();
			
			for(int j = 0; j < c; j++){
				JButton button = new JButton("");
				button.addActionListener(this);
				line.add(button);
				add(button);
			}
			
			buttons.add(line);	
		}
		
		JButton saveButton = new JButton("SAVE");
		add(saveButton, (l*c));
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				save = true;
			}
		});
		
		setVisible(true);
		
		while(!save){
			try{
				Thread.sleep(200);
			} catch(InterruptedException e){
			}
		}
		
		saveMap();
		
	}
	
	public int[] getNumElements(){
		setSize(300, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		setResizable(false);
		getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.WHITE));
		setLayout(new GridLayout(3, 1));
		
		int[] elements = new int[2];
		elements[0] = 0;
		elements[1] = 0;
		
		Integer[] nums = new Integer[10];
		
		for(int i = 1; i < 11; i++){
			nums[i-1] = new Integer(i);
		}
		
		JLabel label = new JLabel("Lines:");
		add(label);
		
		JComboBox<Integer> cols = new JComboBox<Integer>(nums);
		cols.setEditable(false);
		add(cols);
		
		JButton confirm = new JButton("Confirm");
		add(confirm);
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				elements[0] = ((Integer)(cols.getSelectedItem())).intValue();
			}
		});
		
		setVisible(true);
		
		while(elements[0] <= 0){
			try{
				Thread.sleep(200);
			} catch(InterruptedException e){
			}
		}
		
		setVisible(false);
		getContentPane().removeAll();
		
		label.setText("Columns:");
		add(label);
		
		JComboBox<Integer> lines = new JComboBox<Integer>(nums);
		lines.setEditable(false);
		add(lines);
		
		JButton confirm2 = new JButton("Confirm");
		add(confirm2);
		confirm2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				elements[1] = ((Integer)(lines.getSelectedItem())).intValue();
			}
		});
		
		setVisible(true);
		
		while(elements[1] <= 0){
			try{
				Thread.sleep(200);
			} catch(InterruptedException e){
			}
		}
		
		return elements;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)(e.getSource());
		
		switch(button.getText()){
			case "": button.setText("H"); break;
			case "H": button.setText("P"); break;
			case "P": button.setText("LINE"); break;
			case "LINE": button.setText("ELBOW"); break;
			case "ELBOW": button.setText("T TYPE"); break;
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
			case ELBOW: return 'C';
			case T_TYPE: return 'T';
			case LINE: return '.';
			case NONE: return ' ';
		}
		
		return 'E';
		
	}
	
}

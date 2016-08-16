package frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FrameClass extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private String fileName;
	private boolean confirmed;
	
	public FrameClass(){
		super("PowerMap");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		setResizable(false);
		getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.WHITE));
	}
	
	public void resizeSmall(){
		setSize(300, 150);
		setResizable(false);
		setLayout(new GridLayout(4, 1));
	}
	
	public void resizeToError(){
		setSize(300, 150);
		setLayout(new GridLayout(3, 1));
	}
	
	public String getMapFileName(){
		fileName = "";
		
		resizeSmall();
		
		JLabel label = new JLabel("File Name: ");
		add(label);
		
		JTextField fileNameField = new JTextField();
		add(fileNameField);
		
		JButton confirm = new JButton("Load");
		add(confirm);
		setVisible(true);
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Load")){
					fileName = fileNameField.getText();
				}
			}
		});
		
		setVisible(true);
		
		while(fileName.equals("")){
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){
			}
		}
		
		return fileName.concat(".pmm");
	}
	
	public void mapNotFound(String text){
		resizeToError();
		
		confirmed = false;
		
		JLabel label = new JLabel("File \"" + text + "\" not found. Please try again.");
		add(label);
		
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmed = true;
				return;
			}
		});
		
		add(confirm);
		
		setVisible(true);
		
		while(!confirmed){
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
}

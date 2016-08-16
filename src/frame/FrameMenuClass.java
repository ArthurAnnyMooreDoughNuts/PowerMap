package frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FrameMenuClass extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private int option;
	
	public FrameMenuClass(){
		super("PowerMap");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(false);
		setResizable(false);
		getRootPane().setBorder(BorderFactory.createMatteBorder(8, 8, 8, 8, Color.WHITE));
		loadMenu();
	}
	
	private void loadMenu(){
		option = 0;
		
		setSize(500, 400);
		setLayout(new GridLayout(3, 1));
		
		JButton play = new JButton("Play");
		add(play);
		play.addActionListener(this);
		
		JButton load = new JButton("Load Map");
		add(load);
		load.addActionListener(this);
		
		JButton create = new JButton("Create Map");
		add(create);
		create.addActionListener(this);
		
		setVisible(true);
		
		while(option == 0){
			try{
				Thread.sleep(200);
			} catch(InterruptedException e){
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case "Play": option = 1; break;
			case "Load Map": option = 2; break;
			case "Create Map": option = 3; break;
		}
	}

	public int getOption(){
		return option;
	}
	
}

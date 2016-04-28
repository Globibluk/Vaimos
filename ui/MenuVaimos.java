package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sim.Simulation;

public class MenuVaimos extends JFrame implements ActionListener {

	private static final long serialVersionUID = 0L;
	private JPanel panel;
	private JButton selectButton, runButton, loadButton;
	private JFileChooser fc;
	
	private File file;
		
	Simulation sim;
	boolean loaded = false;
	
	public MenuVaimos()
	{
		setTitle("VAIMOS");
		panelSetup();
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private void panelSetup()
	{
		panel = new JPanel();
		panel.setBounds(100, 100, 500, 500);
		JTextArea ta = new JTextArea();
		ta.setText("Welcome to Vaimos simulation.\nPlease select a program file.");
		panel.add(ta);
		JTextField tf = new JTextField(10);
		tf.setText("Prog");
		panel.add(tf);
		selectButton = new JButton("Select File");
		selectButton.addActionListener(this);
		panel.add(selectButton);
		loadButton = new JButton("Load");
		loadButton.addActionListener(this);
		panel.add(loadButton);
		runButton = new JButton("Run");
		runButton.addActionListener(this);
		panel.add(runButton);
		fc = new JFileChooser();	
		file = null;
	}

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		
		if(source == selectButton)
		{			
			int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                file = fc.getSelectedFile();
	        }
		}
		if(source == loadButton && file != null)
		{
			sim = new Simulation(file);
			loaded = sim.setup();
		}
		
		if(source == runButton && loaded)
			sim.start();		
	}
}
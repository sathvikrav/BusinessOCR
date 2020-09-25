package BusinessParser;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BusinessParser implements ActionListener{
	JFrame frame;
	JLabel directive;
	JPanel panel;
	JTextArea input;
	JButton submit;
	JTextArea info_display;
	
	/* Our Business Parser uses a Java Swing based GUI and consists of a label with directions,
	 * an input area to put the business card info, a submit button to submit
	 * to submit the info, and an info display area to to display the name,
	 * phone, and email. The JPanel will be used to organize these components*/
	public BusinessParser() {
		frame = new JFrame();
		directive = new JLabel("Please enter the business card information.");
		panel = new JPanel(new GridBagLayout()); //We use the GridBagLayout because of its flexible layout style and the fact that it centers the panel
		input = new JTextArea();
		info_display = new JTextArea();
		submit = new JButton("Submit");
		
		frame.setSize(1000, 1000); //initial size of the frame
		submit.addActionListener(this); //this should submit the information to the ContactInfo class for parsing
		info_display.setEnabled(false);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0; //places the directive directly in the center of the frame
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;//makes the directive cover the width of the panel
		c.insets = new Insets(10, 10, 10, 10);//adds external spacing of 10px on all sides
		panel.add(directive, c);
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH; //makes the text area expand to the length of the panel and vertically expand when needed
		panel.add(input, c);
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL; //makes the submit button expand to length of component
		panel.add(submit, c);
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.BOTH; 
		panel.add(info_display, c);
		frame.add(panel);
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set default frame operations
	}

	public static void main(String[] args) {
		new BusinessParser();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String text = input.getText(); //when submit button is clicked, this grabs all the text in the input area
		ContactInfo info = this.getContactInfo(text);
		info_display.setText(info.toString()); //sets all the text in the info_display area after parsing
		
	}
	
	public ContactInfo getContactInfo(String document) {
		return new ContactInfo(document);
	}

}

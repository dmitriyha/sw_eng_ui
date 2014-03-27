import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class About extends UIElement{

	About(JPanel jPanel, String auth,String User) {
		super(jPanel,auth,User);
		// TODO Auto-generated constructor stub
	}

	@Override
	void show() {
		
		JLabel bugs = new JLabel(new ImageIcon("img\\about.jpg"));
		bugs.setBounds(0,0, 565, 435);
		bugs.validate();
		bugs.setVisible(true);
		getFrame().add(bugs);

		
		
		getFrame().repaint(0);
	}
	
	
	
	
}

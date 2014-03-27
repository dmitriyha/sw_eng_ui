import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SignIn extends UIElement{
	
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel label ;
	
	SignIn(JPanel f,String auth,String User) {
		super(f,auth,User);
	}
	
	@Override
	public void show (final OuterUI i){
		
		label=new JLabel();
		label.setBounds(231, 300, 136, 23);
		getFrame().add(label);
		
		JButton btnSignIn = new JButton("Log In");
		btnSignIn.setBounds(231, 220, 136, 23);
		getFrame().add(btnSignIn);
		
		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					if(!textField.getText().equals("")){
						if(!(String.copyValueOf(passwordField.getPassword())).equals("")){
							String url="http://localhost:9090/SignIn?method=in&user="+
									textField.getText()+"&password="+
									String.copyValueOf(passwordField.getPassword());
							Http h = new Http(new URL(url));
							
							JSONParser parser = new JSONParser();
							Object obj= parser.parse(h.send());
							JSONObject jsonObject = (JSONObject) obj;
							
							authToken=(String) jsonObject.get("AuthToken");
							user=textField.getText();
							label.setText("");
							i.setWelcomeText("Welcome, "+user);
							
							i.setUserToken(user, authToken);
							
							i.hideSignup();
							i.setButtonsActive();
							
							
							Splash s= new Splash(getFrame(),authToken,user);
							s.show();
							
							
						}
						else{
							label.setText("password missing");
						}
					}
					else{
						label.setText("username missing");
					}
					label.repaint(0);
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					label.setText("username or password incorrect");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				label.repaint(0);
				//frame.repaint();
				getFrame().repaint(0);
			}
			
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(231, 254, 136, 23);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Splash s= new Splash(getFrame(),authToken,user);
				s.show();
			}
		});
		getFrame().add(btnBack);
		
		textField = new JTextField();
		textField.setBounds(231, 158, 136, 20);
		getFrame().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(231, 189, 136, 20);
		getFrame().add(passwordField);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(175, 161, 47, 14);;
		getFrame().add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(175, 192, 46, 14);
		getFrame().add(lblPassword);
		
		
		
		getFrame().repaint(0);
	}

	@Override
	void show() {
		// TODO Auto-generated method stub
		
	}
	
}

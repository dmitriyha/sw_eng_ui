import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
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


public class OuterUI{
	
	JFrame frame;
	JPanel panel;
	
	protected String authToken ; 
	protected String user ;
	
	JButton btnSignUp;
	JButton btnSignIn ;
	JButton btnProfile;
	JButton btnMessages;
	
	JLabel lblWelcome;
	JButton btnSignOut=new JButton();
	OuterUI thisStuff= this;
	
	OuterUI(JFrame f,String usr,String auth) {
		// TODO Auto-generated constructor stub
		frame=f;
		authToken=auth;
		user=usr;
		frame.add(btnSignOut);
		this.hideLogout();
	}
	
	void show() {
		JLabel banner = new JLabel(new ImageIcon("img\\banner.jpg"));
		banner.setBounds(10, 11, 650, 60);
		banner.validate();
		banner.setVisible(true);
		frame.getContentPane().add(banner);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSearch.setBounds(10, 218, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBrowse.setBounds(10, 184, 89, 23);
		btnBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Browse b=new Browse(panel,authToken,user);
				b.show(thisStuff);
				frame.repaint();
			}
		});
		
		frame.getContentPane().add(btnBrowse);
		
		btnProfile = new JButton("Profile");
		btnProfile.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnProfile.setBounds(10, 116, 89, 23);
		btnProfile.setEnabled(false);
		frame.getContentPane().add(btnProfile);
		
		lblWelcome = new JLabel("Welcome, guest!");
		lblWelcome.setBounds(10, 82, 205, 14);
		frame.getContentPane().add(lblWelcome);
		
		btnMessages = new JButton("Messages");
		btnMessages.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMessages.setBounds(10, 150, 89, 23);
		btnMessages.setEnabled(false);
		frame.getContentPane().add(btnMessages);
		
		
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(109, 116, 565, 435);
		frame.add(panel);
		
		Splash s=new Splash(panel,user,authToken);
		s.show();
		
		JButton btnAbout = new JButton("About");
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAbout.setBounds(10, 252, 89, 23);
		btnAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				About s=new About(panel,user,authToken);
				s.show();
			}
		});
		
		frame.getContentPane().add(btnAbout);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExit.setBounds(10, 286, 89, 23);
		
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				signOut();
				System.exit(1);
			}
		});
		
		frame.getContentPane().add(btnExit);
		
		frame.repaint();
	}
	
	public void hideSignup(){
		
		btnSignOut = new JButton("Sign out");
		btnSignOut.setBounds(571, 82, 89, 23);
		frame.getRootPane().add(btnSignOut);
		btnSignOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				signOut();
				
//					SignIn s= new SignIn(getFrame(),authToken);
//					s.show();
			}
		});
		
		frame.remove(btnSignIn);
		frame.remove(btnSignUp);
		frame.add(btnSignOut);
		frame.repaint();
	}
	
	public void hideLogout(){
		btnSignIn = new JButton("Sign in");
		btnSignIn.setBounds(571, 82, 89, 23);
		frame.getRootPane().add(btnSignIn);
		btnSignIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
				SignIn s= new SignIn(panel,authToken,user);
				s.show(thisStuff);
				authToken=s.getToken();
				user=s.getUser();
				
				
				
				frame.repaint(0);
			}
		});
		
		btnSignUp = new JButton("Sign up");
		btnSignUp.setBounds(472, 82, 89, 23);
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SignUp s= new SignUp(panel,authToken,user);
				s.show(thisStuff);
			}
		});
		frame.add(btnSignIn);
		frame.add(btnSignUp);
		frame.remove(btnSignOut);
		frame.repaint();
	}
	
	public void setUserToken(String user,String token){
		this.user=user;
		this.authToken=token;
	}
	
	public void setWelcomeText(String text){
		lblWelcome.setText(text);
	}
	
	public void signOut(){
		//signout procedure
		
		String url="http://localhost:9090/SignOut?method=out&user="+user+
				"&authToken="+authToken;
		
		try {
			Http h = new Http(new URL(url));
			JSONParser parser = new JSONParser();
			Object obj= parser.parse(h.send());
			JSONObject jsonObject = (JSONObject) obj;
			
			System.out.println((String) jsonObject.get("message"));
			authToken="-1";
			user="-1";
			setWelcomeText("Welcome, guest");
			setButtonsInactive();
			
			hideLogout();
			
			Splash s= new Splash(panel,authToken,user);
			s.show();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setButtonsActive(){
		btnProfile.setEnabled(true);
		btnMessages.setEnabled(true);
	}
	
	public void setButtonsInactive() {
		btnProfile.setEnabled(false);
		btnMessages.setEnabled(false);
	}
	
	public void repaintFrame(){
		frame.repaint();
	}
	
}

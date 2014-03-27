import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SignUp extends UIElement{
	private JTextField username;
	private JTextField email;
	private JTextField ccard;
	private JTextField screenName;
	private JPasswordField password;
	private JPasswordField repetedPass;
	private JLabel lblRetypePassword;
	private JLabel errorMessages;
	JButton btnSignUp ;
	Choice gender;
	Choice interestedIn;
	Choice location;
	
	SignUp(JPanel jPanel, String authToken, String user) {
		super(jPanel, authToken, user);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show(final OuterUI i) {
		
		
		username = new JTextField();
		username.setBounds(239, 66, 140, 20);
		getFrame().add(username);
		
		email = new JTextField();
		email.setBounds(239, 128, 140, 20);
		getFrame().add(email);
		
		ccard = new JTextField();
		ccard.setBounds(239, 159, 140, 20);
		getFrame().add(ccard);
		
		screenName = new JTextField();
		screenName.setBounds(239, 97, 140, 20);
		getFrame().add(screenName);
		
		password = new JPasswordField();
		password.setBounds(239, 190, 140, 20);
		getFrame().add(password);
		
		repetedPass = new JPasswordField();
		repetedPass.setBounds(239, 221, 140, 20);
		getFrame().add(repetedPass);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(135, 69, 94, 14);
		getFrame().add(lblUsername);
		
		JLabel lblScreenName = new JLabel("Screen Name");
		lblScreenName.setBounds(135, 100, 94, 14);
		getFrame().add(lblScreenName);
		
		JLabel lblEmail = new JLabel("e-Mail");
		lblEmail.setBounds(135, 131, 94, 14);
		getFrame().add(lblEmail);
		
		JLabel lblCreditCard = new JLabel("Credit Card");
		lblCreditCard.setBounds(135, 162, 94, 14);
		getFrame().add(lblCreditCard);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(135, 193, 94, 14);
		getFrame().add(lblPassword);
		
		lblRetypePassword = new JLabel("Retype password");
		lblRetypePassword.setBounds(135, 227, 113, 14);
		getFrame().add(lblRetypePassword);
		
		errorMessages = new JLabel("");
		errorMessages.setBounds(239, 350, 300, 14);
		getFrame().add(errorMessages);
		
		btnSignUp = new JButton("Sign up");
		btnSignUp.setBounds(239, 252, 140, 40);
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println(gender.getSelectedItem()+interestedIn.getSelectedItem()+location.getSelectedItem());
				
				
				if(!username.getText().equals("") && !username.getText().contains(" ")){
					if(!screenName.getText().equals("")){
						if(!email.getText().equals("") && !email.getText().contains(" ")){
							if(!ccard.getText().equals("") ){
								if(!gender.getSelectedItem().contains("Select") && !interestedIn.getSelectedItem().contains("Select") && !location.getSelectedItem().contains("Select")){
									if(!(String.copyValueOf(password.getPassword())).equals("") && !(String.copyValueOf(password.getPassword())).contains(" ")){
										if((String.copyValueOf(password.getPassword())).equals(String.copyValueOf(repetedPass.getPassword()))){
											errorMessages.setText("");
											
											String ccNo = ccard.getText().replaceAll(" ", "");
											String scrName= screenName.getText().replaceAll(" ", "%20");
											
											String url= "http://localhost:9090/SignUp?method=add"+
											"&username="+username.getText()+
											"&screenName="+scrName+
											"&ccard="+ccNo+
											"&email="+email.getText()+
											"&password="+String.copyValueOf(password.getPassword())+
											"&gender="+gender.getSelectedItem()+
											"&interestedIn="+interestedIn.getSelectedItem()+
											"&location="+location.getSelectedItem();
											
											Http h;
											try {
												h = new Http(new URL(url));
												JSONParser parser = new JSONParser();
												Object obj= parser.parse(h.send());
												JSONObject jsonObject = (JSONObject) obj;
												
												if(((String) jsonObject.get("message")).equals("user exists")){
													errorMessages.setText("User with the username \""+username.getText()+"\" already exists");
												}
												else{
													authToken=((String) jsonObject.get("AuthToken"));
													
													i.setButtonsActive();
													i.setWelcomeText("Welcome, "+username.getText());
													i.hideSignup();
													i.setUserToken(username.getText(), authToken);
													
													Splash s= new Splash(getFrame(),authToken,username.getText());
													s.show();
												}
												
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
										else{
											errorMessages.setText("Passwords do not match");
										}
									}
								
									else{
										errorMessages.setText("Password missing");
										if((String.copyValueOf(password.getPassword())).contains(" ")){
											errorMessages.setText("Password contains spaces");
										}
									}
								}else{
									errorMessages.setText("one of the 3 dropdown menus missing");
								}
							}
							else{
								errorMessages.setText("Credit card No. missing");
							}
						}
						else{
							errorMessages.setText("e-mail missing");
							if(email.getText().contains(" ")){
								errorMessages.setText("email contains spaces");
							}
						}
					}
					else{
						errorMessages.setText("Screen name missing");
					}
				}
				else{
					errorMessages.setText("Username missing");
					if(username.getText().contains(" ")){
						errorMessages.setText("Username contains spaces");
					}
				}
				getFrame().invalidate();
				getFrame().validate();
				getFrame().repaint();
			}
		});
		
		getFrame().add(btnSignUp);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(239, 303, 140, 40);
		getFrame().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Splash s= new Splash(getFrame(),authToken,user);
				s.show();
			}
		});
		
		gender = new Choice();
		gender.setBounds(400, 66, 140, 20);
		gender.addItem("Select Gender");
		gender.addItem("Male");
		gender.addItem("Female");
		getFrame().add(gender);
		i.repaintFrame();
		
		
		interestedIn = new Choice();
		interestedIn.setBounds(400, 96, 140, 20);
		interestedIn.addItem("Select Orientation");
		interestedIn.addItem("Men");
		interestedIn.addItem("Women");
		getFrame().add(interestedIn);
		
		location = new Choice();
		location.setBounds(400, 125, 140, 20);
		location.addItem("Select Location");
		String locs[]={"Bolsward", "Dokkum", "Drachten", "Franeker", "Harlingen", "Heerenveen", "Hindeloopen", "IJlst", "Leeuwarden", "Sloten", "Sneek", "Stavoren", "Workum", "Apeldoorn", "Arnhem", "Bredevoort", "Buren", "Culemborg", "Deil", "Dieren", "Doetinchem", "Ede", "Enspijk", "Gendt", "Groenlo", "Harderwijk", "Hattem", "Huissen", "Nijkerk", "Nijmegen", "Tiel", "Wageningen", "Wijchen", "Winterswijk", "Zaltbommel", "Zutphen", "Appingedam", "Delfzijl", "Groningen", "Hoogezand-Sappemeer", "Stadskanaal", "Winschoten", "Veendam", "Geleen", "Gennep", "Heerlen", "Kerkrade", "Kessel", "Landgraaf", "Maastricht", "Montfort", "Nieuwstadt", "Roermond", "Sittard", "Schin op Geul", "Stein", "Thorn", "Valkenburg aan de Geul", "Venlo", "Weert", "Bergen op Zoom", "Breda", "'s-Hertogenbosch", "Eindhoven", "Geertruidenberg", "Grave", "Helmond", "Heusden", "Klundert", "Oosterhout", "Oss", "Ravenstein", "Roosendaal", "Sint-Oedenrode", "Tilburg", "Valkenswaard", "Veldhoven", "Waalwijk", "Willemstad", "Woudrichem", "Alkmaar", "Amstelveen", "Amsterdam", "Den Helder", "Edam, Volendam", "Enkhuizen", "Haarlem", "Heerhugowaard", "Hilversum", "Hoofddorp", "Hoorn", "Laren", "Purmerend", "Medemblik", "Monnickendam", "Muiden", "Naarden", "Schagen", "Velsen", "Weesp", "Zaanstad", "Almelo", "Blokzijl", "Deventer", "Enschede", "Genemuiden", "Hasselt", "Hengelo", "Kampen", "Oldenzaal", "Steenwijk", "Vollenhove", "Zwolle", "Alphen aan den Rijn", "Delft", "Dordrecht", "Gorinchem", "Gouda", "Leiden", "Rotterdam", "Spijkenisse", "The Hague", "Zoetermeer", "Amersfoort", "Nieuwegein", "Utrecht", "Veenendaal", "Arnemuiden", "Goes", "Hulst", "Middelburg", "Sluis", "Terneuzen", "Veere", "Vlissingen ", "Zierikzee"};
		
		for (int in=0;Array.getLength(locs)>in;in++){
			location.add(locs[in]);
		}
		
		
		getFrame().add(location);
		
		i.repaintFrame();
	}

	@Override
	void show() {
		// TODO Auto-generated method stub
		
	}

}

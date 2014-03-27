import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.awt.Choice;
import java.awt.Label;


public class UI {

	private JFrame frame;
	JButton btnSignUp ;
	private JLabel label = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLogIn.setBounds(571, 82, 89, 23);
		frame.getContentPane().add(btnLogIn);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSignUp.setBounds(472, 82, 89, 23);
		frame.getContentPane().add(btnSignUp);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSearch.setBounds(10, 218, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBrowse.setBounds(10, 184, 89, 23);
		frame.getContentPane().add(btnBrowse);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnProfile.setBounds(10, 116, 89, 23);
		frame.getContentPane().add(btnProfile);
		
		JLabel lblWelcome = new JLabel("Welcome, guest!");
		lblWelcome.setBounds(10, 82, 205, 14);
		frame.getContentPane().add(lblWelcome);
		
		JButton btnMessages = new JButton("Messages");
		btnMessages.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMessages.setBounds(10, 150, 89, 23);
		frame.getContentPane().add(btnMessages);
		
		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(109, 116, 565, 435);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Label foto = new Label("New label");
		foto.setBounds(10, 10, 60, 60);
		panel.add(foto);
		
		Label name = new Label("");
		name.setBounds(76, 10, 325, 22);
		panel.add(name);
		
		Label Location = new Label("Location: ");
		Location.setBounds(76, 38, 325, 22);
		panel.add(Location);
		
		Label Gender = new Label("Gender: ");
		Gender.setBounds(76, 66, 325, 22);
		panel.add(Gender);
		
		Label interestedIn = new Label("Interested in :");
		interestedIn.setBounds(76, 94, 325, 22);
		panel.add(interestedIn);
		
		
		
		JButton btnAbout = new JButton("About");
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAbout.setBounds(10, 252, 89, 23);
		frame.getContentPane().add(btnAbout);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExit.setBounds(10, 286, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JSpinner gender = new JSpinner();
		gender.setModel(new SpinnerListModel(new String[] {"Male", "Female"}));
		gender.setBounds(125, 11, 117, 20);
		frame.getContentPane().add(gender);
		
		JSpinner orientation = new JSpinner();
		orientation.setModel(new SpinnerListModel(new String[] {"Men", "Women"}));
		orientation.setBounds(125, 42, 117, 20);
		frame.getContentPane().add(orientation);
		
		JSpinner location = new JSpinner();
		location.setModel(new SpinnerListModel(new String[] {"Bolsward", "Dokkum", "Drachten", "Franeker", "Harlingen", "Heerenveen", "Hindeloopen", "IJlst", "Leeuwarden", "Sloten", "Sneek", "Stavoren", "Workum", "Apeldoorn", "Arnhem", "Bredevoort", "Buren", "Culemborg", "Deil", "Dieren", "Doetinchem", "Ede", "Enspijk", "Gendt", "Groenlo", "Harderwijk", "Hattem", "Huissen", "Nijkerk", "Nijmegen", "Tiel", "Wageningen", "Wijchen", "Winterswijk", "Zaltbommel", "Zutphen", "Appingedam", "Delfzijl", "Groningen", "Hoogezand-Sappemeer", "Stadskanaal", "Winschoten", "Veendam", "Geleen", "Gennep", "Heerlen", "Kerkrade", "Kessel", "Landgraaf", "Maastricht", "Montfort", "Nieuwstadt", "Roermond", "Sittard", "Schin op Geul", "Stein", "Thorn", "Valkenburg aan de Geul", "Venlo", "Weert", "Bergen op Zoom", "Breda", "'s-Hertogenbosch", "Eindhoven", "Geertruidenberg", "Grave", "Helmond", "Heusden", "Klundert", "Oosterhout", "Oss", "Ravenstein", "Roosendaal", "Sint-Oedenrode", "Tilburg", "Valkenswaard", "Veldhoven", "Waalwijk", "Willemstad", "Woudrichem", "Alkmaar", "Amstelveen", "Amsterdam", "Den Helder", "Edam, Volendam", "Enkhuizen", "Haarlem", "Heerhugowaard", "Hilversum", "Hoofddorp", "Hoorn", "Laren", "Purmerend", "Medemblik", "Monnickendam", "Muiden", "Naarden", "Schagen", "Velsen", "Weesp", "Zaanstad", "Almelo", "Blokzijl", "Deventer", "Enschede", "Genemuiden", "Hasselt", "Hengelo", "Kampen", "Oldenzaal", "Steenwijk", "Vollenhove", "Zwolle", "Alphen aan den Rijn", "Delft", "Dordrecht", "Gorinchem", "Gouda", "Leiden", "Rotterdam", "Spijkenisse", "The Hague", "Zoetermeer", "Amersfoort", "Nieuwegein", "Utrecht", "Veenendaal", "Arnemuiden", "Goes", "Hulst", "Middelburg", "Sluis", "Terneuzen", "Veere", "Vlissingen ", "Zierikzee"}));
		location.setBounds(125, 76, 117, 20);
		frame.getContentPane().add(location);
		
		Choice choice = new Choice();
		choice.setBounds(325, 11, 125, 20);
		choice.addItem("lol");
		frame.getContentPane().add(choice);
	}
}

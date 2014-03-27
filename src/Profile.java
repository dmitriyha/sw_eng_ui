import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;


public class Profile extends UIElement{
	
	JSONObject j;
	
	
	Profile(JPanel f, String authToken, String user,JSONObject j) {
		super(f, authToken, user);
		// TODO Auto-generated constructor stub
		this.j=j;
	}

	@Override
	public void show(OuterUI i) {
		JLabel foto = new JLabel("");
		foto.setIcon(new ImageIcon(DatatypeConverter.parseBase64Binary((String) j.get("image"))));
		foto.setBounds(10, 10, 60, 60);
		getFrame().add(foto);
		
		JLabel name = new JLabel((String)j.get("screenName"));
		name.setBounds(76, 10, 325, 22);
		getFrame().add(name);
		
		JLabel Location = new JLabel("Location: "+j.get("location"));
		Location.setBounds(76, 38, 325, 22);
		getFrame().add(Location);
		
		JLabel Gender = new JLabel("Gender: "+j.get("gender"));
		Gender.setBounds(76, 66, 325, 22);
		getFrame().add(Gender);
		
		JLabel interestedIn = new JLabel("Interested in :"+j.get("interestedIn"));
		interestedIn.setBounds(76, 94, 325, 22);
		getFrame().add(interestedIn);
		
		i.repaintFrame();
	}

	@Override
	void show() {
		// TODO Auto-generated method stub
		
	}

}

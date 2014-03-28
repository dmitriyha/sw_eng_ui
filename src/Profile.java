import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;


public class Profile extends UIElement{
	
	JSONObject j;
	JTextField message;
	
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
		
		JLabel lblMsg = new JLabel("Message:");
		lblMsg.setBounds(10, 128, 75, 22);
		getFrame().add(lblMsg);
		
		message = new JTextField();
		message.setBounds(76, 128, 400, 25);
		getFrame().add(message);
		
		JButton send=new JButton("Send");
		send.setBounds(486, 128, 75, 25);
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!message.getText().equals("")){
					String msg=message.getText().replaceAll("=", " ");
					message.setText("");
					try {
						Http h =new Http(new URL("http://localhost:9090/MessageControl?method=send"+
						"&from="+user+
						"&to="+j.get("user")+
						"&authToken="+authToken+
						"&message="+msg));
						
						
						h.send();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		
		if(user.equals( j.get("user")) || authToken.equals("-1")){
			send.setEnabled(false);
		}
		
		getFrame().add(send);
		
		i.repaintFrame();
	}

	@Override
	void show() {
		// TODO Auto-generated method stub
		
	}

}

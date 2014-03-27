import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Browse extends UIElement{
	
	String split[];
	int arrayLength;
	int page=0;
	JButton next;
	JButton prev;
	
	OuterUI i;
	
	Browse(JPanel f, String authToken, String user) {
		super(f, authToken, user);
		// TODO Auto-generated constructor stub
		
		Http h;
		try {
			h = new Http(new URL("http://localhost:9090/ProfileSend?method=show&authToken="+this.authToken));
			
			String s= h.send();
			split=s.split("}");
			arrayLength=Array.getLength(split);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void show(final OuterUI in){
		
		i=in;
		populateList(0);
	}
	
	@Override
	void show() {
		// TODO Auto-generated method stub
		
	}
	
	private void populateList(int start){
		getFrame().removeAll();
		int location=0;
		try {
			System.out.println(start);
			JSONParser parser = new JSONParser();
			for (int in = 0+(start*4); in<4+(start*4);in++){
				Object obj= parser.parse(split[in]+"}");
				final JSONObject jsonObject = (JSONObject) obj;
				
				System.out.println(jsonObject.toJSONString());
				
				JPanel user_1 = new JPanel();
				user_1.setBounds(10, 11+(92*location), 545, 81);
				getFrame().add(user_1);
				user_1.setLayout(null);
				
				location++;
				
				
				JButton btnViewUser = new JButton("View User");
				btnViewUser.setBounds(436, 11, 99, 59);
				btnViewUser.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Profile s = new Profile(getFrame(), authToken, user, jsonObject);
						s.show(i);
					}
				});
				user_1.add(btnViewUser);
				
				JLabel lblFoto = new JLabel("");
				lblFoto.setIcon(new ImageIcon(DatatypeConverter.parseBase64Binary((String) jsonObject.get("image"))));
				lblFoto.setBounds(10, 11, 60, 60);
				user_1.add(lblFoto);
				
				JLabel lblLocation = new JLabel("Location: "+ jsonObject.get("location"));
				lblLocation.setBounds(78, 11, 348, 14);
				user_1.add(lblLocation);
				
				JLabel lblGender = new JLabel("Gender: "+ jsonObject.get("gender"));
				lblGender.setBounds(78, 33, 348, 14);
				user_1.add(lblGender);
				
				JLabel lblInterestedIn = new JLabel("Interested in: "+ jsonObject.get("interestedIn"));
				lblInterestedIn.setBounds(78, 56, 348, 14);
				user_1.add(lblInterestedIn);
			}
			
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(ArrayIndexOutOfBoundsException e){
			
		}
		
		next = new JButton("Next>");
		next.setBounds(292, 379, 113, 45);
		next.setEnabled(false);
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				page++;
				populateList(page);
				getFrame().invalidate();
				getFrame().repaint();
				i.repaintFrame();
				
			}
		});
		getFrame().add(next);
		
		prev = new JButton("<Previous");
		prev.setBounds(169, 379, 113, 45);
		prev.setEnabled(false);
		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				page--;
				populateList(page);
				getFrame().invalidate();
				getFrame().repaint();
				i.repaintFrame();
				
			}
		});
		getFrame().add(prev);
		
		if(start*4+4<arrayLength){
			next.setEnabled(true);
		}
		if(start>0){
			prev.setEnabled(true);
		}
	}

}

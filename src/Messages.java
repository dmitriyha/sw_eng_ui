import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Messages extends UIElement{
	OuterUI i;
	
	String split[];
	int arrayLength;
	int page=0;
	JButton next;
	JButton prev;
	Messages(JPanel f, String authToken, String user) {
		super(f, authToken, user);
		// TODO Auto-generated constructor stub
		
		Http h;
		try {
			h = new Http(new URL("http://localhost:9090/MessageControl?method=allMsg&user="+user+"&authToken="+authToken));
			
			String s= h.send();
			split=s.split("}");
			arrayLength=Array.getLength(split);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		
	}
	
	public void show(OuterUI in){
		i=in;
		populateList(0);
	}
	
	private void populateList(int start) {
		
		
		
		getFrame().removeAll();
		int location=0;
		try {
			System.out.println(start);
			final JSONParser parser = new JSONParser();
			
			for (int in = 0+(start*4); in<4+(start*4);in++){
				Object obj = new Object();
				try{
					obj= parser.parse(split[in]+"}");
					
					
					
				}catch(NullPointerException e){
					
				}
				final JSONObject jsonObject = (JSONObject) obj;
				
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
						
						Http h;
						try {
							h = new Http(new URL("http://localhost:9090/ProfileSend?method=getUser&username="+jsonObject.get("from")));
							String s= h.send();
							Object obj=parser.parse(s);
							JSONObject jsonObject=(JSONObject) obj;
							Profile p = new Profile(getFrame(), authToken, user, jsonObject);
							p.show(i);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						} 
						
						
					}
				});
				user_1.add(btnViewUser);
				
				
				
				JLabel lblLocation = new JLabel("From: "+ jsonObject.get("name"));
				lblLocation.setBounds(10, 11, 348, 14);
				user_1.add(lblLocation);
				
				JLabel lblGender = new JLabel("Time: "+ jsonObject.get("date"));
				lblGender.setBounds(10, 33, 348, 14);
				user_1.add(lblGender);
				
				JLabel lblInterestedIn = new JLabel("Message: "+ jsonObject.get("message"));
				lblInterestedIn.setBounds(10, 56, 348, 14);
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

	@Override
	void show() {
		// TODO Auto-generated method stub
		
	}
	
}

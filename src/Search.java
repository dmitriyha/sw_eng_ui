import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Search extends UIElement{
	String split[];
	int arrayLength;
	int page=0;
	
	Search(JPanel f, String authToken, String user) {
		super(f, authToken, user);
		// TODO Auto-generated constructor stub
	}
	
	public void show(final OuterUI i) {
		// TODO Auto-generated method stub
		JButton btnLocationMatch = new JButton("location match");
		btnLocationMatch.setBounds(10, 11, 139, 23);
		btnLocationMatch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Http h;
				try {
					h = new Http(new URL("http://localhost:9090/HttpMatch?method=location&user="+user));
					
					String s= h.send();
					
					SearchCommand c=new SearchCommand(getFrame(), authToken, user,s);
					c.show(i);
					i.repaintFrame();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		getFrame().add(btnLocationMatch);
		
		
		JButton btnBlindMatch = new JButton("Blind Match");
		btnBlindMatch.setBounds(150, 11, 139, 23);
		btnBlindMatch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Http h;
				try {
					h = new Http(new URL("http://localhost:9090/HttpMatch?method=blind&user="+user));
					
					String s= h.send();
					
					SearchCommand c=new SearchCommand(getFrame(), authToken, user,s);
					c.show(i);
					i.repaintFrame();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		getFrame().add(btnBlindMatch);
	}
	
	@Override
	void show() {
		// TODO Auto-generated method stub
		
	}

}

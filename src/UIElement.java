import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public abstract class UIElement {
	
	private JPanel frame;
	protected String authToken ="-1"; 
	protected String user ="-1"; 
	
	UIElement(JPanel f,String authToken, String user){
		this.authToken=authToken;
		this.user=user;
		setFrame(f);
		getFrame().removeAll();
	}
	
	abstract void show();
	

	protected JPanel getFrame() {
		return frame;
	}

	private void setFrame(JPanel frame) {
		this.frame = frame;
	}
	
	protected String getUser(){
		return user;
	}
	
	protected String getToken(){
		return user;
	}

	public void show(JLabel lblWelcome) {
		// TODO Auto-generated method stub
		
	}

	public void show(JFrame frame, JLabel lblWelcome) {
		// TODO Auto-generated method stub
		
	}

	public void show(OuterUI i) {
		// TODO Auto-generated method stub
		
	}
}

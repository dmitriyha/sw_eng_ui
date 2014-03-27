

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Http {
	URL link;
	public Http(URL url){
		link=url;
	}
	
	public String send() throws Exception{
		URLConnection yc = link.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        String inputLine;

        inputLine = in.readLine();
        
        return inputLine;
	}
}

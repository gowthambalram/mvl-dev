package com.neutrinos.manulife;

/**
 * This class was automatically generated by the data modeler tool.
 */
 
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class NB_TOKEN_GENERATOR implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    public NB_TOKEN_GENERATOR() {
    }

public static String getToken(String tokenUrl,String urlParameters) {
		String accessToken="";
		try {
	    	
	        StringBuilder result = new StringBuilder();
	        URL url = new URL(tokenUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));

	        conn.setUseCaches(false);
	        conn.setDoOutput(true);


	        DataOutputStream wr = new DataOutputStream (
	            conn.getOutputStream());
	            wr.writeBytes(urlParameters);
	            wr.close();

	            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = rd.readLine()) != null) {
	            	System.out.println(line);
	                result.append(line);
	            }
	            rd.close();
	           String tokenResponse= result.toString();
	            JSONObject obje = new JSONObject(tokenResponse);
	            accessToken=(String) obje.get("access_token");
	            System.out.println(obje.get("access_token"));
	        }
	        catch (Exception e) {
	            return e.getMessage().toString();
	        }
		
	    return accessToken;
	    }
	
}
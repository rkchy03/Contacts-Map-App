package com.hp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.regex.Pattern;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MapActivity extends Activity {
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
   
   
   private GoogleMap googleMap;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.drawable.ma);
      
      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		
		if(!isNetworkAvailable())
		{
			Toast.makeText(MapActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
			//Intent intentContact = new Intent().setClass(this, ContactActivity.class);
			
		}
		else
		{
		
		 URL oracle=null;
	        try {
	            oracle = new URL("http://private-b08d8d-nikitest.apiary-mock.com/contacts");
	        } catch (MalformedURLException ex) {
	            //Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        BufferedReader in=null;
	        try {
	            in = new BufferedReader(
	                    new InputStreamReader(oracle.openStream()));
	        } catch (IOException ex) {
	           // Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
	      
	        }
	        String username="";
	        String email="",email1="";
	        String phone="",phone1="";
	        String officePhone="",officePhone1="";
	        String latitude="";
	        String longitude="";

	        int i=0;
	        String inputLine;
	        
	            {
	            try {
	                while ((inputLine = in.readLine()) != null)
	                {
	                    // System.out.println(inputLine);
	                    
	                    if(inputLine.contains("name"))
	                        username+="@"+inputLine;
	                    
	                      if(Pattern.compile("email").matcher(inputLine).find())
	                    email=inputLine;
	                     if(Pattern.compile("phone").matcher(inputLine).find())
	                    phone=inputLine;
	                    if(Pattern.compile("officePhone").matcher(inputLine).find())
	                    officePhone=inputLine;
	                     if(Pattern.compile("latitude").matcher(inputLine).find())
	                    latitude+="@"+inputLine;
	                     if(inputLine.contains("longitude"))
	                    {longitude+="@"+inputLine;
	                    i++;
	                    
	                    
	                    //System.out.println(username);
	                       // System.out.println(email);
	                    email1+="@"+email;
	                    phone1+="@"+phone;
	                        //System.out.println(phone);
	                      officePhone1+="@"+officePhone;  
	                       // System.out.println(latitude);
	                       // System.err.println(longitude);
	                       // System.out.println("\n");
	                        //System.out.println("\n");
	                        
	                        // username="";
	         email="";
	         phone="";
	        officePhone="";
	        //latitude="";
	       // longitude="";
	                    
	                    }
	                   
	                    
	                }   } catch (Exception e) {
	                    
	                    System.out.println(e);
	                
	            }
	        
	        
	            try {
	                in.close();
	            } catch (IOException ex) {
	                //Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
	            }
	    }
	            
	          
	           
	         
	           
	        int count=0;
	          
	            
	        String ph[];
	        
	        ph=new String[i];
	        
	               StringTokenizer st7 = new StringTokenizer(phone1, ",");

			while (st7.hasMoreElements()) {
				ph[count]=(st7.nextElement().toString().replace("@", "").replace("\"phone\": ",""));
			count++;
	                } 
	                count=0;
	                double lati[];
	                lati=new double[i];
	                
	                StringTokenizer st3 = new StringTokenizer(latitude, ",");

			while (st3.hasMoreElements()) {
				lati[count]=Double.parseDouble(st3.nextElement().toString().replace("@", "").replace("\"latitude\": ", ""));
			count++;
	                }   
	                
	                count=0;
	                String uname[];
	                uname=new String[i];
	                
	           StringTokenizer st4 = new StringTokenizer(username, ",");

			while (st4.hasMoreElements()) {
				uname[count]=(st4.nextElement().toString().replace("@", "").replace("\"name\": ", ""));
			count++;
	                } 
	                
	                count=0;
	                String mail[];
	                mail=new String[i];
	                
	                StringTokenizer st5 = new StringTokenizer(email1, ",");

			while (st5.hasMoreElements()) {
				mail[count]=(st5.nextElement().toString().replace("@", "").replace("\"email\": ", ""));
			count++;
	                } 
	                
	                count=0;
	                String ophone[];
	                ophone=new String[i];
	                StringTokenizer st6 = new StringTokenizer(officePhone1, ",");

			while (st6.hasMoreElements()) {
				ophone[count]=(st6.nextElement().toString().replace("@", "").replace("\"officePhone\": ", ""));
			count++;
	                } 
	                
	          
	             
	                
	                
	                
	                    
      
      try {
         if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().
            findFragmentById(R.id.map)).getMap();
         }
         /*LatLng Tests = new LatLng(74 , 77.6271);
         googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
         Marker TP = googleMap.addMarker(new MarkerOptions().
         position(Tests).title("MYTest"));*/
         LatLng Tests;
         
         for(int my=0;my<i;my++)
         {
        	  Tests = new LatLng(lati[my],77.6271);
        	 googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
         Marker TP1 = googleMap.addMarker(new MarkerOptions().
         position(Tests).title(uname[my].trim()+" "+mail[my].trim()
        		 ).snippet("ph: "+ph[my].trim()+" oph: "+ophone[my].trim()));
         
         /*
          * position(Tests).title("name"+uname[my]+"\nemail"+mail[my]
        		 +"\nphone"+ph[my]+"\nofficePhone"+ophone[my]
        		 ));
          * */
         }
         
        // googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Tests, 17));
         
         
         
         
      }
      catch (Exception e) {
         e.printStackTrace();
      }
		}
   }
}
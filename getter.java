/*------------------------------
* getter.java
* Description: Main utility class used by the project, for extracting financial & industry data.
*
* By: Harrison Walters
-------------------------------*/

package Clockwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class getter {
	
	public static String companyName(String company) throws Exception {
		
		URL getURL = new URL("http://download.finance.yahoo.com/d/quotes.csv?s="+company+"&f=n&e=.csv");
        BufferedReader in = new BufferedReader(new InputStreamReader(getURL.openStream()));
        String inputLine = in.readLine();
        in.close();
        
        return inputLine;
		
	}
	
	public static String [] generalData(String company) throws Exception {
		
		URL getURL = new URL("http://download.finance.yahoo.com/d/quotes.csv?s="+company+"&f=aa2a5bb4b6cp2dee7e8e9gf6hjj1j4j5j6kk4k5mm3m4m5m6m7m8opp5rr5s6s7t8vwy&e=.csv");
        BufferedReader in = new BufferedReader(new InputStreamReader(getURL.openStream()));
        String inputLine = in.readLine();
        in.close();
        
        return inputLine.split(",");
        
	}
	
	public static String industry(String company) {
		
		Document doc = null;
		try {
			doc = Jsoup.connect("https://finance.yahoo.com/q/in?s="+company+"+Industry").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return doc.select("#yfncsumtab > tbody > tr:nth-child(2) > td:nth-child(1) > table.yfnc_datamodoutline1 > tbody > tr > td > table > tbody > tr:nth-child(2) > td > a").text();
    	
	}
	
	public static String [] competition(String company) {
		
		Document doc = null;
		try {
			doc = Jsoup.connect("https://ca.finance.yahoo.com/q/co?s="+company).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String [] listData = new String[3];
		
		String str;
		str = doc.select("#yfncsumtab > tbody > tr:nth-child(2) > td:nth-child(1) > table.yfnc_datamodoutline1 > tbody > tr > td > table > tbody > tr:nth-child(1) > th:nth-child(3) > a").text();
    	if (!str.equals(""))
    		listData[0] = str; else
    			listData[0] = "N/A";
    	str = doc.select("#yfncsumtab > tbody > tr:nth-child(2) > td:nth-child(1) > table:nth-child(2) > tbody > tr > td > table > tbody > tr:nth-child(1) > th:nth-child(4) > a").text();
    	if (!str.equals(""))
    		listData[1] = str; else
    			listData[1] = "N/A";
    	str = doc.select("#yfncsumtab > tbody > tr:nth-child(2) > td:nth-child(1) > table:nth-child(2) > tbody > tr > td > table > tbody > tr:nth-child(1) > th:nth-child(5) > a").text();
    	if (!str.equals(""))
    		listData[2] = str; else
    			listData[2] = "N/A";
    	
    	return listData;
		
	}

}

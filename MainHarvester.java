package Clockwork;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.Timer;
import javax.swing.UIManager;

public class MainHarvester {
	
	private static JFrame frame;
	static JLabel counterA;
	static JLabel counterB;
	static JLabel counterC;
	static JLabel titleG;
	
	static int minIntervals = 1;
	static ArrayList<String> companies;
	
	
	static int minute;
	static int hour;
	static int day;
	static int year;
	
	public static void main(String[] args) {
		
		loadCompanies();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	          public void run() {
	               createAndShowGUI();
	          }
	        });
		
		
		//writeInfo();
		
	}
	
	public static void writeGeneral(String type) {
		
		for(String company: companies) {

				try {
					
					String filename = null;
					if (type.equals("general"))
						filename = "harvested_data/"+company+".bdat";
					else if (type.equals("competition"))
						filename = "harvested_competition/"+company+".bdat";
				    FileWriter fw = new FileWriter(filename,true);

	            	Calendar rightNow = Calendar.getInstance();
	            	int hr = rightNow.get(Calendar.HOUR_OF_DAY);
	            	int mn = rightNow.get(Calendar.MINUTE);
	            	int dy = rightNow.get(Calendar.DAY_OF_YEAR);
	            	int yr = rightNow.get(Calendar.YEAR);
				   
					String timeLog = yr+","+dy+","+hr+","+mn;
					fw.write("<"+timeLog+">\n");
					if (type.equals("general"))
						fw.write(String.join(",", getter.generalData(company))+"\n");
					else if (type.equals("competition"))
						fw.write(String.join(",", getter.competition(company))+"\n");
					fw.write("\n");
				    
				    fw.close();
				    
				} catch (Exception e) {
					e.printStackTrace();
				} 
	        
		}
		
		JLabel label = null;
		if (type.equals("general"))
			label = counterA;
		else if (type.equals("competition"))
			label = counterB;
		
		label.setText( label.getText().substring(1, label.getText().length()) );
		label.setText( "["+String.valueOf(Integer.valueOf(label.getText().substring(0,label.getText().indexOf("]")))+1)+"]" );
	        
	}
	
	public static void loadCompanies() {
		companies = new ArrayList<String>();
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("companies.bdat"));
 
			while ((sCurrentLine = br.readLine()) != null) {
				companies.add(sCurrentLine);
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(companies.toString());
	}
	
	public static void addComponentsToPane(Container pane) {

        pane.setLayout(null);
        
        UIManager.put("Label.font", new Font("Verdana",1,13));
        JLabel title = new JLabel("Current Time");
        title.setBounds(5, 5, 150, 20);
        JLabel titleB = new JLabel("Rec Intervals");
        titleB.setBounds(5, 5+50, 200, 20);
        JLabel titleD = new JLabel("Rec Hours (24h)");
        titleD.setBounds(265+100, 5, 150, 20);
        titleG = new JLabel("- Not Recording -");
        titleG.setForeground(new Color(180,180,180));
        titleG.setBounds(265+100, 310, 150, 20);
        counterA = new JLabel("-");
        counterA.setBounds(20, 4+50+22, 80, 20);
        counterB = new JLabel("-");
        counterB.setBounds(20, 4+50+22+22, 80, 20);
        counterC = new JLabel("-");
        counterC.setBounds(20, 4+50+22+22+22, 80, 20);
        UIManager.put("Label.font", new Font("Verdana",2,13));
        JLabel time = new JLabel();
        time.setBounds(20, 5+18, 200, 20);
        JLabel titleC = new JLabel("(m) General Data");
        titleC.setBounds(100+40, 5+50+22, 150, 20);
        JLabel titleF = new JLabel("(d) Competition");
        titleF.setBounds(100+40, 5+50+22+22, 150, 20);
        JLabel titleH = new JLabel("(d) Industry Data");
        titleH.setBounds(100+40, 5+50+22+22+22, 150, 20);
        JLabel titleE = new JLabel("to");
        titleE.setBounds(360+100, 5+30+2, 150, 20);
        
        JSpinner spinner = new JSpinner();
        spinner.setBounds(40+20, 7+50+22, 70, 20);
        spinner.setValue(20);
        JSpinner spinnerB = new JSpinner();
        spinnerB.setBounds(40+20, 7+50+22+22, 70, 20);
        spinnerB.setValue(2);
        JSpinner spinnerC = new JSpinner();
        spinnerC.setBounds(40+20, 7+50+22+22+22, 70, 20);
        spinnerC.setValue(1);
        JSpinner h1 = new JSpinner();
        h1.setBounds(280+100, 5+23+2, 70, 20);
        h1.setValue(6);
        JSpinner h2 = new JSpinner();
        h2.setBounds(280+100, 5+43+2, 70, 20);
        h2.setValue(13);
        
        JButton b1 = new JButton("Begin Rec");
        b1.setBounds(276+100, 400-65, 100, 24);
        b1.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	spinner.setEnabled(false);
            	spinnerB.setEnabled(false);
            	h1.setEnabled(false);
            	h2.setEnabled(false);
            	b1.setEnabled(false);
            	counterA.setText("[0]");
            	counterB.setText("[0]");
            } 
        });
        
        
        frame.add(title);
        frame.add(titleB);
        frame.add(titleC);
        frame.add(titleD);
        frame.add(titleE);
        frame.add(titleF);
        frame.add(titleG);
        frame.add(titleH);
        frame.add(counterA);
        frame.add(counterB);
        frame.add(counterC);
        frame.add(time);
        frame.add(b1);
        frame.add(h1);
        frame.add(h2);
        frame.add(spinner);
        frame.add(spinnerB);
        frame.add(spinnerC);

        int delay = 1000; //milliseconds
        ActionListener iterator = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	
            	Calendar rightNow = Calendar.getInstance();
            	hour = rightNow.get(Calendar.HOUR_OF_DAY);
            	minute = rightNow.get(Calendar.MINUTE);
            	day = rightNow.get(Calendar.DAY_OF_YEAR);
            	year = rightNow.get(Calendar.YEAR);

                String second = new SimpleDateFormat("ss").format(new Date());
                time.setText(year+"/"+day+"d "+hour+"h:"+minute+"m ("+second+"s)");
                
                if (b1.isEnabled() == false) {
                	if ((hour >= (Integer)(h1.getValue())) && (hour < (Integer)(h2.getValue()))) {
                    	if ((minute % (Integer)(spinner.getValue()) == 0) && (Integer.valueOf(second) == 0)) {
                        	writeGeneral("general");
                        }
                    	
                    	titleG.setText("- Recording -");
                        titleG.setForeground(Color.green);
                        titleG.setBounds(265+14+100, 310, 150, 20);
                    } else {
                    	titleG.setText("- Not Recording -");
                        titleG.setForeground(Color.red);
                        titleG.setBounds(265+100, 310, 150, 20);
                    }
                	
                	if (day % (Integer)(spinnerB.getValue()) == 0)
                	if ((hour == 0) && (minute == 0) && (Integer.valueOf(second) == 0)) {
                		writeGeneral("competition");
                	}
                }
                
                
            }
        };
        new Timer(delay, iterator).start();
        
	}
	
	private static void updateDropbox() {
		
	}
	
	private static void createAndShowGUI() {

        frame = new JFrame("Harvester (v2)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
 
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
    }

}

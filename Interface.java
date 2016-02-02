package Clockwork;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicArrowButton;

public class MainTester {

	
	static int mm = 60;
	static int yy = 2015;
	static int dd = 160;
	static int hh = 12;
	
	static int hStart = 6;
	static int hEnd = 13;
	static int mInterval = 20;
	
	private static JFrame frame;
	private static JTable table;
	private static JTextArea text;
	private static JTextArea ind;
	private static JLabel titleData;
	private static JLabel titleDay;
	private static BasicArrowButton r;
	private static BasicArrowButton l;
	private static String [] listData =
		{
		"-",
		"-",
		"-"
	};
	private static JList list;
	
	private static int time = 1;
	
	public static int numCompanies;
	private static int TABLEHEIGHT = 400;
	
	public static String dataValues[][] =
		{
			{"YAHOO FINANCE","",""},
			{ "[0] Ask", "1.0", "-" }, 
			{ "[1] Average Daily Volume", "1.0", "-" }, 
			{ "[2] Ask Size", "1.0", "-" }, 
			{ "[3] Bid", "1.0", "-" }, 
			{ "[4] Book Value", "1.0", "-" }, 
			{ "[5] Bid Size", "1.0", "-" }, 
			{ "[6] Change", "1.0", "-" }, 
			{ "[7] Change in %", "1.0", "-" }, 
			{ "[9] Dividend/Share", "1.0", "-" }, 
			{ "[10] EPS", "1.0", "-" }, 
			{ "[11] EPS Estimate Current Yr", "1.0", "-" }, 
			{ "[12] EPS Estimate Next Yr", "1.0", "-" }, 
			{ "[13] EPS Estimate Next QRT", "1.0", "-" }, 
			{ "[14] Day's Low", "1.0", "-" }, 
			{ "[15] Float Shares", "1.0", "-" }, 
			{ "[16] Day's High", "1.0", "-" }, 
			{ "[17] 52W Low", "1.0", "-" }, 
			{ "[18] Market Cap", "1.0", "-" }, 
			{ "[19] EBITDA", "1.0", "-" }, 
			{ "[20] Change from 52W Low", "1.0", "-" }, 
			{ "[21] % Change from 52W Low", "1.0", "-" }, 
			{ "[22] 52W High", "1.0", "-" }, 
			{ "[23] Change from 52W High", "1.0", "-" }, 
			{ "[24] % Change from 52W High", "1.0", "-" }, 
			{ "[27] Day's Range", "1.0", "-" }, 
			{ "[28] 50-day Moving Avg", "1.0", "-" }, 
			{ "[29] 200-day Moving Avg", "1.0", "-" }, 
			{ "[30] Change from 200-day Moving Avg", "1.0", "-" }, 
			{ "[31] % Change from 200-day Moving Avg", "1.0", "-" }, 
			{ "[32] Change from 50-day Moving Avg", "1.0", "-" }, 
			{ "[33] % Change from 50-day Moving Avg", "1.0", "-" }, 
			{ "[34] Open", "1.0", "-" }, 
			{ "[35] Previous Close", "1.0", "-" }, 
			{ "[36] Price/Sales", "1.0", "-" }, 
			{ "[37] P/E Ratio", "1.0", "-" }, 
			{ "[38] PEG Ratio", "1.0", "-" },
			{ "[40] Revenue", "1.0", "-" }, 
			{ "[41] Short Ratio", "1.0", "-" }, 
			{ "[42] 1Y Target Price", "1.0", "-" }, 
			{ "[43] Vol", "1.0", "-" }, 
			{ "[47] 52W Range", "1.0", "-" }, 
			{ "[48] Dividend Yield", "1.0", "-" }
		};
	
    public static void addComponentsToPane(Container pane) {

        pane.setLayout(null);

        text = new JTextArea();
        text.setSelectionColor(Color.GRAY);
        text.setBounds(15, 43+10, 107, 25);
        text.setBorder(BorderFactory.createEtchedBorder());
        
        ind = new JTextArea();
        ind.setSelectionColor(Color.GRAY);
        ind.setBounds(440, 240+25, 200, 25);
        ind.setBorder(BorderFactory.createEtchedBorder());
        ind.setText("-");
        ind.setEditable(false);
        
        JButton b1 = new JButton("Get Info");
        b1.setBounds(120, 43+10, 100, 24);
        b1.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	try {
					getINFO(text.getText());
					getCOMPETITORS(text.getText());
					getINDUSTRYDATA(text.getText());
				} catch (Exception eA) {
					eA.printStackTrace();
				}
            } 
        });
        
        UIManager.put("Label.font", new Font("Verdana",1,13));
        JLabel titleMain = new JLabel("Ticker Symbol");
        titleMain.setBounds(15, 20+10, 250, 20);
        titleData = new JLabel(" - ");
        titleData.setBounds(15, 90, 400, 20);
        JLabel titleCompetitors = new JLabel("Competitors");
        titleCompetitors.setBounds(440, 90, 250, 20);
        JLabel titleIndustry = new JLabel("Industry Data");
        titleIndustry.setBounds(440, 240, 250, 20);
        UIManager.put("Label.font", new Font("Verdana",2,12));
        titleDay = new JLabel("Time: -");
        titleDay.setBounds(65, 115+16+TABLEHEIGHT, 250, 20);
        
        list = new JList(listData);
        JScrollPane scrollpaneB = new JScrollPane(list);
        scrollpaneB.setBounds(440, 115, 200, 100);
        list.setSelectionBackground(Color.green);
        
        String columnNames[] = { "Data", "Weight", "Val" };
		table = new JTable( dataValues, columnNames );
		table.getColumn("Data").setMinWidth(200);
		table.getColumn("Weight").setMaxWidth(70);
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setBounds(15, 115, 400, TABLEHEIGHT);
        table.setSelectionBackground(Color.green);
        ((DefaultCellEditor)table.getDefaultEditor(String.class)).setClickCountToStart(1);
        
        r = new BasicArrowButton(BasicArrowButton.EAST);
        l = new BasicArrowButton(BasicArrowButton.WEST);
        l.setBounds(15, 115+10+TABLEHEIGHT, 20, 35);
        r.setBounds(35, 115+10+TABLEHEIGHT, 20, 35);
        l.setEnabled(false);
        r.setEnabled(false);
        l.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	time--;
            	if (time!=0)
            		r.setEnabled(true);

            	mm -= mInterval;
            	if (mm < 0) {
            		mm = 60-mInterval;
            		hh -= 1;
            		if (hh < hStart) {
            			hh = hEnd-1;
            			dd -= 1;
            		}
            	}
            		
            	getPrevdata(text.getText());	
            } 
        });
        r.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	time++;
            	titleDay.setText("Time: ("+String.valueOf(time)+")");
            	if (time == 0)
            		r.setEnabled(false);
            	
            	mm += mInterval;
            	if (mm >= 60) {
            		mm = 0;
            		hh += 1;
            		if (hh == hEnd) {
            			hh = hStart;
            			dd += 1;
            		}
            	}
            		
            	getPrevdata(text.getText());	
            } 
        });
        
        pane.add(titleMain);
        pane.add(titleData);
        pane.add(titleDay);
        pane.add(titleCompetitors);
        pane.add(titleIndustry);
        pane.add(b1);
        pane.add(text);
        pane.setBackground(new Color(240,240,240));
        pane.add(scrollpane);
        pane.add(scrollpaneB);
        pane.add(l);
        pane.add(r);
        pane.add(ind);
        
    }
    
    public static void getPrevdata(String company) {
    	
    	Scanner scanner = null;
		try {
			scanner = new Scanner(new File("harvested_data/"+company+".bdat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				
    	String lookFor = "<"+yy+","+dd+","+hh+","+mm+">";
    	String info = "null";
    	
    	while(scanner.hasNextLine()) {
    		if (scanner.nextLine().equals(lookFor)) {
    			info = scanner.nextLine();
    			break;
    		}
    		String temp;
    		temp = scanner.nextLine();
    		temp = scanner.nextLine();
    	}
    	
    	if (info.equals("null")) {
    		for(int i=0; i<30; i++)
            	dataValues[i+1][2] = "no data";
            table.repaint();
    	} else {
            String [] data = info.split(",");
            for(int i=0; i<data.length; i++)
            	dataValues[i+1][2] = data[i];
            table.repaint();
    	}
    	
    	titleDay.setText("Time: ("+String.valueOf(time)+") "+lookFor);
    	
    }
    
    private static void getINFO(String company) throws Exception {

        String [] data = getter.generalData(company);
        for(int i=0; i<data.length; i++)
        	dataValues[i+1][2] = data[i];
        table.repaint();
        
        titleData.setText("<"+text.getText()+"> "+getter.companyName(company));  
        titleDay.setText("Time: Present moment");
        time = 1;
        r.setEnabled(false);
        l.setEnabled(true);
        
    }
    
    public static void getCOMPETITORS(String company) {
    	
    	String [] str = getter.competition(company);
    	for(int i=0; i<3; i++) 
    		listData[i] = str[i];
    	list.repaint();
    	
    }
    
    public static void getINDUSTRYDATA(String company) {
    	
    	ind.setText(getter.industry(company));
    	
    }
 
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
        	  frame = new JFrame("Tester");
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              addComponentsToPane(frame.getContentPane());
              frame.setSize(800, 600);
              frame.setVisible(true);
              frame.setResizable(false);
              frame.setLocationRelativeTo(null);
          }
        });
    
    }
}

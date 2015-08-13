package bnerUI;

import bner.BoundBreakStore;
import bnerUI.EWrapMsgGen;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NerProgram extends JFrame {
    private JMenuBar 		menuBar = new JMenuBar();
    private JMenu		mainMenu = new JMenu ("Read");
    private JMenu		resolveMenu = new JMenu ("Resolve");
    private JMenu		reasonMenu = new JMenu ("Reason");
    private JButton		m_ok = new JButton ("OK");
    private NerTextPanel	m_files = new NerTextPanel("Uploaded files to Process",false);
    private NerTextPanel	m_report = new NerTextPanel("Report Output",false);
    private BoundBreakStore	m_task = new BoundBreakStore ( );
    
    String reportXML;
    
    NerProgram(){
	JPanel scrollingWindowDisplayPanel = new JPanel( new GridLayout(0,1));
	scrollingWindowDisplayPanel.add(m_files);
	scrollingWindowDisplayPanel.add(m_report);
	
	menuBar.add(mainMenu);
	menuBar.add(resolveMenu);
	menuBar.add(reasonMenu);
	
	this.setJMenuBar(menuBar);
	JMenuItem importMenuItem = new JMenuItem();
	
	JMenuItem selectMenuItem = new JMenuItem();
	selectMenuItem.addActionListener(new ActionListener(){
	    public void actionPerformed( ActionEvent e){
		//@to-do: Add file select
	    }
	});
	
	JMenuItem zipselectMenuItem = new JMenuItem();
	zipselectMenuItem.addActionListener(new ActionListener(){
	    public void actionPerformed( ActionEvent e){
		//@to-do: Add Zip Upload
	    }
	});
	JMenuItem dictionaryMenuItem = new JMenuItem();
	dictionaryMenuItem.addActionListener(new ActionListener(){
	    public void actionPerformed( ActionEvent e){
		//@to-do: Add Zip Upload
	    }
	});
	
	
	JMenuItem processFileMenuItem = new JMenuItem();
	processFileMenuItem.addActionListener(new ActionListener(){
	    public void actionPerformed( ActionEvent e){
		
		onProcess_file();
	    }
   
	});
	
	JMenuItem processZipMenuItem = new JMenuItem();
	processZipMenuItem.addActionListener(new ActionListener(){
	    public void actionPerformed( ActionEvent e){
		//@to-do: Begin Parallel processing zip 
		onProcess_zip();
	    }
	});
	
	JMenuItem clearReportMenuItem = new JMenuItem();
	clearReportMenuItem.addActionListener(new ActionListener(){
	    public void actionPerformed( ActionEvent e){
		//@to-do: Begin Parallel processing zip 
	    }
	});
	
	JMenuItem createReportMenuItem = new JMenuItem();
	createReportMenuItem.addActionListener(new ActionListener(){
	    public void actionPerformed( ActionEvent e){
		//@to-do: Begin Parallel processing zip 
	    }
	});
	
	JMenuItem viewReportMenuItem = new JMenuItem();
	viewReportMenuItem.addActionListener(new ActionListener(){
	    public void actionPerformed( ActionEvent e){
		//@to-do: Begin Parallel processing zip 
	    }
	});
	JMenuItem recognizeMenuItem = new JMenuItem();
	recognizeMenuItem.addActionListener(new ActionListener(){
	    public void actionPerformed( ActionEvent e){
		onRecognText();
	    }
	});
	JMenuItem recognizeZipMenuItem = new JMenuItem();
	recognizeZipMenuItem.addActionListener(new ActionListener(){
	    public void actionPerformed( ActionEvent e){
	    onRecognTextZip(); 
	    }
	});
	
	 
	selectMenuItem.setText("Upload Source File");
	zipselectMenuItem.setText("Upload Source Zip");
	dictionaryMenuItem.setText("Upload Dictionary");
	
	processFileMenuItem.setText("Process Text File");
	processZipMenuItem.setText("Process Zip Files");
	recognizeMenuItem.setText("Recognize Source file from Dictionary");
	recognizeZipMenuItem.setText("Recognize Source zip from Dictionary");
	
	clearReportMenuItem.setText("Clear Report");
	createReportMenuItem.setText("Generate XML");
	viewReportMenuItem.setText("View report contents");
	
	mainMenu.add(selectMenuItem);
	mainMenu.add(zipselectMenuItem);
	mainMenu.add(dictionaryMenuItem);
	
	resolveMenu.add(processFileMenuItem);
	resolveMenu.add(processZipMenuItem);
	resolveMenu.add(recognizeMenuItem);
	resolveMenu.add(recognizeZipMenuItem);
	
	
	reasonMenu.add(clearReportMenuItem);
	reasonMenu.add(createReportMenuItem);
	reasonMenu.add(viewReportMenuItem);
	
	JPanel pMidPanel = new JPanel();
	
	JPanel buttonPanel = createButtonPanel();
	pMidPanel.add(m_ok);
	
	getContentPane().add(scrollingWindowDisplayPanel, BorderLayout.CENTER);
	getContentPane().add(buttonPanel, BorderLayout.EAST);
	setSize(800,800);
	setTitle("B - NER - E");
	setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE);
	
	    
    }

    public void onProcess_file() {
	try{
	    BoundBreakStore.storeReport(1);    
	    currentTime(100);
	}
	catch( Exception e){
	    bnerp.inform(this,"Error - "+e);
	    return;
	}
	
	String msg = EWrapMsgGen.reportSummary(5,130);
	//m_files.add( );
    }

    public void onProcess_zip(){
	try{
	    BoundBreakStore.storeReport(2);    
	}
	catch( Exception e){
	    bnerp.inform(this,"Error - "+e);
	    return;
	}
    }
    public void onRecognText(){
	try{
	    BoundBreakStore.storeReport(5);    
	}
	catch( Exception e){
	    bnerp.inform(this,"Error - "+e);
	    return;
	}
    }
    
    public void onRecognTextZip(){
    	try{
    	    BoundBreakStore.storeReport(6);    
    	}
    	catch( Exception e){
    	    bnerp.inform(this,"Error - "+e);
    	    return;
    	}
        }
    
    public void currentTime(long time){
	String msg = EWrapMsgGen.currentTime(time);
	
    }
    
    public void reportParameters (String xml){
	displayXML(EWrapMsgGen.REPORT_PARAMETERS, xml);
    }
    
    
    private void displayXML(String reportParameters, String xml) {
	// TODO Auto-generated method stub
	
    }

    private JPanel createButtonPanel() {
	JPanel buttonPanel = new JPanel(new GridLayout(0,1));
	
	buttonPanel.add(new JPanel());
	
	return buttonPanel;
    }

   
}

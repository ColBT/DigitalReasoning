package bnerUI;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class bnerp {

    public static void main(String[] args) {
	
	NerProgram ner = new NerProgram();
	ner.setVisible(true);
	
    }
     
    static public void inform( final Component parent, final String str){
	if (SwingUtilities.isEventDispatchThread() ){
	    showMsg(parent,str, JOptionPane.INFORMATION_MESSAGE);
	}
	else {
	    SwingUtilities.invokeLater( new Runnable () {
		public void run(){
		    showMsg (parent,str, JOptionPane.INFORMATION_MESSAGE);
    		}
    		
    	    }
		    
		   );
	}
    }
    
    static private void showMsg ( Component parent, String str, int type ){
	// this function displays dialog message
	JOptionPane.showMessageDialog(parent ,  str, "Remote Programming Exercise", type);
    }
    
    public static final String CONSTANT_TEXT_SOURCE="C:/NLP/B_NER_E/src/data/nlp_data.txt";
    public static final String CONSTANT_ZIP_SOURCE="C:/NLP/B_NER_E/src/data/nlp_data.zip";
    public static final String CONSTANT_DICTIONARY_SOURCE="C:/NLP/B_NER_E/src/data/NER.txt";
    public static final String CONSTANT_REPORT_DIRECTORY_OUTPUT="C:/NLP/B_NER_E/bin/data/";
}

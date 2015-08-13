package bnerUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

 class NerTextPanel extends JPanel {
    public static final Color textBackgroundColor = new Color(5,5,5);
    public static final Color textForegroundColor = new Color(0,245,0);
    public static final Font textComponentFont = new JList().getFont();
    public static final Color textCaretColor = Color.WHITE;
    public static final String lineSeparator = System.getProperty("line.separator");
    
    private JTextArea m_textArea	= new JTextArea();
    private JScrollPane m_scrollPane	= new JScrollPane(m_textArea);
    
    private final static String CRLF = "\r\n";
    private final static String LF = "\n";
    private final static String TAB = "\t";
    private final static String EIGHT_SPACES = "       ";
    private final static String EMPTY_STRING ="";    
    
    NerTextPanel (){
	this(null,false);
    }

    public NerTextPanel(String title, boolean editable) {
	super(new BorderLayout());
	if (title != null){
	    Border border = BorderFactory.createTitledBorder(title);
	    setBorder(border);
	}
	
	m_textArea.setBackground(textBackgroundColor);
	m_textArea.setForeground(textForegroundColor);
	m_textArea.setFont(textComponentFont);
	m_textArea.setCaretColor(textCaretColor);
	m_textArea.setEditable(editable);
	add(m_scrollPane);
    }

    public void clear(){
	m_textArea.setText(EMPTY_STRING);
    }
    
    
    
}

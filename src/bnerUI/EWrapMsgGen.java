package bnerUI;

import java.io.DataOutputStream;
import java.sql.Date;
import java.text.DateFormat;

public class EWrapMsgGen extends AnyWrapMsgGen{
    
    public static final String REPORT_PARAMETERS = "Report Parameters:";
    
    static public String reportParameters(String xml){
	return REPORT_PARAMETERS + "\n" + xml;
    }
    
    static public String reportSummary (int sentence_nu, int word_nu){
	return "Sentence Count: "+sentence_nu +
		"Word Count: "+word_nu;
	
    }
    static public String currentTime(long time){
	return "Current Time = "+time+
	" ("+DateFormat.getDateInstance().format(new Date(time*1000))+")";
    }
    
    
}

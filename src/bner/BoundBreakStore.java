package bner;
/**
 *  @author bt
 *  Used to Identify sentence boundaries and tokenize the text.
 */
import bnerUI.bnerp;
import bner.XML;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.text.BreakIterator;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;


import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class BoundBreakStore {
    
    	public static List properNouns() {
	   
		int wordCount = 1,count=1;
		List nounlist = new ArrayList();
		
		String str2 = "", str1 = "";
		try {
		File fileDir = new File(bnerp.CONSTANT_DICTIONARY_SOURCE);
		
		BufferedReader in = new BufferedReader(
				   new InputStreamReader(
		                      new FileInputStream(fileDir), "UTF8"));
		 
				String str;
		 
				while ((str = in.readLine()) != null) {
				    
				    nounlist.add(str);
				}
		 
				in.close();
			    } 
		catch (IOException e) 
	    {
			System.out.println(e.getMessage());
	    }
		
		return nounlist;
			
	}
	
    
		
	static void extractWords(String target, BreakIterator wordIterator) throws FileNotFoundException {

	      wordIterator.setText(target);
	      int start = wordIterator.first();
	      int end = wordIterator.next();

	      int t = 0;
	       
	      
	      List entity_actual = new ArrayList();
	      entity_actual.add("<?xml version=\"1.0\"?><DOCUMENT>");
	      
	      while (end != BreakIterator.DONE) {
	         String word = target.substring(start,end);
	         if (Character.isLetterOrDigit(word.charAt(0))) {
	            entity_actual.add("<entity><entity_word>"+word+"</entity_word><word_count>"+t+"</word_count></entity>");
	           t++;
	         }
	        
	         start = end;
	         end = wordIterator.next();
	         
	      }
	      entity_actual.add("</DOCUMENT>");     
	          
	      
	      try {
		  PrintStream original = System.out;
		  System.setOut(new PrintStream(new FileOutputStream(bnerp.CONSTANT_REPORT_DIRECTORY_OUTPUT+"entity_report.xml")));
	          
		     for (int i =0;i < entity_actual.size(); i++){
			  
			  System.out.println((String) entity_actual.get(i));
		      }
		     System.setOut(original);
		    } catch (Exception e) {
		         e.printStackTrace();
		    }
	    
	      if (entity_actual.size() > 0){
	      Entity[] entity = new Entity[entity_actual.size()-1];
	     
	      for (int i =0;i < entity_actual.size()-1; i++){
			 
		  entity[i] = new Entity();
		  entity[i].set_entity(entity_actual.toString());
		  i++;
	      }
	     
	      }
	   } 
	
	 static void markBoundaries(String target, BreakIterator iterator) {

	      StringBuffer markers = new StringBuffer();
	      markers.setLength(target.length() + 1);
	      for (int k = 0; k < markers.length(); k++) {
	         markers.setCharAt(k,' ');
	      }

	      iterator.setText(target);
	      int boundary = iterator.first();

	      List entity_boundary_list = new ArrayList();
	      
	      // Determine entity position
	      while (boundary != BreakIterator.DONE) {
	         markers.setCharAt(boundary,'^');
	         boundary = iterator.next();
	         entity_boundary_list.add(boundary);
	      }
	      
	      // Object Data Mode
	      List line_list = new ArrayList();
	      Entity[] entity_position = new Entity[entity_boundary_list.size()-1];  

	      for (int i =0;i < entity_boundary_list.size()-1; i++){
		 
		  entity_position[i] = new Entity();
		  entity_position[i].set_entity(entity_boundary_list.toString());
		  entity_position[i].set_entity_id(i);
		  
		  i++;
	      }
	    
	    
	     // System.out.println("Number markers = "+ (entity_position.length));   
	     
	   } 
	
	 static List inputTarget(){
		 List Input_Target = new ArrayList();
		 List entity_boundary_list = new ArrayList();
		 
		  try{
			  File file = new File(bnerp.CONSTANT_TEXT_SOURCE);
			   BufferedReader br = new BufferedReader(new FileReader(file));
			      String str3 = "", str1 = "";

			      while ((str1 = br.readLine()) != null) {
			              str3 += str1;
			      }
			      
			      Locale currentLocale = new Locale ("en","US");
			      BreakIterator wordIterator = 
			         BreakIterator.getWordInstance(currentLocale);
			      
			      StringBuffer markers = new StringBuffer();
			      markers.setLength(str3.length() + 1);
			      for (int k = 0; k < markers.length(); k++) {
			         markers.setCharAt(k,' ');
			      }

			      wordIterator.setText(str3);
			      int boundary = wordIterator.first();
			 
			 // Determine entity position
			      while (boundary != BreakIterator.DONE) {
			         markers.setCharAt(boundary,'^');
			         boundary = wordIterator.next();
			         entity_boundary_list.add(boundary);
			      }
			      
			 // Object Data Mode
			      List line_list = new ArrayList();
			      Entity[] entity_position = new Entity[entity_boundary_list.size()-1];  

			      for (int i =0;i < entity_boundary_list.size()-1; i++){
				 
				  entity_position[i] = new Entity();
				  entity_position[i].set_entity(entity_boundary_list.toString());
				  entity_position[i].set_entity_id(i);
				  
				  i++;
			      }
			    
			 //System.out.println("Number markers = "+ (entity_position.length));   
			      for (int i =0 ;i < entity_boundary_list.size()-1; i++){
		          }
			      
			      wordIterator.setText(str3);
			      int start = wordIterator.first();
			      int end = wordIterator.next();

			      int t = 0;
			       
			     
			      List entity_actual = new ArrayList();
			      //entity_actual.add("<?xml version=\"1.0\"?><DOCUMENT>");
			      entity_actual.add("<?xml-stylesheet href=\"#s1\" type=\"text/css\"?>");
			      entity_actual.add("    <DOCUMENT>");
			      entity_actual.add("	 <s id=\"s1\">");
			      entity_actual.add("	        entity_word { display: none }");
			      entity_actual.add("	        word_count { display: block }");
			      entity_actual.add("	 </s>");
			      while (end != BreakIterator.DONE) {
			         String word = str3.substring(start,end);
			         if (Character.isLetterOrDigit(word.charAt(0))) {
			            entity_actual.add("<entity><entity_word>"+word+"</entity_word><word_count>"+t+"</word_count></entity>");
			            Input_Target.add(word);
			           t++;
			         }
			        
			         start = end;
			         end = wordIterator.next();
			         
			      }
			      entity_actual.add("</DOCUMENT>");     
			          
			      
			      try {
			      PrintStream original = System.out;
				  System.setOut(new PrintStream(new FileOutputStream(bnerp.CONSTANT_REPORT_DIRECTORY_OUTPUT+"entity_report.xml")));
			          
				     for (int i =0;i < entity_actual.size(); i++){
					  
					  System.out.println((String) entity_actual.get(i));
					  
				      }
				   System.setOut(original);
				    } catch (Exception e) {
				         e.printStackTrace();
				    }
			    
			      if (entity_actual.size() > 0){
			      Entity[] entity = new Entity[entity_actual.size()-1];
			     
			      for (int i =0;i < entity_actual.size()-1; i++){
					 
				  entity[i] = new Entity();
				  entity[i].set_entity(entity_actual.toString());
				  i++;
			      }
			     
			      }
			     // System.out.println(Input_Target+" "+entity_boundary_list);
			      
		  } catch (IOException e){
			  System.err.println("Caught IOException: " + e.getMessage());
		  }
		 // System.out.println(Input_Target);
		 return Input_Target;
	 }
	 
	 static List inputTargetZip(){
		 List Input_Target = new ArrayList();
		 List entity_boundary_list = new ArrayList();
		 
		  try{
			  String str3 = "", str1 = "";
			  final ZipFile file = new ZipFile( bnerp.CONSTANT_ZIP_SOURCE );
		      try
		      {
		          final Enumeration<? extends ZipEntry> entries = file.entries();
		          while ( entries.hasMoreElements() )
		          {
		             final ZipEntry entry = entries.nextElement();
		             
			          str3 +=convertStreamToString( file.getInputStream( entry ) ).replaceAll("\\n","");
		          }
		        
		      }
		     
		      finally
		      {
		          file.close();
		      }
		      System.out.println(str3);
			      
			      Locale currentLocale = new Locale ("en","US");
			      BreakIterator wordIterator = 
			         BreakIterator.getWordInstance(currentLocale);
			      
			      StringBuffer markers = new StringBuffer();
			      markers.setLength(str3.length() + 1);
			      for (int k = 0; k < markers.length(); k++) {
			         markers.setCharAt(k,' ');
			      }

			      wordIterator.setText(str3);
			      int boundary = wordIterator.first();
			 
			 // Determine entity position
			      while (boundary != BreakIterator.DONE) {
			         markers.setCharAt(boundary,'^');
			         boundary = wordIterator.next();
			         entity_boundary_list.add(boundary);
			      }
			      
			 // Object Data Mode
			      List line_list = new ArrayList();
			      Entity[] entity_position = new Entity[entity_boundary_list.size()-1];  

			      for (int i =0;i < entity_boundary_list.size()-1; i++){
				 
				  entity_position[i] = new Entity();
				 // entity_position[i].set_entity(entity_boundary_list.toString());
				  entity_position[i].set_entity_id(i);
				  
				  i++;
			      }
			    
			 //System.out.println("Number markers = "+ (entity_position.length));   
			      for (int i =0 ;i < entity_boundary_list.size()-1; i++){
		          }
			      
			      wordIterator.setText(str3);
			      int start = wordIterator.first();
			      int end = wordIterator.next();

			      int t = 0;
			       
			     
			      List entity_actual = new ArrayList();
			    //  entity_actual.add("<?xml version=\"1.0\"?><DOCUMENT>");
			      entity_actual.add("<?xml-stylesheet href=\"#s1\" type=\"text/css\"?>");
			      entity_actual.add("    <DOCUMENT>");
			      entity_actual.add("	 <s id=\"s1\">");
			      entity_actual.add("	        entity_word { display: none }");
			      entity_actual.add("	        word_count { display: block }");
			      entity_actual.add("	 </s>");
			      
			      while (end != BreakIterator.DONE) {
			         String word = str3.substring(start,end);
			         if (Character.isLetterOrDigit(word.charAt(0))) {
			            entity_actual.add("<entity><entity_word>"+word+"</entity_word><word_count>"+(t+1)+"</word_count></entity>");
			            Input_Target.add(word);
			           t++;
			         }
			        
			         start = end;
			         end = wordIterator.next();
			         
			      }
			      entity_actual.add("</DOCUMENT>");     
			          
			      
			      try {
			      PrintStream original = System.out;
				  System.setOut(new PrintStream(new FileOutputStream(bnerp.CONSTANT_REPORT_DIRECTORY_OUTPUT+"entity_report.xml")));
			          
				     for (int i =0;i < entity_actual.size(); i++){
					  
					  System.out.println((String) entity_actual.get(i));
					  
				      }
				   System.setOut(original);
				    } catch (Exception e) {
				         e.printStackTrace();
				    }
			    
			      if (entity_actual.size() > 0){
			      Entity[] entity = new Entity[entity_actual.size()-1];
			     
			      for (int i =0;i < entity_actual.size()-1; i++){
					 
				  entity[i] = new Entity();
				  entity[i].set_entity(entity_actual.toString());
				  i++;
			      }
			     
			      }
			     // System.out.println(Input_Target+" "+entity_boundary_list);
			      
		  } catch (IOException e){
			  System.err.println("Caught IOException: " + e.getMessage());
		  }
		 // System.out.println(Input_Target);
		 return Input_Target;
	 }
	 
	 static void wordInspect() throws IOException {
	       File file = new File(bnerp.CONSTANT_TEXT_SOURCE);
		   BufferedReader br = new BufferedReader(new FileReader(file));
		      String str3 = "", str1 = "";

		      while ((str1 = br.readLine()) != null) {
		              str3 += str1;
		      }
		      
		      Locale currentLocale = new Locale ("en","US");
		      BreakIterator wordIterator = 
		         BreakIterator.getWordInstance(currentLocale);
		     
		      markBoundaries(str3, wordIterator);
		      extractWords(str3, wordIterator);
		      br.close();
		      
		   }
	 static void wordInspectZip() throws IOException {
		// Zip files - object to read from multiple files.
		  Locale currentLocale = new Locale ("en","US");
		  BreakIterator wordIterator = 
			         BreakIterator.getWordInstance(currentLocale);
	      
	      String str3 = "", str1 = "";
	      final ZipFile file = new ZipFile( bnerp.CONSTANT_ZIP_SOURCE );
	      try
	      {
	          final Enumeration<? extends ZipEntry> entries = file.entries();
	          while ( entries.hasMoreElements() )
	          {
	             final ZipEntry entry = entries.nextElement();
	             
		          str3 +=convertStreamToString( file.getInputStream( entry ) ).replaceAll("\\n","");
	          }
	        
	      }
	     
	      finally
	      {
	          file.close();
	      }
	      System.out.println(str3);
	      markBoundaries(str3, wordIterator);
	      extractWords(str3, wordIterator);
		 
		      
		   }
	 
	   static void markBoundaries_Sentence(String target, BreakIterator iterator) {

		      StringBuffer markers = new StringBuffer();
		      markers.setLength(target.length() + 1);
		      for (int k = 0; k < markers.length(); k++) {
		         markers.setCharAt(k,' ');
		      }

		      iterator.setText(target);
		      int boundary = iterator.first();
		     
		      List list = new ArrayList();
		      
		// Determine Sentence boundary      
		      while (boundary != BreakIterator.DONE) {
		         markers.setCharAt(boundary,'^');
		         boundary = iterator.next();
		         list.add(boundary);
		      }
		     
		// Object Data Model   
		      List line_list = new ArrayList();
		      Sentence[] source_container = new Sentence[list.size()-1];  
		      
		      for (int i =0;i < list.size()-1; i++){
			 
			  source_container[i] = new Sentence();
			  source_container[i].set_sentence_id(i);
			  source_container[i].set_sentence_position(list.get(i));
			  i++;
		      }
		   // XML report 
		      List sentence_structure = new ArrayList();
		      //sentence_structure.add("<?xml version=\"1.0\"?><DOCUMENT>");
		      sentence_structure.add("<?xml-stylesheet href=\"#s1\" type=\"text/css\"?>");
		      sentence_structure.add("    <DOCUMENT>");
		      sentence_structure.add("	 <s id=\"s1\">");
		      sentence_structure.add("	        sentence_id { display: block }");
		      sentence_structure.add("	        sentence_position { display: block }");
		      sentence_structure.add("	 </s>");
		      for (int i =0 ;i < list.size()-1; i++){
		            
		              sentence_structure.add("<sentence><sentence_id>"+(i+1)+"</sentence_id><sentence_position>"+list.get(i)+"</sentence_position></sentence>");
		      }
		      
		      sentence_structure.add("</DOCUMENT>"); 
		      
		      for (int i =0;i < sentence_structure.size(); i++){
			  
			  //System.out.println((String) sentence_structure.get(i));
		      }
		         
		      
		      try {
			  PrintStream original = System.out;
			  System.setOut(new PrintStream(new FileOutputStream(bnerp.CONSTANT_REPORT_DIRECTORY_OUTPUT+"sentence_report.xml")));
		          
			     for (int i =0;i < sentence_structure.size(); i++){
				  
				  System.out.println((String) sentence_structure.get(i));
			      }
			     System.setOut(original);
			    } catch (Exception e) {
			         e.printStackTrace();
			    }
		      
	   }
		      
   
	  static void sentenceInspect() throws IOException {
		//file object to read from file.
	      File file = new File(bnerp.CONSTANT_TEXT_SOURCE);
	      
	      Locale currentLocale = new Locale ("en","US");
	      BreakIterator sentenceIterator = 
	         BreakIterator.getSentenceInstance(currentLocale);
	        
	      BufferedReader br = new BufferedReader(new FileReader(file));
	      String str2 = "", str1 = "";

	      while ((str1 = br.readLine()) != null) {
	              str2 += str1;
	      }
	     
	      markBoundaries_Sentence(str2, sentenceIterator);
	      br.close();
	   }
	
	 
	  static void sentenceInspectZip() throws IOException {
		// Zip files - object to read from multiple files.
			  
		  Locale currentLocale = new Locale ("en","US");
	      BreakIterator sentenceIterator = 
	      BreakIterator.getSentenceInstance(currentLocale);
	     
	     
	      String str2 = "", str1 = "";
	      final ZipFile file = new ZipFile( bnerp.CONSTANT_ZIP_SOURCE );
	      try
	      {
	          final Enumeration<? extends ZipEntry> entries = file.entries();
	          while ( entries.hasMoreElements() )
	          {
	             final ZipEntry entry = entries.nextElement();
	             
		          str2 +=convertStreamToString( file.getInputStream( entry ) ).replaceAll("\\n","");
	              
	              //readInputStream( file.getInputStream( entry ) );
	             
	          }
	        
	      }
	     
	      finally
	      {
	          file.close();
	      }
	      
	      markBoundaries_Sentence(str2, sentenceIterator);
        
	  }
	  
	  private static String convertStreamToString(final InputStream is ) throws IOException {
		  try{
		    Scanner s = new Scanner(is,"UTF-8").useDelimiter("\\A");
		    return s.hasNext() ? s.next() : "";
		  } catch  (java.util.NoSuchElementException e) {
		        return "";
	      }
		}
	  
	  private static int readInputStream( final InputStream is ) throws IOException {
		    final byte[] buf = new byte[ 8192 ];
		    int read = 0;
		    int cntRead;
		    while ( ( cntRead = is.read( buf, 0, buf.length ) ) >=0  )
		    {
		        read += cntRead;
		    }
		    return read;
		}
	  
	public static void dictionaryCompare() throws IOException{
	   
		 List<String> nouns = properNouns();
		 List<String> Input_Target = inputTarget();

		 for(int l=0; l < nouns.size(); l++) {
			 nouns.set(l, nouns.get(l).trim().toLowerCase());
			}

		 System.out.println(nouns);
		 for(int m=0;m < Input_Target.size(); m++) {
			 Input_Target.set(m, Input_Target.get(m).trim().toLowerCase());
			}
		 
	     // XML report 
	      List matched = new ArrayList();
	      List not_matched = new ArrayList();
	      
	     // matched.add("<?xml version=\"1.0\"?><DOCUMENT><recognized>"+
	      
	      matched.add("<?xml-stylesheet href=\"#s1\" type=\"text/css\"?>");
	      matched.add("    <DOCUMENT>");
	      matched.add("	 <s id=\"s1\">");
	      matched.add("	        identified_count { display: none }");
	      matched.add("	        identified_noun { display: block }");
	      matched.add("	 </s><recognized>");
		
	      int k = 0;
	      for (int i =0; i < nouns.size(); i++){
	    	 
	    	  for (int j =0; j < Input_Target.size(); j++){
	    		 
	    	      if (nouns.get(i).equals(Input_Target.get(j))){
	    			  
	    		  matched.add("<identified_noun>"+nouns.get(i)+"</identified_noun><identified_count>"+(k+1)+"</identified_count>");
	    		  k++;
	    	      }
	    	  }
	      }
	      
	      matched.add("</recognized></DOCUMENT>"); 
	   	
	      try {
	    	  
	    	PrintStream original = System.out; 
	    	System.setOut(new PrintStream(new FileOutputStream(bnerp.CONSTANT_REPORT_DIRECTORY_OUTPUT+"recognized_report.xml")));
	          
		     for (int i =0;i < matched.size(); i++){
			  System.out.println((String) matched.get(i));
		      }
		     
		    System.setOut(original);
		    
		    } catch (Exception e) {
		         e.printStackTrace();
		    }
	    	      
	}
	
	public static void dictionaryCompareZip() throws IOException{
		   
		 List<String> nouns = properNouns();
		 List<String> Input_Target = inputTargetZip();

		 for(int l=0; l < nouns.size(); l++) {
			 nouns.set(l, nouns.get(l).trim().toLowerCase());
			}

		 System.out.println(nouns);
		 for(int m=0;m < Input_Target.size(); m++) {
			 Input_Target.set(m, Input_Target.get(m).trim().toLowerCase());
			}
		 
		 // XML report 
	      List matched = new ArrayList();
	      List not_matched = new ArrayList();
	     // matched.add("<?xml version=\"1.0\"?><DOCUMENT><recognized>");
	      matched.add("<?xml-stylesheet href=\"#s1\" type=\"text/css\"?>");
	      matched.add("    <DOCUMENT>");
	      matched.add("	 <s id=\"s1\">");
	      matched.add("	        identified_count { display: none }");
	      matched.add("	        identified_noun { display: block }");
	      matched.add("	 </s><recognized>");
		
	      int k = 0;
	      for (int i =0; i < nouns.size(); i++){
	    	 
	    	  for (int j =0; j < Input_Target.size(); j++){
	    		 
	    		  if (nouns.get(i).equals(Input_Target.get(j))){
	    			  
	    		      matched.add("<identified_noun>"+nouns.get(i)+"</identified_noun><identified_count>"+k+"</identified_count>");
	    		      k++;
	    			  
	      		  }
	    	  }
		  }
	      
	      matched.add("</recognized></DOCUMENT>"); 
	   	
	      try {
	    	  
	    	PrintStream original = System.out; 
	    	System.setOut(new PrintStream(new FileOutputStream(bnerp.CONSTANT_REPORT_DIRECTORY_OUTPUT+"recognized_report.xml")));
	          
		     for (int i =0;i < matched.size(); i++){
			  
			  System.out.println((String) matched.get(i));
		      }
		     
		    System.setOut(original);
		    
		    } catch (Exception e) {
		         e.printStackTrace();
		    }
	    	      
	}
	
	public static void storeReport(int report) throws IOException{
	    // Sentence structure derived from sentenceInspect.
	    // Entity information derived from wordInspect.
	    // Dictionary matched derived from dictionaryCompare.
	    
	    if (report == 1){
		sentenceInspect();
		wordInspect();
		//JOptionPane.showMessageDialog(null, "Sentence Structure report from file.");
		JOptionPane.showMessageDialog(null, "Words from the text Source file are being analyzed."+"\n"
			+ "Review the output below in the 'Uploaded files to Process.'"+"\n"+"Then select 'Reason >> Generate XML' to access the report.");
	    } else if (report ==2){
	    sentenceInspectZip();
	    wordInspectZip();
		//JOptionPane.showMessageDialog(null, "Sentence Structure report from Zip.");
		JOptionPane.showMessageDialog(null, "Words from the text Source Zip are being analyzed."+"\n"
			+ "Review the output below in the 'Uploaded files to Process.'"+"\n"+"Then select 'Reason >> Generate XML' to access the report."+"\n"
			+ "(This is a Multi-Threaded Process)");
	    
	    } else if (report ==3){
		
		JOptionPane.showMessageDialog(null, "Entity Information from file");
	    } else if (report ==4){
		JOptionPane.showMessageDialog(null, "Entity Information from zip");
	    
	    } else if (report ==5){
		dictionaryCompare();
		JOptionPane.showMessageDialog(null, "Words from the Text file are being compared to Dictionary matches from Dictionary file."+"\n"
			+"This will also reproduce the Entity report with the words used in comparison to the Dictionary.");
	    } else if (report ==6){
	    dictionaryCompareZip();
		//JOptionPane.showMessageDialog(null, "Dictionary matches from zip");
		JOptionPane.showMessageDialog(null, "Words from the Zip file are being compared to Dictionary matches from Dictionary file."+"\n"
				+"This will also reproduce the Entity report with the words used in comparison to the Dictionary."+"\n"
				+"(There are a lot more words, Let's use a Multi-Threaded process.)");
		   
	    }
	    
	}

	public static void main(String[] args) throws IOException {
	    
		//inputTarget();
	    	//inputTargetZip();
		//sentenceInspectZip();
	    	//sentenceInspect();
	    	//wordInspect();
		//wordInspectZip();
	    	//properNouns();	
	    	//dictionaryCompare();
	    	//dictionaryCompareZip();
	}


}

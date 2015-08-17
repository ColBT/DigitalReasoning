package com.exercise.interview;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import bner.*;
import bnerUI.bnerp;

public class Bound {


	public static void dictionaryCompareZip() throws IOException{
		   
		 List<String> nouns = BoundBreakStore.properNouns();
		 List<String> Input_Target = BoundBreakStore.inputTargetZip();

		 for(int l=0; l < nouns.size(); l++) {
			 nouns.set(l, nouns.get(l).trim().toLowerCase());
			}

		 System.out.println(nouns);
		 for(int m=0;m < Input_Target.size(); m++) {
			 Input_Target.set(m, Input_Target.get(m).trim().toLowerCase());
			}
		 
		 // XML report 
	}
    
	 static void sentenceInspectZip() throws IOException {
		// Zip files - object to read from multiple files.
			  
		  Locale currentLocale = new Locale ("en","US");
	      BreakIterator sentenceIterator =   BreakIterator.getSentenceInstance(currentLocale);
	     
	      String str2 = "", str1 = "";
	      final ZipFile file = new ZipFile( Dr.CONSTANT_ZIP_SOURCE );
	      try
	      {
	          final Enumeration<? extends ZipEntry> entries = file.entries();
	          while ( entries.hasMoreElements() )
	          {
	             final ZipEntry entry = entries.nextElement();
	             
		         // str2 +=convertStreamToString( file.getInputStream( entry ) ).replaceAll("\\n","");
	              
	              //readInputStream( file.getInputStream( entry ) );
	             
	          }
	        
	      }
	     
	      finally
	      {
	          file.close();
	      }
	      
	     // markBoundaries_Sentence(str2, sentenceIterator);
     
	  }
}



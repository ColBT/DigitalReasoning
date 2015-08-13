package bner;

public class Sentence {
    
    
    private static int sentence_id;
    private static Object sentence_position;
    private static int sentence_total;    
    
    public void set_sentence_id (int sentence_no ){
	this.sentence_id = sentence_no;
    }
    public void set_sentence_position( Object object ){
	Sentence.sentence_position = object;
    }
    public void set_sentence_total( int sentence_sum ){
	Sentence.sentence_total = sentence_sum;
    }
    
    public int get_sentence_id( ){
	return sentence_id;
    }
    public Object get_sentence_position( ){
	return sentence_position;
    }
    public int get_sentence_total( ){
	return sentence_total;
    }
    
    /* Print the Sentence details */
    public void printSentence(){
	    	 
	 System.out.println("Sentence No :" + sentence_id );
	 System.out.println("Sentence Position :" + sentence_position ); 
	 System.out.println("Sentence Total :" + sentence_total ); 
    }
}

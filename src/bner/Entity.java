package bner;

public class Entity {
    String entity;
    int entity_id;
    int sentence_id;
    int sentence_position;
    String class_id;
    
    
    public void set_entity (String word){
    // This constructor has one parameter.
	this.entity 	= word;
    }
    public void set_entity_id( int entity_no ){
	this.entity_id = entity_no;
    }
    public void set_sentence_id( int sentence_no ){
	this.sentence_id = sentence_no;
    }
    public void set_sentence_position( int sentence_place ){
	this.sentence_position = sentence_place;
    }
    
    public void set_class_id( String class_name){
	this.class_id = class_name;
    }

    public String get_entity( ){
        
        return entity;
    }
    
    public int get_entity_id( ){
        System.out.println("Word count :" + entity_id ); 
        return entity_id;
    }
    public int get_sentence_id( ){
	return sentence_id;
    }
    public int get_sentence_position( ){
	return sentence_position;
    }
    
    public String get_class_id( ){
	return class_id;
    }
    
    /* Print the Entity details */
    public void printEntity(){
	    	 
	 System.out.println("Entity :" + entity ); 
	 System.out.println("Entity No :" + entity_id ); 
	 System.out.println("Sentence No :" + sentence_id );
	 System.out.println("Sentence Position :" + sentence_position ); 

	 //System.out.println("Class Type :" + class_id ); 
    }
}

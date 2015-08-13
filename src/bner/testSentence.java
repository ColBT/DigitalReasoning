package bner;

import java.io.*;

public class testSentence{

   public static void main(String args[]){
      /* Create two objects using constructor */
      Sentence sentOne = new Sentence();

      // Invoking methods for each object created
      sentOne.set_sentence_position(263);
      sentOne.set_sentence_total(5);
      sentOne.printSentence();
     
    }

}
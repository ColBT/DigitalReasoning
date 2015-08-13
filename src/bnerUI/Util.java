package bnerUI;

public class Util {
    
    public static boolean StringIsEmpty(String str){
	return str == null || str.length()==0;
    }
    
    public static String NormalizeString(String str){
	return str != null ? str : "";
    }
    
    
}

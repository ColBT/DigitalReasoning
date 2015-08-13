package poolThread;

public class matchDictionary extends Thread {
	
	private String library;
	private String Input_Target;
	
	public matchDictionary (String library,String Input_Target){
		this.library = library;
		this.Input_Target = Input_Target;
	}
	
	public void Run(){
		
		do{
			library=Input_Target;
		} while (library!=Input_Target);
		
		System.out.println("Matched" + Input_Target);
	}
}

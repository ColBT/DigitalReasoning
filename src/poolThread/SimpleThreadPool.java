package poolThread;
import bner.BoundBreakStore;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {

    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        
	
	 
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            Runnable matcher = new matchDictionary("test","test");
            executor.execute(worker);
          }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

}
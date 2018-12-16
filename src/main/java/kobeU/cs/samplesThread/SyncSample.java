package kobeU.cs.samplesThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SyncSample {

    static class IntBox {
        int val = -1;
    }
    public static int fib(int n) {
        if(n<2) return 1;
        int r1 = fib(n-1);
        int r2 = fib(n-2);
        return r1+r2;
    }
    public static int Job(String tag, int n) {
        System.out.println(tag +"@"+Thread.currentThread());
        return fib(n);
    }
    public static int J1() { return Job("J1", 10); }
    public static int J2() { return Job("J2", 20); }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        System.out.println("----- Executor ------");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                J2();
            }
        });
        executor.execute(()->{ // lambda expression の場合
            J2();
        });
        J1();
        Thread.sleep(1000); // 待ちます
        
        System.out.println("----- Wait/Notify ------");
        final IntBox box = new IntBox();
        executor.execute(()->{
            int val = J2();
            System.out.println("Call notify..");
            synchronized (box) {
                box.val = val;
                box.notifyAll();
            }
        }); 
        J1();
        System.out.println("waiting result...");
        int val;
        while(true) {
            synchronized (box) {
                val = box.val;
                if(val >= 0) break; // while の break
                box.wait();
            }
        }
        System.out.println("Val: " + val);
        
        System.out.println("----- Future ------");
        Future<Integer> future = executor.submit(()-> { // Callable<Integer>#call()
            return J2();
        });
        J1();
        System.out.println("Waiting result...");
        int val2 = future.get();
        System.out.println("Val: " + val2);
    }

}

package concurrency;

public class TestThread2 extends Thread {

    @Override
    public void run()
     {
        int i = 0 ;
        while( i < 50)
        {
        System.out.println("Hello from the Other Thread");
        i++;
        }
    }

    public static void main(String[] args) {


        (new TestThread2()).start();

        int i = 0 ;
        while( i < 50)
        {
        System.out.println("Hello from the Main Thread");
        i++;
        }
        
    }
    
}

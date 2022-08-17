package concurrency;

import concurrency.TestData;

public class TestThread  implements Runnable{

    private String name;
    private int sleepTime;
    private Thread partnerThread = null;
    private TestData commonData;

    TestThread(String name, int sleepTime, Thread partnerThread, TestData commonData)
    {
        this.name = name;
        this.sleepTime = sleepTime;        
        this.partnerThread = partnerThread;
        this.commonData = commonData;
    }

    
    public static void main(String[] args) throws InterruptedException  
    {

        TestData testData = new TestData();

        System.out.println(testData);

        TestThread testThread_2 = new TestThread("SECOND THREAD", 3000,null,testData);
        Thread myThread_2= new Thread(testThread_2);
        myThread_2.start();

        TestThread testThread_1 = new TestThread("FIRST THREAD", 2000,myThread_2,testData);    
        Thread myThread_1= (new Thread(testThread_1));    
        myThread_1.start();
        

        myThread_1.interrupt(); // Set the interrupt flag to true for myThread_1   
        myThread_2.interrupt();     

    
    }

@Override
public void run()  {
    int i = 0 ;
    while( i < 15)
        {
           

            for(int j = 0; j<5 ; j++)
            {
                this.commonData.setElementByIndex(j, this.commonData.getElementByIndex(j)+1);
            }

            System.out.println("Hello from the "+this.name);
            System.out.println(this.commonData);
            System.out.println("check statement");

            i++;

            try
            {
                Thread.sleep(this.sleepTime);
            }
            catch(InterruptedException e)
            {
                System.out.println("Thread was Interrupted!!!");
                
            }
        }

        if(this.partnerThread != null)
        {
            try {
                this.partnerThread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    System.out.println(this.name+" has ENDED!!!");
}

}
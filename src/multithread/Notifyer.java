package multithread;

/**
 * Class that throws message every 5 seconds
 */
public class Notifyer extends Thread{

    SecondsCounter counter;
    int timer;

    public Notifyer(SecondsCounter counter){
        this.counter = counter;
    }

    @Override
    public void run(){

        while (counter.isAlive()){
            try{
                timer = counter.getTime();
                if (timer != 0 && timer % 5 == 0){
                    System.out.println("5 seconds passed");
                    sleep(2000);
                }

            }catch(IllegalStateException | InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println("Process finished in: " + counter.getTime() + " sec.");
    }
}

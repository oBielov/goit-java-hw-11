package multithread;

/**
 * Thread that prints timer every second if thread is running
 */
public class SecondsCounter extends Thread{

    private int time;
    private final int finish;

    /**
     * @param finish limit for timer
     */
    public SecondsCounter(int finish){
        this.finish = finish;
    }

    public synchronized int getTime(){
        return time;
    }

    /**
     * Until the timer reached limit passed as constructor argument
     * thread prints every second value of pseudo-timer stored in {@link #time} variable
     */
    @Override
    public void run(){
        while(currentThread().isAlive()){
            try{
                sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            synchronized (this){
                time++;
            }
            System.out.println(currentThread().getName() + " time: " + time);

            if (time == finish){
                currentThread().interrupt();
            }

        }
    }
}

package multithread;

import java.util.function.IntConsumer;

/**
 * Class takes int number as a limiter and in range of tis limit
 * prints: "fizz" if number divisible by 3, "buzz" if number divisible by 5,
 * "fizzBuzz" if number divisible by both 3 and 5, number itself if all other
 * conditions are false. Class is multithreaded in four threads, one for every condition.
 */
public class FizzBuzz {

    private int number = 1;
    private final int limit;

    /**
     * Constructor takes int as argument to set a last number in loop
     */
    public FizzBuzz(int limit){
        this.limit = limit;
    }

    /**
     * Method to check if number is divisible by 3. Takes Runnable object as
     * argument to print result after thread started and pass condition.
     */
    public synchronized void fizz(Runnable print){
        while(number <= limit){
            if (number % 3 == 0 && number % 5 != 0){
                print.run();
                number++;
                notifyAll();
            }
            else{
                threadWait();
            }
        }
    }

    /**
     * Method to check if number is divisible by 5. Takes Runnable object as
     * argument to print result after thread started and pass condition.
     */
    public synchronized void buzz(Runnable print){
        while (number <= limit){
            if (number % 5 == 0 && number % 3 != 0){
                print.run();
                number++;
                notifyAll();
            }
            else{
                threadWait();
            }
        }
    }

    /**
     * Method to check if number is divisible by 3 and 5. Takes Runnable object as
     * argument to print result after thread started and pass condition.
     */
    public synchronized void fizzBuzz(Runnable print){
        while (number <= limit){
            if (number % 15 == 0){
                print.run();
                number++;
                notifyAll();
            }
            else{
                threadWait();
            }
        }
    }

    /**
     * Method to check if number is divisible by neither 3 nor 5. Takes IntConsumer object as
     * argument to print result after thread started and pass condition.
     */
    public synchronized void addNumber(IntConsumer print){
        while (number <= limit){
            if (number % 3 != 0 && number % 5 != 0){
                print.accept(number);
                number++;
                notifyAll();
            }
            else{
                threadWait();
            }
        }
    }

    /**
     * Service method to take out exceptions
     */
    private void threadWait(){
        try{
            wait();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //main

    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new FizzBuzz(100);

        Runnable printFizz = ()->System.out.println("Fizz");
        Runnable printBuzz = ()->System.out.println("Buzz");
        Runnable printFizzBuzz = ()->System.out.println("FizzBuzz");
        IntConsumer printNumber = System.out::println;

        new Thread(() -> fizzBuzz.fizz(printFizz)).start();
        new Thread(() -> fizzBuzz.buzz(printBuzz)).start();
        new Thread(() -> fizzBuzz.fizzBuzz(printFizzBuzz)).start();
        new Thread(()-> fizzBuzz.addNumber(printNumber)).start();


    }

}

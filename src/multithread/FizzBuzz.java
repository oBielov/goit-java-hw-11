package multithread;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Class takes int number as a limiter and in range of tis limit
 * prints: "fizz" if number divisible by 3, "buzz" if number divisible by 5,
 * "fizzBuzz" if number divisible by both 3 and 5, number itself if all other
 * conditions are false. Class is multithreaded in four threads, one for every condition.
 */
public class FizzBuzz {

    private int number = 1;
    private final int limit;
    StringJoiner join = new StringJoiner(", ");

    /**
     * Constructor takes int as argument to set a last number in loop
     */
    public FizzBuzz(int limit){
        this.limit = limit;
    }

    /**
     * Method to check if number is divisible by 3.
     */
    public synchronized void fizz(){
        while(number <= limit){
            if (number % 3 == 0 && number % 5 != 0){
                join.add("Fizz");
                number++;
                notifyAll();
            }
            else{
                threadWait();
            }
        }
        System.out.println(join.toString());
    }

    /**
     * Method to check if number is divisible by 5.
     */
    public synchronized void buzz(){
        while (number <= limit){
            if (number % 5 == 0 && number % 3 != 0){
                join.add("Buzz");
                number++;
                notifyAll();
            }
            else{
                threadWait();
            }
        }
    }

    /**
     * Method to check if number is divisible by 3 and 5.
     */
    public synchronized void fizzBuzz(){
        while (number <= limit){
            if (number % 15 == 0){
                join.add("FizzBuzz");
                number++;
                notifyAll();
            }
            else{
                threadWait();
            }
        }
    }

    /**
     * Method to check if number is divisible by neither 3 nor 5.
     */
    public synchronized void addNumber(){
        while (number <= limit){
            if (number % 3 != 0 && number % 5 != 0){
                join.add(String.valueOf(number));
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

    public void runAll(Thread a, Thread b, Thread c, Thread d){
        for(Thread thread : Arrays.asList(a, b, c, d)){
            thread.start();
        }
    }

    //main

    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new FizzBuzz(100);

        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzBuzz);
        Thread threadD = new Thread(fizzBuzz::addNumber);

        fizzBuzz.runAll(threadA, threadB, threadC, threadD);

    }

}

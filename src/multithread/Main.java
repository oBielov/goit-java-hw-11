package multithread;

public class Main {

    public static void main(String[] args) {
        SecondsCounter counter = new SecondsCounter(20);
        Notifyer notifyer = new Notifyer(counter);

        counter.start();
        notifyer.start();

    }

}

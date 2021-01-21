package hilos;

public class Hilos {

    public static void main(String args[]){
        /*
        HelloThread h0 = new HelloThread();

        Thread h1 = new Thread(new HelloRunnable());
        System.out.println("Hilo principal "+Thread.currentThread().getName());
        h0.start();
        System.out.println("Instruccion cualquiera");
        // PAUSA  en el hilo principal*/
        /*
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*//*
        try {
            h0.join(1000); // Sincronizacion via barrera
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Continua el main");
        h1.start();
        */

        Counter c = new Counter(0);
        SynchronizedThread h0 = new SynchronizedThread(c);
        SynchronizedThread h1 = new SynchronizedThread(c);
        h0.start();
        h1.start();
    }

}

package hilos;

public class Counter {
    private int count = 0;

    public Counter(int count){
        this.count = count;
    }

    public synchronized void increaseAndPrint(){
        for(int i=0;i<10;i++){
            count++;
            System.out.print(count + " ");
        }
    }
}

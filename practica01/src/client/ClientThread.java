package client;

import java.util.Random;

public class ClientThread extends Thread {

    public void ClientThread() {

    }
    public void run(){
        int nSolicitudes = 100000;
        TCPClient cliente = new TCPClient();
        Random random = new Random();
        int id = random.nextInt(5);
        long startTime= System.currentTimeMillis();
        for(int i=0;i<=10000;i++){
            if(i == 10000){
                cliente.nuevoCliente(-1);
            }else {
                cliente.nuevoCliente(id);
            }
       }
        long spentTime= System.currentTimeMillis() -startTime;
        System.out.println("El tiempo de ejecucion fue: "+spentTime);
    }
}

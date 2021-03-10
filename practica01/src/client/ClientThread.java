package client;

import java.util.Random;

public class ClientThread extends Thread {
    private int nCliente;

    public ClientThread(int nCliente) {
        this.nCliente = nCliente;
    }

    @Override
    public void run(){
        int nSolicitudes = 1000;
        TCPClient cliente = new TCPClient();
        Random random = new Random();
        int id = random.nextInt(5);
        try {
            cliente.nuevoCliente(id,nCliente,nSolicitudes);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}

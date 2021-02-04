package client;

public class Launcher {

    public static void main(String args[])  {
        int nClientes = 1;
        for (int i = 0; i <nClientes;i++){
            ClientThread clientThread = new ClientThread();
            clientThread.start();
        }

    }
}

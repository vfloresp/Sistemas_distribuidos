package client;

public class Launcher {

    public static void main(String args[])  {
        int nClientes = 200;
        for (int i = 0; i <nClientes;i++){
            ClientThread clientThread = new ClientThread(i+1);
            clientThread.start();
        }

    }
}

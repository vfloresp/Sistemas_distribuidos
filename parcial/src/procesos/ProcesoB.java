package procesos;

import interfaces.Message;
import java.net.*;
import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProcesoB {

    public static void main(String[] args){
        try {
            System.setProperty("java.security.policy","/home/vfloresp/Documents/ITAM/Sistemas_distribuidos/parcial/src/procesos/politicas.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while(true){
                System.out.println("Waiting for messages...");
                Socket processB = listenSocket.accept();
                Connection c = new Connection(processB);
                c.start();
            }
        } catch (IOException e){
            System.out.println("Listen: "+e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    String name = "Message";

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {                             // an echo server
            String data = in.readUTF();
            System.out.println("Mensaje recivido A: " + data);
            Registry registry = null;
            try{
                registry = LocateRegistry.getRegistry("localhost");
                Message msg = (Message) registry.lookup(name);
                System.out.println("Mensaje enviado C: " + data);
                String res = msg.modMessage(data);
                System.out.println("Mensaje recibido C: " +res);
                System.out.println("Mensaje enviado A: "+res);
                out.writeUTF(res);
            }catch (NotBoundException e) {
                e.printStackTrace();
            }
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

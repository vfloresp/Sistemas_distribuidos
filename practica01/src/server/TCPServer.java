package server;

import java.net.*;
import java.io.*;


public class TCPServer {

    public static void main(String args[]) {
        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                System.out.println("Waiting for messages...");
                Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
                Connection c = new Connection(clientSocket);
                c.start();
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }

}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

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
        AddressBook agenda = new AddressBook();
        try {
            int idUsuario = in.readInt();
            while (idUsuario > 0) {
                System.out.println("Message received from: " + idUsuario);
                String Usuario = agenda.getRecord(idUsuario).getName();
                out.writeUTF(Usuario);
                idUsuario = in.readInt();
            }


        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Se cerro la conexion");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

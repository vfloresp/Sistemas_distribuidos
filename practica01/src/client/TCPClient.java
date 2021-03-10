package client;

import java.net.*;
import java.io.*;

public class TCPClient {

    public void nuevoCliente(int id,int nCliente, int nSolicitudes){
        Socket s = null;
        try {
            long startTotalTime= System.currentTimeMillis();
            long times[] = new long[nSolicitudes];
            int n = 0;
            int serverPort = 7896;

            s = new Socket("localhost", serverPort);
            //   s = new Socket("127.0.0.1", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            while(n<nSolicitudes){
                long startTime= System.currentTimeMillis();
                out.writeInt(id);
                String record = in.readUTF();
                long spentTime= System.currentTimeMillis() -startTime;
                times[n] = spentTime;
                n++;
                //System.out.println("El Cliente "+nCliente+" tardo " + spentTime + " en la solicitud "+n);
            }
            out.writeInt(-1);
            long finishTotalTime = System.currentTimeMillis() - startTotalTime;
            System.out.println("El tiempo total para el cliente "+nCliente+" fue "+finishTotalTime);


            //out.writeInt(id);
            //String data = in.readUTF();
            //System.out.println("Received: " + data);


        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
        }
    }

    public static void main(String args[]) {

        Socket s = null;
        try {
            int serverPort = 7896;

            s = new Socket("localhost", serverPort);
            //   s = new Socket("127.0.0.1", serverPort);
            //DataInputStream in = new DataInputStream(s.getInputStream());
            //DataOutputStream out = new DataOutputStream(s.getOutputStream());


            //out.writeInt(2);
            //String data = in.readUTF();
            //System.out.println("Received: " + data);


        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
        }
    }
}

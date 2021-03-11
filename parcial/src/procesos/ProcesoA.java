package procesos;

import java.net.*;
import java.io.*;

public class ProcesoA {

    public static void main(String[] args){
        Socket s = null;
        try {
            int serverPort = 7896;
            s = new Socket("localhost",serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String msg = "Hola mundo";
            System.out.println("Mensaje enviado: "+msg);
            out.writeUTF(msg);
            String response = in.readUTF();
            System.out.println("Mensaje recibido: " + response);

        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(s != null)
                try{
                    s.close();
                } catch (IOException e){
                    System.out.println("close: " +e.getMessage());
                }
        }
    }

}

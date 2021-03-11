package procesos;

import interfaces.Message;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ProcesoC implements Message{

    public static void main(String[] args){
        System.setProperty("java.security.policy", "/home/vfloresp/Documents/ITAM/Sistemas_distribuidos/parcial/src/procesos/politicas.policy");
        if(System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        try {
            LocateRegistry.createRegistry(1099);
            String name = "Message";
            ProcesoC engine = new ProcesoC();
            Message stub = (Message) UnicastRemoteObject.exportObject(engine,0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name,stub);
            System.out.println("Servicio desplegado!");

        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @Override
    public String modMessage(String message) throws RemoteException {
        System.out.println("Mensaje recibido: " + message);
        String newMessage = message + " nuevo";
        System.out.println("Mensaje enviado: " + newMessage);
        return newMessage;
    }
}

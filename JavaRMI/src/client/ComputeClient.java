package client;

import interfaces.Compute;
import interfaces.Credential;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ComputeClient {

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "/home/vfloresp/Documents/ITAM/Sistemas_distribuidos/JavaRMI/src/client/client.policy");

        String name = "Compute";

        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry("localhost");
            Compute comp = (Compute) registry.lookup(name);
            Credential aCredential = new Credential(3,"chuchito");
            System.out.println(comp.power(3,3,aCredential));
            System.out.println(comp.square(9,aCredential));

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }


        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }

}

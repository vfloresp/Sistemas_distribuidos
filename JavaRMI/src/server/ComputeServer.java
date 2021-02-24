package server;

import interfaces.Compute;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComputeServer implements Compute {

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "~/Documents/ITAM/primavera_2021/sist_distribuidos/Sistemas_distribuidos/JavaRMI/src/server/server.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }

    @Override
    public double square(int number) throws RemoteException {
        /*CUALQUIER COSA COMPLEJA*/
        return number * number;
    }

    @Override
    public double power(int num1, int num2) throws RemoteException {
        return java.lang.Math.pow(num1, num2);
    }
}

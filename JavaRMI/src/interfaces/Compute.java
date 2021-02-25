package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {

    // Calculate the square of a number
    public double square(int number, Credential aCredential) throws RemoteException;

    // Calculate the power of a number
    public double power(int num1, int num2, Credential aCredential) throws RemoteException;

}

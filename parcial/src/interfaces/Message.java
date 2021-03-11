package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Message extends Remote{

    public String modMessage(String message) throws RemoteException;

}

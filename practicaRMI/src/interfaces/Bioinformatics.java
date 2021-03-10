package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bioinformatics extends Remote{
    public Task executeBioinformaticsTask(Task aTask) throws RemoteException;
}

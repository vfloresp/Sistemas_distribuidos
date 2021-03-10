package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataMining extends Remote{
    public Task executeDataMiningTask(Task aTask) throws RemoteException;
}

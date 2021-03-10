package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class starter {
    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        SlaveNode[] slaveNodes = {new SlaveNode(),new SlaveNode(), new SlaveNode() };
        String[] services = new String[3];
        slaveNodes[0].start("Bioinformatics");
        slaveNodes[1].start("DataMining");
        slaveNodes[2].start("ImageProcessing");
    }
}

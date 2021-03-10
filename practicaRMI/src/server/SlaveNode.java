package server;

import interfaces.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SlaveNode implements Bioinformatics,DataMining, ImageProcessing{

    public SlaveNode() throws RemoteException{
        super();
    }

    public void start(String service){
        System.setProperty("java.security.policy","/home/vfloresp/Documents/ITAM/Sistemas_distribuidos/practicaRMI/src/server/server.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try{
            SlaveNode engine = new SlaveNode();
            switch (service) {
                case "Bioinformatics" -> {
                    Bioinformatics stub = (Bioinformatics) UnicastRemoteObject.exportObject(engine, 0);
                    Registry registry = LocateRegistry.getRegistry();
                    registry.rebind(service, stub);
                    System.out.println("Bioinformatics Engine bound");
                }
                case "DataMining" -> {
                    DataMining stub = (DataMining) UnicastRemoteObject.exportObject(engine, 0);
                    Registry registry = LocateRegistry.getRegistry();
                    registry.rebind(service, stub);
                    System.out.println("Data Mining Engine bound");
                }
                case "ImageProcessing" -> {
                    ImageProcessing stub = (ImageProcessing) UnicastRemoteObject.exportObject(engine, 0);
                    Registry registry = LocateRegistry.getRegistry();
                    registry.rebind(service, stub);
                    System.out.println("Image Processing Engine bound");
                }
            }
        }catch(Exception e){
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }

    @Override
    public Task executeBioinformaticsTask(Task aTask) throws RemoteException {
        try {
            Thread.sleep(aTask.getLength());
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        aTask.setOutput("BioinformaticsOutput");
        return aTask;
    }

    @Override
    public Task executeDataMiningTask(Task aTask) throws RemoteException {
        try {
            Thread.sleep(aTask.getLength());
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        aTask.setOutput("DataMiningOutput");
        return aTask;
    }

    @Override
    public Task executeImageProcessingTask(Task aTask) throws RemoteException {
        try {
            Thread.sleep(aTask.getLength());
        } catch (InterruptedException ex) {
            Logger.getLogger(SlaveNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        aTask.setOutput("ImageProcessingOutput");
        return aTask;
    }
}

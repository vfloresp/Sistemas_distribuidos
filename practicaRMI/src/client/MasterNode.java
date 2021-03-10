package client;

import interfaces.Bioinformatics;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import interfaces.DataMining;
import interfaces.ImageProcessing;
import interfaces.Task;

public class MasterNode {

    public static void main(String[] args){
        System.setProperty("java.security.policy","/home/vfloresp/Documents/ITAM/Sistemas_distribuidos/practicaRMI/src/client/client.policy");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            Task[] ImageProcessing = {
                    new Task("T1", "ImageProcessing", (long)5000),
                    new Task("T2", "ImageProcessing", (long)10000),
                    new Task("T3", "ImageProcessing", (long)15000),
                    new Task("T4", "ImageProcessing", (long)20000),
                    new Task("T5", "ImageProcessing", (long)30000),
                    new Task("T6", "ImageProcessing", (long)5000),
                    new Task("T7", "ImageProcessing", (long)10000),
                    new Task("T8", "ImageProcessing", (long)15000),
                    new Task("T9", "ImageProcessing", (long)20000),
                    new Task("T10", "ImageProcessing", (long)30000),
            };

            Task[] DataMining = {
                    new Task("T11", "DataMining", (long)5000),
                    new Task("T12", "DataMining", (long)10000),
                    new Task("T13", "DataMining", (long)15000),
                    new Task("T14", "DataMining", (long)20000),
                    new Task("T15", "DataMining", (long)30000),
                    new Task("T16", "DataMining", (long)5000),
                    new Task("T17", "DataMining", (long)10000),
                    new Task("T18", "DataMining", (long)15000),
                    new Task("T19", "DataMining", (long)20000),
                    new Task("T20", "DataMining", (long)30000),
                    new Task("T21", "DataMining", (long)5000),
                    new Task("T22", "DataMining", (long)10000),
                    new Task("T23", "DataMining", (long)15000),
                    new Task("T24", "DataMining", (long)20000),
                    new Task("T25", "DataMining", (long)30000),
                    new Task("T26", "DataMining", (long)5000),
                    new Task("T27", "DataMining", (long)10000),
                    new Task("T28", "DataMining", (long)15000),
                    new Task("T29", "DataMining", (long)20000),
                    new Task("T30", "DataMining", (long)30000),
            };

            Task[] Bioinformatics = {
                    new Task("T31", "Bioinformatics", (long)5000),
                    new Task("T32", "Bioinformatics", (long)10000),
                    new Task("T33", "Bioinformatics", (long)15000),
                    new Task("T34", "Bioinformatics", (long)20000),
                    new Task("T35", "Bioinformatics", (long)30000),
                    new Task("T36", "Bioinformatics", (long)5000),
                    new Task("T37", "Bioinformatics", (long)10000),
                    new Task("T38", "Bioinformatics", (long)15000),
                    new Task("T39", "Bioinformatics", (long)20000),
                    new Task("T40", "Bioinformatics", (long)30000),
                    new Task("T41", "Bioinformatics", (long)5000),
                    new Task("T42", "Bioinformatics", (long)10000),
                    new Task("T43", "Bioinformatics", (long)15000),
                    new Task("T44", "Bioinformatics", (long)20000),
                    new Task("T45", "Bioinformatics", (long)30000)
            };

            System.out.println("Sending for ImageProcessing BoT for execution");
            BoTExecution ex1 = new BoTExecution(ImageProcessing, registry);
            ex1.start();

            System.out.println("Sending for DataMining BoT for execution");
            BoTExecution ex2 = new BoTExecution(DataMining, registry);
            ex2.start();

            System.out.println("Sending for Bioinformatics BoT for execution");
            BoTExecution ex3 = new BoTExecution(Bioinformatics, registry);
            ex3.start();
        }catch(Exception e){
            System.err.println("exception");
            e.printStackTrace();
        }

    }

    private static class BoTExecution extends Thread{
        Task[] BoT;
        Registry registry;
        public BoTExecution(Task[] aBoT, Registry aRegistry) {
            BoT = aBoT;
            registry=aRegistry;
        }

        public void  run(){
            try {
                Task taskType = BoT[0];
                DataMining dm = null;
                ImageProcessing ip = null;
                Bioinformatics bi = null;

                switch (taskType.getRequirementId()) {
                    case "DataMining" -> dm = (DataMining) registry.lookup(taskType.getRequirementId());
                    case "ImageProcessing" -> ip = (ImageProcessing) registry.lookup(taskType.getRequirementId());
                    case "Bioinformatics" -> bi = (Bioinformatics) registry.lookup(taskType.getRequirementId());
                }

                for (int i=0;i<BoT.length;i++){
                    Task task = BoT[i];
                    System.out.println("Sending for execution task "+ task.getTaskId());
                    switch (task.getRequirementId()) {
                        case "DataMining" -> {
                            task = dm.executeDataMiningTask(task);
                            System.out.println("Data Mining task " + task.getTaskId() + " was executed with output " + task.getOutput());
                        }
                        case "ImageProcessing" -> {
                            task = ip.executeImageProcessingTask(task);
                            System.out.println("Image Processing task " + task.getTaskId() + " was executed with output " + task.getOutput());
                        }
                        case "Bioinformatics" -> {
                            task = bi.executeBioinformaticsTask(task);
                            System.out.println("Bioinformatics task " + task.getTaskId() + " was executed with output " + task.getOutput());
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}

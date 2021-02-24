package client;

public class ComputeClient {

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "~/Documents/ITAM/primavera_2021/sist_distribuidos/Sistemas_distribuidos/JavaRMI/src/client/client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }

}

package jugadores;

import javax.jms.*;

import insumo.Papa;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JugadorB {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "PAPA_B";
    private static String subjectOponent = "PAPA_A";
    private boolean perdedor = false;
    private int timer;

    public void produceMessages() {
        MessageProducer messageProducer;
        ObjectMessage objMessage;

        try{
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            connectionFactory.setTrustAllPackages(true);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);

            messageProducer = session.createProducer(destination);
            objMessage = session.createObjectMessage();

            Papa papa = new Papa("Papa B");

            objMessage.setObject(papa);
            messageProducer.send(objMessage);

            Destination destOponent = session.createQueue(subjectOponent);
            MessageConsumer messageConsumer = session.createConsumer(destOponent);

            while(!perdedor){
                System.out.println("Esperando papa...");
                objMessage = (ObjectMessage) messageConsumer.receive();
                papa = (Papa) objMessage.getObject();
                System.out.println("Se recibio la papa " + papa.getId());
                timer = papa.decrementar();
                if(timer == 0){
                    perdedor = true;
                    System.out.println("Perdiste :(");
                }else{
                    objMessage = session.createObjectMessage();
                    objMessage.setObject(papa);
                    messageProducer.send(objMessage);
                }
            }
            messageProducer.close();
            messageConsumer.close();
            session.close();
            connection.close();
        }catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new JugadorB().produceMessages();
    }
}

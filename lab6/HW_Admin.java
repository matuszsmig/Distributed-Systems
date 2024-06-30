import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HW_Admin {
    private static final String EXCHANGE_ADMIN_DOCTOR = "adminExchangeDoctor";
    private static final String EXCHANGE_ADMIN_TECHNICIAN = "adminExchangeTechnician";
    private static final String ADMIN_QUEUE = "adminQueue";
    private static Channel channel = null;

    public static void main(String[] argv) throws Exception {
        System.out.println("Admin (if you want to say something just type): ");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_ADMIN_DOCTOR, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(EXCHANGE_ADMIN_TECHNICIAN, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(ADMIN_QUEUE, true, false, false, null);
        channel.queueBind(ADMIN_QUEUE, EXCHANGE_ADMIN_DOCTOR, "doctor");
        channel.queueBind(ADMIN_QUEUE, EXCHANGE_ADMIN_TECHNICIAN, "technician");

        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId("admin")
                .build();

        while (true) {
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Admin received: " + message);
                }
            };

            channel.basicConsume(ADMIN_QUEUE, true, consumer);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String adminMessage = br.readLine();

            channel.basicPublish(EXCHANGE_ADMIN_DOCTOR, "doctorReply", props, adminMessage.getBytes("UTF-8"));
            channel.basicPublish(EXCHANGE_ADMIN_TECHNICIAN, "technicianReply", props, adminMessage.getBytes("UTF-8"));
        }
    }
}

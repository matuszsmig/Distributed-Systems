import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.UUID;

public class HW_Doctor {
    private static final String EXCHANGE_NAME = "testExchange";
    private static final String EXCHANGE_ADMIN = "adminExchangeDoctor";
    private static final String REPLY_QUEUE_NAME = "doctorReplyQueue";
    private static Channel channel = null;

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(EXCHANGE_ADMIN, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(REPLY_QUEUE_NAME, true, false, false, null);
        String correlationId = UUID.randomUUID().toString();

        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .replyTo(REPLY_QUEUE_NAME)
                .correlationId(correlationId)
                .build();

        channel.queueBind(REPLY_QUEUE_NAME, EXCHANGE_ADMIN, "doctorReply");

        while (true) {
            String welcomeString = """
                Choose type of injury:
                - hip
                - knee
                - elbow
                """;
            System.out.print(welcomeString);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter injury: ");
            String injury = br.readLine();
            if (injury.equals("hip") || injury.equals("knee") || injury.equals("elbow")) {
                System.out.println("Enter patient lastname: ");
                String lastname = br.readLine();
                String message = injury + " " + lastname;

                channel.basicPublish(EXCHANGE_NAME, injury, props, message.getBytes("UTF-8"));
                channel.basicPublish(EXCHANGE_ADMIN, "doctor", props, message.getBytes("UTF-8"));

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String correlationIdReceived = delivery.getProperties().getCorrelationId();
                    String response = new String(delivery.getBody(), "UTF-8");
                    if (correlationIdReceived.equals(correlationId)) {
                        System.out.println("Received response: " + response);
                    } else if (correlationIdReceived.equals("admin")) {
                        System.out.println("From admin: " + response);
                    }
                };
                channel.basicConsume(REPLY_QUEUE_NAME, true, deliverCallback, consumerTag -> {});
            }
        }
    }
}

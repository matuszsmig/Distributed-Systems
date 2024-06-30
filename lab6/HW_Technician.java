import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;

public class HW_Technician {
    private static final String EXCHANGE_NAME = "testExchange";
    private static final String EXCHANGE_ADMIN = "adminExchangeTechnician";
    private static final String ADMIN_QUEUE = "technicianAdminQueue";
    private static Channel channel = null;

    public static void main(String[] argv) throws Exception {
        if (argv.length < 2) {
            System.exit(1);
        }

        String technicianRole1 = argv[0];
        String technicianRole2 = argv[1];
        String queueName = "queue";
        System.out.println("I'm technician of " + technicianRole1 + " and " + technicianRole2);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        channel.basicQos(1);
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(EXCHANGE_ADMIN, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, EXCHANGE_NAME, technicianRole1);
        channel.queueBind(queueName, EXCHANGE_NAME, technicianRole2);

        channel.queueDeclare(ADMIN_QUEUE, true, false, false, null);
        channel.queueBind(ADMIN_QUEUE, EXCHANGE_ADMIN, "technicianReply");

        Consumer adminConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("From admin: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                String injury = message.split(" ")[0];

                if (properties.getHeaders() != null && properties.getHeaders().containsKey("isResponse")) {
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    return;
                }

                if (injury.equals(technicianRole1) || injury.equals(technicianRole2)) {
                    try {
                        channel.basicAck(envelope.getDeliveryTag(), false);
                        System.out.println("Received: " + message);
                        Thread.sleep(5000);
                        String response = message + " done";

                        String replyQueue = properties.getReplyTo();

                        AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                                .correlationId(properties.getCorrelationId())
                                .headers(Map.of("isResponse", true))
                                .build();
                        channel.basicPublish("", replyQueue, replyProps, response.getBytes("UTF-8"));

                        AMQP.BasicProperties adminProps = new AMQP.BasicProperties.Builder()
                                .headers(Map.of("isResponse", true))
                                .build();
                        channel.basicPublish(EXCHANGE_ADMIN, "technician", adminProps, response.getBytes("UTF-8"));

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    channel.basicReject(envelope.getDeliveryTag(), true);
                }
            }
        };

        channel.basicConsume(ADMIN_QUEUE, false, adminConsumer);
        channel.basicConsume(queueName, false, consumer);
    }
}

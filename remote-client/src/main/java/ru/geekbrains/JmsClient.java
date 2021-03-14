package ru.geekbrains;

import ru.geekbrains.service.ProductRepresentation;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class JmsClient {

    public static void main(String[] args) throws IOException, NamingException {
        Context context = createInitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        JMSContext jmsContext = factory.createContext("jmsUser", "HPSoft1976");
        Destination dest = (Destination) context.lookup("jms/productQueue");
        JMSProducer producer = jmsContext.createProducer();
        ObjectMessage message = jmsContext.createObjectMessage(new ProductRepresentation(null, "Product jms",
                "Product jms", new BigDecimal(100), 1L, null));
        producer.send(dest, message);
    }


    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EjbClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}

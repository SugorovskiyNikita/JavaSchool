/*package com.tsystems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;


/**
 * Created by nikita on 21.10.2020.
 */
/*@Configuration
@EnableJms
public class MessageConfig {

    private static final String TEST_QUEUE = "MyQueue";

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");

        InitialContext initialContext = new InitialContext(properties);
        ConnectionFactory connectionFactory =
                (ConnectionFactory) initialContext.lookup("jms/RemoteConnectionFactory");
        UserCredentialsConnectionFactoryAdapter connectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
        connectionFactoryAdapter.setTargetConnectionFactory(connectionFactory);
        return connectionFactoryAdapter;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(TEST_QUEUE);
        return template;
    }

    @Bean
    MessageConverter converter() {
        return new SimpleMessageConverter();
    }
}*/

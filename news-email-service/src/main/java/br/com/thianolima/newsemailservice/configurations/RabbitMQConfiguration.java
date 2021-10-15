package br.com.thianolima.newsemailservice.configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${spring.rabbitmq.exchange.noticia}")
    private String exchangeNoticia;

    @Value("${spring.rabbitmq.queue.email}")
    private String queueEmail;

    @Bean
    public Exchange exchangeNoticia() {
        return ExchangeBuilder
                .fanoutExchange(exchangeNoticia)
                .durable(true)
                .build();
    }

    @Bean
    public Queue queueEmail() {
        return QueueBuilder
                .durable(queueEmail)
                .build();
    }

    @Bean
    public Binding bindingNoticiaEmail() {
        return BindingBuilder
                .bind(queueEmail())
                .to(exchangeNoticia())
                .with("")
                .noargs();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

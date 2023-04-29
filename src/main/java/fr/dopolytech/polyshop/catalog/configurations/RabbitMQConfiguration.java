package fr.dopolytech.polyshop.catalog.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Bean
    public Queue catalogQueue() {
        return new Queue("catalogQueue", true);
    }

    @Bean
    public TopicExchange inventoryExchange() {
        return new TopicExchange("inventoryExchange");
    }

    @Bean
    public Binding orderBinding(Queue queue, TopicExchange inventoryExchange) {
        return BindingBuilder.bind(queue).to(inventoryExchange).with("inventory.update.success");
    }
}
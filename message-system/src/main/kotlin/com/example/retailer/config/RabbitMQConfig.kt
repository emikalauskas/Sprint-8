package com.example.retailer.config

import com.example.retailer.adapter.Consumer
import com.example.retailer.adapter.ConsumerImpl
import com.example.retailer.adapter.DistributorPublisher
import com.example.retailer.adapter.DistributorPublisherImpl
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
    @Bean
    fun topic(): TopicExchange {
        return TopicExchange("distributor_exchange", true, false)
    }
    @Bean
    fun autoDeleteRetailerQueue(): Queue {
        return Queue("retailer", false, false, true)
    }
    @Bean
    fun bindingRetailer(
        topic: TopicExchange,
        autoDeleteRetailerQueue: Queue
    ): Binding {
        return BindingBuilder.bind(autoDeleteRetailerQueue)
            .to(topic)
            .with("retailer.emikalauskas.#")
    }
    @Bean
    fun consumer(): Consumer {
        return ConsumerImpl()
    }
    @Bean
    fun publisher(): DistributorPublisher {
        return DistributorPublisherImpl()
    }
}
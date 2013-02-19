package wells.config

import groovy.util.logging.Log4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Bean
import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.command.ActiveMQQueue
import org.apache.activemq.pool.PooledConnectionFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.listener.DefaultMessageListenerContainer
import javax.jms.Destination
import org.apache.activemq.command.ActiveMQQueue
import org.springframework.beans.factory.annotation.Value
import wells.jms.service.JMSMessageService
import wells.jms.service.MessageService
import wells.config.PropertyPlaceholderConfig

@Log4j
@Configuration
@EnableAspectJAutoProxy
@Import(PropertyPlaceholderConfig.class)
class JMSConfig {
	
	@Value('${jms.brokerURL}')
	String brokerURL
	@Value('${jms.responseQueueName}')
	String addressResponseQueue
	@Value('${jms.errorQueueName}')
	String addressFailureQueue
	@Value('${jms.requestQueueName}')
	String addressRequestQueue
	@Value('${messageListenerContainer.concurrentConsumers}')
	int concurrentConsumers
	@Value('${messageListenerContainer.maxConcurrentConsumers}')
	int maxConcurrentConsumers
	@Value('${messageListenerContainer.autoStartup}')
	boolean autoStartup

	@Bean
	ActiveMQConnectionFactory factory() {
		def factory = new ActiveMQConnectionFactory(brokerURL)
		return factory
	}

	@Bean(destroyMethod = 'stop')
	PooledConnectionFactory jmsFactory() {
		def factory = new PooledConnectionFactory(factory())
		factory.maxConnections = 10
		return factory
	}

	@Bean
	JmsTemplate jmsTemplate() {
		def template = new JmsTemplate(jmsFactory())
		return template
	}
	
	/*@Bean
	DefaultMessageListenerContainer messageListenerContainer() {
		def bean = new DefaultMessageListenerContainer()
		bean.setConnectionFactory(jmsFactory())
		bean.setDestinationName(addressRequestQueue)
		bean.setMessageListener(messageListener())
		bean.setConcurrentConsumers(concurrentConsumers)
		bean.setMaxConcurrentConsumers(maxConcurrentConsumers)
		bean.setAutoStartup(autoStartup)
		return bean
	}*/
	
	@Bean
	Destination responseQueue(){
		ActiveMQQueue destination = new ActiveMQQueue(addressResponseQueue)
	}

	@Bean
	Destination requestQueue(){
		ActiveMQQueue destination = new ActiveMQQueue(addressRequestQueue)
	}
	
	/*@Bean
	MessageListener messageListener() {
		return new ServiceProvider<Address, AddressVerificationResponse>(
			addressFailureQueue,
			converter(),
			addressVerifier(),
			messageService())
	}*/
	
	@Bean(name = 'jmsMessageService')
	MessageService messageService() {
		MessageService messageService = new JMSMessageService(jmsTemplate())
		return messageService
	}
}

package wells.jms.service

import javax.jms.JMSException
import javax.jms.MapMessage
import javax.jms.Session
import javax.jms.Message
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator

class JMSMessageService implements MessageService {
	
	JmsTemplate jmsTemplate
	
	JMSMessageService(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate
	}
	
	void sendMessage(String queueName, String strMessage) {
		def messageCreator = { Session session ->
			 session.createTextMessage(strMessage)
		 } as MessageCreator
		 
		 jmsTemplate.send(queueName, messageCreator)
	}
	
	void recieveMessage() {
		
	}
}

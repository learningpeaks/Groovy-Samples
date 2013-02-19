package wells.jms.service

import javax.jms.JMSException
import javax.jms.MapMessage
import javax.jms.Session
import javax.jms.Message
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator

interface MessageService {
	
	void sendMessage(String queueName, String strMessage)
	void recieveMessage()
	
}

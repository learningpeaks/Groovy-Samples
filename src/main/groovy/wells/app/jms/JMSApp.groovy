package wells.app.jms

import org.springframework.context.annotation.AnnotationConfigApplicationContext

import wells.config.PropertyPlaceholderConfig

import wells.jms.service.MessageService



class JMSApp {
	static String ENV_PROPERTIES = "env.properties"
	static void main(String[] args){
		
		PropertyPlaceholderConfig.setPropertiesFileNames(ENV_PROPERTIES);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()
		context.scan("wells.config")
		context.refresh()
		try{
			MessageService messageSerivce = context.getBean("jmsMessageService")
			messageSerivce.sendMessage("First Message to ApacheActiveMQ")
		}catch(Exception e){
			e.printStackTrace()
		}
	}
}

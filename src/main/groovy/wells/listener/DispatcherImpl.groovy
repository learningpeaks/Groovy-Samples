package wells.listener

import java.io.IOException
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import wells.model.RecordProcessorTask

class DispatcherImpl extends Dispatcher implements ApplicationContextAware {

	ApplicationContext context
	
	DispatcherImpl() {
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		RecordProcessorTask task = context.getBean('recordProcessorTask')
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext
	}

}

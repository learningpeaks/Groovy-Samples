package wells.config

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class PropertyPlaceholderConfig {
	private static ClassPathResource[] classPathResources
	private static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer
	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {

		propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer()
		for(int i=0; i<classPathResources.length ; i++){
			ClassPathResource clp = classPathResources[i]
			System.out.println("classpathExits"+clp.exists())
			System.out.println("classpathFileName"+clp.getFilename())
		}
		
		propertyPlaceholderConfigurer.setLocations(classPathResources)

		return propertyPlaceholderConfigurer
	}

	public static void setPropertiesFileNames(String... names) {

		classPathResources = new ClassPathResource[names.length]

		for (int i = 0 ;i < names.length ;i++) {
			classPathResources[i] = new ClassPathResource(names[i])
		}
	}
}

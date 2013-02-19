/*
 * Copyright (c) 2012 Prime Therapeutics LLC. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Prime
 * Therapeutics LLC. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the employment agreement you entered into
 * with Prime Therapeutics LLC.
 */
package wells.config

import groovy.json.JsonSlurper
import groovy.util.logging.Log4j

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Scope
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@Log4j
@Configuration
@EnableAspectJAutoProxy
@Import(PropertyPlaceholderConfig.class)
class PrintConverterConfiguration {
	//properties
	/*@Value('${appName}')
	String appName

	@Value('${threadPool.size}')
	int initialPoolSize

	@Value('${threadPool.maxSize}')
	int maxPoolSize

	@Value('${threadPool.queueCapacity}')
	int queueCapacity

	@Value('${ghostscript.cmd}')
	String ghostScriptCommand

	@Value('${ghostscript.args}')
	String ghostScriptArgs

	@Value('${ghostscript.driversMap}')
	String driversMap

	@Value('${cacheDirectory}')
	File cacheDirectory

	@Value('${pharmacySetupFileName}')
	String pharmacySetupFileName

	@Bean
	def jsonConfig() {

		def slurper = new JsonSlurper()
		def pharmacySetupMap = slurper.parse(new File(pharmacySetupFileName).newReader())
		assert null != pharmacySetupMap, 'Something went wrong with the json pharmacy setup config'
		return pharmacySetupMap
	}

	@Bean(name = "runner")
	Runner runner() {
		Runner runner = new RunnerImpl(appName, watcherServices(), dispatcher())
		return runner
	}

	@Bean
	Dispatcher dispatcher() {
		Dispatcher disp = new DispatcherImpl()
		disp.setTaskExecutor(taskExecutor())
		return disp
	}

	@Bean(name = "taskExecutor")
	ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor()
		executor.corePoolSize = initialPoolSize
		executor.maxPoolSize = maxPoolSize
		executor.waitForTasksToCompleteOnShutdown = true
		executor.queueCapacity = queueCapacity
		log.info("task executor initialPoolsize ${initialPoolSize}")
		log.info("task executor maxPoolsize ${maxPoolSize}")
		return executor
	}

	@Bean(name = "recordProcessorTask")
	@Scope("prototype")
	RecordProcessorTask recordProcessorTaskFactory() {
		return new MOPCRecordProcessorTaskImpl(transformer(), loader(), Calendar.getInstance())
	}

	@Bean(name = "transformer")
	Transformer transformer() {
		return new PDFTransformer(conversionService())
	}

	@Bean(name = "conversionService")
	ConversionService conversionService() {

		def slurper = new JsonSlurper()
		Map drivers = slurper.parseText(driversMap)
		def pharmacySetupMap = jsonConfig()
		
		Map outputDirectories = [:]
		pharmacySetupMap.each { outputDirectories.put(DestinationPharmacy.getPharmacy(it.key), it.value.outputDirectory) }
		assert outputDirectories.size() > 0, "Output directories not properly configured in setup map"

		Map errorDirectories = [:]
		pharmacySetupMap.each { errorDirectories.put(DestinationPharmacy.getPharmacy(it.key), it.value.errorsDirectory) }
		assert errorDirectories.size() > 0, "Error directories not properly configured in setup map"
				
		return new ConversionServiceImpl(ghostScriptCommand, ghostScriptArgs, drivers, outputDirectories, cacheDirectory, errorDirectories)
	}

	@Bean(name = "loader")
	Loader loader() {
		FTPSLoaderServiceImpl ftpsLoader = new FTPSLoaderServiceImpl(ftpsClient(), archiveOrderService())
		return ftpsLoader
	}

	@Bean(name = "monitor")
	Monitor monitor() {
		return new PrintConverterMonitor()
	}

	@Bean(name = "ftpsClient")
	FTPSClientBean ftpsClient() {
		Map pharmacySetupMap = jsonConfig()
		assert pharmacySetupMap.size() > 0, 'Pharmacy Setup Map is broken'
		
		Map bufferServers = [:]
		pharmacySetupMap.each { bufferServers.put(it.key, it.value.bufferServer) }
		
		assert bufferServers.size() > 0, 'Buffer server information not properly configured in setup map'
		
		FTPSClientBean bean = new FTPSClientBean(bufferServers)
		return bean
	}
	
	@Bean(name = "jmsConnection")
	FTPSClientBean jmsConnection() {
		Map pharmacySetupMap = jsonConfig()
		assert pharmacySetupMap.size() > 0, 'Pharmacy Setup Map is broken'
		
		Map bufferServers = [:]
		pharmacySetupMap.each { bufferServers.put(it.key, it.value.bufferServer) }
		
		assert bufferServers.size() > 0, 'Buffer server information not properly configured in setup map'
		
		FTPSClientBean bean = new FTPSClientBean(bufferServers)
		return bean
	}

	@Bean(name = "archiveOrderService")
	ArchiveOrderService archiveOrderService() {
		
		def pharmacySetupMap = jsonConfig()
		assert pharmacySetupMap.size() > 0, 'Pharmacy Setup Map is broken'
		
		def archiveDirs = [:]
		def errorDirs = [:]
		
		pharmacySetupMap.each { 
				archiveDirs.put(DestinationPharmacy.getPharmacy(it.key), new File(it.value.archiveDirectory)) 
				errorDirs.put(DestinationPharmacy.getPharmacy(it.key), new File(it.value.errorsDirectory))
			}
		assert archiveDirs.size() > 0, 'Arhive directories not properly configured in setup map'
		assert errorDirs.size() > 0, 'Error directories not properly configured in setup map'
		
		return new ArchiveOrderServiceImpl(archiveDirs, errorDirs)
	}

	@Bean
	List<WatcherService> watcherServices() {
		def watchers = []
		
		def pharmacySetupMap = jsonConfig()
		assert pharmacySetupMap.size() > 0, 'Pharmacy Setup Map is broken'
		
		pharmacySetupMap.each {
			def service = new WatcherServiceImpl(DestinationPharmacy.getPharmacy(it.key),
				it.value.inputDirectory, it.value.inProcessDirectory)
			watchers << service
		}
		
		assert watchers.size() > 0, 'No Watchers were configured'
		
		return watchers
	}*/


}

package wells.externalinterfaces

abstract class GenericInterface {
	static int RETRIES_ALLOWED = 3
	
	void interactWithInterface(){
		int retriesRemaining = RETRIES_ALLOWED
		boolean interfaceTransmitSuccessful = false
		while(!interfaceTransmitSuccessful && retriesRemaining > 0) {
			if(retriesRemaining < RETRIES_ALLOWED) {
				// TODO Add interface name that is retrying chandra 
				log.info("Retrying Interface ${InterfaceNamevariable}")
			}
			// we get a new ftps connection on each retry
			//FTPSClient ftpsClient = ftpsClientBean.createConnection(orderToLoad.destinationPharmacy)
			retriesRemaining -= 1
			interfaceTransmitSuccessful = invokeInterface()
		}
	}
	
	abstract boolean invokeInterface() 
}

/*
 * Copyright (c) 2012 Prime Therapeutics LLC. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Prime
 * Therapeutics LLC. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the employment agreement you entered into
 * with Prime Therapeutics LLC.
 */
package wells.bean

import groovy.util.logging.Log4j

import org.apache.commons.net.ftp.FTPSClient
@Log4j
class FTPSClientBean {

	Map ftpsSettings
	static final String PROTOCOL = 'SSL'
	static final String AUTH_VALUE = 'SSL'
	static final String SSL_PROTOCOL = 'SSLv3'
	static final boolean IS_IMPLICIT = true
	static final int PROTECTION_BUFFER_SIZE = 0
	static final String PROT = 'P'

	static final String KEY_STORE_TYPE = 'JKS'

	FTPSClientBean(Map ftpsSettings) {
		this.ftpsSettings = ftpsSettings
		assert null != ftpsSettings, "Map of FTPS Settings is null!"
	}
	
	def getFTPSClient() {
		return new FTPSClient(PROTOCOL, IS_IMPLICIT)
	}

	FTPSClient createConnection(String jsonName) {
		FTPSClient ftpsClient = getFTPSClient()
		
		ftpsClient.setAuthValue(AUTH_VALUE)
		Integer port = ftpsSettings[jsonName].port.toInteger()
		ftpsClient.connect(ftpsSettings[jsonName].ftpsServer, port)
		ftpsClient.execPBSZ(PROTECTION_BUFFER_SIZE)
		ftpsClient.execPROT(PROT)
		ftpsClient.login(ftpsSettings[jsonName].username, ftpsSettings[jsonName].password)
		ftpsClient.enterLocalActiveMode()
		ftpsClient.setBufferSize(ftpsSettings[jsonName].socketBufferSize.toInteger())
		
		return ftpsClient
	}

}

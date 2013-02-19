package wells.service

import java.util.List;

import wells.model.OutputMessage

class WellsServiceImpl implements WellsService {
	List invokeInterfaceList
	@Override
	public void createService(List interfaceList) {
		invokeInterfaceList = interfaceList
	}

	@Override
	public OutputMessage invokeService() {
		invokeInterfaceList.each {
			
		}
		return null
	}

}

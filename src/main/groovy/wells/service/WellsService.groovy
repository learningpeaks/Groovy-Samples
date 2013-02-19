package wells.service

import wells.model.OutputMessage

interface WellsService {
	void createService(List interfaceList)
	OutputMessage invokeService()
}

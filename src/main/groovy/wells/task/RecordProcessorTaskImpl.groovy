package wells.task

import java.util.Calendar

import wells.model.WellsMessage
import wells.model.RecordProcessorTask
import wells.service.WellsServiceImpl

class RecordProcessorTaskImpl implements RecordProcessorTask {
	protected Calendar calendar
	protected Long taskStartTime
	protected Long taskEndTime
	protected Long taskCreationTime
	WellsServiceImpl wellsService
	
	RecordProcessorTaskImpl(WellsServiceImpl wellsService){
		this.wellsService = wellsService
	}
	@Override
	public WellsMessage call() throws Exception {
		wellsService.invokeService()
		return null
	}

	@Override
	public void setInputRecord(WellsMessage inputRecord) {
		
	}
}

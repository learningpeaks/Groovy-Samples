package wells.model

import java.util.concurrent.Callable

import wells.model.WellsMessage


interface RecordProcessorTask extends Callable {
	
	void setInputRecord(WellsMessage inputRecord)
}

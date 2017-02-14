package commons.model.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import commons.cache.ApplicationMemory;
import commons.cache.IThreadCache;
import commons.model.exception.OperationException;

public abstract class BaseOperation<Entity> {

	@Autowired
	@Qualifier(ApplicationMemory.NAME)
	private IThreadCache applicationMemory;

	// @Transactional(propagation=Propagation.MANDATORY)
	public void evaluate(Object... parametri) throws OperationException {
		//TO DO
	}

	public IThreadCache getApplicationMemory() {
		return applicationMemory;
	}

	public void setApplicationContext(IThreadCache applicationMemory) {
		this.applicationMemory = applicationMemory;
	}
}

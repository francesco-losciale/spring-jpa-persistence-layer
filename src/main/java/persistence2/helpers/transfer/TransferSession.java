package persistence2.helpers.transfer;

import java.util.HashMap;
import java.util.Map;

import persistence2.helpers.bean.TransferMetadata;

public class TransferSession {
	private final Map<Object, Object> mapped = new HashMap<Object, Object>();
	private final Map<Object, Object> mapping = new HashMap<Object, Object>();
	private TransferMetadata mappingMetadata;

	public TransferSession(final TransferMetadata mappingMetadata) {
		this.mappingMetadata = mappingMetadata;
	}

	public Map<Object, Object> getMapped() {
		return mapped;
	}

	public Map<Object, Object> getMapping() {
		return mapping;
	}

	public TransferMetadata getMappingMetadata() {
		return mappingMetadata;
	}
}

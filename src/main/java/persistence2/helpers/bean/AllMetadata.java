package persistence2.helpers.bean;

import persistence2.helpers.exception.ManagerException;

public class AllMetadata {

	private TransferMetadata mappingMetadata;

	private OperationMetadata operationMetadata;

	public AllMetadata(TransferMetadata mappingMetadata, OperationMetadata operationMetadata) {
		super();
		this.mappingMetadata = mappingMetadata;
		this.operationMetadata = operationMetadata;
	}

	public AllMetadata(Metadata... metadatas) {
		if (metadatas == null) return;

		for (Metadata metadata : metadatas) {

			if (metadata == null) continue;

			Class<?> _class = metadata.getClass();

			// TransferMetadata
			if (_class.equals(TransferMetadata.class)) {
				if (mappingMetadata != null) throw new ManagerException("TransferMetadata definito due volte");
				this.mappingMetadata = (TransferMetadata) metadata;
			} else {// OperationMetadata
				if (_class.equals(OperationMetadata.class)) {
					if (operationMetadata != null) throw new ManagerException("OperationMetadata definito due volte");
					this.operationMetadata = (OperationMetadata) metadata;
				}
			}
		}
	}

	public TransferMetadata getTransferMetadata() {
		return mappingMetadata;
	}

	@SuppressWarnings("unused")
	private void setTransferMetadata(TransferMetadata mappingMetadata) {
		this.mappingMetadata = mappingMetadata;
	}

	public OperationMetadata getOperationMetadata() {
		return operationMetadata;
	}

	@SuppressWarnings("unused")
	private void setOperationMetadata(OperationMetadata operationMetadata) {
		this.operationMetadata = operationMetadata;
	}

	public TransferMetadata getMappingMetadata() {
		return mappingMetadata;
	}
}

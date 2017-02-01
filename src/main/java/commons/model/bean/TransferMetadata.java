package commons.model.bean;

import java.util.HashSet;
import java.util.Set;

import commons.model.exception.TransferException;

public class TransferMetadata implements Metadata {

	public static final TransferMetadata DEFAULT = new TransferMetadata();

	public static final TransferMetadata TUTTI = new TransferMetadata(TransferType.TUTTI);

	public static final TransferMetadata SEMPLICE = new TransferMetadata(TransferType.SEMPLICE);

	private TransferType transferType;

	private Set<String> propreties = new HashSet<String>();

	private Class<?> classView;

	public TransferMetadata() {
		this(TransferType.RISTRETTO);
	}

	public TransferMetadata(TransferType type) {
		super();
		this.transferType = type;
	}

	public TransferMetadata(TransferType mappingType, Set<String> properties, Class<?> classView) {
		super();
		if (properties == null || classView == null) throw new TransferException("properties e classView not null");

		if (mappingType != TransferType.ESCLUDI_E_VISTA && mappingType != TransferType.INCLUDI_E_VISTA) throw new TransferException("MappingType deve essere coerente");

		this.propreties = properties;
		this.classView = classView;
		this.transferType = mappingType;
	}

	public TransferMetadata(Class<?> classView) {
		super();
		if (classView == null) throw new TransferException("classView not null");
		this.transferType = TransferType.VISTA;
		this.propreties = null;
		this.classView = classView;
	}

	public TransferMetadata(TransferType mappingType, Set<String> properties) {
		super();
		if (properties == null) throw new TransferException("properties not null");
		if (mappingType != TransferType.ESCLUDI && mappingType != TransferType.INCLUDI) throw new TransferException("MappingType deve essere coerente");
		this.transferType = mappingType;
		this.propreties = properties;
		this.classView = null;
	}

	public Set<String> getProperties() {
		return propreties;
	}

	@SuppressWarnings("unused")
	private void setProperties(Set<String> properties) {
		this.propreties = properties;
	}

	private void resetProperties() {
		propreties = new HashSet<String>();
	}

	@SuppressWarnings("unused")
	private void addProperty(String property) {
		if (propreties == null) resetProperties();
		propreties.add(property);
	}

	public Class<?> getClassView() {
		return classView;
	}

	@SuppressWarnings("unused")
	private void setClassView(Class<?> classView) {
		this.classView = classView;
	}

	public TransferType getTransferType() {
		return transferType;
	}

	public void setTransferType(TransferType transferType) {
		this.transferType = transferType;
	}

	public Set<String> getPropreties() {
		return propreties;
	}

	public void setPropreties(Set<String> propreties) {
		this.propreties = propreties;
	}
}

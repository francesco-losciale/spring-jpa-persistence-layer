package commons.model.bean;

@Deprecated
public class MappingMetadata extends TransferMetadata {

	public static TransferMetadata DEFAULT = TransferMetadata.DEFAULT;

	public static TransferMetadata TUTTI = new TransferMetadata(TransferType.TUTTI);
}

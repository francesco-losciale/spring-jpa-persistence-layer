package commons.model.dto;

/**
 * @author Padrin Stefano (stefano.padrin@finconsgroup.com)
 */
public interface ISimpleEntityDTO extends IBaseDTO {

	public static final String ID = "id";

	public Long getId();

	public void setId(Long id);
}

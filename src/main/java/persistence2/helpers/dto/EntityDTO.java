package persistence2.helpers.dto;
/**
 * @author Padrin Stefano (stefano.padrin@finconsgroup.com)
 */
@SuppressWarnings("serial")
public class EntityDTO extends BaseDTO implements ISimpleEntityDTO {

	public static final String ID = "id";

	private Long id;

	// usata solo per essere serializzata in json
	@SuppressWarnings("unused")
	private String show;

	@SuppressWarnings("unused")
	private String identifier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShow() {
		return toString();
	}

	public String getIdentifier() {
		return getId() != null ? getId() + "" : "";
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof EntityDTO) {
			EntityDTO compareWith = (EntityDTO) obj;
			if (compareWith.getId() != null && this.id != null) {
				if (compareWith.getId().longValue() == this.id.longValue()) {
					return true;
				}
			}
		}

		return false;
	}

	public int hashCode() {
		return super.hashCode();
	}
}

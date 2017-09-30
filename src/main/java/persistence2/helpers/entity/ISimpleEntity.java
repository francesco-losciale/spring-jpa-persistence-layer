package persistence2.helpers.entity;

import java.io.Serializable;

public interface ISimpleEntity<PK extends Serializable> extends Serializable, Cloneable, Comparable {

	public static final String ID = "id";

}

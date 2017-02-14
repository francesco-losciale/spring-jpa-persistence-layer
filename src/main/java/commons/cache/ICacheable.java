package commons.cache;

import java.io.Serializable;

public interface ICacheable extends Serializable {

	public boolean equals(Comparable<?> par);

	public String getUniqueKey();
}

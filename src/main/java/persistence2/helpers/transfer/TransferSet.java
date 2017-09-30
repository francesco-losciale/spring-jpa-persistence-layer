package persistence2.helpers.transfer;

import persistence2.helpers.bean.ClassPlus;

public class TransferSet extends TransferCollection {

	public TransferSet(final TransferFactory mappingFactory) {
		super(mappingFactory);
	}

	public Object execute(final Object from, final ClassPlus plusTo) {
		final Class<?> classTo = plusTo.getClasse();
		if (!REFLECTION_HELPER.isSet(classTo)) {
			return null;
		}

		return super.execute(from, plusTo);
	}
}
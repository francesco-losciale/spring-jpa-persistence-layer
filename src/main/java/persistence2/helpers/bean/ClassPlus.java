package persistence2.helpers.bean;

import java.lang.reflect.Type;

public class ClassPlus {

	private Class<?> classe;

	private Type generic;

	public ClassPlus(final Class<?> cls) {
		this(cls, null);
	}

	public ClassPlus(final Class<?> cls, final Type generic) {
		this.classe = cls;
		this.generic = generic;
	}

	public ClassPlus(final Class<?> cls, final Class<?> Generic) {
		this.classe = cls;
		this.generic = Generic;
	}

	public Class<?> getClasse() {
		return classe;
	}

	public void setClasse(Class<?> classe) {
		this.classe = classe;
	}

	public Type getGeneric() {
		return generic;
	}

	public void setGeneric(Class<?> Generic) {
		this.generic = Generic;
	}
}

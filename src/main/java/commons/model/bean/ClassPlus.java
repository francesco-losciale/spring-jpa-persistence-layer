package commons.model.bean;

import java.lang.reflect.Type;

public class ClassPlus {

	private Class<?> classe;

	private Type generica;

	public ClassPlus(final Class<?> cls) {
		this(cls, null);
	}

	public ClassPlus(final Class<?> cls, final Type generica) {
		this.classe = cls;
		this.generica = generica;
	}

	public ClassPlus(final Class<?> cls, final Class<?> generica) {
		this.classe = cls;
		this.generica = generica;
	}

	public Class<?> getClasse() {
		return classe;
	}

	public void setClasse(Class<?> classe) {
		this.classe = classe;
	}

	public Type getGenerica() {
		return generica;
	}

	public void setGenerica(Class<?> generica) {
		this.generica = generica;
	}
}

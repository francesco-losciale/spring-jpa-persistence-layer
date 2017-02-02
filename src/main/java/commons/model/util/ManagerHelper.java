package commons.model.util;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import commons.model.bean.EntityMapped;
import commons.model.dto.IBaseDTO;
import commons.model.dto.ILogicDeleteDTO;
import commons.model.dto.ISimpleEntityDTO;
import commons.model.entity.IBaseEntity;
import commons.model.entity.LogicDeleteEntity;
import commons.model.exception.ManagerException;
import commons.model.transfer.Mapping;

@SuppressWarnings("unchecked")
public class ManagerHelper {

	public static final String FISCALONE_DEVELOPMODE = "fiscalone.developmentpmode";
	public static final ReflectionHelper REFLECTION_HELPER = new ReflectionHelper();

	/**
	 * estraggo il primo elemeno dalla collezione, se ne sono presenti più di
	 * uno rilancio un exception
	 *
	 * @param <T>
	 * @param collection
	 * @return
	 */
	public static <T> T getFirst(Collection<T> collection) {
		if (collection == null) return null;

		int size = collection.size();

		if (size == 0) return null;

		if (size > 1) throw new ManagerException("Trovati n elementi, mentre ne aspettavo 1");

		return (T) collection.iterator().next();
	}

	/**
	 * verifico se sono nell ambiente di svilupo
	 *
	 * @return true sono in sviluppo, false in ambiente diverso
	 */
	public static boolean developmentMode() {

		Object developMode = System.getProperty(FISCALONE_DEVELOPMODE);

		boolean evDevMode = (developMode != null && ((String) developMode).equals("true"));

		return evDevMode;
	}

	public static DetachedCriteria criteriaByExample(ISimpleEntityDTO entity, DetachedCriteria criteria) {
		return criteriaByExample(entity, criteria, null);
	}

	public static DetachedCriteria criteriaByExample(IBaseEntity entity, DetachedCriteria criteria) {
		return criteriaByExample(entity, criteria, null);
	}

	public static DetachedCriteria criteriaByExample(Object entity, DetachedCriteria criteria, String[] orders) {
		Map<String, DetachedCriteria> criteri = new HashMap<String, DetachedCriteria>();
		DetachedCriteria output = criteriaByExample(entity, criteria, null, criteri);
		if (orders != null && orders.length > 0) {
			for (String string : orders) {
				String nome = string;
				String verso = "asc";
				int k = string.lastIndexOf(' ');
				if (k > 0) {
					nome = string.substring(0, k).trim();
					verso = string.substring(k).trim();
				}
				boolean asc = !verso.toUpperCase().startsWith("DES");
				k = nome.lastIndexOf('.');
				String nomeEnt = null;
				String nomeField = nome;
				if (k > 0) {
					nomeEnt = nome.substring(0, k);
					nomeField = nome.substring(k + 1);
				}
				if (nomeEnt == null || nomeEnt.trim().length() == 0) {
					criteria.addOrder(asc ? Order.asc(nomeField) : Order.desc(nomeField));
					continue;
				}
				for (String key : criteri.keySet()) {
					if (!key.endsWith(nomeEnt)) continue;
					k = key.indexOf('.');
					if (k < 0) continue;
					if (k >= 0 && !key.substring(k + 1).equals(nomeEnt)) continue;
					criteri.get(key).addOrder(asc ? Order.asc(nomeField) : Order.desc(nomeField));
				}
			}
		}
		return output;
	}

	public static DetachedCriteria criteriaByExample(Object pars, DetachedCriteria criteria, String baseName, Map<String, DetachedCriteria> criteri) {

		Object parameters = pars;
		if (pars instanceof ISimpleEntityDTO) {
			Class<IBaseEntity> clsEntity = ManagerHelper.getMapped((ISimpleEntityDTO) pars);
			if (clsEntity != null) {
				Class<IBaseEntity> entityClass = ManagerHelper.getMapped((IBaseDTO) pars);
				parameters = (IBaseEntity) REFLECTION_HELPER.newInstance(entityClass);

				parameters = new Mapping().mapping(pars, parameters);

				// parameters = ManagerHelper.dtoToEntity((EntityDTO) pars);
			}
		}
		if (parameters instanceof LogicDeleteEntity) {
			((LogicDeleteEntity) parameters).setDateDelete(ManagerConstants.DATE_DELETE_IS_NULL);
		}

		Map<String, PropertyDescriptor> map = REFLECTION_HELPER.getStructure(parameters);

		boolean salta = true;
		if (map != null) {
			if (map.get("id") != null) {
				salta = trattaAttributo("id", map, parameters, baseName, criteri, criteria);
			}
			if (!salta) {
				for (String key : map.keySet()) {
					trattaAttributo(key, map, parameters, baseName, criteri, criteria);
				}
			}
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	private static boolean trattaAttributo(String key, Map<String, PropertyDescriptor> map, Object parameters, String baseName, Map<String, DetachedCriteria> criteri, DetachedCriteria criteria) {
		String name = key.toString();
		PropertyDescriptor pd = map.get(key);

		if (REFLECTION_HELPER.haveAnnotation(pd, Transient.class) || REFLECTION_HELPER.haveAnnotation(pd, OneToMany.class)) return false;

		if (key.equals("version")) return false;

		if (key.equals("userModify")) return false;

		if (key.equals("dateModify")) return false;

		if (key.equals("dateInsert")) return false;

		if (key.equals("userInsert")) return false;

		Object value = REFLECTION_HELPER.read(parameters, pd);

		if (value == null) return false;

		if (value instanceof Set<?>) return false;

		if (value instanceof String && StringUtils.isEmpty((String) value)) return false;

		if (value instanceof ISimpleEntityDTO || value instanceof IBaseEntity) {
			String newName = baseName + "." + name;
			DetachedCriteria crit = criteri.get(newName);
			if (crit == null) {
				crit = criteria.createCriteria(name, DetachedCriteria.INNER_JOIN);
				criteri.put(newName, crit);
			}
			criteriaByExample(value, crit, newName, criteri);
			return true;
		}

		if (value instanceof String) {
			criteria.add(Restrictions.ilike(name, value.toString(), MatchMode.ANYWHERE));
			return true;
		}

		if (value == ManagerConstants.DATE_DELETE_IS_NULL) {
			criteria.add(Restrictions.isNull(ILogicDeleteDTO.DATE_DELETE));
			return true;
		}

		criteria.add(Restrictions.eq(name, value));
		return true;
	}

	public static Class<IBaseEntity> getMapped(IBaseDTO dto) {
		Class<?> sc = dto.getClass().getSuperclass();
		if (sc != null && REFLECTION_HELPER.isExtension(sc, IBaseEntity.class)) return (Class<IBaseEntity>) sc;
		return ManagerHelper.getMapped(dto.getClass());
	}

	public static Class<IBaseEntity> getMapped(Class<?> cls) {
		EntityMapped annotation = cls.getAnnotation(EntityMapped.class);
		if (annotation == null) return null;
		try {
			return (Class<IBaseEntity>) Class.forName(annotation.value());
		} catch (ClassNotFoundException e) {
			System.err.println("getMapped:" + cls.getName() + ":" + annotation.value() + ":" + e.getMessage());
		}
		return null;
	}

	public static boolean isView(Class<?> cls) {
		EntityMapped annotation = cls.getAnnotation(EntityMapped.class);
		if (annotation == null) return true;
		return annotation.view();
	}

	public static IBaseEntity newEntity(Class<?> classEntity) {
		return (IBaseEntity) REFLECTION_HELPER.newInstance(classEntity);
	}

	public static IBaseDTO newDto(Class<?> classDto) {
		return (IBaseDTO) REFLECTION_HELPER.newInstance(classDto);
	}
}

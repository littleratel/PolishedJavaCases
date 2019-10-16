package designpattern.decorator.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import designpattern.decorator.Component;

/**
 * Factory of components.
 */
public final class Components {
	private static Map<String, Component> cmps = new HashMap<String, Component>();
	private static Map<String, Class<? extends Component>> cmpsClasses = new HashMap<String, Class<? extends Component>>();

	static {
		String packageName = Components.class.getPackage().getName();
		Reflections refle = new Reflections(packageName);
		Set<Class<? extends Component>> allTypes = refle.getSubTypesOf(Component.class);
		for (Class<? extends Component> type : allTypes) {
			cmpsClasses.put(type.getSimpleName(), type);
		}
	}

	private Components() {
	}

	/**
	 * Get concrete Component instance based on the input class
	 * 
	 * @param clazz
	 */
	public static Component getComponent(Class<? extends Component> clazz) {
		String clazzName = clazz.getSimpleName();
		if (!cmps.containsKey(clazzName)) {
			Component cmp = null;
			try {
				cmp = (Component) cmpsClasses.get(clazzName).newInstance();
				cmps.put(clazzName, cmp);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return cmp;
		} else {
			return cmps.get(clazzName);
		}
	}
}

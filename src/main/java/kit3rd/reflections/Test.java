package kit3rd.reflections;

import java.io.Closeable;
import java.util.Set;
import org.reflections.Reflections;
import com.google.common.base.Stopwatch;

public class Test {

	public static void main(String[] args) {
		
		// 查找指定包下，所有继承Closeable.class的class
		String basePackages = "cn.bin";
		Reflections refle = new Reflections("java.");
		Stopwatch stopwatch = Stopwatch.createStarted();
		Set<Class<? extends Closeable>> allTypes = refle.getSubTypesOf(Closeable.class);
		System.out.println(stopwatch.toString());
		for (Class type : allTypes) {
			System.out.println(type.getName());
			System.out.println(type.getSimpleName());
		}
		
	}
}
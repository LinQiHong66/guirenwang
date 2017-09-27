package common.autodoc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolTest {
	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			ConcurrentHashMap<String, Object> cmp = new ConcurrentHashMap<>();
			while(index%2 == 1) {
				fixedThreadPool.execute(new Runnable() {
					public void run() {
						try {
							System.out.println(index);
							/*Thread.sleep(2000);*/
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
	}
}
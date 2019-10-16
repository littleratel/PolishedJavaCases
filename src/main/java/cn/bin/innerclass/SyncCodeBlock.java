package cn.bin.innerclass;

public class SyncCodeBlock {

	public int i;

	// public void syncBlock() {
	// // 同步代码库
	// synchronized (this) {
	// i++;
	// }
	// }

	public synchronized void syncMethod() {
		i++;
	}

}
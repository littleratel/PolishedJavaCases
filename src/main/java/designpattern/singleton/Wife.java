package designpattern.singleton;

public class Wife {
	private Wife() {
	}

	public static Wife getWife() {
		return WifeHolder.wife;
	}

	private static class WifeHolder {
		private static final Wife wife = new Wife();
	}
}
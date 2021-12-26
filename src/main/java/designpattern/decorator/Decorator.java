package designpattern.decorator;

public abstract class Decorator implements ExtendedComponent {

	private ExtendedComponent innerDecorator = null;

	@Override
	public final void addInnerDecorator(final ExtendedComponent decorator) {
		if (null != innerDecorator) {
			ExtendedComponent mostInnerDecorator = decorator;
			while (null != mostInnerDecorator.getInnerDecorator()) {
				mostInnerDecorator = mostInnerDecorator.getInnerDecorator();
			}
			mostInnerDecorator.setInnerDecorator(innerDecorator);
		}

		setInnerDecorator(decorator);
	}

	@Override
	public void send(String cmd) {
		if (innerDecorator == null) {
			return;
		}
		innerDecorator.send(cmd);
	}

	@Override
	public final void setInnerDecorator(final ExtendedComponent innerDecorator) {
		this.innerDecorator = innerDecorator;
	}

	@Override
	public final ExtendedComponent getInnerDecorator() {
		return innerDecorator;
	}

	@Override
	public final void removeInnerDecorator() {
		final ExtendedComponent newInner = innerDecorator.getInnerDecorator();
		innerDecorator.setInnerDecorator(null);
		innerDecorator = newInner;
	}

	@Override
	public final void removeInnerDecorator(final ExtendedComponent decoratorToRemove) {
		if (innerDecorator.equals(decoratorToRemove)) {
			removeInnerDecorator();
		} else {
			innerDecorator.removeInnerDecorator(decoratorToRemove);
		}
	}
}

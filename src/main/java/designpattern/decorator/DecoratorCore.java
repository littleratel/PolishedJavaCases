package designpattern.decorator;

import exceptions.NonExistantDecoratorException;

class DecoratorCore extends Decorator {

	DecoratorCore(Component cmp) {
		this.setInnerDecorator(new Core(cmp));
	}

	private final class Core implements ExtendedComponent {

		private final Component cmp;

		private Core(Component cmp) {
			this.cmp = cmp;
		}

		@Override
		public void send(String cmd) {
			cmp.send(cmd);
		}

		@Override
		public void addInnerDecorator(ExtendedComponent decorator) {
			throw new UnsupportedOperationException(
					"addInnerDecorator is not supported for decorator cores, Class: " + getClass().getName());
		}

		@Override
		public void setInnerDecorator(ExtendedComponent innerDecorator) {
			throw new UnsupportedOperationException(
					"setInnerDecorator is not supported for decorator cores, Class: " + getClass().getName());
		}

		@Override
		public ExtendedComponent getInnerDecorator() {
			System.out.println("No Decorator around the core!");
			return null;
		}

		@Override
		public void removeInnerDecorator(final ExtendedComponent decoratorToRemove) {
			throw new NonExistantDecoratorException(
					"No such Decorator around the core: " + decoratorToRemove.getClass().getSimpleName());
		}

		@Override
		public void removeInnerDecorator() {
			throw new UnsupportedOperationException(
					"removeInnerDecorator is not supported for decorator cores, Class: " + getClass().getName());
		}
	}
}

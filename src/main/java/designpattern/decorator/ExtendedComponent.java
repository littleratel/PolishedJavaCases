package designpattern.decorator;

public interface ExtendedComponent extends Component {

	/**
	 * Gets the closest inner core layer.<br>
	 * Core should return null as they can't have any inner layers.
	 * 
	 * @return the innerDecorator (null on cores)
	 */
	public abstract ExtendedComponent getInnerDecorator();

	/**
	 * Sets the inner layer.<br>
	 * UnsupportedOperationException should be throw as they can't have any inner
	 * layers.
	 * 
	 * @param innerDecorator
	 *            the decorator to add
	 */
	public abstract void setInnerDecorator(final ExtendedComponent innerDecorator);

	/**
	 * Adds a layer right below this one.
	 * 
	 * @param decorator
	 *            the decorator to add
	 */
	public abstract void addInnerDecorator(final ExtendedComponent decorator);

	/**
	 * Will check layer for layer until decoratorToRemove is found and removed.
	 * 
	 * @param decoratorToRemove
	 *            decorator to remove.
	 */
	public abstract void removeInnerDecorator(final ExtendedComponent decoratorToRemove);

	/**
	 * Will remove the closest inner decorator layer. <br>
	 * UnsupportedOperationException should be throw if they don't have any inner
	 * layers.
	 * 
	 * Example:<br>
	 * DecoratorC->DecoratorB->DecoratorA->Core <br>
	 * DecoratorA will be removed.
	 * 
	 */
	public abstract void removeInnerDecorator();

}

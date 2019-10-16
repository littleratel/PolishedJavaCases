package exceptions;

public class NonExistantDecoratorException extends RuntimeException {
	private static final long serialVersionUID = -584118464557310384L;

	public NonExistantDecoratorException(final String decoratorToRemoveName) {
		super(decoratorToRemoveName);
	}
}

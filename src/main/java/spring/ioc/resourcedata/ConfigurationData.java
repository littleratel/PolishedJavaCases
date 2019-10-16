package spring.ioc.resourcedata;

public interface ConfigurationData {

    /**
     * Returns the id of this configuration data.
     * It is not permitted for implementations to return null.
     * In such case an {@link IllegalStateException} will be thrown.
     * 
     * @return - the id of this {@link ConfigurationData}
     * @throws IllegalStateException if underlying implementation
     *         returns null.
     */
    String getId();

    /**
     * Returns the configuration data type of this implementation.
     * 
     * @return the type extending this interface.
     */
    Class<? extends ConfigurationData> getType();
}

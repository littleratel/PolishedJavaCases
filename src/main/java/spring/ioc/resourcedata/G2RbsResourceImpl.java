package spring.ioc.resourcedata;

/**
 * G2RbsResource 应该从以下方式获取:<br>
 * <ul>
 * <li>1. bean-xml
 * <li>2.Eris DB
 * <li>3.Tass DB
 * </ul>
 * 
 */
public class G2RbsResourceImpl implements G2RbsResource {

	private String id = G2RbsResourceImpl.class.getName();
	private Class<? extends ConfigurationData> type = (Class<? extends ConfigurationData>) G2RbsResourceImpl.class
			.getComponentType();
	private String getSerialCli = "G2RbsResourceImpl.getSerialCli()";

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Class<? extends ConfigurationData> getType() {
		return type;
	}

	@Override
	public String getSerialCli() {
		return getSerialCli;
	}

}

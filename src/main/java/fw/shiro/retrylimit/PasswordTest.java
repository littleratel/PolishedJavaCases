package fw.shiro.retrylimit;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class PasswordTest {

	public static void main(String[] args) {
		PasswordTest test = new PasswordTest();
		test.testRetryLimitHashedCredentialsMatcherWithMyRealm();
	}

	private void testRetryLimitHashedCredentialsMatcherWithMyRealm() {

		String path = "classpath:shiro/shiro-retryLimitHashedCredentialsMatcher.ini";

		for (int i = 1; i <= 5; i++) {
			try {
				System.out.println("==========> 尝试认证 liu，次数： " + i);
				login(path, "liu", "234");
			} catch (Exception e) {
				System.out.println("==========> liu 认证失败，次数： " + i);
			}

			try {
				System.out.println("==========> 尝试认证 wang ，次数： " + i);
				login(path, "wang", "5566");
			} catch (Exception e) {
				System.out.println("==========> wang 认证失败，次数： " + i);
			}
		}

		login(path, "liu", "234");
		login(path, "wang", "5566");
	}

	private void login(String configFile, String username, String password) {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		try {
			// 4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			System.out.println("==========> 认证失败！！！");
		}
	}
}

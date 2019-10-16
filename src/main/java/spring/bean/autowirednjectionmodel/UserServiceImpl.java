package spring.bean.autowirednjectionmodel;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * IUserDAO 有两个实现类，设置字段名为userDaoMyBatis，与IUserDAO的一个实现类的bean name一样； 如果
 * Autowired 默认是byType注入的，运行程序会抛出异常： expected single matching bean but found 2:
 * userDaoMyBatis,userDaoHibernate。
 * 但是没有，所以默认注入方式是按byName方式的。
 **/

// @Service("userService")
public class UserServiceImpl implements IUserService {

	// @Resource
	@Autowired
	// @Qualifier("userDaoMyBatis")
	private IUserDAO userDaoMyBatis;

	@Override
	public void testService() {
		userDaoMyBatis.testDao();
	}
}

package spring.bean.autowirednjectionmodel;

import org.springframework.stereotype.Repository;

//@Repository("userDaoMyBatis")
public class UserDaoMyBatisImpl implements IUserDAO{

	@Override
	public void testDao() {
		 System.out.println("mybatis实现的dao层");
	}

}

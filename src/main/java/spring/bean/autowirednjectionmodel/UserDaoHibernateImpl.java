package spring.bean.autowirednjectionmodel;

import org.springframework.stereotype.Repository;

//@Repository("userDaoHibernate")
public class UserDaoHibernateImpl implements IUserDAO {

	@Override
	public void testDao() {
		System.out.println("Hibernate实现的dao");
	}

}

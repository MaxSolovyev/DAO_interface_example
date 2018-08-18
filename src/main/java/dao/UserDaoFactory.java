package dao;





import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;
import util.PropertiesReader;

import java.sql.SQLException;

public class UserDaoFactory {

	private UserDao userDao;

	public UserDaoFactory() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		String usedTech = PropertiesReader.getProperties("used.tech");

		if (usedTech.equals("jdbc")) {
			this.userDao = new UserDaoJDBCimpl(DBHelper.getConnection());
		} else if (usedTech.equals("hibernate")) {
			this.userDao = new UserDaoHibernateImpl(createSessionFactory(DBHelper.getConfiguration()));
		}
	}

	public UserDao getUserDao() {
		return userDao;
	}

	private static SessionFactory createSessionFactory(Configuration configuration) {
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}

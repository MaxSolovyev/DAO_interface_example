package dao;


import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
	private SessionFactory sessionFactory;

	public UserDaoHibernateImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUserById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		User user = null;
		try {
			user = (User) session.get(User.class, id);

			//user.setPassword("123"); - that is a persistent state object, so we doesn`t need to
			//call save(), persist() or saveOrUpdate() method, to change rows in db

			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t get user by id: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}

		return user;
	}

	@Override
	public User getUserByName(String name) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		User user = null;
		try {
			Query query = session.createQuery("SELECT u FROM User u WHERE u.name = :name");
			query.setParameter("name", name);

			user = (User) query.uniqueResult();

			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t remove user: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}

		return user;
	}

	@Override
	public void addUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t add user: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void editUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t edit user: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		User user;
		try {
			Query query = session.createQuery("SELECT u FROM User u WHERE u.id = :id");
			query.setParameter("id", id);

			user = (User) query.uniqueResult();

			if (user != null) {
				session.delete(user);
			}

			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t remove user: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		List<User> users = null;
		try {
			users = session.createQuery("FROM User").list();
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Can`t get list of users: " + e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}

		return users;
	}
}

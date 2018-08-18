package dao;


import model.User;

import java.util.List;

public interface UserDao {
	User getUserById(long id);
	User getUserByName(String name);
	void addUser(User user);
	void editUser(User user);
	void remove(long id);
	List<User> getAllUsers();
}

package DB.DAO;

import entity.User;

import java.util.List;

/**
 * DAO for interacting with users table.
 * Only the required DAO methods are defined.
 *
 * @author D. Kuliha
 */
public interface UserDAO {

    User getUser(String login);

    List<User> findAllUsers();

    boolean updateUser(User user);

    boolean createUser(User user);

    boolean deleteUsers(User...users);

    String getPasswordByLogin(String login);

    boolean containsLogin(String login);
}

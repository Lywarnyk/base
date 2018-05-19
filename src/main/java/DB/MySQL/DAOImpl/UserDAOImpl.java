package DB.MySQL.DAOImpl;

import DB.DAO.UserDAO;
import DB.SQLQuery;
import DB.util.Extractor;
import entity.User;
import exception.MainException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DB.MySQL.ConnectionPool.*;

/**
 * Implementation of UserDAO interface
 * for MySQL DataBase
 *
 * @author D. Kuliha
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);


    @Override
    public User getUser(String login) {
        User user = null;
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next())
                user = Extractor.extractUser(resultSet);
        } catch (SQLException e) {
            LOG.error("Cannot get user by login", e);
            throw new MainException("Cannot get user by login", e);
        } finally {
            close(con);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        Connection con = getConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQLQuery.FIND_ALL_USERS);
            while (resultSet.next()) {
                users.add(Extractor.extractUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.UPDATE_USER);
            int k = 1;
            pstmt.setString(k++, user.getLogin());
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k++, user.getRole());
            pstmt.setInt(k, user.getId());
            if (pstmt.execute()) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error("Cannot update user", e);
            throw new MainException("Cannot update user", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public boolean createUser(User user) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.CREATE_USER,
                    Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, user.getLogin());
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setString(k, user.getRole());
            if (pstmt.executeUpdate() > 0) {
                ResultSet resultSet = pstmt.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            LOG.error("Cannot create user", e);
            throw new MainException("Cannot create new user", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public boolean deleteUsers(User... users) {
        boolean result = true;
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            for (User user : users) {
                pstmt = con.prepareStatement(SQLQuery.DELETE_USER);
                pstmt.setInt(1, user.getId());
                result = pstmt.executeUpdate() > 0;
            }
            if (result) {
                con.commit();
                return true;
            }
        } catch (SQLException e) {
            rollback(con);
            throw new MainException();
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public String getPasswordByLogin(String login) {
        String password = "";
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.FIND_USER_PASSWORD_BY_LOGIN);
            pstmt.setString(1, login);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
        }
        return password;
    }

    @Override
    public boolean containsLogin(String login) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error("Cannot find user by login", e);
            throw new MainException("Cannot find user by login", e);
        } finally {
            close(con);
        }
        return false;
    }


    //Singleton
    private static UserDAOImpl instance;

    public static synchronized UserDAOImpl getInstance() {
        if (instance == null) instance = new UserDAOImpl();
        return instance;
    }

    private UserDAOImpl() {
    }
}

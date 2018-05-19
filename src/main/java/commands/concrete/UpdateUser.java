package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.UserDAO;
import commands.Command;
import controllers.Page;
import entity.User;
import security.Password;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Updating user
 *
 * @author D. Kuliha
 */
public class UpdateUser implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(Password.hash(request.getParameter("password")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setRole(request.getParameter("role"));
        user.setId(Integer.parseInt(request.getParameter("userID")));
        UserDAO userDAO = DAOFactory.getUserDao(dataBase);
        userDAO.updateUser(user);
        return Page.USER_CREATED.page();
    }

    /////////////////////////
    //Singleton
    ///////////////////////////
    private UpdateUser() {
    }

    private static UpdateUser instance;

    public static synchronized UpdateUser getInstance() {
        if (instance == null) instance = new UpdateUser();
        return instance;
    }
}

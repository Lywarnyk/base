package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.UserDAO;
import commands.Command;
import controllers.Page;
import entity.User;
import exception.MainException;
import org.apache.log4j.Logger;
import security.Password;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Creating new User
 *
 * @author D. Kuliha
 */
public class CreateNewUser implements Command {

    private static final Logger LOG = Logger.getLogger(CreateNewUser.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        User user = new User();
        UserDAO userDAO = DAOFactory.getUserDao(dataBase);

        //login validation
        String login = request.getParameter("login");
        if(userDAO.containsLogin(login)) {
            LOG.error("Login is already taken.");
            throw new MainException("Login is already taken");
        }
        //setting user parameters from request
        user.setLogin(login);
        user.setPassword(Password.hash(request.getParameter("password")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setRole(request.getParameter("role"));

        //creating user
        userDAO.createUser(user);

        LOG.debug("Command finished");
        return Page.USER_CREATED.page();
    }

    /////////////////////////////
    //Singleton
    /////////////////////////////
    private CreateNewUser() {
    }

    private static CreateNewUser instance;

    public static synchronized CreateNewUser getInstance() {
        if (instance == null) instance = new CreateNewUser();
        return instance;
    }
}

package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.UserDAO;
import commands.Command;
import controllers.Page;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Getting a list of all users from DB
 * without passwords and putting it to the request scope
 *
 * @author D. Kuliha
 */
public class GetAllUsers implements Command {

    private static final Logger LOG = Logger.getLogger(GetAllUsers.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        UserDAO userDAO = DAOFactory.getUserDao(dataBase);
        List<User> users = userDAO.findAllUsers();
        request.setAttribute("users", users);

        LOG.debug("Command finished");
        return Page.USERS.page();
    }

    /////////////////////////////////////
    //Singleton
    //////////////////////////////////
    private GetAllUsers() {
    }
    private static GetAllUsers instance;
    public static synchronized GetAllUsers getInstance() {
        if (instance == null) instance = new GetAllUsers();
        return instance;
    }

}

package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.UserDAO;
import commands.Command;
import controllers.Page;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Deleting user by id
 *
 * @author D. Kuliha
 */
public class DeleteUserById implements Command {

    private static final Logger LOG = Logger.getLogger(DeleteUserById.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");
        UserDAO userDAO = DAOFactory.getUserDao(dataBase);
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("userID")));
        userDAO.deleteUsers(user);
        LOG.debug("Command finished");
        return Page.USER_CREATED.page();
    }
    //////////////////////////////
    //Singleton
    ///////////////////////////////////
    private DeleteUserById() {
    }
    private static DeleteUserById instance;
    public static synchronized DeleteUserById getInstance() {
        if (instance == null) instance = new DeleteUserById();
        return instance;
    }
}

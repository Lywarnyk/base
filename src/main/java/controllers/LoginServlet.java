package controllers;

import DB.DAO.DAOFactory;
import DB.DAO.UserDAO;
import entity.User;
import org.apache.log4j.Logger;
import security.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static commands.Command.dataBase;

/**
 * Servlet verifies credentials of user.
 *
 * @author D.Kuliha
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -694020694772984504L;

    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doPost starts");

        UserDAO userDao = DAOFactory.getUserDao(dataBase);

        //obtain login and password
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String savedPassword = userDao.getPasswordByLogin(login);

        //validating password
        if (Password.hash(password).equals(savedPassword)) {
            LOG.debug("password equals.");
            User user = userDao.getUser(login);
            request.getSession().setAttribute("user", user);
            String startPage = choosePageByUserRole(user);
            request.getSession().setAttribute("startPage", startPage);
            response.sendRedirect(Page.WELCOME.page());
        } else {
            LOG.info("wrong login/password " + login + "/" + password);
            request.getSession().setAttribute("errorMessage", "Incorrect login or password");
            request.getRequestDispatcher(Page.ERROR_PAGE.page()).forward(request, response);
        }
    }

    /**
     * Obtain start page for user by his role
     *
     * @return path of start page
     */
    private String choosePageByUserRole(User user) {
        String page = null;
        switch (user.getRole()) {
            case "admin":
                page = Page.ADMIN_PAGE.page();
                break;
            case "driver":
                page = Page.DRIVER_PAGE.page();
                break;
            case "dispatcher":
                page = Page.DISPATCHER_PAGE.page();
                break;
        }
        return page;
    }

}

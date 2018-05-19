package controllers;

import commands.Command;
import commands.Commands;
import exception.MainException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main controller. Obtains a String value of command from request.
 * Gets instance of command from Commands container and executes it.
 * Obtains a page from executing command and sends redirect or forward.
 * <p>
 * @author D. Kuliha
 */
@MultipartConfig
@WebServlet("/controller")
public class FrontController extends HttpServlet {

    private static final long serialVersionUID = -8417800033241832819L;

    private static final Logger LOG = Logger.getLogger(FrontController.class);

    /**
     * Realisation of Post-Redirect-Get pattern,
     * secures user from resending data.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doPost starts");

        String page = processRequest(request, response);
        LOG.debug(page);
        response.sendRedirect(page);

        LOG.debug("doPost finished");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doGet starts");

        String page = processRequest(request, response);
        LOG.debug(page);
        request.getRequestDispatcher(page).forward(request, response);

        LOG.debug("doGet finished");
    }

    /**
     * Gets instance of requested command and executes it.
     *
     * @return path of result page.
     * Also is a main exception handler. Collects all exceptions,
     * handles it and gives error message.
     */
    private String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Page.ERROR_PAGE.page();
        Command command = Commands.getCommand(request.getParameter("command"));
        try {
            page = command.execute(request, response);
        } catch (MainException e) {
            LOG.error(e.getMessage(), e);
            request.getSession().setAttribute("errorMessage", e.getMessage());
        } catch (Exception e){
            LOG.error("Unexpected error", e);
            request.getSession().setAttribute("errorMessage", "Ooops! Something went wrong.");
        }
        return page;
    }
}

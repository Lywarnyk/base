package commands.concrete;

import commands.Command;
import controllers.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Exit command.
 *
 * @author D. Kuliha
 */
public class Exit implements Command {
    private static final Logger LOG = Logger.getLogger(Exit.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOG.debug("Command starts");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        LOG.debug("Command finished");
        return Page.INDEX_PAGE.page();
    }
    ///////////////////
    //Singleton
    ///////////////////
    private Exit() {
    }

    private static Exit instance;

    public static synchronized Exit getInstance() {
        if (instance == null) instance = new Exit();
        return instance;
    }
}

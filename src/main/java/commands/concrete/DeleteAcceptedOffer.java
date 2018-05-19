package commands.concrete;

import DB.DAO.AcceptedOfferDAO;
import DB.DAO.DAOFactory;
import commands.Command;
import controllers.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Deleting accepted offer by id
 *
 * @author D. Kuliha
 */
public class DeleteAcceptedOffer implements Command{

    private static final Logger LOG = Logger.getLogger(DeleteAcceptedOffer.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        AcceptedOfferDAO acceptedOfferDAO = DAOFactory.getAcceptedOfferDao(dataBase);
        acceptedOfferDAO.deleteAcceptedOffer(request.getParameter("routeID"));

        LOG.debug("Command finished");
        return Page.ROUTES.page();
    }

    /////////////////////////////
    //Singleton
    /////////////////////////////
    private DeleteAcceptedOffer() {
    }
    private static DeleteAcceptedOffer instance;
    public static synchronized DeleteAcceptedOffer getInstance() {
        if (instance == null) instance = new DeleteAcceptedOffer();
        return instance;
    }
}

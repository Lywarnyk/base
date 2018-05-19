package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.OfferDAO;
import commands.Command;
import controllers.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Deleting offer by id
 *
 * @author D. Kuliha
 */
public class DeleteOffer implements Command {

    private static final Logger LOG = Logger.getLogger(DeleteCarById.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        OfferDAO offerDAO = DAOFactory.getOfferDao(dataBase);
        offerDAO.deleteOfferByID(Integer.parseInt(request.getParameter("offerID")));

        LOG.debug("Command finished");
        return Page.OFFER_CREATED.page();
    }

    //////////////////////////////////////
    //Singleton
    /////////////////////////////////////
    private DeleteOffer() {
    }
    private static DeleteOffer instance;
    public static synchronized DeleteOffer getInstance() {
        if (instance == null) instance = new DeleteOffer();
        return instance;
    }
}

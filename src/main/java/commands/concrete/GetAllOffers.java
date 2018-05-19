package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.OfferDAO;
import commands.Command;
import controllers.Page;
import entity.Offer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Gets a list of all offers from DB and
 * puts it to the request scope
 *
 * @author D. Kuliha
 */
public class GetAllOffers implements Command {

    private static final Logger LOG = Logger.getLogger(GetAllOffers.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        OfferDAO offerDAO = DAOFactory.getOfferDao((dataBase));
        List<Offer> offers = offerDAO.findAllOffers();
        request.setAttribute("offers", offers);

        LOG.debug("Command finished");
        return Page.OFFERS.page();
    }

    //////////////////////////////////////
    //Singleton
    /////////////////////////////////////
    private GetAllOffers() {
    }
    private static GetAllOffers instance;
    public static synchronized GetAllOffers getInstance() {
        if (instance == null) instance = new GetAllOffers();
        return instance;
    }

}

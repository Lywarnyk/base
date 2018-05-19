package commands.concrete;

import DB.DAO.AcceptedOfferDAO;
import DB.DAO.DAOFactory;
import commands.Command;
import controllers.Page;
import entity.AcceptedOffer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Getting list of all accepted offers
 * and putting it to the session scope
 *
 * @author D. Kuliha
 */
public class GetAllAcceptedOffers implements Command {

    private static final Logger LOG = Logger.getLogger(GetAllAcceptedOffers.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        AcceptedOfferDAO acceptedOfferDAO = DAOFactory.getAcceptedOfferDao((dataBase));
        List<AcceptedOffer> acceptedOffers = acceptedOfferDAO.findAllAcceptedOffers();
        request.getSession().setAttribute("acceptedOffers", acceptedOffers);

        LOG.debug("Command finished");
        return Page.ACCEPTED_OFFERS.page();
    }

    ////////////////////////////////////
    //Singleton
    ////////////////////////////////////
    private GetAllAcceptedOffers() {
    }
    private static GetAllAcceptedOffers instance;
    public static synchronized GetAllAcceptedOffers getInstance() {
        if (instance == null) instance = new GetAllAcceptedOffers();
        return instance;
    }

}

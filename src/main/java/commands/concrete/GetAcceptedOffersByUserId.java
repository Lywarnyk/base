package commands.concrete;

import DB.DAO.AcceptedOfferDAO;
import DB.DAO.DAOFactory;
import commands.Command;
import controllers.Page;
import entity.AcceptedOffer;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Getting list of accepted offers by user id and putting it to session scope
 *
 * @author D. Kuliha
 */
public class GetAcceptedOffersByUserId implements Command {

    private static final Logger LOG = Logger.getLogger(GetAcceptedOffersByUserId.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        User user = (User) request.getSession().getAttribute("user");
        AcceptedOfferDAO acceptedOfferDAO = DAOFactory.getAcceptedOfferDao(dataBase);
        List<AcceptedOffer> acceptedOffers = acceptedOfferDAO.findAllAcceptedOffersByUser(user);
        request.getSession().setAttribute("acceptedOffers", acceptedOffers);

        LOG.debug("Command starts");
        return Page.ACCEPTED_OFFERS.page();
    }

    ///////////////////////////////
    //Singleton
    ///////////////////////////////
    private GetAcceptedOffersByUserId() {
    }
    private static GetAcceptedOffersByUserId instance;
    public static synchronized GetAcceptedOffersByUserId getInstance() {
        if (instance == null) instance = new GetAcceptedOffersByUserId();
        return instance;
    }
}

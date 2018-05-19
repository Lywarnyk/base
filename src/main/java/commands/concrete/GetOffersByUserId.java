package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.OfferDAO;
import commands.Command;
import controllers.Page;
import entity.Offer;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Getting a list of offers from DB
 * which belong to user.
 *
 * @author D. Kuliha
 */
public class GetOffersByUserId implements Command{

    private static final Logger LOG = Logger.getLogger(GetOffersByUserId.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        OfferDAO offerDAO = DAOFactory.getOfferDao(dataBase);
        User user = (User) request.getSession().getAttribute("user");
        List<Offer> offers = offerDAO.findOffersByUserId(user);
        request.setAttribute("offers", offers);

        LOG.debug("Command finished");
        return Page.OFFERS.page();
    }

    /////////////////////////////////
    //Singleton
    /////////////////////////////////
    private GetOffersByUserId() {
    }
    private static GetOffersByUserId instance;
    public static synchronized GetOffersByUserId getInstance() {
        if (instance == null) instance = new GetOffersByUserId();
        return instance;
    }
}

package DB.DAO;

import entity.Offer;
import entity.User;

import java.util.List;

/**
 * DAO for interacting with offers table.
 * Only the required DAO methods are defined.
 *
 * @author D. Kuliha
 */
public interface OfferDAO {


    List<Offer> findAllOffers();

    Offer getOfferById(String offerID);

    boolean deleteOfferByID(int offerId);

    boolean createOffer(Offer offer);

    /**
     * Selects and returns all offers from DB that user had created.
     */
    List<Offer> findOffersByUserId(User user);

    /**
     *
     * @param routeId ID number of routes to delete from offers.
     * @return true if at least one offer was deleted.
     */
    boolean deleteOffersByRouteId(int routeId);
}

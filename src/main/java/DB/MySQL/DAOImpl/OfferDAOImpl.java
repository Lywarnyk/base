package DB.MySQL.DAOImpl;

import DB.DAO.OfferDAO;
import DB.SQLQuery;
import DB.util.Extractor;
import entity.Offer;
import entity.User;
import exception.MainException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DB.MySQL.ConnectionPool.close;
import static DB.MySQL.ConnectionPool.getConnection;

/**
 * Implementation of OfferDAO interface
 * for MySQL DataBase
 *
 * @author D. Kuliha
 */
public class OfferDAOImpl implements OfferDAO {
    private static final Logger LOG = Logger.getLogger(OfferDAOImpl.class);


    @Override
    public List<Offer> findAllOffers() {
        List<Offer> offers = new ArrayList<>();
        Connection con = getConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQLQuery.FIND_ALL_OFFERS);
            while (resultSet.next()) {
                offers.add(Extractor.extractOffer(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Cannot find all routes", e);
            throw new MainException("Cannot find all routes", e);
        } finally {
            close(con);
        }
        return offers;
    }

    @Override
    public Offer getOfferById(String offerID) {
        Offer offer = null;
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.FIND_OFFER_BY_ID);
            pstmt.setInt(1, Integer.parseInt(offerID));
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                offer = Extractor.extractOffer(resultSet);
            }
        } catch (SQLException e) {
            LOG.error("Cannot get Offer by id", e);
            throw new MainException("Cannot get Offer by id", e);
        } finally {
            close(con);
        }
        return offer;
    }

    @Override
    public boolean deleteOfferByID(int offerId) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.DELETE_OFFER_BY_ID);
            pstmt.setInt(1, offerId);
            if (pstmt.execute()) return true;
        } catch (SQLException e) {
            LOG.error("Cannot delete offer", e);
            throw new MainException("Cannot delete offer", e);
        } finally {
            close(con);
        }
        return false;

    }

    @Override
    public boolean createOffer(Offer offer) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.CREATE_OFFER);
            int k = 1;
            pstmt.setInt(k++, offer.getRoute().getId());
            pstmt.setInt(k++, offer.getUser().getId());
            pstmt.setString(k, offer.getCarParams());
            if (pstmt.execute()) return true;
        } catch (SQLException e) {
            LOG.error("Cannot create new offer", e);
            throw new MainException("Cannot create new offer", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public List<Offer> findOffersByUserId(User user) {
        List<Offer> offers = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.FIND_OFFERS_BY_USER_ID);
            pstmt.setInt(1, user.getId());
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                offers.add(Extractor.extractOffer(resultSet, user));
            }
        } catch (SQLException e) {
            LOG.error("Cannot find offers by user ID", e);
            throw new MainException("Cannot find offers by user ID", e);
        } finally {
            close(con);
        }
        return offers;
    }

    @Override
    public boolean deleteOffersByRouteId(int id) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.DELETE_OFFERS_BY_ROUTE_ID);
            pstmt.setInt(1, id);
            if (pstmt.execute()) return true;
        } catch (SQLException e) {

            LOG.error("Cannot delete offer", e);
            throw new MainException("Cannot delete offer", e);
        } finally {
            close(con);
        }
        return false;
    }

    /////////////////////////////////
    //Singleton
    /////////////////////////////////

    private OfferDAOImpl() {
    }

    private static OfferDAOImpl instance;

    public static synchronized OfferDAOImpl getInstance() {
        if (instance == null) instance = new OfferDAOImpl();
        return instance;
    }
}

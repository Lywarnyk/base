package DB.MySQL.DAOImpl;

import DB.DAO.AcceptedOfferDAO;
import DB.SQLQuery;
import DB.util.Extractor;
import entity.AcceptedOffer;
import entity.Offer;
import entity.Route;
import entity.User;
import exception.MainException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DB.MySQL.ConnectionPool.close;
import static DB.MySQL.ConnectionPool.getConnection;

/**
 * Implementation of AcceptedOfferDAO interface
 * for MySQL DataBase
 *
 * @author D. Kuliha
 */
public class AcceptedOfferDAOImpl implements AcceptedOfferDAO {
    private static final Logger LOG = Logger.getLogger(AcceptedOfferDAOImpl.class);

    @Override
    public List<AcceptedOffer> findAllAcceptedOffers() {
        List<AcceptedOffer> acceptedOffers = new ArrayList<>();
        Connection con = getConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQLQuery.FIND_ALL_ACCEPTED_OFFERS);
            while (resultSet.next()) {
                acceptedOffers.add(Extractor.extractAcceptedOffer(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new MainException("Cannot find all accepted offers", e);
        } finally {
            close(con);
        }
        return acceptedOffers;
    }

    @Override
    public boolean deleteAcceptedOffer(String routeID) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.DELETE_ACCEPTED_OFFER_BY_ROUTE_ID);
            pstmt.setInt(1, Integer.parseInt(routeID));
            if (pstmt.execute()) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new MainException("Cannot delete accepted offer", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public boolean createAcceptedOffer(Offer offer, String carId) {
        LOG.debug("offer id = " + offer.getOfferID() + ", car id = " + carId);
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.CREATE_NEW_ACCEPTED_OFFER);
            int k = 1;
            pstmt.setInt(k++, offer.getRoute().getId());
            pstmt.setInt(k++, offer.getUser().getId());
            pstmt.setInt(k, Integer.parseInt(carId));
            if (pstmt.execute()) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new MainException("Cannot create new accepted offer", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public List<AcceptedOffer> findAllAcceptedOffersByUser(User user) {
        LOG.info("user id = " + user.getId());
        List<AcceptedOffer> acceptedOffers = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.FIND_ALL_ACCEPTED_OFFERS_BY_USER_ID);
            pstmt.setInt(1, user.getId());
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                acceptedOffers.add(Extractor.extractAcceptedOffer(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new MainException("Cannot find all accepted offers by user id", e);
        } finally {
            close(con);
        }
        return acceptedOffers;
    }

    @Override
    public List<Route> findAllRoutesByCarId(int id) {
        List<Route> routes = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.FIND_ALL_ROUTES_BY_CAR_ID);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                routes.add(Extractor.extractRoute(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new MainException("Cannot find all routes by car id", e);
        } finally {
            close(con);
        }
        return routes;
    }


    /////////////////////////////////////
    //Singleton
    /////////////////////////////////////
    private static AcceptedOfferDAOImpl instance;

    public static synchronized AcceptedOfferDAOImpl getInstance() {
        if (instance == null) instance = new AcceptedOfferDAOImpl();
        return instance;
    }

    private AcceptedOfferDAOImpl() {
    }
}

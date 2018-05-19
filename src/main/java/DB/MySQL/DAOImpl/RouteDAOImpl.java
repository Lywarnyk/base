package DB.MySQL.DAOImpl;

import DB.DAO.RouteDAO;
import DB.SQLQuery;
import DB.util.Extractor;
import entity.Route;
import exception.MainException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DB.MySQL.ConnectionPool.*;

/**
 * Implementation of RouteDAO interface
 * for MySQL DataBase
 *
 * @author D. Kuliha
 */
public class RouteDAOImpl implements RouteDAO {
    private static final Logger LOG = Logger.getLogger(RouteDAOImpl.class);


    @Override
    public List<Route> findAllRoutes() {
        List<Route> routes = new ArrayList<>();
        Connection con = getConnection();
        Statement pstmt;
        try {
            pstmt = con.createStatement();
            ResultSet resultSet = pstmt.executeQuery(SQLQuery.FIND_ALL_ROUTE);
            while (resultSet.next()) {
                routes.add(Extractor.extractRoute(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Cannot find all routes.", e);
            throw new MainException("Cannot find all routes.", e);
        } finally {
            close(con);
        }
        return routes;
    }

    @Override
    public boolean createRoute(Route route) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.CREATE_ROUTE);
            int k = 1;
            pstmt.setDate(k++, route.getCreationDate());
            pstmt.setDate(k++, route.getDepartureDate());
            pstmt.setDate(k++, route.getArrivalDate());
            pstmt.setString(k++, route.getDeparturePoint());
            pstmt.setString(k++, route.getDestinationPoint());
            pstmt.setString(k++, route.getDescription());
            pstmt.setString(k, route.getStatus());
            if (pstmt.execute()) return true;
        } catch (SQLException e) {
            LOG.error("Cannot create new route: " + route, e);
            throw new MainException("Cannot create new route.", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public boolean updateRoute(Route route) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.UPDATE_ROUTE);
            int k = 1;
            pstmt.setDate(k++, route.getDepartureDate());
            pstmt.setDate(k++, route.getArrivalDate());
            pstmt.setString(k++, route.getDeparturePoint());
            pstmt.setString(k++, route.getDestinationPoint());
            pstmt.setString(k++, route.getDescription());
            pstmt.setString(k++, route.getStatus());
            pstmt.setInt(k, route.getId());
            if (pstmt.execute()) return true;
        } catch (SQLException e) {
            LOG.error("Cannot update route: " + route, e);
            throw new MainException("Cannot update route", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public boolean updateRouteStatus(Route route) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.UPDATE_ROUTE_STATUS);
            int k = 1;
            pstmt.setString(k++, route.getStatus());
            pstmt.setInt(k, route.getId());
            if (pstmt.execute()) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error("Cannot update route's status: " + route.getStatus(), e);
            throw new MainException("Cannot update route's status", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public boolean deleteRoute(Route route) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.DELETE_ROUTE_BY_ID);
            pstmt.setInt(1, route.getId());
            if (pstmt.execute()) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error("Cannot delete route", e);
            throw new MainException("Cannot delete route", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public boolean deleteRouteByID(String routeID) {
        Route route = new Route();
        route.setId(Integer.parseInt(routeID));
        return deleteRoute(route);
    }

    @Override
    public List<Route> findAllOpenRoutes() {
        List<Route> routes = new ArrayList<>();
        Connection con = getConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQLQuery.FIND_ALL_OPEN_ROUTES);
            while (resultSet.next()) {
                routes.add(Extractor.extractRoute(resultSet));
            }
        } catch (SQLException e) {
           LOG.error("Cannot find open routes", e);
           throw new MainException("Cannot find open routes", e);
        } finally {
            close(con);
        }
        return routes;
    }

    @Override
    public boolean createRoutes(List<Route> routes) {
        boolean result = true;
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            con.setAutoCommit(false);
            for (Route route : routes) {
                pstmt = con.prepareStatement(SQLQuery.CREATE_ROUTE);
                int k = 1;
                pstmt.setDate(k++, route.getCreationDate());
                pstmt.setDate(k++, route.getDepartureDate());
                pstmt.setDate(k++, route.getArrivalDate());
                pstmt.setString(k++, route.getDeparturePoint());
                pstmt.setString(k++, route.getDestinationPoint());
                pstmt.setString(k++, route.getDescription());
                pstmt.setString(k, route.getStatus());
                result = pstmt.executeUpdate() > 0;
            }
            if (result) {
                con.commit();
                return true;
            }
        } catch (SQLException e) {
            rollback(con);
            LOG.error("Cannot execute transaction", e);
            throw new MainException("Cannot execute transaction", e);
        } finally {
            close(con);
        }
        return false;
    }

    ///////////////////////////
    //Singleton
    ///////////////////////////
    private static RouteDAOImpl instance;

    public static synchronized RouteDAOImpl getInstance() {
        if (instance == null) instance = new RouteDAOImpl();
        return instance;
    }

    private RouteDAOImpl() {
    }
}

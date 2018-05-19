package commands.concrete;

import DB.DAO.AcceptedOfferDAO;
import DB.DAO.CarDAO;
import DB.DAO.DAOFactory;
import commands.Command;
import controllers.Page;
import entity.Car;
import entity.Route;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * TASK
 * Created by Owner on 05.02.2018.
 */
public class GetRoutesByCarId implements Command{

    private static final Logger LOG = Logger.getLogger(GetRoutesByCarId.class);


    /////////////////////////////
    //Singleton
    //////////////////////////////
    private GetRoutesByCarId() {
    }
    private static GetRoutesByCarId instance;
    public static synchronized GetRoutesByCarId getInstance() {
        if (instance == null) instance = new GetRoutesByCarId();
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int carId = Integer.parseInt(request.getParameter( "carId"));
        LOG.info(carId + " car id");
        AcceptedOfferDAO acceptedOfferDAO = DAOFactory.getAcceptedOfferDao(dataBase);
        List<Route> routes = acceptedOfferDAO.findAllRoutesByCarId(carId);
        CarDAO carDAO = DAOFactory.getCarDao(dataBase);
        Car car = carDAO.findCarByID(carId);
        request.setAttribute("routes" , routes);
        request.setAttribute("car" , car);
        return Page.TASK.page();
    }
}

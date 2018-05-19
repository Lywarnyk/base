package commands.concrete;

import DB.DAO.CarDAO;
import DB.DAO.DAOFactory;
import commands.Command;
import controllers.Page;
import entity.Car;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Getting a list of all cars from DB
 * and putting it tio the request scope
 *
 * @author D. Kuliha
 */
public class GetAllCars implements Command{

    private static final Logger LOG = Logger.getLogger(GetAllCars.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        CarDAO carDAO = DAOFactory.getCarDao((dataBase));
        List<Car> cars = carDAO.findAllCars();
        request.setAttribute("cars", cars);

        LOG.debug("Command finished");
        return Page.CARS.page();
    }

    //////////////////////////////////////////
    //Singleton
    //////////////////////////////////////////
    private GetAllCars() {
    }
    private static GetAllCars instance;
    public static synchronized GetAllCars getInstance() {
        if (instance == null) instance = new GetAllCars();
        return instance;
    }

}

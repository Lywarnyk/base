package commands.concrete;

import DB.DAO.CarDAO;
import DB.DAO.DAOFactory;
import commands.Command;
import controllers.Page;
import entity.Car;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Updating a car
 *
 * @author D. Kuliha
 */
public class UpdateCar implements Command {

    private static final Logger LOG = Logger.getLogger(UpdateCar.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        Car car = new Car();
        CarDAO carDAO = DAOFactory.getCarDao((dataBase));

        car.setId(Integer.parseInt(request.getParameter("carId")));
        car.setPlateNumber(request.getParameter("plateNumber"));
        car.setModel(request.getParameter("model"));
        car.setLoadCapacity(Integer.parseInt(request.getParameter("capacity")));
        car.setCondition(request.getParameter("condition"));

        carDAO.updateCar(car);

        LOG.debug("Command finished");
        return Page.CAR_CREATED.page();
    }
    //////////////////////////
    //Singleton
    //////////////////////////
    private UpdateCar() {
    }

    private static UpdateCar instance;

    public static synchronized UpdateCar getInstance() {
        if (instance == null) instance = new UpdateCar();
        return instance;
    }
}

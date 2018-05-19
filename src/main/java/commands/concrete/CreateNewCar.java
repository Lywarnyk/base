package commands.concrete;

import DB.DAO.CarDAO;
import DB.DAO.DAOFactory;
import commands.Command;
import controllers.Page;
import entity.Car;
import exception.MainException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Creating new car
 *
 * @author D. Kuliha
 */
public class CreateNewCar implements Command{

    private static final Logger LOG = Logger.getLogger(CreateNewCar.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        CarDAO carDAO = DAOFactory.getCarDao((dataBase));
        Car car = new Car();
        String plateNumber = request.getParameter("plateNumber");
        if(carDAO.contains(plateNumber)){
            LOG.error("Plate number "+plateNumber + " already exists");
            throw new MainException("Plate number " + plateNumber + " already exists");
        }
        car.setPlateNumber(plateNumber);
        car.setModel(request.getParameter("model"));
        car.setLoadCapacity(Integer.parseInt(request.getParameter("capacity")));
        car.setCondition(request.getParameter("condition"));
        carDAO.createCar(car);

        LOG.debug("Command finished");
        return Page.CAR_CREATED.page();
    }

    //////////////////////////////
    //Singleton
    //////////////////////////////
    private CreateNewCar() {
    }

    private static CreateNewCar instance;

    public static synchronized CreateNewCar getInstance() {
        if (instance == null) instance = new CreateNewCar();
        return instance;
    }
}

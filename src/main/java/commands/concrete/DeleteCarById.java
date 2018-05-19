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
 * Deleting car by id
 *
 * @author D. Kuliha
 */
public class DeleteCarById implements Command {

    private static final Logger LOG = Logger.getLogger(DeleteCarById.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        CarDAO carDAO = DAOFactory.getCarDao((dataBase));
        Car car = new Car();
        car.setId(Integer.parseInt(request.getParameter("carId")));
        carDAO.deleteCar(car);

        LOG.debug("Command finished");
        return Page.CAR_CREATED.page();
    }

    /////////////////////////////////
    //Singleton
    /////////////////////////////////
    private DeleteCarById() {
    }

    private static DeleteCarById instance;

    public static synchronized DeleteCarById getInstance() {
        if (instance == null) instance = new DeleteCarById();
        return instance;
    }
}
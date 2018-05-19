package DB.DAO;

import entity.Car;

import java.util.List;

/**
 * DAO for interacting with cars table.
 * Only the required DAO methods are defined.
 *
 * @author D. Kuliha
 */
public interface CarDAO {

    List<Car> findAllCars();

    boolean updateCar(Car car);

    boolean updateCarCondition(String carCondition, int carId);

    boolean createCar(Car car);

    boolean deleteCar(Car car);

    /**
     * Selects all cars in new/good condition without cars that need repair.
     * @return list of cars
     */
    List<Car> findAllOperativeCars(String carParams);

    boolean contains(String plateNumber);

    Car findCarByID(int carId);
}

package DB.MySQL.DAOImpl;

import DB.DAO.CarDAO;
import DB.SQLQuery;
import DB.util.Extractor;
import entity.Car;
import exception.MainException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DB.MySQL.ConnectionPool.close;
import static DB.MySQL.ConnectionPool.getConnection;

/**
 * Implementation of CarDAO interface
 * for MySQL DataBase
 *
 * @author D. Kuliha
 */
public class CarDAOImpl implements CarDAO {
    private static final Logger LOG = Logger.getLogger(CarDAOImpl.class);

    @Override
    public List<Car> findAllCars() {
        List<Car> cars = new ArrayList<>();
        Connection con = getConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQLQuery.FIND_ALL_CARS);
            while (resultSet.next()) {
                cars.add(Extractor.extractCar(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Cannot find all cars.", e);
            throw new MainException("Cannot find all cars.", e);
        } finally {
            close(con);
        }
        return cars;
    }

    @Override
    public boolean updateCar(Car car) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.UPDATE_CAR);
            int k = 1;
            pstmt.setString(k++, car.getModel());
            pstmt.setString(k++, car.getPlateNumber());
            pstmt.setInt(k++, car.getLoadCapacity());
            pstmt.setString(k++, car.getCondition());
            pstmt.setInt(k, car.getId());
            if (pstmt.execute()) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error("Cannot update car - " + car, e);
            throw new MainException("Cannot update car.", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public boolean updateCarCondition(String carCondition, int carId) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.UPDATE_CAR_CONDITION);
            pstmt.setString(1, carCondition);
            pstmt.setInt(2, carId);
            if (pstmt.execute()) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error("Cannot update car condition " + carCondition, e);
            throw new MainException("Cannot update car condition", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public boolean createCar(Car car) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.CREATE_CAR);
            int k = 1;
            pstmt.setString(k++, car.getPlateNumber());
            pstmt.setString(k++, car.getModel());
            pstmt.setInt(k++, car.getLoadCapacity());
            pstmt.setString(k, car.getCondition());
            if (pstmt.execute()) return true;
        } catch (SQLException e) {
            LOG.error("Cannot create car.", e);
            throw new MainException("Cannot create car.", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public boolean deleteCar(Car car) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.DELETE_CAR);
            pstmt.setInt(1, car.getId());
            if (pstmt.execute()) return true;
        } catch (SQLException e) {
            LOG.error("Cannot delete the car carId = " + car.getId());
            throw new MainException("Cannot delete the car", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public List<Car> findAllOperativeCars(String carParams) {
        List<Car> cars = new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.FIND_ALL_OPERATIVE_CARS);
            pstmt.setInt(1, Integer.parseInt(carParams));
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                cars.add(Extractor.extractCar(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("Cannot find all operative cars.", e);
            throw new MainException("Cannot find all operative cars.", e);
        } finally {
            close(con);
        }
        return cars;
    }

    @Override
    public boolean contains(String plateNumber) {
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.FIND_PLATE_NUMBER);
            pstmt.setString(1, plateNumber);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error("Cannot find plate number.", e);
            throw new MainException("Cannot find plate number.", e);
        } finally {
            close(con);
        }
        return false;
    }

    @Override
    public Car findCarByID(int carId) {
        Car car = null;
        Connection con = getConnection();
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(SQLQuery.FIND_CAR_BY_ID);
            pstmt.setInt(1, carId);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                car = Extractor.extractCar(resultSet);
            }
        } catch (SQLException e) {
            LOG.error("Cannot find car by id.", e);
            throw new MainException("Cannot find car by id.", e);
        } finally {
            close(con);
        }

        return car;
    }

    ///////////////////////////////////
    ///Singleton
    ///////////////////////////////////
    private static CarDAOImpl instance;

    public static synchronized CarDAOImpl getInstance() {
        if (instance == null) instance = new CarDAOImpl();
        return instance;
    }

    private CarDAOImpl() {
    }
}

package entity;


import java.io.Serializable;

/**
 * Entity Car
 *
 * @author D. Kuliha
 */
public class Car implements Serializable {

    private static final long serialVersionUID = -6943234234430148079L;

    private int id;
    private String plateNumber;
    private String model;
    private int loadCapacity;
    private String condition;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                ", model='" + model + '\'' +
                ", loadCapacity=" + loadCapacity +
                ", condition='" + condition + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        return plateNumber != null ? plateNumber.equals(car.plateNumber) : car.plateNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (plateNumber != null ? plateNumber.hashCode() : 0);
        return result;
    }
}

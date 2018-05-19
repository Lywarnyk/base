package entity;

import java.io.Serializable;

/**
 * Entity Accpeted offer
 *
 * @author D. Kuliha
 */
public class AcceptedOffer implements Serializable {

    private static final long serialVersionUID = -7645547373803170783L;

    private Route route;
    private User user;
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AcceptedOffer{" +
                "route=" + route +
                ", user=" + user +
                ", car=" + car +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcceptedOffer that = (AcceptedOffer) o;

        if (route != null ? !route.equals(that.route) : that.route != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return car != null ? car.equals(that.car) : that.car == null;
    }

    @Override
    public int hashCode() {
        int result = route != null ? route.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        return result;
    }
}

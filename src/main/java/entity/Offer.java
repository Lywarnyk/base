package entity;


import java.io.Serializable;

public class Offer implements Serializable {
    private int offerID;
    private Route route;
    private User user;
    private String carParams;


    public int getOfferID() {
        return offerID;
    }

    public void setOfferID(int offerID) {
        this.offerID = offerID;
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

    public String getCarParams() {
        return carParams;
    }

    public void setCarParams(String carParams) {
        this.carParams = carParams;
    }
    @Override
    public String toString() {
        return "Offer{" +
                "offerID=" + offerID +
                ", route=" + route +
                ", user=" + user +
                ", carParams='" + carParams + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (offerID != offer.offerID) return false;
        if (route != null ? !route.equals(offer.route) : offer.route != null) return false;
        if (user != null ? !user.equals(offer.user) : offer.user != null) return false;
        return carParams != null ? carParams.equals(offer.carParams) : offer.carParams == null;
    }

    @Override
    public int hashCode() {
        int result = offerID;
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (carParams != null ? carParams.hashCode() : 0);
        return result;
    }
}

package entity;


import java.io.Serializable;
import java.sql.Date;

/**
 * Entity Route
 *
 * @author D. Kuliha
 */
public class Route implements Serializable {

    private static final long serialVersionUID = -3156856566358456172L;

    private int Id;
    private Date creationDate;
    private Date departureDate;
    private Date arrivalDate;
    private String departurePoint;
    private String destinationPoint;
    private String description;
    private String status;



    public boolean intersects(Route route) {
        if(this.departureDate.equals(route.departureDate)&&this.arrivalDate.equals(route.arrivalDate))
            return true;
        if(this.departureDate.after(route.departureDate)&&this.departureDate.before(route.arrivalDate)){
            return true;
        }else if (this.arrivalDate.after(route.departureDate)&&this.arrivalDate.before(route.arrivalDate)){
            return true;
        }else if(route.departureDate.after(this.departureDate)&&route.departureDate.before(this.arrivalDate)){
            return true;
        }else if(route.arrivalDate.after(this.departureDate)&&route.arrivalDate.before(this.arrivalDate)){
            return true;
        }
        return false;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDepartureDate() {

        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Route{" +
                "Id=" + Id +
                ", creationDate=" + creationDate +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departurePoint='" + departurePoint + '\'' +
                ", destinationPoint='" + destinationPoint + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (Id != route.Id) return false;
        if (creationDate != null ? !creationDate.equals(route.creationDate) : route.creationDate != null) return false;
        if (departureDate != null ? !departureDate.equals(route.departureDate) : route.departureDate != null)
            return false;
        if (arrivalDate != null ? !arrivalDate.equals(route.arrivalDate) : route.arrivalDate != null) return false;
        if (departurePoint != null ? !departurePoint.equals(route.departurePoint) : route.departurePoint != null)
            return false;
        if (destinationPoint != null ? !destinationPoint.equals(route.destinationPoint) : route.destinationPoint != null)
            return false;
        if (description != null ? !description.equals(route.description) : route.description != null) return false;
        return status != null ? status.equals(route.status) : route.status == null;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (departurePoint != null ? departurePoint.hashCode() : 0);
        result = 31 * result + (destinationPoint != null ? destinationPoint.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}


package demo;


public class Flight {
    String id ;
    String name ; 
    String national_id;
    String from;
    String to;
    String date;
    String trip;

    public Flight(String id, String name, String national_id, String from, String to, String date, String trip) {
        this.id = id;
        this.name = name;
        this.national_id = national_id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.trip = trip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }
    
    
}

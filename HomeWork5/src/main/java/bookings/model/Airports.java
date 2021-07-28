package bookings.model;

import javax.persistence.*;

/**
 * Класс Airports.
 * DTO для сущности Airports
 * слой MODEL
 * @version 1.1
 */
@Entity(name = "airports_data")
@Table(name = "airports_data", schema = "bookings")
@NamedQuery(name = "airports", query = "FROM airports_data ORDER BY city")
public class Airports {
    @Id
    @Column(name = "airport_code",columnDefinition = "bpchar(3)", nullable = false, length = 3)
    String airportCode;
    @Column(name = "airport_name",columnDefinition = "jsonb", nullable = false)
    String airportName;

    @Column(name = "city",columnDefinition = "jsonb")
    String city;
    @Column(name = "coordinates",columnDefinition = "point")
    String coordinates;
    @Column(name = "timezone",columnDefinition = "point")
    String timezone;

    /** сеттер для поля airportName
     * @param  airportCode
     */
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    /** сеттер для поля airportName
     * @param  airportName
     */
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    /** сеттер для поля airportName
     * @param  city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /** сеттер для поля airportName
     * @param  coordinates
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    /** сеттер для поля airportName
     * @param  timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /** геттер для поля airportCode
     * @return airportCode
     */
    public String getAirportCode() {
        return airportCode;
    }

    /** геттер для поля airportName
     * @return airportName
     */
    public String getAirportName() {
        return airportName;
    }

    /** геттер для поля airportName
     * @return airportName
     */
    public String getCity() {
        return city;
    }

    /** геттер для поля airportName
     * @return coordinates
     */
    public String getCoordinates() {
        return coordinates;
    }

    /** геттер для поля airportName
     * @return timezone
     */
    public String getTimezone() {
        return timezone;
    }
}

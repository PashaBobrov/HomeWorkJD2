package bookings.model;

import javax.persistence.*;

/**
 * Класс Flights.
 * DTO для сущности flights
 * слой MODEL
 * @version 1.1
 */
@Entity(name = "flights")
@Table(name = "flights", schema = "bookings")
public class Flights {
    @Id
    @Column(name = "flight_id")
    private Integer flightId;
    @Column(name = "flight_no",columnDefinition = "bpchar(6)", nullable = false, length = 6)
    private String flightNo;
    @Column(name = "scheduled_departure",columnDefinition = "timestamptz")
    private String scheduledDeparture;
    @Column(name = "scheduled_arrival",columnDefinition = "timestamptz")
    private String scheduledArrival;
    @Column(name = "departure_airport",columnDefinition = "bpchar(3)", length = 3)
    private String departureAirport;
    @Column(name = "arrival_airport",columnDefinition = "bpchar(3)", length = 3)
    private String arrivalAirport;
    private String status;
    @Column(name = "aircraft_code",columnDefinition = "bpchar(3)", length = 3)
    private String aircraftCode;
    @Column(name = "actual_departure",columnDefinition = "timestamptz")
    private String actualDeparture;
    @Column(name = "actual_arrival",columnDefinition = "timestamptz")
    private String actualArrival;

    /** сеттер для поля flightId
     * @param  flightId
     */
    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    /** сеттер для поля flightNo
     * @param  flightNo
     */
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    /** сеттер для поля scheduledDeparture
     * @param  scheduledDeparture
     */
    public void setScheduledDeparture(String scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    /** сеттер для поля scheduledArrival
     * @param  scheduledArrival
     */
    public void setScheduledArrival(String scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    /** сеттер для поля departureAirport
     * @param  departureAirport
     */
    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    /** сеттер для поля arrivalAirport
     * @param  arrivalAirport
     */
    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    /** сеттер для поля status
     * @param  status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /** сеттер для поля aircraftCode
     * @param  aircraftCode
     */
    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    /** сеттер для поля actualDeparture
     * @param  actualDeparture
     */
    public void setActualDeparture(String actualDeparture) {
        this.actualDeparture = actualDeparture;
    }

    /** сеттер для поля actualArrival
     * @param  actualArrival
     */
    public void setActualArrival(String actualArrival) {
        this.actualArrival = actualArrival;
    }

    /** геттер для поля flightId
     * @return flightId
     */
    public Integer getFlightId() {
        return flightId;
    }

    /** геттер для поля flightNo
     * @return flightNo
     */
    public String getFlightNo() {
        return flightNo;
    }

    /** геттер для поля scheduledDeparture
     * @return scheduledDeparture
     */
    public String getScheduledDeparture() {
        return scheduledDeparture;
    }

    /** геттер для поля scheduledArrival
     * @return scheduledArrival
     */
    public String getScheduledArrival() {
        return scheduledArrival;
    }

    /** геттер для поля departureAirport
     * @return departureAirport
     */
    public String getDepartureAirport() {
        return departureAirport;
    }

    /** геттер для поля arrivalAirport
     * @return arrivalAirport
     */
    public String getArrivalAirport() {
        return arrivalAirport;
    }

    /** геттер для поля arrivalAirport
     * @return arrivalAirport
     */
    public String getStatus() {
        return status;
    }

    /** геттер для поля aircraftCode
     * @return aircraftCode
     */
    public String getAircraftCode() {
        return aircraftCode;
    }

    /** геттер для поля actualDeparture
     * @return actualDeparture
     */
    public String getActualDeparture() {
        return actualDeparture;
    }

    /** геттер для поля actualArrival
     * @return actualArrival
     */
    public String getActualArrival() {
        return actualArrival;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author jayso
 */
public class Flight implements Serializable {

    private Date departureDate;
    private Date arrivalDate;
    private String departureCity;
    private String destinationCity;
    private String departureCode;

    public Flight() {
    }

    public Flight(Date departureDate, String departureCity, String destinationCity, String departureCode) {
        this.departureDate = departureDate;
        this.arrivalDate = generateArrivalTime(departureDate);
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureCode = departureCode;
    }

    private static Date generateArrivalTime(Date departureDate) {
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(departureDate);

        // Generate a random hour
        int randomHour = random.nextInt(6) + 1;

        // Add the generated hour to the departureDate
        calendar.add(Calendar.HOUR_OF_DAY, randomHour);

        return calendar.getTime();
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

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureCode() {
        return departureCode;
    }

    public void setDepartureCode(String departureCode) {
        this.departureCode = departureCode;
    }

}

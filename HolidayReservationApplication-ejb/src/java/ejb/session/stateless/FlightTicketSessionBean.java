/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.ejb.Stateless;

/**
 *
 * @author jayso
 */
@Stateless
public class FlightTicketSessionBean implements FlightTicketSessionBeanLocal {

    @Override
    public ArrayList<Flight> getAvailableFlights(Date departDate, Date returnDate, String departCity, String destCity, Integer numOfTravelers) {
        ArrayList<Flight> flights = new ArrayList<>();
        // Departure
        String departFlightCode = generateRandomFlightCode();
        Date departTime = generateRandomFlightHour(departDate);

        // Return
        String retFlightCode = generateRandomFlightCode();
        Date retTime = generateRandomFlightHour(returnDate);
        
        flights.add(new Flight(departTime, departCity, destCity, departFlightCode));
        flights.add(new Flight(retTime, destCity, departCity, retFlightCode));
        return flights;
    }

    private Date generateRandomFlightHour(Date date) {
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Generate a random hour
        int randomHour = random.nextInt(24);

        // Generate a random minute in 5-minute intervals
        int randomMinute = random.nextInt(12) * 5;

        // Set the generated hour and minute in the calendar
        calendar.set(Calendar.HOUR_OF_DAY, randomHour);
        calendar.set(Calendar.MINUTE, randomMinute);

        return calendar.getTime();
    }

    private String generateRandomFlightCode() {
        // Define the set of characters to choose from (uppercase letters A-Z)
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random random = new Random();

        // Generate a random flight code (2 letters)
        StringBuilder flightCode = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            flightCode.append(randomChar);
        }

        // Generate a random 3-number string
        StringBuilder randomNumbers = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbers.append(randomNumber);
        }

        return flightCode.toString() + randomNumbers.toString();
    }
}

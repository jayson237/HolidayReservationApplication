/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Flight;
import entity.ItineraryItem;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author jayso
 */
@Local
public interface FlightTicketSessionBeanLocal {
    
    public ArrayList<Flight> getAvailableFlights(Date departDate, Date returnDate, String departCity, String destCity, Integer numOfTravelers);
}

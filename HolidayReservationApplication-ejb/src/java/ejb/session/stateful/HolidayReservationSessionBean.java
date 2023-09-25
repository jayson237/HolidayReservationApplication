/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package ejb.session.stateful;

import ejb.session.stateless.CarRentalSessionBeanLocal;
import ejb.session.stateless.FlightTicketSessionBeanLocal;
import ejb.session.stateless.HotelSessionBeanLocal;
import entity.CarRental;
import entity.Flight;
import entity.Hotel;
import entity.ItineraryItem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author jayso
 */
@Stateful
public class HolidayReservationSessionBean implements HolidayReservationSessionBeanRemote, HolidayReservationSessionBeanLocal {

    @EJB
    private HotelSessionBeanLocal hotelSessionBean;

    @EJB
    private FlightTicketSessionBeanLocal flightTicketSessionBean;

    @EJB
    private CarRentalSessionBeanLocal carRentalSessionBean;

    @Override
    public ArrayList<ItineraryItem> searchForHolidayPackages(Date departDate, Date returnDate, String departCity, String destCity, int numOfTravelers) {
        ArrayList<ItineraryItem> itineraries = new ArrayList<>();

        ArrayList<Flight> flights = flightTicketSessionBean.getAvailableFlights(departDate, returnDate, departCity, destCity, numOfTravelers);
        Flight departureFlight = flights.get(0);
        Flight returnFlight = flights.get(1);
        itineraries.add(new ItineraryItem(1, departureFlight.getDepartureDate(), String.format("Board flight %s from %s Airport at %s", departureFlight.getDepartureCode(), departureFlight.getDepartureCity(), formatCustomDate(departureFlight.getDepartureDate()))));
        itineraries.add(new ItineraryItem(2, departureFlight.getArrivalDate(), String.format("Flight %s lands at %s Airport at %s", departureFlight.getDepartureCode(), departureFlight.getDestinationCity(), formatCustomDate(departureFlight.getArrivalDate()))));
        itineraries.add(new ItineraryItem(3, returnFlight.getDepartureDate(), String.format("Board flight %s from %s Airport at %s", returnFlight.getDepartureCode(), returnFlight.getDepartureCity(), formatCustomDate(returnFlight.getDepartureDate()))));
        itineraries.add(new ItineraryItem(4, returnFlight.getArrivalDate(), String.format("Flight %s lands at %s Airport at %s", returnFlight.getDepartureCode(), departureFlight.getDestinationCity(), formatCustomDate(returnFlight.getArrivalDate()))));

        CarRental car = carRentalSessionBean.getAvailableCar(departureFlight.getArrivalDate(), returnFlight.getDepartureDate());
        itineraries.add(new ItineraryItem(5, car.getCollectDate(), String.format("Collect rental car model %s at %s", car.getCarType(), formatCustomDate(car.getCollectDate()))));
        itineraries.add(new ItineraryItem(6, car.getReturnDate(), String.format("Return rental car model %s at %s", car.getCarType(), formatCustomDate(car.getReturnDate()))));

        Hotel hotel = hotelSessionBean.getAvailableHotel(car.getCollectDate(), car.getReturnDate());
        itineraries.add(new ItineraryItem(7, hotel.getCheckInDate(), String.format("Check-in to %s at %s", hotel.getName(), formatCustomDate(hotel.getCheckInDate()))));
        itineraries.add(new ItineraryItem(8, hotel.getCheckOutDate(), String.format("Check-out from %s at %s", hotel.getName(), formatCustomDate(hotel.getCheckOutDate()))));

        itineraries.sort(new DateComparator());
        
        int sequenceNumber = 1;
        for (ItineraryItem i: itineraries) {
            i.setSequenceNumber(sequenceNumber);
            sequenceNumber++;
        }
        
        return itineraries;
    }

    private String formatCustomDate(Date date) {
        // Define the desired output date format
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a, dd MMMM yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));

        // Format the date to the desired format
        return sdf.format(date);
    }

    private class DateComparator implements Comparator<ItineraryItem> {

        @Override
        public int compare(ItineraryItem itinerary1, ItineraryItem itinerary2) {
            return itinerary1.getDateTime().compareTo(itinerary2.getDateTime());
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateful;

import entity.ItineraryItem;
import java.util.Date;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author jayso
 */
@Local
public interface HolidayReservationSessionBeanLocal {

    public ArrayList<ItineraryItem> searchForHolidayPackages(Date departDate, Date returnDate, String departCity, String destCity, int numOfTravelers);
}

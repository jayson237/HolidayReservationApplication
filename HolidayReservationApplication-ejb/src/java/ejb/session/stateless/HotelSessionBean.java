/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.Hotel;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.ejb.Stateless;

/**
 *
 * @author jayso
 */
@Stateless
public class HotelSessionBean implements HotelSessionBeanLocal {

    @Override
    public Hotel getAvailableHotel(Date timeBeforeCheckingIn, Date timeAfterCheckingOut) {
        Calendar arrivalCalendar = Calendar.getInstance();
        arrivalCalendar.setTime(timeBeforeCheckingIn);
        arrivalCalendar.add(Calendar.HOUR_OF_DAY, 1);
        Date checkInDate = arrivalCalendar.getTime();

        Calendar returnCalendar = Calendar.getInstance();
        returnCalendar.setTime(timeAfterCheckingOut);
        returnCalendar.add(Calendar.HOUR_OF_DAY, -1);
        Date checkOutDate = returnCalendar.getTime();

        return new Hotel(generateRandomHotel(), checkInDate, checkOutDate);
    }
    
    private String generateRandomHotel() {
        // Define the set of characters to choose from (uppercase letters A-Z)
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random random = new Random();

        StringBuilder hotel = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            hotel.append(randomChar);
        }
        return hotel.toString();
    }
}

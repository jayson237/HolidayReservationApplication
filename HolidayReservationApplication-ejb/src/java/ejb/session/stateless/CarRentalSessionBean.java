/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.CarRental;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.ejb.Stateless;

/**
 *
 * @author jayso
 */
@Stateless
public class CarRentalSessionBean implements CarRentalSessionBeanLocal {

    @Override
    public CarRental getAvailableCar(Date arrivalDate, Date returnDate) {
        Calendar arrivalCalendar = Calendar.getInstance();
        arrivalCalendar.setTime(arrivalDate);
        arrivalCalendar.add(Calendar.HOUR_OF_DAY, 1);
        Date updatedArrivalDate = arrivalCalendar.getTime();

        Calendar returnCalendar = Calendar.getInstance();
        returnCalendar.setTime(returnDate);
        returnCalendar.add(Calendar.HOUR_OF_DAY, -1);
        Date updatedReturnDate = returnCalendar.getTime();

        return new CarRental(generateRandomCar(), updatedArrivalDate, updatedReturnDate);
    }

    private String generateRandomCar() {
        // Define the set of characters to choose from (uppercase letters A-Z)
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random random = new Random();

        StringBuilder car = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            car.append(randomChar);
        }
        return car.toString();
    }

}

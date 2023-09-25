/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package holidayreservationapplicationclient;

import ejb.session.stateful.HolidayReservationSessionBeanRemote;
import ejb.session.stateless.UserSessionBeanRemote;
import entity.ItineraryItem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author jayso
 */
public class MainApp {

    private HolidayReservationSessionBeanRemote holidayReservationSessionBean;
    private UserSessionBeanRemote userSessionBean;

    public MainApp(HolidayReservationSessionBeanRemote holidayReservationSessionBean, UserSessionBeanRemote userSessionBean) {
        this.holidayReservationSessionBean = holidayReservationSessionBean;
        this.userSessionBean = userSessionBean;
    }

    public void runApp() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("*** Welcome to Holiday Reservation System ***\n");
            System.out.println("1: Login");
            System.out.println("2: Search Holiday");
            System.out.println("3: Exit\n");
            System.out.print("> ");
            int input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1:
                    logIn(sc);
                    break;
                case 2:
                    searchHoliday(sc);
                    break;
                case 3:
                    System.out.println("You have exited. Goodbye.");
                    break;
                default:
                    System.out.println("Invalid input, please try again");
                    runApp();
                    break;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e + " please try again\n");
            runApp();
        }
    }

    public void logIn(Scanner sc) {

        System.out.println("*** Holiday Reservation System :: Login ***\n");
        System.out.print("Enter email> ");
        String email = sc.nextLine().trim();
        System.out.print("Enter password> ");
        String password = sc.nextLine().trim();

        try {
            if (userSessionBean.checkUserExists(email, password)) {
                String username = userSessionBean.getUserByEmail(email).getUsername();
                System.out.println("Login successful as " + username + "!\n");
                goToDashboard(sc, username);
            } else {
                System.out.println("No account match or wrong login details. Try again.\n");
                logIn(sc);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e + " please try again\n");
            logIn(sc);
        }
    }

    public void goToDashboard(Scanner sc, String username) throws ParseException {
        System.out.println("*** Welcome to Holiday Reservation System ***\n");
        System.out.println("You are log in as " + username + "\n");

        System.out.println("2: Search Holiday");
        System.out.println("3: Exit\n");
        System.out.print("> ");
        int input = sc.nextInt();
        sc.nextLine();
        switch (input) {
            case 2:
                searchHoliday(sc);
                break;
            case 3:
                System.out.println("You have exited. Goodbye.");
                break;
            default:
                System.out.println("Invalid input, please try again");
                goToDashboard(sc, username);
                break;
        }

    }

    public void searchHoliday(Scanner sc) throws ParseException {
        System.out.println("*** Holiday Reservation System :: Search Holiday ***\n");
        System.out.print("Enter Departure Date (dd/mm/yyyy)> ");
        String departureDate = sc.nextLine().trim();
        System.out.print("Enter Return Date (dd/mm/yyyy)> ");
        String returnDate = sc.nextLine().trim();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date departDate = dateFormat.parse(departureDate);
        Date retDate = dateFormat.parse(returnDate);

        if (retDate.before(departDate) || retDate.equals(departDate)) {
            System.out.println("Invalid date input\n");
            searchHoliday(sc);
            return;
        }

        System.out.print("Enter Departure City> ");
        String departureCity = sc.nextLine().trim();
        System.out.print("Enter Destination City> ");
        String destinationCity = sc.nextLine().trim();
        System.out.print("Enter Number of Travellers> ");
        int numOfTravellers = sc.nextInt();
        sc.nextLine();

        try {
            ArrayList<ItineraryItem> itineraries = holidayReservationSessionBean.searchForHolidayPackages(departDate, retDate, departureCity, destinationCity, numOfTravellers);
            System.out.println("Seq. No.             Date/Time   Itinerary");
            for (ItineraryItem i : itineraries) {
                System.out.println(i.toString());
            }
            System.out.println("------------------------");
            System.out.println("1: Make a reservation");
            System.out.println("2: Back");

        } catch (Exception e) {
            System.out.println("Error: " + e + " please try again\n");
            searchHoliday(sc);
        }

    }
}

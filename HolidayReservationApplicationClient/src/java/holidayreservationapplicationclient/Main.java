/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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
import javax.ejb.EJB;

/**
 *
 * @author jayso
 */
public class Main {

    @EJB
    private static HolidayReservationSessionBeanRemote holidayReservationSessionBean;

    @EJB
    private static UserSessionBeanRemote userSessionBean;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainApp app = new MainApp(holidayReservationSessionBean, userSessionBean);
        app.runApp();
    }

}

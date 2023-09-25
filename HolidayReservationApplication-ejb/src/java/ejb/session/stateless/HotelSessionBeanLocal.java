/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb.session.stateless;

import entity.Hotel;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author jayso
 */
@Local
public interface HotelSessionBeanLocal {

    public Hotel getAvailableHotel(Date timeBeforeCheckingIn, Date timeAfterCheckingOut);
}

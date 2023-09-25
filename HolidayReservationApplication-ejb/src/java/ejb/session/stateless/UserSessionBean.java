/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb.session.stateless;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import java.sql.ResultSet;
import javax.sql.DataSource;

/**
 *
 * @author jayso
 */
@Stateless
public class UserSessionBean implements UserSessionBeanRemote, UserSessionBeanLocal {

    @Resource(name = "holidayReservationApplicationDataSource")
    private DataSource holidayReservationApplicationDataSource;


    @Override
    public boolean checkUserExists(String email, String password) {
        User user = this.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE EMAIL = '" + email + "';";
        try {
            Connection connection = holidayReservationApplicationDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("USERID"));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                return user;
            }
            return null;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}

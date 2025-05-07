/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.EmpHourly;
import business.EmpSalary;
import business.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class PersonDA {

    private static final Logger LOG = Logger.getLogger(PersonDA.class.getName());

    public static LinkedHashMap<Integer, Person> selectAll() throws SQLException, NamingException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM person";
        ps = connection.prepareStatement(query);

        rs = ps.executeQuery();
        LinkedHashMap<Integer, Person> persons = new LinkedHashMap();

        while (rs.next()) {

            if (rs.getObject("salary") != null) {

                int empID = rs.getInt("employeeID");
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                LocalDate birthDate = rs.getDate("birthDate").toLocalDate();
                LocalDate hireDate = rs.getDate("hireDate").toLocalDate();
                double salary = rs.getDouble("salary");

                EmpSalary person = new EmpSalary(firstName, middleName, lastName, empID, birthDate, hireDate, salary);
                persons.put(person.getEmployeeID(), person);

            } else if (rs.getObject("rate") != null) {

                int empID = rs.getInt("employeeID");
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                LocalDate birthDate = rs.getDate("birthDate").toLocalDate();
                LocalDate hireDate = rs.getDate("hireDate").toLocalDate();
                double rate = rs.getDouble("rate");
                double avgWeeklyHours = rs.getDouble("avgWeeklyHours");

                EmpHourly person = new EmpHourly(firstName, middleName, lastName, empID, birthDate, hireDate, rate, avgWeeklyHours);
                persons.put(person.getEmployeeID(), person);

            } else {

                int empID = rs.getInt("employeeID");
                String firstName = rs.getString("firstName");
                String middleName = rs.getString("middleName");
                String lastName = rs.getString("lastName");
                LocalDate birthDate = rs.getDate("birthDate").toLocalDate();
                LocalDate hireDate = rs.getDate("hireDate").toLocalDate();

                Person person = new Person(firstName, middleName, lastName, empID, birthDate, hireDate);
                persons.put(person.getEmployeeID(), person);

            }
        }

        ps.close();
        rs.close();
        pool.freeConnection(connection);
        return persons;

    }

}

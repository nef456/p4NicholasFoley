/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import business.Person;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import data.PersonDA;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author n_fol
 */
public class API extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(API.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        String[] parts = uri.split("/");
        System.out.println(uri);

        int code = 200;
        String json = "";
        PrintWriter out = response.getWriter();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonOut, type,
                jsonDeserializationContext) -> {
            return LocalDate.parse(jsonOut.getAsString());
        });
        gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (localDate, type,
                jsonSerializationContext) -> {
            return new JsonPrimitive((localDate.toString()));
        });
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        try {
            if ("employees".equals(parts[3]) && parts.length == 4) {
                LinkedHashMap<Integer, Person> employees = PersonDA.selectAll();
                json = gson.toJson(employees);
            } else if ("employees".equals(parts[3]) && parts.length == 5) {
                int id = Integer.parseInt(parts[4]);
                LinkedHashMap<Integer, Person> employees = PersonDA.selectAll();
                if (employees.containsKey(id)) {
                    json = gson.toJson(employees.get(id));
                } else {
                    code = 404;
                    json = gson.toJson("{}");
                }

            } else if ("hourly".equals(parts[3]) && parts.length == 4) {
                LinkedHashMap<Integer, Person> hourly = PersonDA.selectAll();
                System.out.println(hourly.values());
                
                if(hourly.values().equals("EmpHourly")){
                json = gson.toJson(hourly);
                } else{
                    json = "{\"message\": \"No Hourly Employees\"}";
                }
            }

        } catch (NamingException | SQLException ex) {
            LOG.log(Level.SEVERE, "***DB FAIL :(", ex);
            //FAIL do fail stuff
            code = 500;
            json = "{\"message\": \"DB down, try later.\"}";
        } catch (NumberFormatException ex) {
            code = 400;
            json = "{\"message\": \"Invalid URI: " + parts[4] + "\"}";
        }

        response.setStatus(code);
        out.write(json);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

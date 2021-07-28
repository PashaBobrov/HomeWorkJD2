package bookings.controller.web.servlets;

import bookings.storage.JDBC_Storage;
import bookings.view.AirporstService;
import bookings.view.api.IAirportsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Класс сервлет AirportServlet.
 * Данный сервлет запускается по URL /airports
 * отображает аеропорты с сортировкой по городу
 * @version 1.1
 */
@WebServlet(name = "AirportsServlet",urlPatterns = "/airports")
public class AirportsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        IAirportsService airportsService = AirporstService.getInstance();
        req.setAttribute("listAirports", airportsService.getAirports());
        getServletContext().getRequestDispatcher("/view/airports.jsp").forward(req, resp);
    }


}

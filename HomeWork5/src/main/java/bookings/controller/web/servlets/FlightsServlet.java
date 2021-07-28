package bookings.controller.web.servlets;

import bookings.model.Flights;
import bookings.view.AirporstService;
import bookings.view.FlightsService;
import bookings.view.api.IAirportsService;
import bookings.view.api.IFlightsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Класс сервлет FlightsServlet.
 * Данный сервлет запускается по URL /airports
 * отображает аеропорты с сортировкой по городу
 * @version 1.1
 */
@WebServlet(name = "FlightsServlet",urlPatterns = "/flights")
public class FlightsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,String[]> paramReq = req.getParameterMap();
        IFlightsService flightsService= FlightsService.getInstance();
        IAirportsService airportsService = AirporstService.getInstance();
        req.setAttribute("listAirports", airportsService.getAirports());

        int currentPage = 1;
        int sizePage = 15;
        if(req.getParameter("page") != "" && req.getParameter("page") != "") {
            currentPage = Integer.parseInt(req.getParameter("page"));
        }
        int sizeFlights = flightsService.getSizeFlights(paramReq);
        int countPages = (int) Math.ceil(sizeFlights * 1.0 / sizePage);
        List<Flights> listFlights = flightsService.getFlights(paramReq,
                sizePage,(currentPage - 1) * sizePage);
        req.setAttribute("listFlights",listFlights);
        req.setAttribute("countPages", countPages);
        req.setAttribute("currentPage", String.valueOf(currentPage));
        req.setAttribute("airportDeparture",  req.getParameter("airportDeparture"));
        req.setAttribute("airportArrival",  req.getParameter("airportArrival"));

        getServletContext().getRequestDispatcher("/view/flights.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        IAirportsService airportsService = AirporstService.getInstance();
        req.setAttribute("listAirports", airportsService.getAirports());
        req.setAttribute("airportDeparture", "DME");
        req.setAttribute("airportArrival", "KZN");
        getServletContext().getRequestDispatcher("/view/flights.jsp").forward(req, resp);
    }


}

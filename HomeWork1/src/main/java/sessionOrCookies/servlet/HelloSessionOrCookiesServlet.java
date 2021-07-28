package sessionOrCookies.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sessionOrCookies.dto.Person;
import sessionOrCookies.utils.StorageSession;
import sessionOrCookies.utils.api.IStorage;
import sessionOrCookies.utils.api.TypeStorage;
import sessionOrCookies.utils.StorageCookies;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс сервлет HelloSessionOrCookiesServlet.
 * Данный сервлет запускает по URL /sessionOrCookies
 * Читает из параметров имя, фамилию, возвраст
 * Записывает данные параметры в COOKIE или сессию,
 * в зависимости от параметра заголовка страницы "typeStorage" (COOKIES или SESSION)
 * @version 1.1
 */
@WebServlet(name = "HelloSessionOrCookiesServlet",urlPatterns = "/sessionOrCookies")
public class HelloSessionOrCookiesServlet extends HttpServlet {

    final String HEADER_PARAM = "typeStorage";
    final String PARAM1 = "firstName";
    final String PARAM2 = "lastName";
    final String PARAM3 = "age";
    final String OBJ_NAME = "person";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String value1 = req.getParameter(PARAM1);
        String value2 = req.getParameter(PARAM2);
        String value3 = req.getParameter(PARAM3);

        IStorage storage = null;
        switch (TypeStorage.getType(req.getHeader(HEADER_PARAM))) {
            case COOKIES:
                storage = new StorageCookies(resp,req);
                break;
            case SESSION:
                storage = new StorageSession(req);
                break;
            default:
                throw new IllegalArgumentException("HEADER_PARAM "+ HEADER_PARAM + " error");
        }

        Person person = null;
        if(!checkValueNotNULL(value1,value2,value3)) {
            person = (Person) storage.getValue(OBJ_NAME);
        } else {
            person = new Person(value1,value2,Integer.valueOf(value3));
            storage.setValue(OBJ_NAME,person);
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        if(person != null) {
            writer.write("<p><span style='color: blue;'> Hello, " + person + "</span></p>");
        }else {
            writer.write("<p><span style='color: blue;'>Hello, it's error no params!</span></p>");
        }
    }

    /** Определяет заполнены входные значения, если хоть одно
     * не заполнено, возвращает false, иначе true
     * @param values - массив значений
     * @return boolean
     */
    private boolean checkValueNotNULL(String... values) {
        boolean result = true;
        for (String value : values) {
            if (value == null) {
                result = false;
                break;
            }
        }
        return result;
    }

}


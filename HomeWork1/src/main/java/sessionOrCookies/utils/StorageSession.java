package sessionOrCookies.utils;

import sessionOrCookies.utils.api.IStorage;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Класс для хранения данных в сессие
 * @version 1.1
 */
public class StorageSession implements IStorage {
    /**  HttpServletRequest request*/
    private HttpServletRequest request;

    /**
     * Конструктор - создание нового объекта
     * @param request - HttpServletRequest
     */
    public StorageSession(HttpServletRequest request) {
        this.request = request;
    }

    /** получение значения из Cookies по имени
     * @param atrName - имя параметра
     * @return значение параметра сессии в виде объекта
     */
    @Override
    public Object getValue(String atrName) {
        return request.getSession().getAttribute(atrName);
    }

    /** сохранение значения в сессию
     * @param objName - имя параметра
     * @param obj - объект
     */
    @Override
    public void setValue(String objName,Object obj) {
        request.getSession().setAttribute(objName,obj);
    }


}

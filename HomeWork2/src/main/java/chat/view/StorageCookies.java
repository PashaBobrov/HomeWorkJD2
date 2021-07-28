package chat.view;

import chat.Storage.api.IStorage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * Класс для хранения данных в Cookie
 * @version 1.1
 */
public class StorageCookies implements IStorage {
    /**  HttpServletResponse response */
    private HttpServletResponse response;
    /**  HttpServletRequest request*/
    private HttpServletRequest request;

    /**
     * Конструктор - создание нового объекта
     * @param setProvider - провайдер для установки данных
     * @param getProvider - провайдер для получения данных
     */
    public StorageCookies(HttpServletResponse setProvider, HttpServletRequest getProvider) {
        this.response = setProvider;
        this.request = getProvider;
    }
    /** получения объекта по названию атрибута
     * @param atrName - имя объекта
     * @return lastName
     */
    @Override
    public Object getValue(String atrName) {
        String stringObject = getCookieValue(atrName);
        return convertFromByteString(stringObject);
    }

    /** сохранение объекта
     * @param objName - имя объекта
     * @param obj - сам объект
     */
    @Override
    public void setValue(String objName,Object obj) {
        String stringObject = convertToByteString(obj);
        SetCookieValue(objName,stringObject);
    }

    /** сериализация объекта в строку байт
     * @param object - объект
     * @return строка байт
     */
    public static String convertToByteString(Object object) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            final byte[] byteArray = bos.toByteArray();
            return Base64.getEncoder().encodeToString(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** десериализация объекта из строки байт
     * @param byteString - строка байт
     * @return объект
     */
    public static Object convertFromByteString(String byteString) {
        final byte[] bytes = Base64.getDecoder().decode(byteString);
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInput in = new ObjectInputStream(bis)) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** сохранение значения в Cookies
     * @param cookieName - имя параметра
     * @param cookieValue - значение параметра
     */
    private void SetCookieValue(String cookieName,String cookieValue) {
        Cookie cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, StandardCharsets.UTF_8));
        cookie.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
        response.addCookie(cookie);
    }

    /** получение значения из Cookies по имени
     * @param cookieName - имя параметра
     * @return значение параметра в виде строки
     */
    private String getCookieValue(String cookieName) {
        return Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals(cookieName))
                .findFirst()
                .map(Cookie::getValue)
                .map(c -> URLDecoder.decode(c,StandardCharsets.UTF_8))
                .orElse(null);
    }

}

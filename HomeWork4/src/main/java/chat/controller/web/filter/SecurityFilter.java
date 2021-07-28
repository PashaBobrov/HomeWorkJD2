package chat.controller.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Класс фильтр SecurityFilter.
 * Данный класс предначен для выявления не аутонтефицированных пользователей
 * если пользователь не прошел аутентификацию, его перенаправляют на страницу входа
 * @version 1.1
 */
@WebFilter(urlPatterns = {"/chat", "/message", "/selectPage"})
public class SecurityFilter implements Filter {
    private  boolean active = true;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();
        if((session != null) && (session.getAttribute("user") != null)) {
            chain.doFilter(request,response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/signIn");
        }
    }
}

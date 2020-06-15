package Api.Helpers;

import Api.Service.AuthService;
import Api.Helpers.Data.TokenData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    private AuthService authService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public AuthInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(request.getMethod().equals("OPTIONS") || request.getRequestURI().startsWith("/error")) {
            return true;
        }
        String token = request.getHeader("AuthToken");

        if(token == null) {
            response.setStatus(401);
            return false;
        }

        TokenData data;
        try {
            data = this.authService.verify(token);
        } catch(Exception ex) {
            logger.debug("Failed to verify token", ex);
            response.setStatus(401); //Unauthorized status
            return false;
        }
        request.setAttribute("user", data);
        return true;

    }
}

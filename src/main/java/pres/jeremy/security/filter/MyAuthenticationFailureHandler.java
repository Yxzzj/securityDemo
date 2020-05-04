package pres.jeremy.security.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        String localizedMessage = e.getLocalizedMessage();
        JSONObject returnObj = new JSONObject();
        returnObj.put("status", "failure");
        returnObj.put("message", localizedMessage);
        response.getWriter().print(returnObj.toString());
        response.getWriter().flush();
    }
}
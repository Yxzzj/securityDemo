package pres.jeremy.security.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
//        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);
//        if (isAjax) {
//            String principal = authentication.getPrincipal().toString();
//            JSONObject returnObj = new JSONObject();
//            returnObj.put("status", "1");
//            returnObj.put("data", principal);
//            response.getWriter().print(returnObj.toString());
//            response.getWriter().flush();
//        } else {
//            super.onAuthenticationSuccess(request, response, authentication);
//        }
        String principal = authentication.getPrincipal().toString();
        JSONObject returnObj = new JSONObject();
        returnObj.put("status", "success");
        returnObj.put("data", principal);
        response.getWriter().print(returnObj.toString());
        response.getWriter().flush();
    }


}

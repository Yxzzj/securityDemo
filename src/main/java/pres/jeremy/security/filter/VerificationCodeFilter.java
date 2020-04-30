package pres.jeremy.security.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;
import pres.jeremy.security.exception.VerificationCodeException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerificationCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler = new MyAuthenticationFailureHandler();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (!"/auth/form".equals(request.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            try {
                verificationCode(request);
                chain.doFilter(request, response);
            } catch (VerificationCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
    }

    public void verificationCode(HttpServletRequest request) throws VerificationCodeException {
        String requestCode = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String savedCode = (String) session.getAttribute("captcha");
        if (!StringUtils.isNotEmpty(savedCode)) {
            //清除验证码，无论失败还是成功，客户端应在登录失败时刷新验证码
            session.removeAttribute("captcha");
        }
        if (StringUtils.isEmpty(requestCode) || StringUtils.isEmpty(savedCode)
                || !requestCode.equalsIgnoreCase(savedCode)) {
            throw new VerificationCodeException();
        }
    }
}

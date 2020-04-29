package pres.jeremy.security.exception;

import org.springframework.security.core.AuthenticationException;

public class VerificationException extends AuthenticationException {

    public VerificationException() {
        super("验证码错误");
    }
}

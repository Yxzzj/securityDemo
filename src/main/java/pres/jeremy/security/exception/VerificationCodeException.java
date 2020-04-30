package pres.jeremy.security.exception;

import org.springframework.security.core.AuthenticationException;

public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException() {
        super("验证码错误");
    }
}

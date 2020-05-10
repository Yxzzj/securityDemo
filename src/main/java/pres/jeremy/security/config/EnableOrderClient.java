package pres.jeremy.security.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ClientBeanProcessor.class})
public @interface EnableOrderClient {
}

package pres.jeremy.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @GetMapping("/hello")
    public String hello() {
        return "hello, admin";
    }

    @GetMapping("/getinfo")
    public String getinfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roleList = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        System.out.println(roleList);
        Object principal = authentication.getPrincipal();
        log.info("principal : {}", principal);
        return "success";
    }
}

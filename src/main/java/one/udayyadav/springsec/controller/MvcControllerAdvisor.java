package one.udayyadav.springsec.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@ControllerAdvice
public class MvcControllerAdvisor {

    @ModelAttribute("profile")
    public Map<String, Object> getTest() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2AuthenticationToken) {
            return ((DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAttributes();
        }
        return null;
    }

}

package one.udayyadav.springsec.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.udayyadav.springsec.models.AppUser;
import one.udayyadav.springsec.models.GoogleUserInfo;
import one.udayyadav.springsec.repository.AppUserRepository;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOidcUserService extends OidcUserService {

    private final AppUserRepository appUserRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());

        // see what other data from userRequest or oidcUser you need
        Optional<AppUser> userOptional = appUserRepository.findById(googleUserInfo.getId());
        if (userOptional.isEmpty()) {
            AppUser user = new AppUser();
            user.setSub(googleUserInfo.getId());
            user.setEmail(googleUserInfo.getEmail());
            user.setName(googleUserInfo.getName());

            // set other needed data
            appUserRepository.save(user);
        }

        return oidcUser;
    }
}
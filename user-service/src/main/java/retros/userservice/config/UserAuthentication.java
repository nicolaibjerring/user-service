package retros.userservice.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Use this class for authentication instead of Spring Authentication
 * Allows us to extract values such as user id, email, username, etc. directly from context
 */
public class UserAuthentication implements Authentication {



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        // If we can construct this object, user is authenticated
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        // Intentionally set empty
    }

    @Override
    public String getName() {
        return null;
    }
}

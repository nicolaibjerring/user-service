package retros.userservice.config;

import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class SecurityContext {

    public Long getUserId() throws AuthenticationException {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        return 1L;
    }
}

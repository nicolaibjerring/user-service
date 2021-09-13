package retros.userservice.config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private String token;
    private Long expiration;
}

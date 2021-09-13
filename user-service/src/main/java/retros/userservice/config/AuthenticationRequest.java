package retros.userservice.config;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthenticationRequest {

    private String username;

    private String password;
}

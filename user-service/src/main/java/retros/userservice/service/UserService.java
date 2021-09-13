package retros.userservice.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import retros.userservice.config.AuthenticationRequest;
import retros.userservice.config.AuthenticationResponse;
import retros.userservice.config.TokenHandler;
import retros.userservice.entity.UserEntity;
import retros.userservice.model.User;
import retros.userservice.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Getter
public class UserService {

    private UserRepository userRepository;
    private ConversionService conversionService;
    private TokenHandler tokenHandler;
    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    public void setUserRepository( final UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

   @Autowired
   public void setTokenHandler( final TokenHandler tokenHandler ) {
        this.tokenHandler = tokenHandler;
   }

    @Autowired
    public void setConversionService( final ConversionService conversionService ) {
        this.conversionService = conversionService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map( user -> conversionService.convert(user, User.class) )
                                                .collect(Collectors.toList());
    }

    public User getUserByUsername( final String username ) {
        /* Optional<UserEntity> user = userRepository.findAll().stream()
                                                            .findFirst()
                                                            .filter(u -> username.equals(u.getUsername()));

        if (user.isEmpty()) {
            throw new RuntimeException("Could not find user");
        }

        return conversionService.convert(user, User.class);*/

        return User.builder().username("test").password("test").userId(1L).build();
    }

    public void createUser(final User user) {
        final UserEntity entity = conversionService.convert(user, UserEntity.class);

        if (Objects.isNull(entity)) {
            throw new RuntimeException();
        }

        userRepository.save(entity);
    }

    public void createTestUsers( final List<User> users ) {

        userRepository.saveAll( users.stream().map( user -> conversionService.convert(user, UserEntity.class))
                                              .collect(Collectors.toList()) );
    }

    public AuthenticationResponse authenticateUser(final AuthenticationRequest request) {
        final String username = request.getUsername();

        // Check if user exists
        User user = null;
        try {
            user = getUserByUsername(username);
        } catch (RuntimeException e) {
            throw new RuntimeException("Authentication failed: Could not find user");
        }

        //TODO Add password encryption
        if (request.getPassword().equals(user.getPassword())) {
            return AuthenticationResponse.builder()
                                         .token(tokenHandler.generateToken(user))
                                         .build();
        } else {
            throw new RuntimeException("Authentication failed: Incorrect password");
        }
    }
}

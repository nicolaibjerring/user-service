package retros.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import retros.userservice.config.AuthenticationRequest;
import retros.userservice.config.AuthenticationResponse;
import retros.userservice.model.User;
import retros.userservice.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserController {

    private UserService userService;

    private final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    public void setUserService( final UserService userService ) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user/create")
    public void addUser(@RequestBody User user) {
        user.setUserId(null);
        userService.createUser(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authenticationRequest,
                                                                 HttpServletResponse response) {
        AuthenticationResponse auth = userService.authenticateUser(authenticationRequest);
        response.addHeader("Authorization", String.format("Bearer %s", auth.getToken()));
        return ResponseEntity.ok(auth);
    }

    @GetMapping("/create")
    public void createTestUsers() {

        User user1 = User.builder()
                         .username("Nicolai")
                         .password("test")
                         .build();
        User user2 = User.builder()
                         .username("Ida")
                         .password("test")
                         .build();

        List<User> users = Arrays.asList(user1, user2);
        userService.createTestUsers(users);
    }
}

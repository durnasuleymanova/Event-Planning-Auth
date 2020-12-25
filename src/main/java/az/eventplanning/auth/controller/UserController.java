package az.eventplanning.auth.controller;

import az.eventplanning.auth.dao.ApplicationUser;
import az.eventplanning.auth.dao.ApplicationUserRepository;
import az.eventplanning.auth.model.AuthRequest;
import az.eventplanning.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static az.eventplanning.auth.model.SecurityConstants.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService service;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder, UserService service) {

        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.service = service;
    }

    @PostMapping(SIGN_UP_URL)
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

    @PostMapping(LOGIN_URL)
    public ResponseEntity<Void> login(@RequestBody AuthRequest request) {
        service.loadUserByUsernameAndPassword(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

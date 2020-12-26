package az.eventplanning.auth.service;

import az.eventplanning.auth.dao.ApplicationUser;
import az.eventplanning.auth.dao.ApplicationUserRepository;
import az.eventplanning.auth.model.AuthRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final ApplicationUserRepository applicationUserRepository;

    public UserService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public ApplicationUser loadUserByUsernameAndPassword(AuthRequest request) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword())
                .orElseThrow(() -> {
                    throw new RuntimeException("User not found");
                });
        return applicationUser;
    }
}

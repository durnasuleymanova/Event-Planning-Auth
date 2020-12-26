package az.eventplanning.auth;

import az.eventplanning.auth.dao.ApplicationUser;
import az.eventplanning.auth.dao.ApplicationUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EventPlanningAuthApplication implements ApplicationRunner {
    private ApplicationUserRepository userRepository;

    public EventPlanningAuthApplication(ApplicationUserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(EventPlanningAuthApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(ApplicationUser.builder().password("durna").username("durna").build());
    }
}

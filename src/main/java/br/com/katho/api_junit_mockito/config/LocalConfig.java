package br.com.katho.api_junit_mockito.config;

import br.com.katho.api_junit_mockito.domain.UserEntity;
import br.com.katho.api_junit_mockito.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

//@Configuration
//@Profile("local")
public class LocalConfig {

    //@Autowired
    private UserRepository userRepository;

    //@Bean
    public void startDBH2() {
        UserEntity u1 = new UserEntity(1, "Katho Metal", "katho@email.com", "123");
        UserEntity u2 = new UserEntity(2, "Kirk", "hamet@email.com", "456");

        userRepository.saveAll(List.of(u1, u2));
    }
}

package br.com.katho.api_junit_mockito.services;

import br.com.katho.api_junit_mockito.domain.User;
import br.com.katho.api_junit_mockito.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null);
    }
}

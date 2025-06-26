package br.com.katho.api_junit_mockito.services;

import br.com.katho.api_junit_mockito.domain.User;

public interface UserService {

    User findById(Integer id);
}

package br.com.katho.api_junit_mockito.services;

import br.com.katho.api_junit_mockito.domain.UserEntity;

public interface UserService {

    UserEntity findById(Integer id);
}

package br.com.katho.api_junit_mockito.services;

import br.com.katho.api_junit_mockito.domain.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity findById(Integer id);

    List<UserEntity> findAll();
}

package br.com.katho.api_junit_mockito.repository;

import br.com.katho.api_junit_mockito.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}

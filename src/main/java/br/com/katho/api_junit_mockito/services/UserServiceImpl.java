package br.com.katho.api_junit_mockito.services;

import br.com.katho.api_junit_mockito.domain.UserEntity;
import br.com.katho.api_junit_mockito.domain.dto.UserDTO;
import br.com.katho.api_junit_mockito.exceptions.ObjectNotFoundException;
import br.com.katho.api_junit_mockito.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository repository;

    @Override
    public UserEntity findById(Integer id) {
        Optional<UserEntity> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public UserEntity create(UserDTO dto) {
        findByEmail(dto);
        return repository.save(mapper.map(dto, UserEntity.class));
    }

    @Override
    public UserEntity update(UserDTO dto) {
        findByEmail(dto);
        return repository.save(mapper.map(dto, UserEntity.class));
    }

    private void findByEmail(UserDTO dto) {
        Optional<UserEntity> user = repository.findByEmail(dto.getEmail());
        if (user.isPresent() && !user.get().getId().equals(dto.getId())) {
            throw new ObjectNotFoundException("E-mail já cadastrado no sistema");
        }
    }
}

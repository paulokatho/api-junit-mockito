package br.com.katho.api_junit_mockito.services;

import br.com.katho.api_junit_mockito.domain.UserEntity;
import br.com.katho.api_junit_mockito.domain.dto.UserDTO;
import br.com.katho.api_junit_mockito.exceptions.DataIntegratyViolationException;
import br.com.katho.api_junit_mockito.exceptions.ObjectNotFoundException;
import br.com.katho.api_junit_mockito.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceImplTest {

    private static final Integer ID      = 1;
    private static final Integer INDEX   = 0;
    private static final String NAME     = "Valdir";
    private static final String EMAIL    = "valdir@mail.com";
    private static final String PASSWORD = "123";
    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";
    public static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private UserEntity user;
    private UserDTO userDTO;
    private Optional<UserEntity> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {

        // Mocka a resposta para não dar exceção
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        UserEntity response = service.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(UserEntity.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {

        // Mocka a resposta para não dar exceção
        Mockito.when(repository.findById(
                Mockito.anyInt()))
                .thenThrow(new ObjectNotFoundException(USUARIO_NAO_ENCONTRADO));

        try {
            service.findById(ID);
        } catch (Exception e) {
            Assertions.assertEquals(ObjectNotFoundException.class, e.getClass());
            Assertions.assertEquals(USUARIO_NAO_ENCONTRADO, e.getMessage());
        }
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<UserEntity> response = service.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(UserEntity.class, response.get(INDEX).getClass());
        Assertions.assertEquals(1, response.size());

        Assertions.assertEquals(ID, response.get(INDEX).getId());
        Assertions.assertEquals(NAME, response.get(INDEX).getName());
        Assertions.assertEquals(EMAIL,response.get(INDEX).getEmail());
        Assertions.assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        UserEntity response = service.create(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(UserEntity.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);

        UserEntity response = service.create(userDTO);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        } catch (Exception e) {
            Assertions.assertEquals(DataIntegratyViolationException.class, e.getClass());
            Assertions.assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, e.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        UserEntity response = service.update(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(UserEntity.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);

        UserEntity response = service.create(userDTO);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        } catch (Exception e) {
            Assertions.assertEquals(DataIntegratyViolationException.class, e.getClass());
            Assertions.assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, e.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
        Mockito.doNothing().when(repository).deleteById(Mockito.anyInt());
        service.delete(ID);

        Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException() {
        Mockito.when(repository.findById(Mockito.anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

       try {
           service.delete(ID);
       } catch (Exception e) {
           Assertions.assertEquals(ObjectNotFoundException.class, e.getClass());
            Assertions.assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
       }
    }

    // Metodo que inicia os valores das constantes
    private void startUser() {
        user = new UserEntity(ID, NAME, EMAIL,  PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new UserEntity(ID, NAME, EMAIL, PASSWORD));
    }
}
package kma.topic2.junit;

import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.service.UserService;
import kma.topic2.junit.validation.UserValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ServiceTests{

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @SpyBean
    private UserValidator userValidator;

    @ParameterizedTest(name = "login [{0}] password [{1}] fullName [{2}]")
    @MethodSource("GoodData")
    public void createNewUserTest(final String login, final String password, final String fullName){
        userService.createNewUser(NewUser.builder()
                .fullName(fullName)
                .login(login)
                .password(password)
                .build());
        assertThat(userRepository.isLoginExists(login)).isTrue();
        Mockito.verify(userValidator).validateNewUser(ArgumentMatchers.any());
    }

    // credentials that should pass test
    private static Stream<Arguments> GoodData(){
        return Stream.of(
                Arguments.of("login", "passw", "Name"),
                Arguments.of("anylog", "123456", "Bella Sis"),
                Arguments.of("e", "eee", "e"),
                Arguments.of("Anything you like", "strong", "1"),
                Arguments.of("2", "2eq21r", "22EE")
        );
    }
}
package kma.topic2.junit;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class ValidatorTests{

    @InjectMocks
    UserValidator userValidator;
    @Mock
    UserRepository userRepository;

    @Test
    void validateNewUser() {
        userValidator.validateNewUser(NewUser.builder()
                .fullName("Name")
                .login("logg")
                .password("groove")
                .build());
    }

    @ParameterizedTest(name = "password [{0}]")
    @MethodSource("BadPasswordData")
    void validateUserWithBadPassword(final String password){
        NewUser build = NewUser.builder()
                .fullName("Name")
                .login("logi")
                .password(password)
                .build();
        assertThatThrownBy(() -> userValidator.validateNewUser(build)).isInstanceOf(ConstraintViolationException.class).hasMessage("You have errors in you object");
    }

    private static Stream<Arguments> BadPasswordData(){
        return Stream.of(
                Arguments.of("badpassword"),
                Arguments.of("поганийпароль"),
                Arguments.of(":|+`3"),
                Arguments.of("")
        );
    }

    @Test
    void validateUserWithAlreadyExistingLogin(){
        Mockito.when(userRepository.isLoginExists(ArgumentMatchers.any())).thenReturn(true);
        NewUser testUser = NewUser.builder()
                .fullName("Name")
                .login("login")
                .password("pass2")
                .build();
        assertThatThrownBy(() -> userValidator.validateNewUser(testUser)).isInstanceOf(LoginExistsException.class);
    }
}
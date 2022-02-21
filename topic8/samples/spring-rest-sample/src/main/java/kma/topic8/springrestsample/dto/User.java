package kma.topic8.springrestsample.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class User {

    private final String login;
    private final String name;

}

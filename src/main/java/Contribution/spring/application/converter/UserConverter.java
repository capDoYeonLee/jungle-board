package Contribution.spring.application.converter;

import Contribution.spring.application.dto.CreateSignUpRequest;
import Contribution.spring.persistence.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toEntity(CreateSignUpRequest request) {
        return User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}

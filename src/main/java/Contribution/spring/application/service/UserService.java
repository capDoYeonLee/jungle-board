package Contribution.spring.application.service;

import Contribution.spring.application.converter.UserConverter;
import Contribution.spring.application.dto.CreateSignUpRequest;
import Contribution.spring.persistence.entity.User;
import Contribution.spring.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter converter;

    @Transactional
    public void singUp(CreateSignUpRequest request) {

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("이메일은 필수입니다.");
        }

        if (request.getPassword() == null || request.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
        }

//        if (userRepository.existsByEmail(request.getEmail())) {
//            throw new IllegalArgumentException("duplicate email");
//        }

        User user = converter.toEntity(request);
        userRepository.save(user);
    }
}

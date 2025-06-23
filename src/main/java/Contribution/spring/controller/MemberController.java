package Contribution.spring.controller;

import Contribution.spring.application.dto.CreateSignUpRequest;
import Contribution.spring.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;

    @PostMapping("/user/sign-up")
    public void singUpUser(@RequestBody CreateSignUpRequest request) {
        userService.singUp(request);
    }
}

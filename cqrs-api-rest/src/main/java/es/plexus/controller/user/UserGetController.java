package es.plexus.controller.user;

import es.plexus.config.security.TokenUtils;
import es.plexus.mapper.user.RestUserMapper;
import es.plexus.usecase.user.FindUserByIdUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserGetController {
    @Autowired
    private FindUserByIdUseCase findUserByIdUseCase;
    @Autowired
    private RestUserMapper userMapper;

    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable long userId) {
        return new ResponseEntity<>(this.userMapper.toDto(findUserByIdUseCase.getUserById(userId)), HttpStatus.OK);
    }
}

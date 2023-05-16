package es.plexus.controller.user;

import es.plexus.dto.user.UserPostDto;
import es.plexus.entity.user.User;
import es.plexus.mapper.user.RestUserMapper;
import es.plexus.usecase.user.CreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserRegisterController {

    @Autowired
    private CreateUserUseCase createUserUseCase;
    @Autowired
    private RestUserMapper userMapper;

    @Autowired
    private RestUserPostMapper userPostMapper;
    @PostMapping("/register")
    public ResponseEntity<?> postUser(@Valid @RequestBody UserPostDto user) {
        User userSave = createUserUseCase.saveUser(this.userPostMapper.toEntity(user));
        return new ResponseEntity<>(this.userMapper.toDto(userSave), HttpStatus.CREATED);
    }
}
